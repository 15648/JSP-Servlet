package com.perscholas.servletjsp_sba;

import java.util.Scanner;

public class PoemApp {

	public static void main(String[] args) {
		// fill in code here in the app so that a user is
		// asked for a title and will return the author and poemtext
		// for the poem matching the specified title

		// code for menu missing
		Scanner scanner = new Scanner(System.in);
	    int swValue;

	    // Display menu graphics
	    System.out.println("============================");
	    System.out.println("|   MENU SELECTION DEMO    |");
	    System.out.println("============================");
	    System.out.println("| Database Option          |");
	    System.out.println("|        1. Add Poem       |");
	    System.out.println("|        2. Edit Poem      |");
	    System.out.println("|        3. Quit App       |");
	    System.out.println("============================");
	    System.out.print("Enter an option:  ");
	    String choice = scanner.next();
	    swValue = Integer.parseInt(choice);
	    

	    // Switch construct
	    switch (swValue) {
	    case 1:
	      System.out.println("Add Poem selected: ");

		  // TODO Add poem action;
	      AddPoemToDB();
	      break;
	    case 2:
	      System.out.println("Edit Poem selected: ");
		  // TODO Edit Poem Action;
	      RetrivePoemDetails();
	      break;
	    case 3:
	      System.out.println("Quit App selected: ");
		  // TODO Quit App Action;
	      break;
	    default:
	      System.out.println("Invalid selection: ");
	      break; // This break is not really necessary
	    }
		scanner.close();
	}
	
	public static void RetrivePoemDetails() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please provide title for your poem:  ");
		String title = scanner.nextLine();
		PoemDAO poemDAO = new PoemDAO();
		Poem myPoem = poemDAO.getPoemByTitle(title);

		System.out.println("Your poem is By " + myPoem.author + " and Text is  " + myPoem.poemText);
		scanner.close();		
	}
	
	public static void AddPoemToDB() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Now let's insert your poem's information");
		Poem newPoem = new Poem();
		//scanner.nextLine(); // this command is called to scan on new line,
		// remove or comment out the above line and see what happens

		System.out.print("What is your poem's author: ");
		newPoem.author = scanner.nextLine();
		System.out.print("What is your poem's title: ");
		newPoem.title = scanner.nextLine();
		System.out.print("What is your poem's poemtext: ");
		newPoem.poemText = scanner.nextLine();

		// Now lets use the poemDAO object to now call insertPoem function
		PoemDAO poemDAO = new PoemDAO();
//need to check this part****	
		poemDAO.insertPoem(newPoem); 

		scanner.close();		
	}
}