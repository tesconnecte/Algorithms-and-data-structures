
import java.util.Iterator;
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class Exercise3 {
     public static void main(String[] args) {
        LinkedList[] arrTest = new LinkedList[11];
        int[] dataToInsert = {6,2,13,5,17,39,1,12};
        //boolean insertionSucceeded = false;
        int indexInsertion;
        int nbCollision = 0;
        int nbInsertion = 0;

        for(int currentValue : dataToInsert){
            indexInsertion = (currentValue%arrTest.length);
            if(arrTest[indexInsertion]==null){
                arrTest[indexInsertion] = new LinkedList();
            }
            arrTest[indexInsertion].add(currentValue);
            
            /*while(!insertionSucceeded){
                if(arrTest[indexInsertion]==0){
                    arrTest[indexInsertion]=currentValue;
                    nbInsertion++;
                    insertionSucceeded=true;
                } else {
                    nbCollision++;
                    if((indexInsertion+1)<arrTest.length){
                        indexInsertion++;
                    } else {
                        System.err.println("Could not insert "+currentValue+" at index "+(currentValue%arrTest.length)+" or above");
                        insertionSucceeded=true;
                    }
                }
            }
            insertionSucceeded = false;*/
            
        }
       /* float collisionFrequency = ((float)nbCollision/(float)nbInsertion);
        System.out.println("--Results for "+nbElements+" elements--");
        System.out.println("Nb elements inserted "+ nbInsertion);
        System.out.println("Nb of collisions "+ nbCollision);
        System.out.println("Collision frequency "+ collisionFrequency);*/
       int index = 0;
       int indexLinkedList = 0;
       for(LinkedList currentList : arrTest){
          System.out.println("Index "+index+" LinkedList at this index contains :");
          if(currentList!=null){
                Iterator listIt = currentList.iterator();
            while(listIt.hasNext()){
                System.out.println("     Value "+ indexLinkedList+ " : "+ listIt.next());
                indexLinkedList++;
            }
          } else {
           System.out.println("     Nothing.");
       }          
          indexLinkedList=0;
          index++;
                
       }
        

     }
}
