
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class BubbleSort {
    public static void bubbleSort ( int array [])
 {
     int memory;
     for(int i=0;i<(array.length-1);i++){
         for(int j=0;j<(array.length-1);j++){
             if(array[(j+1)]<array[j]){
                 memory = array[j];
                 array[j]=array[(j+1)];
                 array[(j+1)]=memory;
             }
         }
     }

 }

 public static void main ( String [] args ) {
 int [] data = { 4, 37, 9, 6, 23, 55, 34, 0, 1 };
 System.out.println("Unsorted Array "+ Arrays.toString(data));
 bubbleSort (data);
 System.out.println("Sorted Array "+ Arrays.toString(data));
 }
}
