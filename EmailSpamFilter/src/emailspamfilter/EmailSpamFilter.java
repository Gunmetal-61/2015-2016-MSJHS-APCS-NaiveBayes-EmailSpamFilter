/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emailspamfilter;

import java.io.File;
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
        System.out.println();
        System.out.println("Choose an Operation Option:");
        System.out.println("\t1) Test a message for spam.\n"
                + "\t2) Add a message to the training set.");
        int operationSelector = scanner.nextInt();
        scanner.nextLine();
        if (operationSelector == 1) {
            System.out.print("Please specify the filepath: ");

        } else if (operationSelector == 2) {
            System.out.print("Please specify all the URL components with spaces in between: ");

        } else {
            System.out.println("Invalid input.  Please answer with \"1\" or \"2\"");
        }
    }
    
    public static void consoleInitial() {
        
    }
    
}
