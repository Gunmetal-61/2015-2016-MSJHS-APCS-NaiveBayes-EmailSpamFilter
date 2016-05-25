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
public class WordFileComposer {

    public static void convertFile() {
	String s = console.next();
	String word = null;
        BufferedReader br = new BufferedReader(new FileReader(s));
	PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(s)));
	StringTokenizer st = new StringTokenizer(br.read());
	while((word = br.readLine()) != null){
		word = removeCapitals(word);
		pw.println(word);
	}
	pw.close();

    }
    
    public static String removeCapitals(String word) {
        return word.toLowerCase();
    }
    
    
}
