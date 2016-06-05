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
 * @author Mitchell
 */
public class NaiveBayesEngine {
    
    public static void runTest(File file) {//wrapper method for classifierInitial.  Meant to be THE method to call when one wants to see if a message is spam or not
        classifierInitial(file);
    }
    
    public static void classifierInitial(File file) {
        String currentQuery = "";
        double matchWordIndex = 0;
        double positiveScore = 1;
        double negativeScore = 1;
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext() == true) {
                currentQuery = scanner.next();
                if (WordBucket.getWordIndex(currentQuery) != 1) {
                    positiveScore = positiveScore * WordBucket.getProbabilityWordGood(currentQuery);
                    negativeScore = negativeScore * WordBucket.getProbabilityWordBad(currentQuery);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NaiveBayesEngine.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        System.out.println();
        System.out.println("Positive Score Probability: " + positiveScore);
        System.out.println("Negative Score Probability: " + negativeScore);
        System.out.println();
        
        if (positiveScore > negativeScore) {
            System.out.println("Likely Not Spam");
        } else if (negativeScore > positiveScore) {
            System.out.println("Likely Spam");
        } else {
            System.out.println("Neutral");
        }
    }
}
