/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesscontrol;

/**
 *
 * @author gladia2or
 */
        
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Subjects {
    Scanner input = new Scanner(System.in);
     String name;
     String password;
     String password1;
     String password2;
     int myLvl;
     int myOwner;
     



    ArrayList<Integer> rights = new ArrayList<Integer>(7);
    ArrayList<Integer> canTransfer = new ArrayList<Integer>(7);
    
    ArrayList<Object> myObjects = new ArrayList<Object>();
    
//    NEW code for owned subjects
    ArrayList<Subjects>mySub = new ArrayList<Subjects>(0);


    public Subjects(int lvl, String name, int id){
        this.name=name.toLowerCase();
        this.myOwner = id;
        this.myLvl = lvl;
        Rights myRights = new Rights(lvl);
        rights = myRights.rights;
        canTransfer = myRights.canTransfer;
        
        
//        System.out.println("\nSubject "+name+" with lvl " + lvl +" privilidges has been created"
//                + "\nAll passwords are being set to password for testing convenience");
        password = "password";
        
//        Un-comment this section for manual password creation
//        System.out.println("\nSubject "+name+" with lvl " + lvl +" privilidges has been created");
//        System.out.print("\nPlease enter a password: ");
//        password1 = input.next();
////        System.out.print("\npassword1 =  "+password1);
//        System.out.print("\nPlease confirm password: ");
//        password2 = input.next();
////        System.out.print("\npassword2 =  "+password2);
//        if(password1.equals(password2)){
////            System.out.print("\nSetting password to "+password1);
//            password = password1;
//        }
//        else{
//            System.out.print("\nPasswords don't match");
//        }
            
            
        
    }
    
   
    
    public int getRights(int x){
        
        return(rights.get(x));
//        return this.rights[x];
        
    }
    
    public int getPass(int x){
        
        return(canTransfer.get(x));
//        return this.rights[x];
        
    }
    
    public int grantRight(int x){
        rights.remove(x);
        rights.add(x, 1);
        
    return(0);
    };
    
     public int transferRight(int x){
        rights.remove(x);
        rights.add(x, 1);
        
    return(0);
    };
     
     public int removeRight(int x){
//         rights.remove(x);
         rights.add(x, 0);
         canTransfer.add(x, 0);
         return(0);
     }
    
    public String getName(){
        return(name);
    }
    
    public int printObjects(){
        for(Object obj : myObjects){
            System.out.print("\n"+obj.name);
        }
        System.out.print("\n------------------------------------");
        return(0);
        
    }
    
    public int checkPassword(String pass){
        
//        System.out.print("\n"+password + pass);
        if(pass.equals(password)){
//                    System.out.print("\nHi there");
                    return(0);

        }
        else{
            return(1);
            
        }
        
    }
    
    public int setPassword(){
        System.out.print("\nPlease enter a password: ");
        password1 = input.next();
        System.out.print("\npassword1 =  "+password1);
        System.out.print("\nPlease confirm password: ");
        password2 = input.next();
        System.out.print("\npassword2 =  "+password2);
        if(password1.equals(password2)){
            System.out.print("\nSetting password to "+password1);
            password = password1;
        }
        else{
            System.out.print("\nPasswords don't match");
        }
        return(0);
    }
    
        public int setAdminPassword(String pass){
            password = pass;
            return(0);

    }
        public int deleteSubDb(String name){
            for(int i = 0; i < myObjects.size();i++){
                Object obj = myObjects.get(i);
                String theName = obj.name;
                if(name.equals(theName)){
                    myObjects.remove(i);
                    break;
                }
            }
            return(0);
        }
        
        public int getLvl(){
            return(myLvl);
        }
    
}


