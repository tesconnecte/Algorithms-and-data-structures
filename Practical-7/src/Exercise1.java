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
    public static void main(String[] args) {
        int[] arrTest = new int[11];
        int[] dataToInsert = {6,2,13,5,17,39,1,12};
        boolean insertionSucceeded = false;
        int indexInsertion;

        for(int currentValue : dataToInsert){
            indexInsertion = (currentValue%arrTest.length);
            while(!insertionSucceeded){
                if(arrTest[indexInsertion]==0){
                    arrTest[indexInsertion]=currentValue;
                    insertionSucceeded=true;
                } else {
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
        System.out.println("Array Insertion result");
        int index = 0;
        for(int currentInt : arrTest){
            System.out.println("Index "+index+" value : "+currentInt);
            index++;            
        }
    }
}
