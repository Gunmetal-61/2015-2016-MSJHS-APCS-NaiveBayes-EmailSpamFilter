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


	public void processWord(String word, Boolean spam){
	//either adds or incremenets
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

	public void incrementWordGood(String word){

	}

	public void incrementWordBad(String word){

	}

	public void addWord(String word){

	}

	public double getProbabilityWordGood(String word){
		return arr.getWordGood(word)/(arr.getWordGood(word)+arr.getWordBad(word));

	}

	public double getWordGood(String word){

	}

	public double getWordBad(String word){

	}

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