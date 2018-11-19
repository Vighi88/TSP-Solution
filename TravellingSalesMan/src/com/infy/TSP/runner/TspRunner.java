package com.infy.TSP.runner;

import java.io.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class TspRunner {

	//Instance Variables
	final static Logger logger = Logger.getLogger(TspRunner.class);
	
    private static int[][] distanceVector;
    private static int distance = Integer.MAX_VALUE;
    private static String bestRoute = "";

    
    public static void main(String args[]){
    	
    	PropertyConfigurator.configure("/log4j.properties");
        //File path location inside docker container
        String matrixFile = args[0];

        //Reading no of cities form command line arguments
        int noOfCities = Integer.parseInt(args[1]);

        TspRunner runner = new TspRunner();
        logger.debug("Calling Bridge Method");
        runner.bridgeMethod(matrixFile, noOfCities);
        logger.debug("Printing Optimized Route !!");
        System.out.print("Best Route From Current Node: " + bestRoute + ". Distance = " + distance);

    }
    
    
    public String bridgeMethod(String filePath, int noOfCities)
    {
    	distanceVector = new int[noOfCities][noOfCities];

        // Loading the file specified by user
        logger.debug("Initiating file read..");
        FileReader fileReader;
        String result = "";
		try {
			fileReader = new FileReader(filePath);
		
        
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Reading user input matrix into system

        for (int row = 0 ; row < noOfCities ; row++) {

            String line = bufferedReader.readLine();

            String[] values = line.trim().split("\\s+");
            
            for (int col = 0; col < noOfCities; col++) {
            	distanceVector[row][col] = Integer.parseInt(values[col]);
            	
            }

        }
        
 
        logger.debug("File read complete !!");
        // Closing file

        bufferedReader.close();


        //calling recursive function
        
        String path = "";

        int[] vertices = new int[noOfCities - 1];


        //Creating array for storing starting vertices in every sub problem
        for (int i = 1; i < noOfCities; i++) 
        {
            vertices[i - 1] = i;
        }


        routeFinder(0, vertices, path, 0);
        result = "Success";
        
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return result;
    }


 
    //Recursive Function
    private int routeFinder(int initialNode, int vertices[], String pathFound, int costUntillNow) 
    {
       //Adding the current vertex to the path
    	pathFound = pathFound + Integer.toString(initialNode) + " - ";
        int arrayLength = vertices.length;
        int newCostCalculated;

        // Exit recursion, if no sub problem exists
        if (arrayLength == 0) 
        {
        	newCostCalculated = costUntillNow + distanceVector[initialNode][0];
            if (newCostCalculated < distance)
            {
                distance = newCostCalculated;
                bestRoute = pathFound + "0";
            }
            return (distanceVector[initialNode][0]);
        }

        // If the current sub problem path has higher cost than the stored one then exit
        else if (costUntillNow > distance)
        {
            return 0;
        }

        // Recursive case
        else 
        {

            int[][] newVertices = new int[arrayLength][(arrayLength - 1)];

            int costOfCurrentNode, costChildPath;

            int bestCost = Integer.MAX_VALUE;

            // Constructing sub path using each node in the list
            for (int i = 0; i < arrayLength; i++) 
            {
                for (int j = 0, k = 0; j < arrayLength; j++, k++) {

                	// Excluding current node from the vertices list
                    if (j == i) {
                        k--;
                        continue;
                    }

                    newVertices[i][k] = vertices[j];
                }

                // Distance for travelling to the current node from its parent

                costOfCurrentNode = distanceVector[initialNode][vertices[i]];

                newCostCalculated = costOfCurrentNode + costUntillNow;

                //Recursive call to calculate cost for sub problems
                costChildPath = routeFinder(vertices[i], newVertices[i], pathFound, newCostCalculated);

                // Gives the cost of every child + the current node cost
                int totalCost = costChildPath + costOfCurrentNode;

                //Selecting minimal cost from all the resultant sub paths
                if (totalCost < bestCost) 
                {
                    bestCost = totalCost;
                }
            }

            return (bestCost);
        }

    }

}
