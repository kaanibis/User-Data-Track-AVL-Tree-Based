/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author v1
 */
public class Hw5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        AVLTree myTree=new AVLTree();
        Scanner sc = new Scanner(System.in);
        System.out.println("What is the name of the txt file ?(Write down only the name of the file NOT file type like '.txt'. The file MUST be in the C:\\ directory !!!");
        String filename=sc.nextLine();
        myTree.processFile(filename); 
        int number=-1;

        while(number!=0){
            System.out.println("Enter the number which you want to find:");
            number=sc.nextInt();
            if(number==0){
                System.exit(0);
            }else{
                myTree.printSearchPath(number);
            }
        }
        
    }
    
}
