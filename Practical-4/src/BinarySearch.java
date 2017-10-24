/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class BinarySearch {
    public static int binarySearch (int [] data , int key, int min, int max )
 {
// Implement Your Binary Search algorithm here
 // Return -1 if the key isn ’t found in the data
 // otherwise return the array index where the key was found
     int index = ((max-min)/2);
     System.out.println(max);
     
     if(data[index]==key){
         return index;
     } else if(min==max){
         return -1;
     }else if(data[index]>key){
         return binarySearch(data,key,min,index);
     } else if(data[index]<key){          
         return binarySearch(data,key,index,max);
     } else {
         return -1;
     }
     
 }

 public static void main ( String [] args )
 {
    int [] data = {3,12,18,34,54,65,76,122};
    int key = 1558;
    int result = binarySearch ( data , key,0,data.length );
    if ( result == -1)
    System . out . println (" Key not found in array ");
    else
    System . out . println (" Key "+ key +" found at index : "+ result );
 }

}
