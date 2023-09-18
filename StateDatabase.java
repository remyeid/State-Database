import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/********************************************************************
 * Written by:
 * Period:
 * 
 * ASSIGNMENT:  Write a program that reads the data from 
 * states.txt into a 2-D array of Strings. The data is in the order:
 * 		state name
 * 		state capital
 * 		state flower
 * 		state bird
 * 		state population
 * Then give the user the following options:
 * 		print, search, sort, exit (using buttons)
 * Your program should continue until the user chooses exit.
 * 
 * print:  prints the array in a JScrollPane - use the code I gave 
 * 		you to get started.
 * search:  should ask the user what field they want to search 
 * 	(using buttons) and then call one of three search methods:
 * 	search for name or capital - 
 * 		- send the field to be searched (name - 0 or capital - 1).
 * 		- ask for the name or capital to search for.
 * 		- use a binary search to find the state and print the data
 * 			 for that state.
 * search for bird or flower - 
 * 		- send the field to to searched (flower - 2 or bird - 3).
 * 		- ask for the flower or bird to search for.
 * 		- use a sequential search to print the names of all states
 * 			with that flower or bird in ONE JOP window.  
 * 	searchForPopulation - 
 * 		- ask for a low and a high population to search for.
 * 		- use a sequential search to print the names of all states
 * 			with populations in that range in ONE JOP window.
 *  
 * For all 3 searches if the search data isn't found you should print
 * 		an appropriate message.  Case shouldn't matter in your search.
 * For all 3 searched include the data you are searching for in your
 * 		JOP window.
 *  
 *  
 * FIRST GRADE: Printing - 20 points - let me see your program run.
 * 			fill and print work - columns line up correctly and 
 * 				have correct headings (10 points)
 * 			your main method is correct (5 points)
 * 
 * SECOND GRADE: Searching - 20 points - let me see your program run.
 * 		Each search follows the directions and works correctly:
 * 			search for name - binary search  (10 points)
 *			search for bird and flower - sequential search, one method (5 points)
 *			search for population - sequential search  (5 points)
 *			your main method is still correct (5 points)
 *
 *
 *******************************************************************/
