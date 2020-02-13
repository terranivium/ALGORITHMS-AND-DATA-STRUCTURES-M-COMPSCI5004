// 2460681S, Wesley Scott

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AssEx1 {
    
	
	/** read strings from file 
     * and add them to an array. Assume that in the file there is one string
     * per line.
     * @param filename
     * return array
     */
	public static String[] readFromFile(String fileName){
		FileReader fileInput = null;
		ArrayList<String> parse = new ArrayList<String>(); // store initial read values, gives array length
		try{
			fileInput = new FileReader(fileName);
			Scanner fileParse = new Scanner(fileInput);
			while(fileParse.hasNextLine()){ // while birds.txt has a line to be read
				parse.add(fileParse.nextLine());
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try{
				fileInput.close();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		String[] dump = new String[parse.size()]; // returned String array
		for(int i = 0; i < parse.size(); i++){
			dump[i] = parse.get(i); // populate with ArrayList values
		}
		return dump;
	}
	
	
	/**
	 * Add all of the elements of an array of Strings
	 * to a set of strings - note repeats will disappear
	 * your set should be instantiated as a TreeSet (see lecture 1)
	 */
	public static Set<String> arrayToSet(String[] myArray){
		List<String> list = Arrays.asList(myArray);
		Set<String> set = new TreeSet<String>(list);
		return set; 
	}
	
	
	/** randomly re-order an array
	 * 
	 * @param origArray
	 */
	public static String[] jumbleArray(String[] origArray){
		Random r = new Random();
		for(int i=0;i<origArray.length;i++){
			origArray[i] = origArray[r.nextInt(origArray.length)];
		}
		return origArray;
	}
	
	/** create a string consisting all of the elements in an array,
	 * one element per row.
	 * Use a for--each loop to return them in the order in which they 
	 * are stored
	 */
	public static String arrayToString(String[] stringArray){
		String outString = "";
		for(String string:stringArray){
			outString += string + "\n";
		}
		return outString;
	}
	
	/** create a string consisting of all of the elements in a set,
	 * one element per row.
	 * Use a for--each loop to return them in the order in which they 
	 * are stored
	 */
	public static String setToString(Set<String> stringSet){
		String outString = "";
		for (String string:stringSet){
			outString += string + "\n";
		}
		return outString;
	}
	
	
	
	/**
	 * main method - do not edit this
	 * @param args
	 */
	public static void main(String[] args){
		String fileName = args[0];
		String[] originalArray = readFromFile(fileName);
		System.out.println("the array has length " + originalArray.length + "\n");
		String[] newArray1 = originalArray.clone();
		String[] newArray2 = originalArray.clone();
		
		jumbleArray(newArray1);
		jumbleArray(newArray2);
		
		System.out.print("The original array is:\n");
		System.out.print(arrayToString(originalArray) + "\n");
		
		System.out.print("The frst jumbled array is:\n");
		System.out.print(arrayToString(newArray1) + "\n");
		System.out.print("The corresponding set is:\n");
		System.out.print(setToString(arrayToSet(newArray1))+"\n");
		
		System.out.print("The second jumbled array is:\n");
		System.out.print(arrayToString(newArray2) + "\n");
		System.out.print("The corresponding set is:\n");
		System.out.print(setToString(arrayToSet(newArray2)) + "\n");
		
		
		
		}
	
}
