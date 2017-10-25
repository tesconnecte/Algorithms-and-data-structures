
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
public class InsertionSort {
    public static void insertionSort ( int array [])
    {
     // Implement Your Insertionsort algorithm here
     // so that it sorts the input array
        int memory;
        int loopVar;
        for(int i=0;i<array.length;i++){
            memory = array[i];
            loopVar=i;
            while((loopVar>0)&&(array[(loopVar-1)]>memory)){
                array[loopVar]=array[(loopVar-1)];
                --loopVar;
            }
            array[loopVar]=memory;
        }
    
    }
    
    public static void main ( String [] args )
     {
    int [] data = { 4, 2, 9, 6, 23, 12, 34, 0, 1 };
    System . out . println (" Unsorted Array "+ Arrays . toString ( data ));
    insertionSort ( data );
    System . out . println (" Sorted Array "+ Arrays . toString ( data ));
    }
   
    
}
