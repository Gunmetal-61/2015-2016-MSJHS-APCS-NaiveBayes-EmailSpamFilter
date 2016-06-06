/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package emailspamfilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mitchell Wu, Catherine Zeng
 * 
 * The core of the Naive Bayes system in the program that actually contains the
 * logic behind this implementation of the algorithm.  Appears short, but our
 * implementation is rather simple and a huge amount of the functionality falls
 * behind the IO system offered by the WordBucket class.
 */
public class NaiveBayesEngine {
    
    /**
     * Wrapper method for classifierInitial.  Meant to be THE method to call 
     * when one wants to see if a message is spam or not.
     * 
     * @param file
     */
    public static void runTest(File file) {//
        classifierInitial(file);
    }
    
    /**
     * The meat of the NaiveBayesEngine.  Is the method that actually does the
     * probability calculations to see if something is spam or not.
     * 
     * @param file
     */
    public static void classifierInitial(File file) {
        String currentQuery = "";
        double hamScore = 1;//Double precision floating point that holds the score for ham (non-spam messages)
        double spamScore = 1;//Double precision floating point that holds the score for spam messages.
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext() == true) {//For every word from the message that one tries to test...
                currentQuery = scanner.next();
                if (WordBucket.getWordIndex(currentQuery) != 1) {//if there is a match present in the WordBucket array (a -1, or false value is not returned)...
                    hamScore = hamScore * WordBucket.getProbabilityWordGood(currentQuery);//get the probability value for the word's frequency in ham messages and multiply with with the hamScore.
                    spamScore = spamScore * WordBucket.getProbabilityWordBad(currentQuery);//get the probability value for the word's frequency in spam messages and multiply with with the spamScore.
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NaiveBayesEngine.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        System.out.println();
        System.out.println("Ham Score Probability: " + hamScore);//Print out the raw number for hamScore.
        System.out.println("Spam Score Probability: " + spamScore);//Print out the raw number for hamScore.
        System.out.println();
        
        if (hamScore > spamScore) {//The final verdict on whether or not a message is spam will be determined by which score value is larger.
            System.out.println("Likely Not Spam");
        } else if (spamScore > hamScore) {
            System.out.println("Likely Spam");
        } else {
            System.out.println("Neutral");
        }
    }
}
