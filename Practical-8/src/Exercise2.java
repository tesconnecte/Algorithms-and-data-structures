
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class Exercise2 {
    
    public static String openFileToString(){
        String FILENAME = "H:\\Algorithms-and-data-structures\\Practical-8\\wordFile.txt";
        String textFile = "";
        
        BufferedReader br = null;
	FileReader fr = null;
        
        try {

			//br = new BufferedReader(new FileReader(FILENAME));
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
        WordOccurence newWord;
        String currentWord = "";
        String textFile = openFileToString();
        HashMap<String,Integer> wordOccurence = new HashMap<String,Integer>();
        
        for (char ch : textFile.toCharArray()){
            if(ch==' '){
                if(wordOccurence.keySet().contains(currentWord)){
                    int occurence = (wordOccurence.get(currentWord)+1);
                    wordOccurence.remove(currentWord);
                    wordOccurence.put(currentWord, occurence);
                } else{
                    wordOccurence.put(currentWord, 1);
                }
                currentWord="";
            } else {
                currentWord+=ch;
            }
        }
        
        Set<String> keySet = wordOccurence.keySet();
        Iterator<String> it = keySet.iterator();
        String currString;
        
        while(it.hasNext()){
            currString=it.next();
            System.out.println(currString+" nb occurence : "+ wordOccurence.get(currString));
        }
        
    }
}
