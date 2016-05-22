/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailspamfilter;

import java.util.Scanner;

/**
 *
 * @author Mitchell
 * APCS semester 2 project. This project implements a self-written version of the
 * Naive Bayes classifier to rudimentarily determine if an email message is spam 
 * or not spam through single word probability.
 * 
 * The EmailSpamFilter class is the "main" class which acts as the control hub for
 * the call and implementation of other class files through the console.
 */
public class EmailSpamFilter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("EmailSpamFilter.java");
        System.out.println("APCS semester 2 project. This project implements a self-written version of the\n" +
                "Naive Bayes classifier to rudimentarily determine if an email message is spam \n" +
                "or not spam through single word probability.");
    }
    
    public static void consoleInitial() {
        
    }
    
}
