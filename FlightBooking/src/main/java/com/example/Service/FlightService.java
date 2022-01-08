package com.example.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Entity.BookingHeader;
import com.example.Entity.Flight;
import com.example.Entity.Journey;
import com.example.Entity.JourneyTransactionDetails;
import com.example.Entity.Passanger;
import com.example.Entity.TicketDetails;
import com.example.Model.FlightModel;
import com.example.Model.PassangerModel;
import com.example.Model.ProceedBookingModel;
import com.example.Model.SearchFlightModel;
import com.example.Repository.BookingHeaderRepository;
import com.example.Repository.CityRepository;
import com.example.Repository.FlightsRepository;
import com.example.Repository.JourneyRepository;
import com.example.Repository.JourneyTransactionDetailsRepository;
import com.example.Repository.PassengerRepository;
import com.example.Repository.TicketDetailsRepository;

@Service
public class FlightService {

	@Autowired
	FlightsRepository flightRepo;

	@Autowired
	JourneyRepository journeyRepo;

	@Autowired
	CityRepository cityRepo;

	@Autowired
	JourneyTransactionDetailsRepository journeyTransactionDetailsRepo; 

	@Autowired
	PassengerRepository passangerRepo;

	@Autowired
	BookingHeaderRepository bookingHeaderRepo; 

	@Autowired
	TicketDetailsRepository ticketDetailsRepo;


	//	@Cacheable(key ="#id",value = "flightStore")
	public Flight findById(Integer id) throws Exception {
		System.out.println("inside service");
		Optional<Flight> flights = flightRepo.findById(id);
		if(flights.isPresent()) {
			return flights.get();
		}else {
			throw new Exception("No Flight with the flight Id " + id);
		}
	}
	public FlightModel addFlight(FlightModel ipflight) throws Exception {
		try {
			Flight flight=addFlightDetails(ipflight);
			ipflight.setFlightId(flight.getFlightId());
			Journey journey=addJourneyDetails(ipflight);
			ipflight.setJourneyId(journey.getJourneyId());

			return ipflight;

		}
		catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}

	public Flight addFlightDetails(FlightModel ipflight) throws Exception {
		try {
			Flight flight=new Flight();
			flight.setFlightName(ipflight.getFlightName());
			flight.setBusinessSeats(ipflight.getBusinessSeats());
			flight.setNonBusinessSeats(ipflight.getNonBusinessSeats());
			flight.setScheduleType(ipflight.getScheduleType());
			return flightRepo.save(flight);
		}
		catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}

	public Journey addJourneyDetails(FlightModel ipflight) throws Exception {
		try { 
			Journey journey=new Journey();
			journey.setFlightId(ipflight.getFlightId());
			journey.setFromLocation(ipflight.getFromLocation());
			journey.setToLocation(ipflight.getToLocation());
			journey.setArrivalTime(ipflight.getArrivalTime());
			journey.setDepartureTime(ipflight.getDepartureTime());

			return journeyRepo.save(journey);

		}
		catch(Exception e) {
			throw new Exception("Something went wrong");
		}
	}

	//	@CacheEvict(key ="#id",value = "flightStore")
	public void deleteById(Integer id) throws Exception {
		try {
			flightRepo.deleteByFlightId(id);	
		}
		catch(Exception e) { 
			throw new Exception("Something went wrong while deleting");
		}
	}

	//	@CachePut(key ="#id",value = "flightStore")
	public FlightModel updateFlight( FlightModel flight) throws Exception {
		Flight flights = flightRepo.findByFlightId(flight.getFlightId());
		if(flights!=null) { 
			flights.setFlightName(flight.getFlightName());
			flights.setBusinessSeats(flight.getBusinessSeats());
			flights.setNonBusinessSeats(flight.getNonBusinessSeats());
			flights.setScheduleType(flight.getScheduleType());
			flightRepo.save(flights);

			Journey journey=journeyRepo.findByJourneyId(flight.getJourneyId());
			if(journey!=null) {
				journey.setFlightId(flight.getFlightId());
				journey.setFromLocation(flight.getFromLocation());
				journey.setToLocation(flight.getToLocation());
				journey.setArrivalTime(flight.getArrivalTime());
				journey.setDepartureTime(flight.getDepartureTime());
				journeyRepo.save(journey);	
			}

			return flight;
		}else {
			throw new Exception("No Flight with the flight Id " + flight.getFlightId());
		} 

	}


