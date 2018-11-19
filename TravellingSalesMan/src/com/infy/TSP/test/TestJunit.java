package com.infy.TSP.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.infy.TSP.runner.TspRunner;

public class TestJunit 
{
	@Test
	   public void invalidFileLocation() {
		  String filePath = "./";
		  int noOfCities = 5;
	      TspRunner runner = new TspRunner();
	      assertEquals("Success", runner.bridgeMethod(filePath, noOfCities));
	   }
		
		
		@Test
		   public void validFileLocation() {
			  String filePath = "D:\\distance.txt";
			  int noOfCities = 5;
		      TspRunner runner = new TspRunner();
		      assertEquals("Success", runner.bridgeMethod(filePath, noOfCities));
		   }
}
