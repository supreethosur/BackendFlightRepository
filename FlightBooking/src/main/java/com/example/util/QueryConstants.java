package com.example.util;

public class QueryConstants {

	public static final String  GET_FLIGHTS= "select ft from Flight ft  where ft.source = :fromPlace and ft.destination = :toPlace  " +
			 "and  ft.isActive=1";
	 
}
