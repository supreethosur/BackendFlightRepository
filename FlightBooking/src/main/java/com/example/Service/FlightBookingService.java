package com.example.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Advice.CustomException;
import com.example.Entity.BookingHeader;
import com.example.Entity.City;
import com.example.Entity.Journey;
import com.example.Entity.JourneyTransactionDetails;
import com.example.Entity.Passanger;
import com.example.Entity.Status;
import com.example.Entity.TicketDetails;
import com.example.Entity.UserDetails;
import com.example.Model.FinalAmount;
import com.example.Model.Flight;
import com.example.Model.FlightModel;
import com.example.Model.HistoryModel;
import com.example.Model.JourneyInputModel;
import com.example.Model.NotificationModel;
import com.example.Model.PassangerModel;
import com.example.Model.ProceedBookingModel;
import com.example.Model.SearchFlightModel;
import com.example.Model.SummaryModel;
import com.example.Model.TicketModel;
import com.example.Repository.BookingHeaderRepository;
import com.example.Repository.CityRepository;
import com.example.Repository.JourneyRepository;
import com.example.Repository.JourneyTransactionDetailsRepository;
import com.example.Repository.PassengerRepository;
import com.example.Repository.StatusRepository;
import com.example.Repository.TicketDetailsRepository;
import com.example.Repository.UserDetailsRepository;

@Service
public class FlightBookingService {

//	@Autowired
//	FlightsRepository flightRepo;

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
	
	@Autowired
	UserDetailsRepository userDetailsRepo;

	@Autowired
	StatusRepository statusRepo; 
	
	@Autowired
	RestTemplate restTemplate;
	

	
	@Autowired
	private KafkaTemplate<String, NotificationModel> kafkaTemplate;
	
	private static final String TOPIC = "new_kafkaTopic";

	//	@Cacheable(key ="#id",value = "flightStore")
//	public Flight findById(Integer id) throws Exception {
//		System.out.println("inside service");
//		Optional<Flight> flights = flightRepo.findById(id);
//		if(flights.isPresent()) {
//			return flights.get();
//		}else {
//			throw new Exception("No Flight with the flight Id " + id);
//		}
//	}
//	public FlightModel addFlight(FlightModel ipflight) throws Exception {
//		try {
//			Flight flight=addFlightDetails(ipflight);
//			ipflight.setFlightId(flight.getFlightId());
//			Journey journey=addJourneyDetails(ipflight);
//			ipflight.setJourneyId(journey.getJourneyId());
//
//			return ipflight;
//
//		}
//		catch(Exception e) {
//			throw new Exception("Something went wrong");
//		}
//	}
//
//	public Flight addFlightDetails(FlightModel ipflight) throws Exception {
//		try {
//			Flight flight=new Flight();
//			flight.setFlightName(ipflight.getFlightName());
//			flight.setBusinessSeats(ipflight.getBusinessSeats());
//			flight.setNonBusinessSeats(ipflight.getNonBusinessSeats());
//			flight.setScheduleType(ipflight.getScheduleType());
//			return flightRepo.save(flight);
//		}
//		catch(Exception e) {
//			throw new Exception("Something went wrong");
//		}
//	}

	public Journey addJourneyDetails(FlightModel ipflight) throws Exception {
		System.out.println("abcdef");
			Journey journey=new Journey();
			journey.setFlightId(ipflight.getFlightId());
			journey.setFromLocation(ipflight.getFromLocation());
			journey.setToLocation(ipflight.getToLocation());
			journey.setAmount(ipflight.getAmount());
			System.out.println(ipflight.getDepartureTime());
			journey.setArrivalTime(LocalTime.parse(ipflight.getArrivalTime(), DateTimeFormatter.ofPattern("HH:mm.ss")));
//			journey.setArrivalTime(ipflight.getArrivalTime());
//			journey.setDepartureTime(ipflight.getDepartureTime());
			journey.setDepartureTime(LocalTime.parse(ipflight.getDepartureTime(), DateTimeFormatter.ofPattern("HH:mm.ss")));

			return journeyRepo.save(journey);

		
	}

