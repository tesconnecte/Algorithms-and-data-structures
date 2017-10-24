
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
public class SelectionSort {
    
 public static int [] selectionSort ( int [] arr ){
 // Implement Your Selectionsort algorithm here
 // so that it sorts the input array
 int min;
 int memory;
 for(int i=0;i<arr.length;i++){
     min=i;
     for(int j=i;j<arr.length;j++){
         if(arr[j]<arr[min]){
             min = j;
         }
     }
     memory = arr[i];
     arr[i]=arr[min];
     arr[min]=memory;
 }

 return arr ;
 }

 public static void main ( String [] args ){

 int [] unsorted = {10 ,34 ,2 ,56 ,7 ,67 ,88 ,42};
 System . out . println (" Unsorted Array "+ Arrays . toString ( unsorted ));

 int [] sorted = selectionSort ( unsorted ) ;
 System . out . println (" Sorted Array "+ Arrays . toString ( sorted ));
 }

    
}
