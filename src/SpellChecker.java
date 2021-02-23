/**
 * Created for WFU CSC201 Lab1
 * This is a program that check word spelling of words in one text file
 * according to correct words in the dictionary file
 * Usage: java SpellChecker Text.txt Dictionary.txt
 * @Author: Clare (Qiyue Zhang)
 */

import java.io.*;
import java.util.*;

public class SpellChecker {

    /**
     * Main method that drives the program
     */
    public static void main(String[] args) {


        try {
            //Check to make sure input commands contains 2 argument to specify files
            if (!argsOk(args)){ System.exit(1); }

            //Notify which file is being checked from which exact dictionary
            System.out.println("Spell Check" + args[0]+"Using dictionary"+args[1]);

            DLinkedList text = new DLinkedList();          //stores words from text
            DLinkedList dictionary = new DLinkedList();    //stores words from dictionary
            DLinkedList wrongWords = new DLinkedList();    //stores words in the text that aren't in dictionary

            //Generate a scanner to read from dictionary,
            Scanner DicSc = new Scanner(new File(args[1]));
            //and save correct words in the dictionary(linked list)
            while (DicSc.hasNext()){
                dictionary.addLast(DicSc.nextLine());
            }

            //Generate a scanner to read from text file,
            Scanner textSc = new Scanner(new File(args[0]));

            int repeat = 0; //keep tract of repeated time for words
            //and save the parsed words in the text(linked list)
            while (textSc.hasNext()){
                String word = textSc.next();

                //Parse the string from text with punctuation deliminators
                String delims = "[ .,?!/:]+";
                String[] tokens = word.split(delims);

                //Count the unique words in text
                for (String each: tokens){
                    //if the word appeared before, save the repetition count
                    if (text.contains(each.toLowerCase())){
                        repeat ++;
                    }
                    //Save the lower-cased version of parsed words to linked list text
                    text.addLast(each.toLowerCase());
                }
            }

            //Using the dictionary to check the spelling
            for (String data : text) {
                //only save each unique wrong word once
                if (!dictionary.contains(data) && !wrongWords.contains(data)){
                    wrongWords.addFirst(data);
                }
            }

            //Print the output message
            System.out.print("words: "+text.size()+", ");
            System.out.print("unique words: " + (text.size() - repeat) + ", ");
            System.out.println("wrong words: " + wrongWords.size());
            System.out.println("misspelled words");
            System.out.println("----------------");

            //Print the wrong words we found
            for (String data: wrongWords) {
                System.out.println(data);
            }
        }

        catch (FileNotFoundException exception){
            System.out.println("Error in opening file. ");
            exception.printStackTrace();
            System.exit(1);
        }
        catch (NullPointerException exception){
            System.out.println("The linked list is empty");
        }
    }

    /**
     * This method returns true if the command line argument is acceptable
     * args should have 2 files names
     * method returns true for acceptable arguments, false otherwise
     */

    private static boolean argsOk(String[] args){
        if (args.length != 2) {
            System.out.println("Usage: java program dictionaryFile fileToCheck");
            return false;
        }
        return true;
    }
}