public class StateDatabase{
	public static void fillArray(String[][] userArray) {
		try {
			Scanner inFile = new Scanner(new File("src/Database.txt"));
			String name, capital, bird, flower, population;
			int counter = 0;
			while (inFile.hasNext()) {
				name = inFile.nextLine();
				capital = inFile.nextLine();
				bird = inFile.nextLine();
				flower = inFile.nextLine();
				population = inFile.nextLine();
				userArray[counter][0] = name;
				userArray[counter][1] = capital;
				userArray[counter][2] = bird;
				userArray[counter][3] = flower;
				userArray[counter][4] = population;
				counter ++;
			}
			inFile.close();
		}catch( Exception e) {
			e.printStackTrace();
		}
	}
	public static void print(String[][] userArray)
	{
		JTextArea area = new JTextArea();
		area.setEditable(false);
		
		area.append("STATE NAME\t\t         CAPITAL\t\t\t  FLOWER\t\t\t   BIRD\t\t\t\t    POPULATION\n\n");
		String name = null, capital = null, flower = null, bird = null, population = null;
		for (int i=0; i<userArray.length; i++)
		{
			
			name = userArray[i][0];
			while (name.length()<33) {
				name += " ";}
			capital = userArray[i][1];
			while (capital.length()<33) {
				capital += " ";}
			flower = userArray[i][2];
			while (flower.length()<33) {
				flower += " ";}
			bird = userArray[i][3];
			while (bird.length()<33) {
				bird += " ";}
			population = userArray[i][4];
			while (population.length()<33) {	
				population += " ";}
			
				
			area.append(name+capital+flower+bird+population+"\n");
			
		}

		area.setBackground(new Color(255,250,205));
		area.setForeground(new Color(0,0,0));
		area.setFont(new Font("Consolas", Font.PLAIN, 14));
		// how many rows will show at one time
		area.setRows(55);
		// how many columns (1 char) will show at one time
		area.setColumns(150);
		JScrollPane pane = new JScrollPane(area);
		JOptionPane.showMessageDialog(null,pane);	
	
	}
	public static void searchNameOrCap(String[][] userArray, int searchForWhat){
		String userChoice = "0";
		boolean trueOrFalseForError = false;
		if (searchForWhat == 0) {
			userChoice = JOptionPane.showInputDialog("Enter the state you want to search for: ");
		}
		else if (searchForWhat == 1) {
			userChoice = JOptionPane.showInputDialog("Enter the state capital you want to search for: ");
		}
		userChoice = userChoice.toUpperCase();
		for(int r = 0; r < userArray.length;r++) {
			for(int c = 0; c <2; c++) {
				if (userChoice.equals(userArray[r][c])){
					trueOrFalseForError = true;
					JOptionPane.showMessageDialog(null, "Here is the information:\n\nState name: "+userArray[r][0]
							+"\nState capital: "+userArray[r][1]+ "\nState flower: "+ userArray[r][2]+"\nState bird: "+
							userArray[r][3]+ "\nState population: "+userArray[r][4]);
				}
			}
		}
		if (trueOrFalseForError == false)
			JOptionPane.showMessageDialog(null, "Sorry - there were no matches for your data.");
	}
	public static void searchFlowerOrBird(String[][] userArray,int searchForWhat) {
		boolean trueOrFalseForError = false;
		String userChoice = JOptionPane.showInputDialog("Enter a flower or bird: ").toUpperCase();
		String totalFlowerOrBird = "";
		if (searchForWhat == 2) {
			totalFlowerOrBird = ("Here are the states that have the " + userChoice+" as their state flower:\n").toUpperCase();
			for (int r = 0; r < userArray.length; r++) {
				if (userChoice.equals(userArray[r][2])) {
					trueOrFalseForError = true;
					totalFlowerOrBird += "\n"+userArray[r][0];
				}
			}
		}
		if (searchForWhat == 3) {
			totalFlowerOrBird = ("Here are the states that have the " + userChoice+" as their state bird:\n").toUpperCase();
			for (int r = 0; r < userArray.length; r++) {
				if (userChoice.equals(userArray[r][3])) {
					trueOrFalseForError = true;
					totalFlowerOrBird += "\n"+userArray[r][0];
				}
			}
		}
		
		if (trueOrFalseForError == true)
			JOptionPane.showMessageDialog(null, totalFlowerOrBird);
		else
			JOptionPane.showMessageDialog(null, "Sorry - there were no matches for your data.");
		
	}
	public static void searchPopulation(String[][] userArray) {
		boolean trueOrFalseForError = false;
		int current = 0;
		int userLow = Integer.parseInt(JOptionPane.showInputDialog("Enter an population range, low:"));
		int userHigh = Integer.parseInt(JOptionPane.showInputDialog("Enter an population range, high:"));
		String totalStatesWithPop = "Here are the states that have a population between " + userLow+" and "+userHigh+":\n";
		for(int r = 0; r < userArray.length;r++) {
			current = Integer.parseInt(userArray[r][4]);
			if ((current>=userLow)&&(current<=userHigh)){
				trueOrFalseForError = true;
				totalStatesWithPop+= "\n"+userArray[r][0];
			}
		}
		JOptionPane.showMessageDialog(null, totalStatesWithPop);
		if (trueOrFalseForError == false)
			JOptionPane.showMessageDialog(null, "Sorry - there were no matches for your data.");
	
	}
	
	public static void main(String[] args) {
		String[][] userArray = new String[51][5];
		fillArray(userArray);
		String[] mainOptionsList = {"Print","Search","Exit"};
		int mainOptions = 0;
		while (mainOptions != 2) {
			mainOptions=JOptionPane.showOptionDialog
				(null, "What do you want to do?", "State Database", 0, 2, null, mainOptionsList, null);
			if (mainOptions == 0)
				print(userArray);
			else if (mainOptions == 1) {
				String[] searchOptions = {"State Name","Capital","Flower","Bird","Population"};
				int searchForWhat=JOptionPane.showOptionDialog
						(null, "What do you want to search by?", "State Database", 0, 3, null, searchOptions, null);
				//name or capital
				if (searchForWhat == 0||searchForWhat == 1)
					searchNameOrCap(userArray,searchForWhat);
				//flower or bird
				else if (searchForWhat == 2||searchForWhat == 3)
					searchFlowerOrBird(userArray,searchForWhat);
				//population
				else if (searchForWhat == 4)
					searchPopulation(userArray);
			}

		}
		JOptionPane.showMessageDialog(null, "Thank you for using the State Database!");
	}
}