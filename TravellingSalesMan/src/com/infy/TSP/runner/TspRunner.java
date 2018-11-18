package com.infy.TSP.runner;

 

import java.io.*;
import org.apache.log4j.Logger;


public class TspRunner {

	//Instance Variables
	final static Logger logger = Logger.getLogger(TspRunner.class);
    
    public static void main(String args[]){

    	String matrixFile = "";

        int noOfCities;

        logger.debug("Initiating file read..");
        FileReader fileReader;
		try {
			fileReader = new FileReader(matrixFile);
		
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);        
 
        logger.debug("File read complete !!");
        // Closing file

        bufferedReader.close();

        //Recursive function call
        procedure();

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

    }


 
    //Recursive Function
    private static void procedure() 
    {
       

        }

    }