	public HashMap<String , List<FlightModel>> searchFlight( SearchFlightModel searchModel){

		HashMap<String , List<FlightModel>> flightMapList=new HashMap<>();
		List<FlightModel> oneWayFlights = new ArrayList<>();
		List<FlightModel> twoWayFlights = new ArrayList<>();
		String scheduleType="";
		String day = searchModel.getTravelStartDate().getDayOfWeek().toString();
		if(day.equals("SUNDAY") || day.equals("SATURDAY") ) {
			scheduleType="WE";
		}
		else {
			scheduleType="WD";
		}
		List<Flight> flight1 = flightRepo.findFlights(scheduleType);
		for (Flight flight : flight1) {
			List<Journey> journey1= journeyRepo.getJourney(flight.getFlightId(),searchModel.getFromPlace().split(";")[0].trim(),searchModel.getToPlace().split(";")[0].trim());

			for (Journey journey : journey1) {
				FlightModel flightModel=new FlightModel();

				JourneyTransactionDetails journeyTransactionDetails =journeyTransactionDetailsRepo.findByJourneyIdAndJourneyDate(journey.getJourneyId(),searchModel.getTravelStartDate());
				Integer businessSeats=flight.getBusinessSeats();
				Integer nonBusinessSeats=flight.getNonBusinessSeats();
				if(journeyTransactionDetails!=null) {
					businessSeats=journeyTransactionDetails.getBusinessSeatsAvailable();
					nonBusinessSeats=journeyTransactionDetails.getNonBusinessSeatsAvailable();
				}

				flightModel.setFlightId(flight.getFlightId());
				flightModel.setJourneyId(journey.getJourneyId());
				flightModel.setAirline(flight.getAirline());
				flightModel.setArrivalDate(searchModel.getTravelStartDate());
				flightModel.setArrivalTime(journey.getArrivalTime());
				flightModel.setDepartureDate(searchModel.getTravelStartDate());
				flightModel.setDepartureTime(journey.getDepartureTime());
				flightModel.setFlightName(flight.getFlightName());
				flightModel.setFromLocation(journey.getFromLocation());
				flightModel.setToLocation(journey.getToLocation());
				flightModel.setScheduleType(scheduleType);
				flightModel.setNonBusinessSeats(businessSeats);		
				flightModel.setNonBusinessSeats(nonBusinessSeats);
				flightModel.setAmount(journey.getAmount());
				oneWayFlights.add(flightModel);
			}
		}
		flightMapList.put("1", oneWayFlights);
		if (searchModel.isRoundTrip()) {
			day = searchModel.getTravelReturnDate().getDayOfWeek().toString();
			if(day.equals("SUNDAY") || day.equals("SATURDAY") ) {
				scheduleType="WE";
			}
			else {
				scheduleType="WD";
			}
			List<Flight> flight2 = flightRepo.findFlights(scheduleType);
			for (Flight flight : flight2) {
				List<Journey> journey2= journeyRepo.getJourney(flight.getFlightId(),searchModel.getToPlace().split(";")[0].trim(),searchModel.getFromPlace().split(";")[0].trim() );
				for (Journey journey : journey2) {
					FlightModel flightModel=new FlightModel();

					JourneyTransactionDetails journeyTransactionDetails =journeyTransactionDetailsRepo.findByJourneyIdAndJourneyDate(journey.getJourneyId(),searchModel.getTravelReturnDate());
					Integer businessSeats=flight.getBusinessSeats();
					Integer nonBusinessSeats=flight.getNonBusinessSeats();
					if(journeyTransactionDetails!=null) {
						businessSeats=journeyTransactionDetails.getBusinessSeatsAvailable();
						nonBusinessSeats=journeyTransactionDetails.getNonBusinessSeatsAvailable();
					}

					flightModel.setFlightId(flight.getFlightId());
					flightModel.setJourneyId(journey.getJourneyId());
					flightModel.setAirline(flight.getAirline());
					flightModel.setArrivalDate(searchModel.getTravelReturnDate());
					flightModel.setArrivalTime(journey.getArrivalTime());
					flightModel.setDepartureDate(searchModel.getTravelReturnDate());
					flightModel.setDepartureTime(journey.getDepartureTime());
					flightModel.setFlightName(flight.getFlightName());
					flightModel.setFromLocation(journey.getFromLocation());
					flightModel.setToLocation(journey.getToLocation());
					flightModel.setScheduleType(scheduleType);
					flightModel.setNonBusinessSeats(businessSeats);		
					flightModel.setNonBusinessSeats(nonBusinessSeats);
					flightModel.setAmount(journey.getAmount());

					twoWayFlights.add(flightModel);
				} 
			}
			flightMapList.put("2", twoWayFlights);
		}
		return flightMapList;
	}

