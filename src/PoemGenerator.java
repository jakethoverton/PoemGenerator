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
			String parse[][] = new String[8][7];
			//Keeps track of what line of text file we are on
			int lineCount = 0;
			//Keeps track of which token on the line we are on
			int tokenCount = 0;
			//Reads through the text file saving each token to the parse array
			while(scan.hasNextLine()){
				Scanner line = new Scanner(scan.nextLine());
				//Each line is saved to a new row in the parse array
				while(line.hasNext()){
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
	* Operates on each token in the array 
	* @param parse 2D array containing data from rules file
	* @param row integer containing what row of the array to access
	* @param col integer containing what column of the array to access
	*/
	public static void drillDown(String parse[][], int row, int col){
		String token = parse[row][col];
		if(token == null){
			return;
		} 
		if(token.contains("|")){
			String selection = handleRandom(token);
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
		} else if(token.contains("<")||token.contains(">")){
			handleAngleBracket(parse, token);
		} else if(token.equals("$END")){
			return;
		} else if (token.equals("$LINEBREAK")){
			System.out.print("\n");
		}
		drillDown(parse, row, col + 1);
		return;
	}
	/**
	* Chooses a random value from the pipe delimited string
	* @param token The pipe delimited String
	*/
	public static String handleRandom(String token){
		ArrayList<String> options = new ArrayList<String>();
		Scanner scn = new Scanner(token);
		scn.useDelimiter("\\|");
		while(scn.hasNext()){
			options.add(scn.next());
		}
		Random rand = new Random();
		String selection = options.get(rand.nextInt(options.size()));
		scn.close();
		return selection;		
	}
	/**
	* Recursively handles angle brackets until a word is found
	* @param token The String surrounded by angle brackets
	*/
	public static void handleAngleBracket(String parse[][], String token){
		token = token.substring(1, token.length() -1);
		token = token + ":";
		for(int i = 0; i < parse.length; i++){
			if(parse[i][0].equals(token)){
				drillDown(parse, i, 0);
				break;
			}
		}
	}
	
}