	//	@CacheEvict(key ="#id",value = "flightStore")
//	public void deleteById(Integer id) throws Exception {
//		try {
//			Flight flight=flightRepo.findByFlightId(id);
//			flight.setIsActive(0);
//			flightRepo.save(flight);	
//		}
//		catch(Exception e) { 
//			throw new Exception("Something went wrong while deleting");
//		}
//	}

	//	@CachePut(key ="#id",value = "flightStore")
//	public FlightModel updateFlight( FlightModel flight) throws Exception {
//		Flight flights = flightRepo.findByFlightId(flight.getFlightId());
//		if(flights!=null) {
//			flights.setFlightName(flight.getFlightName());
//			flights.setBusinessSeats(flight.getBusinessSeats());
//			flights.setNonBusinessSeats(flight.getNonBusinessSeats());
//			flights.setScheduleType(flight.getScheduleType());
//			flights.setAirline(flight.getAirline());
//			flightRepo.save(flights);
//
//			Journey journey=journeyRepo.findByJourneyId(flight.getJourneyId());
//			if(journey!=null) {
//				journey.setFlightId(flight.getFlightId());
//				journey.setFromLocation(flight.getFromLocation());
//				journey.setToLocation(flight.getToLocation());
//				journey.setArrivalTime(flight.getArrivalTime());
//				journey.setDepartureTime(flight.getDepartureTime());
//				journeyRepo.save(journey);	
//			}
//
//			return flight;
//		}else {
//			throw new Exception("No Flight with the flight Id " + flight.getFlightId());
//		} 
//
//	}


