package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1){
            System.out.println("Pass the filename to be read as an argument.");
            System.exit(-1);
        }

        File input = new File(args[0]);
        Scanner fileReader = null;

        try{
            fileReader = new Scanner(input).useDelimiter("[ ,!?.:-;]+");
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.  Check the file name and try again.");
            System.exit(-2);
        }

        // initializes hash table
        HashSeparateChaining hashTable = new HashSeparateChaining();
        // stores the words in the file, inside the has table
        while(fileReader.hasNext()){
            String words = fileReader.next();
            //counts the number of word in
            words = words.toLowerCase();
            hashTable.put(words, 1);
        }

        String word = "";


        while(word.equals("QQQ") != true){
            System.out.print("Insert search word: ");
            Scanner scan = new Scanner(System.in);
            word = scan.next();
            if (word.equals("QQQ")) {
                break;
            }
            word = word.toLowerCase();
            if (hashTable.get(word) != null) {
                System.out.println(word + " appears " + hashTable.get(word) + " times");
            }else{
                System.out.println(word + " appears " + 0 + " times");
            }
            //System.out.println(hashTable.toString());

        }


        // TODO: Build a hash table of frequencies from the given input file
        // and query the user for words in order to lookup their frequencies.

    }
}