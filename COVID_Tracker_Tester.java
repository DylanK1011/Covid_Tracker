//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Test Cases for COVIDTracker.java Class
// Course:   CS 300 Fall 2020
//
// Author:   Dylan Krejci
// Email:    dkrejci@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         N/A
// Online Sources:  https://stackoverflow.com/questions/661028/how-can-i-divide-two-integers-to-get-a-double - Helped to resolve issue of dividing two ints to get a double
//
///////////////////////////////////////////////////////////////////////////////
public class COVIDTrackerTester {

  /**
   * Tester method for COVIDTracker.addTest()
   * 
   * @return true if all tests are passed
   */
  public static boolean testAddTest() {
    String[] pos = new String[2];
	String[] neg = new String[2];
		
	// Case 1: Two empty arrays
	if (!COVIDTracker.addTest(pos, neg, "A1", false) || neg[0] == null) {
	  System.out.println("Error case 1");
		return false;
	}
	
	// Case 2: One empty array, one partially full array. Add to empty array
	if (!COVIDTracker.addTest(pos, neg, "B2", true) || pos[0] == null) {
	  System.out.println("Error Case 2");
	  return false;
	}
	
	// Case 3: Both arrays partially full, filling first one
	if (!COVIDTracker.addTest(pos, neg, "C3", false) || neg[1] == null) {
	  System.out.println("Error Case 3");
	  return false;
	}
	
	// Case 4: One full array, filling second one
	if (!COVIDTracker.addTest(pos, neg, "D4", true) || pos[1] == null) {
	  System.out.println("Error Case 4");
	  return false;
	}
	
	// Case 5: Both arrays are full
	if (COVIDTracker.addTest(pos, neg, "E5", false)) {
	  System.out.println("Error Case 5");
	  return false;
	}
	return true;	
  
  }
	
  /**
   * Tester method for COVIDTracker.removeIndividual()
   * 
   * @return true if all tests are passed
   */
  public static boolean testRemoveIndividual() {
    // Case 1: Present in both pos and neg - multiple removals required
	String[] pos1 = {"A", "B", "C"};
	String[] neg1 = {"A", "B", "C"};
		
	if (!COVIDTracker.removeIndividual(pos1, neg1, "A") || pos1[0] == "A" || neg1[0] == "A" || pos1[2] != null || neg1[2] != null) {
      System.out.println("Error case 1");
	  return false;
	}
		
	// Case 2: Present in pos only
	String[] pos2 = {"A", "B", "C"};
	String[] neg2 = {"B", "C", "D"};
	if (!COVIDTracker.removeIndividual(pos2, neg2, "A") || pos2[0] == "A" || pos2[2] != null) {
	  System.out.println("Error case 2");
	  return false;
	}
		
	// Case 3: Present in neg only
	String[] pos3 = {"B", "C", "D"};
	String[] neg3 = {"A", "B", "C"};
	if (!COVIDTracker.removeIndividual(pos3, neg3, "A") || neg3[0] == "A" || neg3[2] != null) {
      System.out.println("Error case 3");
	  return false;
	}
	  
	// Case 4: Not present in either
	String[] pos4 = {"B", "C", "D"};
	String[] neg4 = {"B", "C", "D"};
	if (COVIDTracker.removeIndividual(pos4, neg4, "A")) {
	  System.out.println("Error case 4");
	  return false;
	}	
    return true;
  }
  
