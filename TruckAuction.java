import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Project_cononie {
/* This program will allow the user to input up to 100 trucks and enter their information while the program calculates
 * whether it will be auctioned based off of the mileage and age of the truck. The program will then calculate the auction price
 * of the truck if it is to be auctioned, then will allow the user to search for a truck by the VIN, display all trucks information
//  or quit program. */

	static String input = " ";
	public static void main(String[] args) {		
		
		// array declarations
		String  [] vin           = new String[100];
		String  [] make          = new String[100];
		String  [] model         = new String[100];
		double  [] ogPrice       = new double[100];
		int     [] miles         = new int[100];
		int     [] age           = new int[100];
		boolean [] auctionStatus = new boolean[100];
		double  [] auctionPrice  = new double[100];
		
		// Working variables
		int start = 0;
		String searchVin = " ";
		int truckCount = 0;
		int selection = 0;
		int foundAt = -1;
		
		//------------------------------------------------------------
		start = JOptionPane.showConfirmDialog(null, "Would you like to start this program? ", "START", JOptionPane.YES_NO_OPTION);
		if(start == JOptionPane.NO_OPTION)
		{
			JOptionPane.showMessageDialog(null, "Goodbye");
		} // End if
		else 
		{
		while(selection != 4) {
			selection = menu();
			
			switch(selection) {
			// get information and calculate auction status and auctionPrice
			case 1:
				truckInfo(vin, make, model, ogPrice, miles, age, truckCount);
				auctionStatus(miles, age, auctionStatus, truckCount);
				auctionPrice(auctionStatus, ogPrice, auctionPrice, truckCount);
				truckCount++;
				break;
				
			// display
			case 2: 
				if(truckCount == 0)
				{
					JOptionPane.showMessageDialog(null, "No trucks have been entered!");
				} // end if
				else 
				{
				for(int i = 0; i < truckCount; i++) {
					displayInfo(vin, make, model, ogPrice, miles, age, auctionStatus, auctionPrice, i);
				
				} // end for(int i = 0; i < truckCount; i++) 
				} // end else
				break;
			
			// search
			case 3: 
				if(truckCount == 0)
				{
					JOptionPane.showMessageDialog(null, "No trucks have been entered!");
				} // end if truckCount == 0
				else
				{
				input = JOptionPane.showInputDialog("Enter the truck's VIN # to search for: ");
				
				// validation for VIN 
				while(checkVin(input) == false)
				{
					input = JOptionPane.showInputDialog("Invalid! Must be 10 characters of letters and digits only, try again");

				} // end while
				searchVin = input;
				foundAt = searchTruck(vin, searchVin, truckCount);

			

				if(foundAt < 0) 
				JOptionPane.showMessageDialog(null, searchVin + " was not found anywhere in the database! ");	
				
				else 
					displayInfo(vin, make, model, ogPrice, miles, age, auctionStatus, auctionPrice, foundAt);
				} // end else truckCount == 0
				break;
				
			// quit program
			case 4: 
				JOptionPane.showMessageDialog(null, "Goodbye!");
				break;
				
			default: 
				JOptionPane.showMessageDialog(null, "Error, wrong selection. Please type a number 1 through 4.");
			} // end switch(selection)
			
			
			
		} // end while(selection != 4)
		} // end else(start==yes)
	} // end main
	
	// ===================================================================================================
	public static int menu() {
		int selection = 0;
		input = JOptionPane.showInputDialog("Press 1 to add a truck" + "\n" +
											"Press 2 to display all trucks" + "\n" +
											"Press 3 to search for a truck" + "\n" +
											"Press 4 to quit program" + "\n" +
											"Please press a number 1 - 4");
	selection = Integer.parseInt(input);
	return selection;
	} // end menu
	
	
	// ===================================================================================================
	public static void truckInfo(String [] vin, String [] make, String [] model, double  [] ogPrice,int[] miles, int[] age, int truckCount) {
	input = JOptionPane.showInputDialog("Enter the truck's VIN number:");	
	
	// validation for truck VIN
	while(checkVin(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be 10 characters of digits and letters, try again. ");
	} // end while validation for VIN
	vin[truckCount] = input;
	
	input = JOptionPane.showInputDialog("Enter the truck's make:");
	
	// validation for truck make
	while(checkMake(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be letters only, try again. ");

	} // end while for make
		make[truckCount] = input;
	
	input = JOptionPane.showInputDialog("Enter the truck's model:");
	
	// validation for truck model
	while(checkModel(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be letters and digits only, try again. ");

	} // end while for model
		model[truckCount] = input;
	
	input = JOptionPane.showInputDialog("Enter the truck's original price:");
	
	// validation for truck original price
	while(checkogPrice(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be digits only, try again. ");

	} // end while for original price
		ogPrice[truckCount] = Double.parseDouble(input);
	input = JOptionPane.showInputDialog("Enter the truck's miles: ");
	
	// validation for truck miles
	while(checkMiles(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be digits only, try again. ");

	} // end while for miles
		miles[truckCount] = Integer.parseInt(input);
	
	
	input = JOptionPane.showInputDialog("Enter the truck's age: ");
	
	// validation for truck age
	while(checkAge(input) == false)
	{
		input = JOptionPane.showInputDialog("Invalid entry, must be digits only, try again. ");

	} // end while for truck age
		age[truckCount] = Integer.parseInt(input);
	
		} // end of truckInfo
// ========================================================================================================================

	public static void auctionStatus(int [] miles, int [] age, boolean[] auctionStatus, int truckCount) {
		
		if(miles[truckCount] > 900000 || age[truckCount] >= 10) {
			auctionStatus[truckCount] =  true;
			} // end if
		else {
			auctionStatus[truckCount] = false;
		} // end else 
	
	}// end auctionStatus
// ========================================================================================================================

	public static void auctionPrice(boolean [] auctionStatus, double [] ogPrice, double [] auctionPrice, int truckCount) {
		
		if(auctionStatus[truckCount] == true) {
		   auctionPrice[truckCount] = ogPrice[truckCount] * 0.25;
	} // end if auctionStatus[truckCount] == true
	
		else {
		   auctionPrice[truckCount] = 0.0;
		
	} // end else auctionStatus[truckCount] == true
} // end auctionPrice

//========================================================================================================================

public static void displayInfo(String [] vin, String [] make, String [] model, double [] ogPrice, 
							   int[] miles, int[] age, boolean[] auctionStatus, double [] auctionPrice,
							   int truckCount)
{
	DecimalFormat DF = new DecimalFormat("##.00");
	
	if(auctionStatus[truckCount] == true) {
	
	JOptionPane.showMessageDialog(null, "Truck VIN #: "   + vin[truckCount]           + "\n" +
	  							"Truck make: "   		  + make[truckCount]          + "\n" +
	  							"Truck model: "    		  + model[truckCount]         + "\n" +
	  							"Original price: "  	  + ogPrice[truckCount]       + "\n" +
	  							"Miles: " 				  + miles[truckCount] 	      + "\n" +
	  							"Truck age:  " 		      + age[truckCount]           + "\n" +
	  							"Auction status: "        + auctionStatus[truckCount] + "\n" +
	  							"Auction price:  "        + DF.format(auctionPrice[truckCount]));
	
	JOptionPane.showMessageDialog(null, "This truck will be auctioned!");
	} // end if auctionStatus[truckCount] == true
	
	else {
	JOptionPane.showMessageDialog(null, "Truck VIN #: "   + vin[truckCount]           + "\n" +
					"Truck make: "   		  + make[truckCount]          + "\n" +
					"Truck model: "    		  + model[truckCount]         + "\n" +
					"Original price: "  	  + ogPrice[truckCount]       + "\n" +
					"Miles: " 				  + miles[truckCount] 	      + "\n" +
					"Truck age:  " 		      + age[truckCount]           + "\n" +
					"Auction status: "        + auctionStatus[truckCount]);	
	JOptionPane.showMessageDialog(null, "This truck will not be auctioned!");
	} // end else auctionStatus[truckCount] == true
} // end display	

//===================================================================================================
public static int searchTruck(String [] vin, String searchVin, int truckCount) {
	
	for(int i = 0; i < truckCount; i++) {
	
		if(searchVin.equals(vin[i])) { // if user input equals vin at position i
			return i;
		}// end if
	} // end for 
	return -1;
} // end searchTruck


//===========================================================================
//============================ validations ==================================
//===========================================================================
public static boolean checkVin(String input) 
{
	
	if(input.length() != 10) { // any length other than 10 will return false
		return false;
	} // end if
	else {
		
		for(int i = 0; i < input.length(); i++) {
			
			if(!Character.isDigit(input.charAt(i)) && !Character.isLetter(input.charAt(i))) { // character must be digit or letter
				return false;
			} // end if 
		} // end for
		return true;
	} // end else
} // end checkVin

//===================================================================================================
public static boolean checkMake(String input)
{
	for(int i = 0; i< input.length(); i++) 
	{
		if(!Character.isLetter(input.charAt(i))) // character must be a letter
		{
			return false;
		
		} // end if
	} // end for 
	
	return true;
} // end checkMake

//===================================================================================================
public static boolean checkModel(String input) 
{
	for(int i = 0; i < input.length(); i++)
	{
		if(!Character.isLetter(input.charAt(i)) && !Character.isDigit(input.charAt(i)))	// character must be letter or digit
		{
			return false;
		
		} // end if
	}// end for
	return true;
} // end checkModel

//===================================================================================================
public static boolean checkogPrice(String input) 
{
	char decimal = '.';
	for(int i = 0; i < input.length(); i++)
		{
			if(!Character.isDigit(input.charAt(i)) && input.charAt(i) != decimal) // if the character is not a digit and not a decimal
																				 // will return false
			{
				return false;
			}
		} // end for
		return true;
} // end checkogPrice


//===================================================================================================
public static boolean checkMiles(String input) 
{
	for(int i = 0; i < input.length(); i++)
	{
		if(!Character.isDigit(input.charAt(i))) // must be a digit
		{
			return false;
		} // end if
	} // end for
return true;
} // end checkMiles

// ===================================================================================================
public static boolean checkAge(String input) 
{
	for(int i = 0; i < input.length(); i++)
	{
		if(!Character.isDigit(input.charAt(i))) // must be a digit
		{
			return false;
		} // end if
	} // end for
return true;
} // end checkAge


} // end class Project_Cononie