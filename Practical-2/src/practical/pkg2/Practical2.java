/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical.pkg2;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Stack;
/**
 *
 * @author 40326915
 */
public class Practical2 {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here
        /***************
        2) Arrays
        ****************/
        /*Scanner keyboard = new Scanner(System.in);
        int[] studentGroup = new int[5];
        for(int i=0;i<5;i++){
            System.out.println("Enter matriculation of student number "+ (i+1));
            studentGroup[i]= keyboard.nextInt();
        }
        
        System.out.println("List of your group : Student matriculation numbers :");
        
        for(int i=0; i<5;i++){
            System.out.println(studentGroup[i]);
        }
        
        boolean addAnotherStudent = true;
        String answer = "";
        int[] studentGroupExtended;
        
        while(addAnotherStudent){
            System.out.println("Add another student ? Answer 'y' or 'n' ");
            answer = keyboard.next();
            studentGroupExtended = new int[(studentGroup.length+1)];
            System.arraycopy(studentGroup,0,studentGroupExtended,0,studentGroup.length);
            
            if(answer.equals("y")){
                System.out.println("Enter matriculation of student number "+ (studentGroup.length+1));                
                studentGroupExtended[(studentGroupExtended.length-1)]=keyboard.nextInt();
                studentGroup = studentGroupExtended;
            } else if(answer.equals("n")){
                addAnotherStudent = false;
            } else {
                System.out.println("Wrong answer Answer 'y' or 'n'");
            }
        }
        
        System.out.println("Completed list of your group : Student matriculation numbers :");
        
        for(int i=0; i<studentGroup.length;i++){
            System.out.println(studentGroup[i]);
        }*/
       
       /***************
        2.2) ArrayLists
        ****************/
       /*
        ArrayList listeDeCourse = new ArrayList();
        listeDeCourse.add("pain");
        listeDeCourse.add("beurre");
        listeDeCourse.add("oeuf");
        listeDeCourse.add("fruits");
        listeDeCourse.add("legumes");
        listeDeCourse.add("pain");
        
        System.out.println("Contents of your liste de courses: " + listeDeCourse);
        
        listeDeCourse.remove("pain");
        
        System.out.println("Contents of your liste de courses: " + listeDeCourse);
        
        listeDeCourse.removeAll(listeDeCourse);

        System.out.println("Contents of your liste de courses: " + listeDeCourse);
        */
        /***************
        2.3) Stacks
        ****************/
        /*
        Stack maPile = new Stack();
        String stringToInverse = "Grenoble";
        
        System.out.println("Initial string : "+stringToInverse);
        
        for(int i = 0;i< stringToInverse.length();i++){
            maPile.push(stringToInverse.charAt(i));
        }
        
        for(int i = 0;i<stringToInverse.length();i++){
            System.out.print(maPile.pop());
        }
        
        System.out.print("\n");
        */
        /***************
        2.4) Queues
        ****************/
        
    }
    
}
