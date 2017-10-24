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
    
    public static int[] getRandomArray(int arrLength){
        int[]arrRandom = new int[arrLength];
        for(int i=0;i<arrLength;i++){
            arrRandom[i]=(1000+ ((int)(Math.random() * 8999)));
        }
        return arrRandom;
    }
     public static void main(String[] args) {
        int nbElements = 1000;
        int[] arrTest = new int[1000];
        int[] dataToInsert = getRandomArray(nbElements);
        boolean insertionSucceeded = false;
        int indexInsertion;
        int nbCollision = 0;
        int nbInsertion = 0;

        for(int currentValue : dataToInsert){
            indexInsertion = (currentValue%arrTest.length);
            while(!insertionSucceeded){
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
            insertionSucceeded = false;
            
        }
        float collisionFrequency = ((float)nbCollision/(float)nbInsertion);
        System.out.println("--Results for "+nbElements+" elements--");
        System.out.println("Nb elements inserted "+ nbInsertion);
        System.out.println("Nb of collisions "+ nbCollision);
        System.out.println("Collision frequency "+ collisionFrequency);
        

     }
}