	public List<String> getCities(String cityName) {

		List<String> cities=cityRepo.findCityNameByCityNameStartswith(cityName);
		return cities;
	}
	public void addPassanger(PassangerModel passangerModel) {

		Passanger passanger =new Passanger();

		passanger.setPassangerName(passangerModel.getPassangeName());
		passanger.setAge(passangerModel.getAge());
		passanger.setAssociatedUserId(passangerModel.getUserId());
		passanger.setGender(passangerModel.getGender());

		Passanger addedpassanger = passangerRepo.save(passanger);

		TicketDetails ticket = new TicketDetails();
		BookingHeader header=bookingHeaderRepo.findByPnrNumber(passangerModel.getPnrNumber());
		Journey journey=journeyRepo.findByJourneyId(header.getJourneyId());
		Double amount=journey.getAmount();
		String className="Non-Business";
		if(passangerModel.isBusinessClass()) {
			amount=amount*1.5;
			className="Business";
		}
		ticket.setClassName(className);
		ticket.setAmount(amount);
		ticket.setOptedMeals(passangerModel.getMealsType());
		ticket.setPnrNo(passangerModel.getPnrNumber());
		TicketDetails addedticket =ticketDetailsRepo.save(ticket);


	}
	public BookingHeader proceedWithBooking(ProceedBookingModel model) {
		BookingHeader header =new BookingHeader();
		header.setBookingDate(LocalDateTime.now());
		header.setBookingStatus(1);
		header.setJourneyId(model.getJourneyId());
		header.setUserId(model.getUserId());
		header.setJourneyDate(model.getJourneyDate());
		BookingHeader bookingHeader=bookingHeaderRepo.save(header);

		return bookingHeader;
	}


	public BookingHeader finalSubmission(Integer pnrNumber) throws Exception {

		List<TicketDetails> ticketNonBusiness=ticketDetailsRepo.findByPnrNoAndClassName(pnrNumber,"Non-Business");
		List<TicketDetails> ticketBusiness=ticketDetailsRepo.findByPnrNoAndClassName(pnrNumber,"Business");
		BookingHeader bookingHeader=bookingHeaderRepo.findByPnrNumber(pnrNumber);
		JourneyTransactionDetails journeyTransactionDetails =journeyTransactionDetailsRepo.findByJourneyIdAndJourneyDate(bookingHeader.getJourneyId(),bookingHeader.getJourneyDate());
		Integer ticketBusinessCount=0;
		Integer ticketNonBusinessCount=0;
		if(ticketBusiness!=null) {
			ticketBusinessCount=ticketBusiness.size();
		}
		if(ticketNonBusiness!=null) {
			ticketNonBusinessCount=ticketNonBusiness.size();
		}
		if(journeyTransactionDetails!=null) {
			Integer businessSeatsAvailable=journeyTransactionDetails.getBusinessSeatsAvailable();
			Integer nonBusinessSeatsAvailable=journeyTransactionDetails.getNonBusinessSeatsAvailable();
			ticketBusinessCount=0;
			ticketNonBusinessCount=0;
			if(ticketBusiness!=null) {
				ticketBusinessCount=ticketBusiness.size();
			}
			if(ticketNonBusiness!=null) {
				ticketNonBusinessCount=ticketNonBusiness.size();
			}

			if( businessSeatsAvailable < ticketBusinessCount) {
				throw new Exception("Please reduce the number of business class tickets or check with other flights");
			}
			else {
				journeyTransactionDetails.setBusinessSeatsAvailable(journeyTransactionDetails.getBusinessSeatsAvailable()-ticketBusinessCount);
			}
			if( nonBusinessSeatsAvailable < ticketNonBusinessCount) {
				throw new Exception("Please reduce the number of non business class tickets or check with other flights");
			}
			else {
				journeyTransactionDetails.setBusinessSeatsAvailable(journeyTransactionDetails.getNonBusinessSeatsAvailable()-ticketNonBusinessCount);
			}

			journeyTransactionDetailsRepo.save(journeyTransactionDetails);
		}
		else {
			Journey journey=journeyRepo.findByJourneyId(bookingHeader.getJourneyId());
			Flight flight=flightRepo.findByFlightId(journey.getFlightId());
			JourneyTransactionDetails journeyTransactionDetails1 =new JourneyTransactionDetails();

			Integer businessSeats=flight.getBusinessSeats();
			Integer nonBusinessSeats=flight.getNonBusinessSeats();


			journeyTransactionDetails1.setBusinessSeatsAvailable(businessSeats-ticketBusinessCount);
			journeyTransactionDetails1.setNonBusinessSeatsAvailable(nonBusinessSeats-ticketNonBusinessCount);
			journeyTransactionDetails1.setJourneyId(bookingHeader.getJourneyId());
			journeyTransactionDetails1.setJourneyDate(bookingHeader.getJourneyDate());

			journeyTransactionDetailsRepo.save(journeyTransactionDetails1);
		}

		bookingHeader.setBookingStatus(2);
		bookingHeader.setBookingDate(LocalDateTime.now());

		BookingHeader header=bookingHeaderRepo.save(bookingHeader);


		return header;
	}







}
