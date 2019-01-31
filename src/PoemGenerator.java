/**
* @author Jacob Thomas Overton <jakethoverton@gmail.com>
* This program generates a psuedorandom poem according to rules provided by Sift.
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
		if(args.length != 1){
			System.out.println("Please supply a text file to parse as an argument.");
			System.exit(1);
		}
		try {
			File rules = new File(args[0]);	
			Scanner scan = new Scanner(rules);
			while(scan.hasNextLine()){
				System.out.println(scan.nextLine());
			}		
		} catch(FileNotFoundException e){
			System.out.println("File not found.");
			System.exit(2);
		}
	}
}