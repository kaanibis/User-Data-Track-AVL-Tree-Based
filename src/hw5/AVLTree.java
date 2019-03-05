/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Stack;





/**
 *
 * @author v1
 */

public class AVLTree {
    AVLTree(){
        root=null;
    }
    Stack<String> stack = new Stack<>();
    
    
    public void insertStudent(int idNr, String name, String surname){
       // AVLNode node=new AVLNode(idNr,name,surname);
        root = insertStudent(idNr,name,surname,root);
        
    }
    public AVLNode searchStudent(int idNr){
        return searchStudent(idNr,root);
    }
    public void deleteStudent(int idNr){
        root=deleteStudent(idNr,root);
    }
    public void printSearchPath(int idNr){
        searchStudent(idNr);
        System.out.println(stack);
        stack.clear();
    }
    public void processFile(String filename) throws FileNotFoundException{
        String path="C:\\"+filename+".txt";
        File file = new File(path);
        Scanner sc=new Scanner(new FileReader(file));
        int addCount,delCount=0;
        int id;
        String name,surname;
        addCount=sc.nextInt();
        delCount=sc.nextInt();
        for(int i=0;i<addCount;i++){
            id=sc.nextInt();
            name=sc.next();
            surname=sc.next();
            insertStudent(id,name,surname);
        }
        
        for(int j=0;j<delCount;j++){
            int del=sc.nextInt();
            deleteStudent(del);
        }
        
    }
    AVLNode root;
    private int height(AVLNode node){
        if(node==null){
            return 0;
        }
        return node.height;
    }
    private AVLNode insertStudent(int idNr, String name, String surname, AVLNode node) {
        if(node==null){
            return(new AVLNode(idNr,name,surname));
        }
        if(idNr<node.idNr){
            node.left=insertStudent(idNr, name, surname, node.left);
        }else if(idNr>node.idNr){
            node.right=insertStudent(idNr, name, surname, node.right);
        }else{
            //For avoiding duplicates!!!
        }
        node.height=Math.max(height(node.left), height(node.right))+1;
        
        int balance=getBalance(node);
        if(balance>1 && idNr<node.left.idNr){
            return rightRotate(node);
        }
        if(balance<-1 && idNr>node.right.idNr){
            return leftRotate(node);
        }
        if(balance>1 && idNr>node.left.idNr){
            node.left=leftRotate(node.left);
            return rightRotate(node);
        }
        if(balance<-1 && idNr<node.right.idNr){
            node.right=rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    private int getBalance(AVLNode node) {
        if (node== null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }

    private AVLNode rightRotate(AVLNode node) {
        AVLNode x = node.left;
        AVLNode f = x.right;

      
        x.right = node;
        node.left = f;  
        node.height = Math.max(height(node.left), height(node.right))+1;
        x.height = Math.max(height(x.left), height(x.right))+1; 
        return x;
    }

    private AVLNode leftRotate(AVLNode node) {
        AVLNode y = node.right;
        AVLNode f = y.left;

        y.left = node;
        node.right = f;
        node.height = Math.max(height(node.left), height(node.right))+1;
        y.height = Math.max(height(y.left), height(y.right))+1;
        return y;
    }

    private AVLNode deleteStudent(int idNr, AVLNode root) {
        if(root==null){
            return root;
        }
        if(idNr<root.idNr){
            root.left=deleteStudent(idNr,root.left);
        }else if(idNr>root.idNr){
            root.right=deleteStudent(idNr,root.right);
        }else{
            if((root.left==null) || (root.right==null)){
                AVLNode temp;
                if(root.left!=null){
                    temp=root.left;
                }else{
                    temp=root.right;
                }
                if(temp==null){
                    temp=root;
                    root=null;
                }else{
                    root=temp;
                }
                temp=null;
            }else{
                //successor
                AVLNode temp = minIdNode(root.right);
                root.idNr=temp.idNr;
                root.Name=temp.Name;
                root.Surname=temp.Surname;
                root.right=deleteStudent(temp.idNr, root.right);
            }
        }
        if(root==null){
            return root;
        }
        
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);
        if (balance > 1 && getBalance(root.left) >= 0){
            return rightRotate(root);
        }
        
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left =  leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }
        return root;
    }

    private AVLNode minIdNode(AVLNode node) {
        AVLNode c=node;
        while(c.left!=null){
            c=c.left;
        }
        return c;
    }

    private AVLNode searchStudent(int idNr, AVLNode root) {
        boolean found=false;
        while(root!=null && !found){
            int rId=root.idNr;
            if(idNr<rId){
                stack.add(rId+" (Left)");
                root=root.left;
            }else if(idNr>rId){
                stack.add(rId+" (Right)");
                root=root.right;
            }else{
                stack.add(rId+" (Found)");
                found = true;
                break;
            }
            
        }
        if(found==true){
            return root;
        }else{
            stack.clear();
            stack.add("The number: "+idNr+" is not found!");
            return null;
        }
    }

    }
    

