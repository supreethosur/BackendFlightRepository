package com.example.util;

public class QueryConstants {

	public static final String  GET_FLIGHTS= "select ft from Flight ft "
			+ "  where  ft.scheduleType  in (:scheduleType,'D') and  ft.isActive=1 " ;
	

	public static final String  GET_JOURNEY= "select j from Journey j where ft.flightId= :flightId"
			+ " j.fromLocation = :fromPlace and j.toLocation = :toPlace  " ;
			
	 
	public static final String  GET_CITIES= "select CONCAT(ct.cityName,' ; ',ct.StateName) from City ct where "
			+ "ct.cityName like CONCAT(''',ct.cityName,'%'')";
	
}