  /**
   * Tester method for COVIDTracker.getPopStats()
   * 
   * @return true if all tests are passed
   */
  public static boolean testGetPopStats()
  {
    // Case 1: Both Populated arrays
	String[] posTest = {"A", "C", "D"};
	String[] negTest = {"A", "B", "B"};
		
	String expectedOutput = "Total Tests: " + "6" + "\nTotal Individuals Tested: "+ "4" + "\nPercent Positive Tests: " + "50.0" + "%\nPercent Positive Individuals: " + "75.0" + "%";
	if (!COVIDTracker.getPopStats(posTest, negTest).equals(expectedOutput)) {
	  System.out.println("Error Case 1");
	  return false;
	}
		
	// Case 2: Populated Positive Array
	String[] posTest2 = {"A", "B", "C"};
	String[] negTest2 = {null, null, null};
		
	String expectedOutput2 = "Total Tests: " + "3" + "\nTotal Individuals Tested: "+ "3" + "\nPercent Positive Tests: " + "100.0" + "%\nPercent Positive Individuals: " + "100.0" + "%";
	if (!COVIDTracker.getPopStats(posTest2, negTest2).equals(expectedOutput2)) {
	  System.out.println("Error Case 2");
	  return false;
	}
		
	// Case 3: Populated Negative Array
	String[] posTest3 = {null, null, null};
	String[] negTest3 = {"A", "B", "C"};
		
	String expectedOutput3 = "Total Tests: " + "3" + "\nTotal Individuals Tested: "+ "3" + "\nPercent Positive Tests: " + "0.0" + "%\nPercent Positive Individuals: " + "0.0" + "%";
	if (!COVIDTracker.getPopStats(posTest3, negTest3).equals(expectedOutput3)) {
	  System.out.println("Error Case 3");
	  return false;
	}
		
	// Case 4: Both unpopulated Arrays
	String[] posTest4 = {null, null, null};
	String[] negTest4 = {null, null, null};
		
	String expectedOutput4 = "Total Tests: " + "0" + "\nTotal Individuals Tested: "+ "0" + "\nPercent Positive Tests: " + "0.0" + "%\nPercent Positive Individuals: " + "0.0" + "%";
	
	if (!COVIDTracker.getPopStats(posTest4, negTest4).equals(expectedOutput4)) {
	  System.out.println("Error Case 4");
	  return false;
	}
		
	// Case 5: Multiple Tests for One Person
	String[] posTest5 = {"A", "A", null};
	String[] negTest5 = {"A", "A", "A"};
		
	String expectedOutput5 = "Total Tests: " + "5" + "\nTotal Individuals Tested: "+ "1" + "\nPercent Positive Tests: " + "40.0" + "%\nPercent Positive Individuals: " + "100.0" + "%";
	if (!COVIDTracker.getPopStats(posTest5, negTest5).equals(expectedOutput5)) {
      System.out.println("Error Case 5");
	  return false;
	}
	return true;
  }
  
  /**
   * Tester method for COVIDTracker.getIndividualStats()
   * 
   * @return true if all tests are passed
   */
  public static boolean testGetIndividualStats() {
    // Case 1: Individual is present in pos and neg
	String[] pos1 = {"A", "B", "C", "D"};
	String[] neg1 = {"A", "A", "B", "C", "D"};
		
	String expectedResult1 = "Total Tests: " + "3" + "\nPositive: " + "1" + "\nNegative: " + "2";
	if (!COVIDTracker.getIndividualStats(pos1,neg1,"A").equals(expectedResult1)) {
	  System.out.println("Error Case 1");
	  return false;
	}
		
	// Case 2: Individual is present in pos only
	String[] pos2 = {"A", "B", "C", "D"};
	String[] neg2 = {"B", "C", "D"};
		
	String expectedResult2 = "Total Tests: " + "1" + "\nPositive: " + "1" + "\nNegative: " + "0";
	if (!COVIDTracker.getIndividualStats(pos2,neg2,"A").equals(expectedResult2)) {
	  System.out.println("Error Case 2");
	  return false;
	}
		
	// Case 3: Individual is present in neg only
	String[] pos3 = {"B", "C", "D"};
	String[] neg3 = {"A", "A", "B", "C", "D"};
		
	String expectedResult3 = "Total Tests: " + "2" + "\nPositive: " + "0" + "\nNegative: " + "2";
	if (!COVIDTracker.getIndividualStats(pos3,neg3,"A").equals(expectedResult3)) {
	  System.out.println("Error Case 3");
	  return false;
	}
		
	// Case 4: Individual isn't present in either
	String[] pos4 = {"B", "C", "D"};
	String[] neg4 = {"B", "C", "D"};
		
	String expectedResult4 = "Total Tests: " + "0" + "\nPositive: " + "0" + "\nNegative: " + "0";
	if (!COVIDTracker.getIndividualStats(pos4,neg4,"A").equals(expectedResult4)) {
	  System.out.println("Error Case 4");
	  return false;
	}
	
	// Case 5: No tests reported
	String[] pos5 = {};
	String[] neg5 = {};
	
	String expectedResult5 = "Total Tests: " + "0" + "\nPositive: " + "0" + "\nNegative: " + "0";
	if (!COVIDTracker.getIndividualStats(pos5,neg5,"A").equals(expectedResult5)) {
      System.out.println("Error Case 5");
	   return false;
	}
	return true;					
  }
  
  /**
   * Main method, prints whether each test passes or fails
   * 
   * @param args
   */
  public static void main(String[] args) {
	System.out.println("testAddTest(): " + testAddTest());
	System.out.println("testRemoveIndividual(): " + testRemoveIndividual());
	System.out.println("testGetPopStats(): " + testGetPopStats());
	System.out.println("testGetIndividualStats(): " + testGetIndividualStats());	
  }
}
