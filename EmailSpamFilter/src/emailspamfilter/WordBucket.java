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
		int i = 0;
		while (scanf.hasNext()){
			arr.set(i,scanf.next());
			i++;
		}
	}

	public void writeArr(){
	//write out arryList from a file
		PrintWriter writer = new PrintWriter("wordbuckettext.txt", "UTF-8");
		writer.println("hello");
		writer.close();


	}


	public void processWord(String word, Boolean spam){
	//either adds or incremenets

	}

	public void incrementWordGood(String word){

	}

	public void incrementWordBad(String word){

	}

	public void addWorld(String word){

	}

	public double getPercentWord(String word){

	}


}