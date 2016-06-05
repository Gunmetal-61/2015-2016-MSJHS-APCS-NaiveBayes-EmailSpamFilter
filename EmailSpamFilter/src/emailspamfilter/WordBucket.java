package emailspamfilter;

//saving word bucket as arraylist of strings
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordBucket{
		
	public static List<String> arr = new ArrayList<>();

	public WordBucket(){
	//initiates blank arraylist
	}

	public static List<String> getArr() {
            return arr;
        }
        
        public static void readFileArr() {
	//read in arraylist from a file
            File f = new File(".\\src\\emailspamfilter\\internal\\wordbuckettext.txt");
            Scanner scanf;
            String test = "";
            try {
                scanf = new Scanner(f);
                int i = 0;
		while (scanf.hasNext()){
                        test = scanf.nextLine();
                        arr.add(test);
                        //arr.add(scanf.nextLine());
			i++;
		}
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	public static void writeArr() {
	//writes arraylist out 
        File f = new File(".\\src\\emailspamfilter\\internal\\wordbuckettext.txt");
        PrintWriter pWriter = null;
        BufferedWriter writer = null;
        try {
            pWriter = new PrintWriter(f);
            pWriter.print("");
            pWriter.close();
            writer = new BufferedWriter(new FileWriter(f));
            for (int i = 0; i<arr.size(); i++){
                writer.write(arr.get(i));
                writer.newLine();
        	}
            writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    writer.close();
                } catch (IOException ex) {
                    Logger.getLogger(WordBucket.class.getName()).log(Level.SEVERE, null, ex);
            }
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
			incrementWord(word, spam);
		}

	}

	/*
	* This method increments the word's count of appearances in spam or nonspam emails. 
	*/
	public static void incrementWord(String word, Boolean spam){
		int index = getWordIndex(word);
		String str = arr.get(index);
		List<String> elementArray = Arrays.asList(str.split("\\s+"));
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
		arr.add(newstr);
	}

	/*
	* This method returns the probability that the message is good, given that it 
	* contains the word.
	*/
	public static double getProbabilityWordGood(String word){
            if (getWordIndex(word) == -1){
                return 1; 
            }else if(getWordGood(word) == 0){
            	return (0.01)/(getWordGood(word)+getWordBad(word));
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
            }else if(getWordBad(word) == 0){
            	return (0.01)/(getWordGood(word)+getWordBad(word));
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
		List<String> elementArray = Arrays.asList(str.split("\\s+"));
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
		List<String> elementArray = Arrays.asList(str.split("\\s+"));
		int temp = Integer.parseInt(elementArray.get(2));
		return temp; 
	}

	/* 
	* This method allows the client to directly set the good and bad counts for a word. 
	*/
	public static void setWordCounts(String word, int good, int bad){
		String str = word + " " + good + " " + bad; 
		if (getWordIndex(word) == -1){
			arr.add(str);
		}else{
			int index = getWordIndex(word); 
			arr.set(index, str);
		}
	}

	/*
	* This method finds the word in the ArrayList and returns the index of the word.
	* If the word is not found, the index is returned as -1.
	*/
	public static int getWordIndex(String word){
		for (int i = 0; i<arr.size(); i++){
			String str = arr.get(i);
			List<String> elementArray = Arrays.asList(str.split("\\s+"));
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