
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */


public class Exercise1 {
    
    public static String openFileToString(){
        String FILENAME = "H:\\Algorithms-and-data-structures\\Practical-8\\wordFile.txt";
        String textFile = "";
        
        BufferedReader br = null;
	FileReader fr = null;
        
        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                    textFile+=sCurrentLine;
            }

	} catch (IOException e) {
            e.printStackTrace();
	} finally {
            try {
                if (br != null)
                    br.close();
                    if (fr != null)
                        fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        
        return textFile; 

        
    }
    
    public static ArrayList<WordOccurence> addEntry(ArrayList<WordOccurence> wordDictionnary,String newEntry){
        boolean alreadyExists = false;
        for(WordOccurence currentWordOccurence : wordDictionnary){
            if(currentWordOccurence.getWord().equals(newEntry)){
                currentWordOccurence.setOccurence((currentWordOccurence.getOccurence()+1));
                alreadyExists = true;
            }
        }
        
        if(alreadyExists==false){
            WordOccurence newWord = new WordOccurence(newEntry);
            wordDictionnary.add(newWord);
        }        
        return wordDictionnary;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        WordOccurence newWord;
        String currentWord = "";
        String textFile = openFileToString();
        ArrayList<WordOccurence> wordOccurence = new ArrayList<WordOccurence>();
        
        for (char ch : textFile.toCharArray()){
            if(ch==' '){
                wordOccurence = addEntry(wordOccurence,currentWord);
                currentWord="";
            } else {
                currentWord+=ch;
            }
        }
        
        for(WordOccurence word : wordOccurence){
            System.out.println(word.getWord()+" : occurence : " + word.getOccurence());
        }
    }
}
