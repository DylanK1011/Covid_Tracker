//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    COVIDTracker - add tests to an array based on result, remove an individual from population data, 
// 							and analyze data for a population and individual
// Course:   CS 300 Fall 2020
//
// Author:   Dylan Krejci
// Email:    dkrejci@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  N/A
//
///////////////////////////////////////////////////////////////////////////////

public class COVIDTracker {
    /**
     * Adds a COVID test to the appropriate array, sorted between positive and negative tests
     * 
     * @param pos An oversized array of id's who have tested positive
     * @param neg An oversized array of id's who have tested negative
     * @param id String identification of a student who has been tested
     * @param isPos True boolean indicates a student is positive, while negative indicates they are negative
     * @return Returns true if there is room in the array to add, false if the array is full
     */
    public static boolean addTest(String[] pos, String[] neg, String id, boolean isPos) {

    	// Positive test
    	if (isPos == true) {
    		for (int i = 0; i < pos.length; ++i) {
    			if (pos[i] == null) {               // Empty space in array found
    				pos[i] = id;					// Add positive test
    				return true;
    			}
    		}
    		
        // Negative test
    	} else if (isPos == false) {				
    		for (int i = 0; i < neg.length; ++i) {  
    			if (neg[i] == null) {               // Empty space in array found
    				neg[i] = id;					// Add negative test
    				return true;		
    			}
    		}
    	}
    	return false;
    }
	
    /**
     * Removes any occurrences of a particular id from the array, and compacts the array to avoid null references
     * 
     * @param pos An oversized array of id's who have tested positive
     * @param neg An oversized array of id's who have tested negative
     * @param id String identification of a student who has been tested
     * @return Returns true if one or more records were removed, false if the ID was not found in either array
     */
    public static boolean removeIndividual(String[] pos, String[] neg, String id) {
    	// Until proven otherwise, assume the id has not tested
    	boolean hasTested = false;
	
    	// Loops through positive array. If an element matches the id, it is removed from the array
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i]==id) {
    			while (i < pos.length - 1) {
    				pos[i] = pos[i+1];
    				++i;
    			}
    			pos[pos.length - 1] = null;
    			i = 0;
    			hasTested = true;
    		}
    	}
	
    	// Repeats process for negative array
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i]==id) {
    			while (i < neg.length - 1) {
    				neg[i] = neg[i+1];
    				++i;
    			}
    			neg[neg.length - 1] = null;
    			i = 0;
    			hasTested = true;
    		}
    	}
	
    	return hasTested;
    }
	
    /**
     * Returns a formatted String that includes the total number of positive and negative tests, 
     * the total number of unique individuals across both arrays, the proportion of positive tests,
     * and the proportion of individuals who have tested positive
     * 
     * @param pos An oversized array of id's who have tested positive
     * @param neg An oversized array of id's who have tested negative
     * @return Returns a formatted version of the arrays with data
     */
    public static String getPopStats(String[] pos, String[] neg) {
    	// Declaration/initialization of variables
    	int totalPosTests = 0;
    	int totalNegTests = 0;
    	int individualPosTests = 0;
    	int individualNegTests = 0;
		
    	int totalTests;
    	int individualsTested;
		
    	double propPosTests;
    	double propIndividualPosTests;
    
    	String idTarget;
    	String formattedData;
		
    	// Calculates total positive tests
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i] != null)
    			totalPosTests++;
    	}
    	
    	// Calculates total negative tests
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i] != null)
    			totalNegTests++;
    	}
		
    	// Combines positive and negative tests into a total tests variable
    	totalTests = totalPosTests + totalNegTests;
		
    	// Finds positivity rate of tests, stored as a double
    	propPosTests = (double)totalPosTests/(double)totalTests * 100.0;

    	// Sorts the positive test array to remove duplicates
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i] != null) {
    			idTarget = pos[i];
    			int j = i + 1;
    			while (j < pos.length) {
    				if (pos[j] != null && pos[j].equals(idTarget))
    					pos[j]=null;
   					++j;
   				}
    		}
    	}
		
    	// Calculates total individuals who tested positive
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i] != null)
    			++individualPosTests;
    	}
		
    	// Sorts through negative test data to remove any who also tested positive
    	for (int i = 0; i < pos.length; ++i) {
    		for (int j = 0; j < neg.length; ++j) {
    			if (neg[j] == pos[i])
    				neg[j] = null;
    		}
    	}

    	// Sorts through negative test data to remove duplicates
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i] != null) {
    			idTarget = neg[i];
    			int j = i + 1;
    			while (j < neg.length) {
    				if (neg[j] != null && neg[j].equals(idTarget))
    					neg[j]=null;
    				++j;
    			}
    		}
    	}
		
    	// Calculates total individuals who tested negative
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i] != null)
    			++individualNegTests;
    	}
		
    	// Calculates total individuals tested and the proportion of individuals who tested positive
    	individualsTested = individualPosTests + individualNegTests;
    	propIndividualPosTests = (double)individualPosTests/(double)individualsTested * 100.0;
		
    	// Accounts for populations of 0, in order to avoid divide-by-zero errors
    	if (individualsTested == 0) {
    		propIndividualPosTests = 0.0;
    		propPosTests = 0.0;
    	}
		
    	// Formatted data to return
    	formattedData = "Total Tests: " + totalTests + "\nTotal Individuals Tested: " + individualsTested + "\nPercent Positive Tests: " + propPosTests + "%\nPercent Positive Individuals: " + propIndividualPosTests + "%";
    	//Option to print data - System.out.println(formattedData);
		
    	// Creates more compact arrays for positive and negative tests, without null values
    	String[] compactedPositives = new String[pos.length];
    	int compPosIndex = 0;
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i] != null) {
    			compactedPositives[compPosIndex] = pos[i];
    			++compPosIndex;
    		}
    	}
		
    	String[] compactedNegatives = new String[neg.length];
    	int compNegIndex = 0;
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i] != null) {
    			compactedNegatives[compNegIndex] = neg[i];
    			++compNegIndex;
    		}
    	}
    		
    	return formattedData;
    }
	
    /**
     * Returns data of one student's testing records. Includes the total number of tests, 
     * and total number of positive and negative tests, for the individual
     * 
     * @param pos An oversized array of id's who have tested positive
     * @param neg An oversized array of id's who have tested negative
     * @param id String identification of a student who has been tested
     * @return Returns a formatted version of the arrays with data, based on one specific student
     */
    public static String getIndividualStats(String[] pos, String[] neg, String id) {
    	// Declaration and initialization of variables
    	int idPosTests = 0;
    	int idNegTests = 0;
    	int idTotalTests;
		
    	String individualData;
		
    	// Calculates total positive tests for a given individual
    	for (int i = 0; i < pos.length; ++i) {
    		if (pos[i] != null && pos[i].equals(id))
    			++idPosTests;
    	}
		
    	// Calculates total negative tests for a given individual
    	for (int i = 0; i < neg.length; ++i) {
    		if (neg[i] != null && neg[i].equals(id))
    			++idNegTests;
    	}
		
    	// Combines positive and negative tests for a total tests value
    	idTotalTests = idPosTests + idNegTests;
		
    	// Formatted string of data
    	individualData = "Total Tests: " + idTotalTests + "\nPositive: " + idPosTests + "\nNegative: " + idNegTests;
    	// Option to print data: System.out.println(individualData);
		
    	return individualData;
    }
}
