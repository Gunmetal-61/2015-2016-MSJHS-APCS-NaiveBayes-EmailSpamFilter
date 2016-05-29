//saving word bucket as arraylist of strings
import java.io.*;
import java.util.*;

public class WordBucket{
		
	public ArrayList<String> arr;

	public WordBucket(){
	//initiates blank array 
	}

	public void getArr() throws FileNotFoundException{
	//read in arraylist from a file
		File f = new File("wordbuckettext.txt");
		Scanner scanf = new Scanner(f);
		for 
	}

	public void writeArr(){
	//write out arryList from a file
		PrintWriter writer = new PrintWriter("wordbuckettext.txt", "UTF-8");
		writer.println("hello");
		writer.close();


	}

	public void increment(){

	}



}