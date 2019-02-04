/**
* @author Jacob Thomas Overton <jakethoverton@gmail.com>
* This program generates a psuedorandom poem according to rules provided by a text file.
*/
import java.util.*;
import java.io.*;

/**
* This class holds the main method for generating the poem.
*/
public class PoemGenerator{
	//Field definining maximum number of lines in the text file
	public static int max_lines = 10;
	//Field definining maximum number of tokens on each line in the text file
	public static int max_tokens = 10;
	/**
	* This is the main method.
	* @param args arguments provided through the command line.
	*/
	public static void main (String args[]){
		//Check to see if the number of arguments is correct
		if(args.length != 1){
			System.out.println("Please supply a text file to parse as an argument.");
			System.exit(1);
		}
		//Start of try/catch block catches FileNotFound
		try {
			//Tries to create a file based on the supplied argument
			File rules = new File(args[0]);	
			//Creates a scanner if file does not throw exception
			Scanner scan = new Scanner(rules);
			//Data Structure to store each token in text file
			String parse[][] = new String[max_lines][max_tokens];
			//Keeps track of what line of text file we are on
			int lineCount = 0;
			//Keeps track of which token on the line we are on
			int tokenCount = 0;
			//Reads through the text file saving each token to the parse array
			while(scan.hasNextLine() && lineCount <= max_lines){
				Scanner line = new Scanner(scan.nextLine());
				//Each line is saved to a new row in the parse array
				while(line.hasNext() && tokenCount <= max_tokens){
					parse[lineCount][tokenCount] = line.next();
					tokenCount++;
				}
				lineCount++;
				tokenCount = 0;
			}
			drillDown(parse, 0, 0);
			scan.close();
		//Exits the program if File cannot be found
		} catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(2);
		}
	}
	/**
	* Operates on each rule defined in the text file.
	* @param parse 2D array containing data from rules file
	* @param row integer containing what row of the array to access
	* @param col integer containing what column of the array to access
	*/
	public static void drillDown(String parse[][], int row, int col){
		//The string we are operating on 
		String token = parse[row][col];
		//Exit this definition of the rule if null is encounterd
		if(token == null){
			return;
		} 
		//The pipe character means something must be chosen at random
		if(token.contains("|")){
			//The selection string holds which word, keyword, or reference was randomly chosen
			String selection = handleRandom(token);
			//This conditional handles whatever was selected
			if(selection.contains("<")||selection.contains(">")){
				handleAngleBracket(parse, selection);
			} else if(selection.equals("$END")){
				return;
			} else if (selection.equals("$LINEBREAK")){
				System.out.print("\n");
			} else {
				System.out.print(selection + " ");
			}
			drillDown(parse, row, col + 1);
			return;
		//Angle brackets refer to a reference to another rule
		} else if(token.contains("<")||token.contains(">")){
			handleAngleBracket(parse, token);
		//These keywords require special actions to be performed
		} else if(token.equals("$END")){
			return;
		} else if (token.equals("$LINEBREAK")){
			System.out.print("\n");
		}
		//This function recursively steps through the definition of a rule until null is encountered
		drillDown(parse, row, col + 1);
		return;
	}
	/**
	* Chooses a random value from the pipe delimited string
	* @param token The pipe delimited String
	* @return String the randomly chosen string
	*/
	public static String handleRandom(String token){
		//This arraylist will contain each string in the token delimited by a pipe
		ArrayList<String> options = new ArrayList<String>();
		Scanner scn = new Scanner(token);
		scn.useDelimiter("\\|");
		while(scn.hasNext()){
			options.add(scn.next());
		}
		//Randomly chooses one string from the arraylist and returns it
		Random rand = new Random();
		String selection = options.get(rand.nextInt(options.size()));
		scn.close();
		return selection;		
	}
	/**
	* Recursively handles angle brackets until a word is found
	* @param token The String surrounded by angle brackets
	* @param parse The 2D array containing the parsed text file
	*/
	public static void handleAngleBracket(String parse[][], String token){
		//Slices the angle brackets off the string and adds a colon
		token = token.substring(1, token.length() -1);
		token = token + ":";
		//Searches for the rule name that matches the string
		for(int i = 0; i < parse.length; i++){
			//If found the rule will be handled by the drillDown method.
			if(parse[i][0].equals(token)){
				drillDown(parse, i, 0);
				break;
			}
		}
	}
	
}