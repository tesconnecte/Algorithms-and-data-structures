package practical.pkg3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
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
public class TestHashMap {
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard2 = new Scanner(System.in);
        HashMap<Integer,String> matricStudent = new HashMap<Integer,String>();
        int matriculationNumber;
        String studentName;
        
        for(int i=0;i<5;i++){
            System.out.println("Enter matriculation of student number "+ (i+1));
            matriculationNumber = keyboard.nextInt();
            System.out.println("Enter student "+matriculationNumber+" name : ");
            studentName = keyboard2.next();
            
           System.out.println("Student "+matriculationNumber+" "+studentName);
            matricStudent.put(matriculationNumber, studentName);
        }
        
       Set<Integer> keySets = matricStudent.keySet();
       Iterator iter = keySets.iterator();
       System.out.println("Your student list contains");
       int currentStudent;
       while(iter.hasNext()){
           currentStudent = Integer.parseInt(iter.next().toString());
           System.out.println(currentStudent+" "+matricStudent.get(currentStudent));
       }
       
       
      
        
        
    }
}
