/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw5;

/**
 *
 * @author v1
 */
public class AVLNode {
    int idNr;
    int height=1;
    String Name;
    String Surname;
    AVLNode left;
    AVLNode right;
    public AVLNode(int idNr, String name, String surname){
        this.idNr=idNr;
        this.Name=name;
        this.Surname=surname;
        left=null;
        right=null;
    }
    void setName(String n, String s){
        this.Name=n;
        this.Surname=s;
    }
}
