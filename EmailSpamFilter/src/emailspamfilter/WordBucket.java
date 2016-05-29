package emailspamfilter;

//saving word bucket as arraylist of strings
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordBucket{
		
	public static ArrayList<String> arr;

	public WordBucket(){
	//initiates blank arraylist
	}

	public static void getArr() throws FileNotFoundException{
	//read in arraylist from a file
		File f = new File("wordbuckettext.txt");
		Scanner scanf = new Scanner(f);
		int i = 0;
		while (scanf.hasNext()){
			arr.set(i,scanf.next());
			i++;
		}
	}

	public static void writeArr(){
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
	public static void processWord(String word, Boolean spam){
		if (getWordIndex(word) == -1){
		 	addWord(word, spam);
		}else{
			incrementWord(word,spam);
		}

	}

	/*
	* This method increments the word's count of appearances in spam or nonspam emails. 
	*/
	public static void incrementWord(String word, Boolean spam){
		int index = getWordIndex(word);
		String str = arr.get(index);
		List<String> elementArray = Arrays.asList(str.split("\\s*,\\s*"));
		if (spam){
			int badNum = Integer.parseInt(elementArray.get(2));
			badNum ++;
			String newstr = elementArray.get(0) + " " + elementArray.get(1) + " " + badNum;
			arr.set(index, newstr);
		}else{
			int goodNum = Integer.parseInt(elementArray.get(1));
			goodNum ++;
			String newstr = elementArray.get(0) + " " + goodNum + " " + elementArray.get(2); 
			arr.set(index, newstr);
		}
	}

	/*
	* This method adds a separate entry into the arrayList for a new word. 
	*/
	public static void addWord(String word, Boolean spam){
		String newstr = "";
		if (spam){
			newstr += word + " 0 1";
		} else{
			newstr += word + " 1 0";
		}
		arr.set(arr.size(), newstr);
	}

	/*
	* This method returns the probability that the message is good, given that it 
	* contains the word.
	*/
	public static double getProbabilityWordGood(String word){
            if (getWordIndex(word) == -1){
                return 1; 
            }
            return getWordGood(word)/(getWordGood(word)+getWordBad(word));
	}

	/*
	* This method returns the probability that the message is bad, given that it 
	* contains the word.
	*/
	public static double getProbabilityWordBad(String word){
            if (getWordIndex(word) == -1){
                return 1; 
            }
            return getWordBad(word)/(getWordGood(word)+getWordBad(word));
	}

	/*
	* This method returns the number of nonspam emails the word appears in.
	*/
	public static double getWordGood(String word){
		int index = getWordIndex(word);
		if (index == -1){
			return 0; 
		}
		String str = arr.get(index);
		List<String> elementArray = Arrays.asList(str.split("\\s*,\\s*"));
		int temp = Integer.parseInt(elementArray.get(1));
		return temp; 
	}

	/*
	* This method returns the number of spam emails the word appears in.
	*/
	public static double getWordBad(String word){
		int index = getWordIndex(word);
		if (index == -1){
			return 0; 
		}
		String str = arr.get(index);
		List<String> elementArray = Arrays.asList(str.split("\\s*,\\s*"));
		int temp = Integer.parseInt(elementArray.get(2));
		return temp; 
	}


	/*
	* This method finds the word in the ArrayList and returns the index of the word.
	* If the word is not found, the index is returned as -1.
	*/
	public static int getWordIndex(String word){
		for (int i = 0; i<arr.size(); i++){
			String str = arr.get(i);
			List<String> elementArray = Arrays.asList(str.split("\\s*,\\s*"));
			if (elementArray.get(0).equals(word)){
				return i; 
			}
		}
		return -1;
	}

	public void sortArr(){
		Collections.sort(arr);
	}

}