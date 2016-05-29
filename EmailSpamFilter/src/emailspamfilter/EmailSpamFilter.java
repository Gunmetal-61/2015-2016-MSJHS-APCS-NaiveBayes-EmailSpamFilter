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
    
    private static Scanner scanner = new Scanner(System.in);
    private static int operation2PromptToggle = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        openingSequencePrompt();
        operationSelector();
    }
    
    /**
     * Method that simply prints name of the program and program description.
     */
    public static void openingSequencePrompt() {
        System.out.println("EmailSpamFilter.java");//name of program printed first
        System.out.println("APCS semester 2 project. This project implements a self-written version of the\n" +//description of program functions
                "Naive Bayes classifier to rudimentarily determine if an email message is spam \n" +
                "or not spam through single word probability.");
    }
    
    /**
     * Method that contains the console for choosing which of two principal operations
     * to do: test a message for spam based on pre-classified data, or add more
     * pre-classified messages to the ground truth data.
     */
    public static void operationSelector() {
        System.out.println();
        System.out.println("Choose an Operation Option:");//two functions
        System.out.println("\t1) Test a message for spam.\n"//#1 is the main feature; to actually test a message for spam.
                + "\t2) Add a message to the training set.");//#2 is a secondary, but very important feature.  Allows us to upload ground truth "spam" or "ham" messages into the system.
        int operationSelector = scanner.nextInt();
        scanner.nextLine();
        
        if (operationSelector == 1) {
            operation1();
        } else if (operationSelector == 2) {
            operation2();
        } else {
            System.out.println("Invalid input.  Please answer with \"1\" or \"2\"");
            operationSelector();
        }
    }
    
    /**
     * Selected operation in operationSelector that adds more pre-classified 
     * messages to the ground truth data.
     */
    public static void operation1() {
        System.out.print("Please specify the filepath of the txt file message: ");
	File testFile = new File(scanner.nextLine());
	WordFileComposer.cleanMessagesForAnalysis(testFile, "TEST");
        NaiveBayesEngine.runTest(testFile);
    }
    
    /**
     * Selected operation in operationSelector that tests a message for spam.
     * Also contains a console selection of its own that allows the user to determine
     * 
     */
    public static void operation2() {
        File addedMessageFile = null;
        if (operation2PromptToggle == 0) {
            System.out.print("Please specify the filepath of the txt file message.  A copy will be created in the system's own directories.");
            addedMessageFile = new File(scanner.nextLine());
            operation2PromptToggle = 1;
        }
    
        System.out.println("Is this message:");//two functions
        System.out.println("\t1) Spam?\n"
                + "\t2) Not Spam?");
        int operationSelector = scanner.nextInt();
        if (operationSelector == 1) {
            WordFileComposer.cleanMessagesForAnalysis(addedMessageFile, "SPAM");
            //add wordBucket word analysis and registering here.
        } else if (operationSelector == 2) {
            WordFileComposer.cleanMessagesForAnalysis(addedMessageFile, "HAM");
            //add wordBucket word analysis and registering here.
        } else {
            System.out.println("Invalid input.  Please answer with \"1\" or \"2\"");
            operation2();
        }
    }
}
