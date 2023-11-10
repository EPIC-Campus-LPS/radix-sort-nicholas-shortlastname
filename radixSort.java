import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;

/**
 * Sort program using HashMaps to sort words alphabetically, based on a letter in ascending index positions
 *
 * @author Nicholas Un
 * @version 1.0, 11/3/23
 */
public class radixSort {
    private String alphabet = " abcdefghijklmnopqrstuvwxyz";
    private String fileOutput = "";
    private HashMap<Character, Integer> alphabetized = new HashMap<Character, Integer>();

    /**
     * Main method
     * Creates a Hashmap where each letter in the alphabet is assigned its numerical position in the alphabet
     */
    public radixSort() {
        this.alphabet = alphabet;
        this.fileOutput = fileOutput;
        for(int i = 0; i < alphabet.length(); i++){
            alphabetized.put(alphabet.charAt(i), i);
        }
        this.alphabetized = alphabetized;
    }

    /**
     * Sorts intake file alphabetically using a HashMap of LinkedLists
     *
     * @param fileName name of file taken in
     */
    public void sort(String fileName) {
        String words = readFile(fileName);
        String[] stringWordList = words.split("  ");
        //find longest word length
        int longest = 0;
        for (String word : stringWordList) {
            if (word.length() > longest) {
                longest = word.length();
            }
        }

        // make all words the same length as the longest by adding spaces to the end
        for (int i = 0; i < stringWordList.length; i++) {
            while (stringWordList[i].length() < longest) {
                stringWordList[i] = stringWordList[i] + " ";
            }
        }

        //set up a hash map where every letter goes to a linked list of their words
        HashMap<Character, LinkedList<String>> wordHashMap = new HashMap<Character, LinkedList<String>>();
        String[] tempStringWordList;
        for(int i = 0; i < alphabet.length(); i++){
            wordHashMap.put(alphabet.charAt(i), new LinkedList<String>());
        }

        // start sorting
        LinkedList tempList;
        int counter;
        for (int i = longest - 1; i >= 0; i--){ // for each of the letters
            tempStringWordList = new String[stringWordList.length];

            counter = 0;
            for(String word : stringWordList){ // for each word in the temporary list of word strings

                wordHashMap.get((word.toLowerCase()).charAt(i)).add(word); // get char at index i of word and add the word to the linkedlist at hashmap key char
            }


            for(int j = 0; j < alphabet.length(); j++){ // for each of the characters in the alphabet
                tempList = wordHashMap.get(alphabet.charAt(j)); // get the list at hashmap key char, where char is the character at index j in alphabet, and assign it to a temporary list variable
                while(!tempList.isEmpty()){ // empty the hashmap lists into the temporary word list
                    tempStringWordList[counter] = (String) tempList.remove();
                    counter++;
                }
            }
            //overwrite the main word list with the temporary one so the words are in sorted order
            stringWordList = tempStringWordList;
        }

        for(int i = 0; i < stringWordList.length; i++){
            stringWordList[i] = stringWordList[i].replaceAll(" ", "");
        }

        makeKeyFile(fileName, stringWordList);
    }

    /**
     * Reads a file and returns its contents
     *
     * @param fileName name of file taken in
     * @return String of file's contents
     */
    private String readFile(String fileName) {
        String words = "";
        try {
            Scanner file = new Scanner(new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\radix-sort-nicholas-shortlastname\\TextFiles\\" + fileName));
            while (file.hasNext()) {
                words += file.nextLine() + " ";
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return words;
    }

    /**
     * Creates a new file, with name [fileName]_key.txt
     * Fills the new file with the sorted file contents
     *
     * @param fileName name of file taken in
     * @param list list of sorted file contents
     */
    private void makeKeyFile(String fileName, String[] list) {
        String[] splitFileName = fileName.split("\\.");
        File keyFile = new File("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\radix-sort-nicholas-shortlastname\\TextFiles\\" + splitFileName[0] + "_key.txt");
        if (keyFile.delete()) {
            System.out.println("Deleted the file: " + keyFile.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\nicholasu750_lpsk12\\IdeaProjects\\radix-sort-nicholas-shortlastname\\TextFiles\\" + splitFileName[0] + "_key.txt");
            for(String word : list) {
                myWriter.write(word + "\n\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file: " + keyFile.getName());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
