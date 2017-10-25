/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class LinearSearch {
    public static int linearSearch ( int [] arr , int key ){
 // Implement Your Linear Search algorithm here
 // Return -1 if the key isn ’t found in the data
 // otherwise return the array index where the key was found
     int index = -1;
     for(int i=0; i<arr.length;i++){
         if(arr[i]==key){
             index=i;
             break;
         }
     }
    return index;

}

 public static void main ( String [] args ){
     int myArrayLength = 100000000;
       int[] data = new int[myArrayLength];
       int randomInt; 
        for(int i=0;i<myArrayLength;i++){
            randomInt=(int)(Math.random() * 100000000 + 1);   
            data[i]=randomInt;         
           
        }
        int key = (int)(Math.random() * 100000000 + 1);
        long startTime = System.nanoTime();
        int result = linearSearch ( data , key );
        long endTime = System.nanoTime();
        if ( result == -1)
        System . out . println (" Key not found in array ");
        else
        System . out . println (" Key "+ key +" found at index : "+ result );
        
        System.out.println("Execution time "+(endTime-startTime));
 }
    
    
}
