
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


public class QuickSort {
        public static void switchValue(int[] arr, int a, int b){
        int temp = arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
    
    public static void quickSort ( int array [] , int lowerIndex , int higherIndex )
{
 // Implement Your Selectionsort algorithm here
// so that it sorts the input array
// Hint : You will probably want to call the quickSort method recursively
    int left = lowerIndex-1;
    int right = higherIndex+1;
    int pivot = array[lowerIndex];
    
    if(lowerIndex>=higherIndex){
        return;
    }
    
    while(true){
        while(array[right]>pivot){
            --right;
        }
        
        while(array[left]<pivot){
            left++; 
        }
        
        if(left<right){
            switchValue(array,left,right);
        } else {
            break;
        }
        
        quickSort(array, lowerIndex, right);
        quickSort(array, (right+1), higherIndex);
        
    }

 }



 public static void main ( String a [])
{

 int [] input = {24 ,2 ,45 ,20 ,56 ,75 ,2 ,56 ,99 ,53 ,12};
System . out . println (" Unsorted Array "+ Arrays . toString ( input )) ;
 int length = input . length ;
 quickSort ( input , 0, length - 1) ;
System . out . println (" Sorted Array "+ Arrays . toString ( input ));
 }

    
}
