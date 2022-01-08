package com.example.util;

public class QueryConstants {

	public static final String  GET_FLIGHTS= "select ft from Flight ft inner join Journey j on ft.flightId=j.flightId"
			+ "  where j.fromLocation = :fromPlace and j.toLocation = :toPlace  " 
			+ "and ft.scheduleType  in (:scheduleType,'D') and  ft.isActive=1"; 
	 
	public static final String  GET_CITIES= "select CONCAT(ct.cityName,' ; ',ct.StateName) from City ct where "
			+ "ct.cityName like CONCAT(''',ct.cityName,'%'')";
	
}