	public HashMap<String , List<FlightModel>> searchFlight( SearchFlightModel searchModel){

		HashMap<String , List<FlightModel>> flightMapList=new HashMap<>();
		List<FlightModel> oneWayFlights = new ArrayList<>();
		List<FlightModel> twoWayFlights = new ArrayList<>();
		String scheduleType="";
		String day = searchModel.getTravelStartDate().getDayOfWeek().toString();
		if(day.equals("SUNDAY") || day.equals("SATURDAY") ) {
			scheduleType="Week-Ends";
		}
		else {
			scheduleType="Week-Days";
		}
		String url="http://localhost:8082/scheduleType/"+scheduleType;
		
		ParameterizedTypeReference<List<Flight>> responseType= new ParameterizedTypeReference<List<Flight>>() {
		};
		
		HttpEntity<?> httpEntity=new HttpEntity(null,null);
		ResponseEntity<List<Flight>> res=restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
		
		List<Flight> flight1 = res.getBody();
		System.out.println(flight1.get(0).getFlightName());
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
				flightModel.setArrivalTime(journey.getArrivalTime().toString());
				flightModel.setDepartureDate(searchModel.getTravelStartDate());
				flightModel.setDepartureTime(journey.getDepartureTime().toString());
				flightModel.setFlightName(flight.getFlightName());
				flightModel.setFromLocation(journey.getFromLocation());
				flightModel.setToLocation(journey.getToLocation());
				flightModel.setScheduleType(scheduleType);
				flightModel.setNonBusinessSeats(businessSeats);		
				flightModel.setNonBusinessSeats(nonBusinessSeats);
				flightModel.setAmount(journey.getAmount());
				oneWayFlights.add(flightModel);
			}
			flightMapList.put("1", oneWayFlights);
		}
		
		if (!searchModel.isOneWayTrip()) {
			day = searchModel.getTravelReturnDate().getDayOfWeek().toString();
			if(day.equals("SUNDAY") || day.equals("SATURDAY") ) {
				scheduleType="WE";
			}
			else {
				scheduleType="WD";
			}
			
			String url1="http://localhost:8082/scheduleType/"+scheduleType;
			
			ParameterizedTypeReference<List<Flight>> responseType1= new ParameterizedTypeReference<List<Flight>>() {
			};
			
			HttpEntity<?> httpEntity1=new HttpEntity(null,null);
			ResponseEntity<List<Flight>> res1=restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, responseType1);
			List<Flight> flight2 = res1.getBody();
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
					flightModel.setArrivalTime(journey.getArrivalTime().toString());
					flightModel.setDepartureDate(searchModel.getTravelReturnDate());
					flightModel.setDepartureTime(journey.getDepartureTime().toString());
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
		System.out.println(flightMapList);
		return flightMapList;
	}

	public List<String> getCities(String cityName) {

		List<City> cities=cityRepo.findByCityNameStartsWith(cityName);
		List<String> cityNames=cities.stream().map(x->x.getCityName()).collect(Collectors.toList());
		return cityNames;
	}
	
	public TicketDetails addPassanger(PassangerModel passangerModel) {
		List<TicketDetails> ticketDetailsList =new ArrayList<>();
//		for (PassangerModel passangerModel : passangerModelList) {
		System.out.println("inside sad service");
			Passanger passanger =new Passanger();

			passanger.setPassangerName(passangerModel.getPassangerName());
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
			ticket.setPassangerId(addedpassanger.getPassengerId());
			ticket.setClassName(className);
			ticket.setAmount(amount);
			ticket.setOptedMeals(passangerModel.getMealsType());
			ticket.setPnrNo(passangerModel.getPnrNumber());
			ticket.setIsActive(1);
			TicketDetails addedticket =ticketDetailsRepo.save(ticket);
			ticket.setTicketNo(addedticket.getTicketNo());
			
//			ticketDetailsList.add(ticket);
//		}
		return ticket;
	}
	
	public List<BookingHeader> proceedWithBooking(ProceedBookingModel model) {
		
		List<BookingHeader> bookingList =new ArrayList<>();
		if(model.isOneWaytrip()) {
			BookingHeader header =new BookingHeader();
			header.setBookingDate(LocalDateTime.now());
			header.setBookingStatus(1);
			header.setJourneyId(model.getJourneyInputModel().get(0).getJourneyId());
			header.setUserId(model.getUserId());
			header.setJourneyDate(model.getJourneyInputModel().get(0).getJourneyDate());
			header.setOneWayTrip(true);
			
			BookingHeader bookingHeader=bookingHeaderRepo.save(header);
			bookingList.add(bookingHeader);
		}
		else {
			for (JourneyInputModel iterable_element : model.getJourneyInputModel()) {
				BookingHeader header =new BookingHeader();
				header.setBookingDate(LocalDateTime.now());
				header.setBookingStatus(1);
				header.setJourneyId(iterable_element.getJourneyId());
				header.setUserId(model.getUserId());
				header.setJourneyDate(iterable_element.getJourneyDate());
				header.setOneWayTrip(false);
				BookingHeader bookingHeader=bookingHeaderRepo.save(header);
				bookingList.add(bookingHeader);
			}
		}
		System.out.println(bookingList.toString() +" op");
		return bookingList;
	}


	public List<BookingHeader> finalSubmission(List<Integer> pnrNumberList) throws CustomException {
		List<BookingHeader> headerList =new ArrayList<>();
		Integer userId;
		for (Integer pnrNumber : pnrNumberList) {
			System.out.println(pnrNumber);
			List<TicketDetails> ticketNonBusiness=ticketDetailsRepo.findByPnrNoAndClassNameAndIsActive(pnrNumber,"Non-Business",1);
			List<TicketDetails> ticketBusiness=ticketDetailsRepo.findByPnrNoAndClassNameAndIsActive(pnrNumber,"Business",1);
			BookingHeader bookingHeader=bookingHeaderRepo.findByPnrNumber(pnrNumber);
			userId=bookingHeader.getUserId();
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
					throw new CustomException("Please reduce the number of business class tickets or check with other flights");
				}
				else {
					journeyTransactionDetails.setBusinessSeatsAvailable(journeyTransactionDetails.getBusinessSeatsAvailable()-ticketBusinessCount);
				}
				if( nonBusinessSeatsAvailable < ticketNonBusinessCount) {
					throw new CustomException("Please reduce the number of non business class tickets or check with other flights");
				}
				else {
					journeyTransactionDetails.setBusinessSeatsAvailable(journeyTransactionDetails.getNonBusinessSeatsAvailable()-ticketNonBusinessCount);
				}

				journeyTransactionDetailsRepo.save(journeyTransactionDetails);
			}
			else {
				Journey journey=journeyRepo.findByJourneyId(bookingHeader.getJourneyId());
				
				
				String url1="http://localhost:8082/"+journey.getFlightId();
				System.out.println(url1);
				ParameterizedTypeReference<Flight> responseType1= new ParameterizedTypeReference<Flight>() {
				};
				
				HttpEntity<?> httpEntity1=new HttpEntity(null,null);
				ResponseEntity<Flight> res1=restTemplate.exchange(url1, HttpMethod.GET, httpEntity1, responseType1);
//				ResponseEntity<Flight> res1=restTemplate.getForEntity(url1, Flight.class);
				Flight flight=res1.getBody();
				System.out.println(url1);
				
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
			headerList.add(header);
			NotificationModel model=new NotificationModel();
			
			String message= "Pnr No Generated -"+pnrNumber;
			model.setMessage(message);
			model.setSubject("Congrats the ticket is booked");
			model.setTimeOfEvent(LocalDateTime.now());
			model.setUserId(userId);
			
//			 kafkaTemplate.send(TOPIC, model);
		}
		System.out.println("submitted");
		return headerList;
	}
	
	public SummaryModel getSummary(Integer pnrNumber) {
		BookingHeader bookingHeader =bookingHeaderRepo.findByPnrNumberAndBookingStatus(pnrNumber,1);
		List<SummaryModel> summaryModelList=new ArrayList<>();
		SummaryModel summaryModel=new SummaryModel();
		FinalAmount finalAmount= new FinalAmount();
		List<PassangerModel> passangerModelList = new ArrayList<>(); 
		double amount = 0.0;
		if(bookingHeader!=null) {
			List<TicketDetails>  ticketDetails=ticketDetailsRepo.findByPnrNoAndIsActive(pnrNumber,1);
			for (TicketDetails ticketDetails2 : ticketDetails) {
				
				amount+=ticketDetails2.getAmount();
				PassangerModel passangerModel =new PassangerModel();
				Passanger passanger=passangerRepo.findByPassengerId(ticketDetails2.getPassangerId());
				
				passangerModel.setAge(passanger.getAge());
				passangerModel.setAmount(ticketDetails2.getAmount());
				passangerModel.setTicketNo(ticketDetails2.getTicketNo());
				if(ticketDetails2.getClassName().equals("Non-Business")) {
					passangerModel.setBusinessClass(false);
				}
				else {
					passangerModel.setBusinessClass(true);
				}
				passangerModel.setGender(passanger.getGender());
				passangerModel.setMealsType(ticketDetails2.getOptedMeals());
				passangerModel.setPassangerName(passanger.getPassangerName());
				passangerModel.setPnrNumber(pnrNumber);
				passangerModel.setUserId(bookingHeader.getUserId());
				
				passangerModelList.add(passangerModel);
			}
			
			double taxAmount=(12.0/100)*amount;
			double serviceCharge = ticketDetails.size() * 100 ;
			
			bookingHeader.setFinalAmount(amount+taxAmount+serviceCharge);
			bookingHeaderRepo.save(bookingHeader);
			
			
			finalAmount.setTaxPercentage(12.0);
			finalAmount.setTotalTicketAmount(amount);
			finalAmount.setServiceCharges(serviceCharge);
			finalAmount.setTaxAmount(taxAmount);
			finalAmount.setFinalAmount(amount+taxAmount+serviceCharge);
			
			summaryModel.setAmount(finalAmount);
			summaryModel.setPassangerList(passangerModelList);
//			summaryModelList.add(summaryModel);
		}
		return summaryModel;
	}
	
	public void deletePassanger(Integer ticketNo) {
		
		TicketDetails ticketsDetails = ticketDetailsRepo.findByTicketNo(ticketNo);
		ticketsDetails.setIsActive(0);
		ticketDetailsRepo.save(ticketsDetails);
	}
	public void cancelBooking(Integer pnrNumber) throws Exception {
		LocalDateTime currentDateTime=LocalDateTime.now();
		
		
		BookingHeader header=bookingHeaderRepo.findByPnrNumber(pnrNumber);
		if(header!=null) {
			LocalDate journeyDate= header.getJourneyDate();
			Journey journey = journeyRepo.findByJourneyId(header.getJourneyId());
			LocalDateTime journeyDateTime = LocalDateTime.of(journeyDate, journey.getDepartureTime());
			if( currentDateTime.isAfter(journeyDateTime.minusHours(24))) {
				throw new Exception("Ticket cannot be cancelled before 24 hours of journey date");	
			}
			else {
				header.setBookingStatus(3);
				
				bookingHeaderRepo.save(header);
			}
		}
		
	}
	public List<HistoryModel> getHistoryByemailIdOrPnr(String emailIdOrPnr) {
		
		
		List<HistoryModel> historyList=new ArrayList<>();
		List<BookingHeader> bookingHeaders =new ArrayList<>();
		boolean check=isStringInteger(emailIdOrPnr);
		if(check) {
			BookingHeader bookingHeader2 = bookingHeaderRepo.findByPnrNumber(Integer.parseInt(emailIdOrPnr));
			bookingHeaders.add(bookingHeader2);
		}
		else {
			UserDetails user=userDetailsRepo.findByUserMailId(emailIdOrPnr);
			bookingHeaders = bookingHeaderRepo.findByUserId(user.getUserId());
		}

		for (BookingHeader bookingHeader : bookingHeaders) {
			Journey journey = journeyRepo.findByJourneyId(bookingHeader.getJourneyId());
			List<TicketDetails> tickets=ticketDetailsRepo.findByPnrNo(bookingHeader.getPnrNumber());
			for (TicketDetails ticket : tickets) {
				HistoryModel model=new HistoryModel();
//				TicketModel ticketModel=new TicketModel();
				Passanger passanger=passangerRepo.findByPassengerId(ticket.getPassangerId());
				model.setTicketNo(ticket.getTicketNo());
				model.setAge(passanger.getAge());
				model.setAmount(ticket.getAmount());
				model.setIsBusinessClass(ticket.getClassName());
				
				model.setGender(passanger.getGender());
				model.setMealsType(ticket.getOptedMeals());
				model.setPassangeName(passanger.getPassangerName());
				model.setJourneyDate(LocalDateTime.of(bookingHeader.getJourneyDate(), journey.getDepartureTime()));
				model.setFromToLocation(journey.getFromLocation() +" - "+ journey.getToLocation());
				
//				ticketList.add(ticketModel);
				Status status= statusRepo.findByStatusId(bookingHeader.getBookingStatus());
				
				model.setPnrNo(bookingHeader.getPnrNumber());
				model.setStatus(status.getStatusDescription());
//				model.setTickets(ticketList);
				
				historyList.add(model);
			}
			
		}
		
		
		
		return historyList;
	}


	public static boolean isStringInteger(String number ){
	    try{
	        Integer.parseInt(number);
	    }catch(Exception e ){
	        return false;
	    }
	    return true;
	}

	public Journey updateJourneyDetails(FlightModel flight) {
		Journey journey=journeyRepo.findByJourneyId(flight.getJourneyId());
		System.out.println("qqqqqqqqqqq");
		Journey journey1=new Journey();
		if(journey!=null) {
			journey.setFlightId(flight.getFlightId());
			journey.setFromLocation(flight.getFromLocation());
			journey.setToLocation(flight.getToLocation());
			System.out.println(flight.getArrivalTime()+ "  oooo "+  DateTimeFormatter.ofPattern("HH.mm.ss") );
			journey.setArrivalTime(LocalTime.parse(flight.getArrivalTime(), DateTimeFormatter.ofPattern("HH.mm.ss")));
			System.out.println(2222222);
			journey.setAmount(flight.getAmount());
//			journey.setArrivalTime(flight.getArrivalTime());
//			journey.setDepartureTime(flight.getDepartureTime());
			journey.setDepartureTime(LocalTime.parse(flight.getDepartureTime(), DateTimeFormatter.ofPattern("HH.mm.ss")));
			journey1=journeyRepo.save(journey);	
			
		}
		return journey1;
	}

	public List<Journey> getjourney(Integer flightId) {
		List<Journey> journey =journeyRepo.findByFlightId(flightId);
 		return journey;
	}

	public UserDetails addUserDetails(HashMap<String, String> map) {
		
		UserDetails user =new UserDetails();
		UserDetails existingUser= userDetailsRepo.findByUserMailIdAndUserName(map.get("userMailId"),map.get("userName"));
		
		if(existingUser!=null) {
			return existingUser;
		}
		else {
			user.setUserMailId(map.get("userMailId"));
			user.setUserName(map.get("userName"));
			
			return userDetailsRepo.save(user);
		}
		
	}




}
