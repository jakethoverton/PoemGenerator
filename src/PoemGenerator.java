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
			String parse[][] = new String[7][7];
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
			//Used for testing: Prints contents of parse array
			System.out.println(Arrays.deepToString(parse));
		//Exits the program if File cannot be found
		} catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(2);
		}
	}
}