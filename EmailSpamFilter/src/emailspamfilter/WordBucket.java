//saving word bucket as arraylist of strings
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("wordbuckettext.txt", "UTF-8");
                writer.println("hello");
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                writer.close();
            }


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