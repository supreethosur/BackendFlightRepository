package com.example.Controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Advice.CustomException;
import com.example.Entity.BookingHeader;
import com.example.Entity.Journey;
import com.example.Entity.TicketDetails;
import com.example.Model.FlightModel;
import com.example.Model.HistoryModel;
import com.example.Model.NotificationModel;
import com.example.Model.PassangerModel;
import com.example.Model.ProceedBookingModel;
import com.example.Model.SearchFlightModel;
import com.example.Model.SummaryModel;
import com.example.Service.FlightBookingService;

@RestController
public class FlightBookingController {

	@Autowired
	FlightBookingService flightService;
	
	@Autowired
	private KafkaTemplate<String, NotificationModel> kafkaTemplate;
	
	private static final String TOPIC = "new_kafkaTopic";

		@GetMapping("/test/{Id}")
		public  String  getFlightsById(@PathVariable Integer Id) throws Exception {
			System.out.println("inside Controller");
			return "Hello";
		}

	//	@PostMapping("/addFlights")
	//	public FlightModel addFlights(@RequestBody FlightModel ipflight) throws Exception {
	//		
	//		return flightService.addFlight(ipflight);
	//	}


	//	@DeleteMapping("/deleteFlight/{Id}")
	//	public void deleteFlight(@PathVariable Integer Id) throws Exception {
	//		flightService.deleteById(Id);
	//	}

	//	@PutMapping("/updateFlight")
	//	public FlightModel updateFlight(@RequestBody FlightModel flight) throws Exception {
	//		FlightModel flight1=flightService.updateFlight(flight);
	//		return flight1;
	//	}

	@PutMapping("/updateJourney")
	public Journey updateJourney(@RequestBody FlightModel flightJourney) throws Exception {
		Journey journey = flightService.updateJourneyDetails(flightJourney);
		return journey;
	}
	@PostMapping("/addJourney")
		public Journey addFlights(@RequestBody FlightModel ipflight) throws Exception {
			System.out.println(490);
			return flightService.addJourneyDetails(ipflight);
		}


	@GetMapping("/getCityStartsWith/{city}")
	public List<String> getCityStartsWith(@PathVariable("city") String cityName) {
		List<String> cities = flightService.getCities(cityName);
		return cities;
	}
	
	@GetMapping("/getJourneyByFlightId/{flightId}")
	public List<Journey> getJourneyByFlightId(@PathVariable Integer flightId) {
		List<Journey> journey = flightService.getjourney(flightId);
		return journey;
	}
	

	@PostMapping("/addPassanger")
	public List<TicketDetails> addPassanger(@RequestBody List<PassangerModel> passangerModel) {
		List<TicketDetails> ticket = flightService.addPassanger(passangerModel);
		return ticket;
	}


	@PostMapping("/proceedWithFlight")
	public List<BookingHeader> proceedWithBooking(@RequestBody ProceedBookingModel model) {
		return flightService.proceedWithBooking(model);
	}

	@PostMapping("/finalSubmission")
	public List<BookingHeader> finalSubmission(@RequestBody List<Integer> pnrNumber) throws CustomException {
		return flightService.finalSubmission(pnrNumber);
	}

	@GetMapping("/getSummary")
	public List<SummaryModel> getSummary(Integer pnrNumber)  {
		return flightService.getSummary(pnrNumber);
	}

	@PostMapping("/deletePassanger")
	public void deletePassanger(Integer passangerId) throws Exception {
		flightService.deletePassanger(passangerId);
	}


	@PostMapping("/cancelBooking")
	public void cancelBooking(Integer pnrNumber) throws Exception {
		flightService.cancelBooking(pnrNumber);
	}

	@GetMapping("/getHistoryByemailId")
	public List<HistoryModel> getHistoryByemailId(String emailId)  {
		return flightService.getHistoryByemailIdOrPnr(emailId);
	}
	
	
	@PostMapping("/searchFlight")
	public HashMap<String, List<FlightModel>> searchFlight(@RequestBody SearchFlightModel searchModel)  {
		System.out.println(13143);
		return flightService.searchFlight(searchModel);
	}
	
//	@GetMapping("/publish")
//	public String publishBook() {
//
//	    kafkaTemplate.send(TOPIC, new NotificationModel("Hello"));
//
//	    return "Published successfully";
//	}
	
	

}
