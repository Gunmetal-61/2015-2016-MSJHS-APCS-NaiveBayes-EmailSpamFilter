/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emailspamfilter;


import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chris Wang, Mitchell Wu
 * 
 * IO-focused class that is designed to process words in a file so that they are
 * put into a palatable and standardized form for the WordBucket class to process.
 * Also calls the WordBucket method to add words into the WordBucket array.
 */
public class WordFileComposer {

    /**
     * Method designed to fulfill the function of option #2 in EmailSpamFilter.main.  
     * Adds the content of messages to the WordBucket array in terms of words 
     * and number of occurrences in spam and non-spam.  Then, it commands the
     * array to be written into a file at ./internal/wordbuckettext.txt to ensure
     * that the ground truth word index persists.
     * 
     * Commented-out segments generate a copy of the original inputted file that
     * has all punctuation removed, capitals replaced, and every word tokenized.  A secondary function
     * that is not necessary for any segment of the program.
     * 
     * @param file
     * @param messageType
     */
    public static void cleanMessagesForAnalysis(File file, String messageType) {
        String word = null;
        Scanner sc;
//        BufferedWriter bw;
//        File fileB = null;
//        if (messageType.equals("SPAM")) {
//            fileB = new File(".\\src\\emailspamfilter\\spammessages\\" + file.getName().replaceFirst("[.][^.]+$", "") + "B.txt");
//        } else if (messageType.equals("HAM")) {
//            fileB = new File(".\\src\\emailspamfilter\\hammessages\\" + file.getName().replaceFirst("[.][^.]+$", "") + "B.txt");
//        } else if (messageType.equals("TEST")) {
//            fileB = new File(".\\src\\emailspamfilter\\testmessages\\" + file.getName().replaceFirst("[.][^.]+$", "") + "B.txt");
//        }
        
        try {
            sc = new Scanner(file);
//            bw = new BufferedWriter(new FileWriter(fileB));
            while(sc.hasNext()){//For every word, remove capitals and punctuation.  Then call the WordBucket.processWord(String word, boolean spamOrNot) method for the WordBucket class to either add a new word to its array or "+1" its count of an already encountered word.
		    word = sc.next();
                    word = removeCapitals(word);
                    word = removePunctuation(word);
		    if(messageType != null){//New words should only be added to the array if we are doing operation2 (adding new ground truth messages).
	            	if (messageType.equals("SPAM")){
		    		WordBucket.processWord(word, true);
			}
		   	else if(messageType.equals("HAM")){
				WordBucket.processWord(word, false);
		   	}
		    }
//                    bw.write(word + " ");
            }
//	bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordFileComposer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (messageType.equals("SPAM")) {//Saves the WordBucket array (currently in RAM) to ./internal/wordbuckettext.txt for persistence between program runs.  New words should only be saved if we are doing operation2 (adding new ground truth messages).
            WordBucket.writeArr();
        } else if (messageType.equals("HAM")) {
            WordBucket.writeArr();
        } else if (messageType.equals("TEST")) {
            
        }
        
    }
    
    public static String removeCapitals(String word) {
        return word.toLowerCase();
    }
    
    public static String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z ]", "");
    }
    
}
