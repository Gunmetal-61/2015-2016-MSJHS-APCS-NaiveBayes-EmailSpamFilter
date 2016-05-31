/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emailspamfilter;

/**
 *
 * @author Chris
 */
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
public class WordFileComposer {

    /**
     * Method designed to fulfill the function of option #2 in EmailSpamFilter.main.  
     * Will simultaneously add a selected message in a txt file as ground truth 
     * and classify its contents.        
     * 
     * @param file
     * @param messageType
     */
    public static void cleanMessagesForAnalysis(File file, String messageType) {//
	
        
        
        String word = null;
        Scanner sc;
        BufferedWriter bw;
        File fileB = new File(file.getName().replaceFirst("[.][^.]+$", "") + "B.txt");
        try {
            sc = new Scanner(file);
            bw = new BufferedWriter(new FileWriter(fileB));
            while(sc.hasNext()){
		    word = sc.next();
                    word = removeCapitals(word);
                    word = removePunctuation(word);
		    if(messageType != null){
	            	if (messageType.equals("SPAM")){
		    		WordBucket.processWord(word, true);
			}
		   	else if(messageType.equals("HAM")){
				WordBucket.processWord(word, false);
		   	}
		    }
                    bw.write(word + " ");
            }
	bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(WordFileComposer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WordFileComposer.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    
    public static String removeCapitals(String word) {
        return word.toLowerCase();
    }
    
    public static String removePunctuation(String word) {
        return word.replaceAll("[^a-zA-Z ]", "");
    }
    
	
	
}
