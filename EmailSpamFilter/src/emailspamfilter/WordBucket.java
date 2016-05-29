//saving word bucket as arraylist of strings
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordBucket{
		
	public ArrayList<String> arr;

	public WordBucket(){
	//initiates blank arraylist
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
	//writes arraylist out 
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("wordbuckettext.txt", "UTF-8");
            for (int i = 0; i<arr.size(); i++){
                writer.println(arr.get(i));
        	}
            writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                writer.close();
        }


	}


	/*
	* Processes a word by updating the arrayList; if the word is already in the word bucket, 
	* word's count is incremented based on whether the message is reported as spam or not spam.
	* If the word is not in the word bucket, a separate entry is added to the arrayList. 
	*/
	public void processWord(String word, Boolean spam){
		if (getWordIndex(word) == -1){
		 	arr.addWord(word);
		}else{
			if (spam){
				arr.incrementWordBad(word);
			}else{
				arr.incrementWordGood(word);
			}
		}

	}

	/*
	* This method increments the word's count of appearances in spam emails. 
	*/
	public void incrementWordGood(String word){

	}

	/*
	* This method increments the word's count of appearances in not spam emails. 
	*/
	public void incrementWordBad(String word){

	}

	/*
	* This method adds a separate entry into the arrayList for a new word. 
	*/
	public void addWord(String word){

	}

	/*
	* This method returns the probability that the messages is good, given that it 
	* contains the word.
	*/
	public double getProbabilityWordGood(String word){
		return arr.getWordGood(word)/(arr.getWordGood(word)+arr.getWordBad(word));

	}

	/*
	* This method returns the number of nonspam emails the word appears in.
	*/
	public double getWordGood(String word){

	}

	/*
	* This method returns the number of spam emails the word appears in.
	*/
	public double getWordBad(String word){

	}


	/*
	* This method finds the word in the ArrayList and returns the index of the word.
	* If the word is not found, the index is returned as -1.
	*/
	public int getWordIndex(String word){
		for (int i = 0; i<arr.size(); i++){
			String str = arr.get(i);
			List<String> elementArray = Arrays.asList(str.split("\\s*,\\s*"));
			if (elementArray[0].equals(word)){
				return i; 
			}
		}
		return -1;
	}

	public void sortArr(){
		Collections.sort(arr);
	}

}