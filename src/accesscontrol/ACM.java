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
import java.util.ArrayList;
import java.util.Scanner;

public class ACM {
    
    Scanner in = new Scanner(System.in);
    
    ArrayList<Object> objects = new ArrayList<Object>(0);
//    ArrayList<> objects1 = new ArrayList<>(0);
    ArrayList<Subjects> subjects = new ArrayList<Subjects>(0);
    ArrayList<Database> myDatabases = new ArrayList<Database>(0);
    ArrayList<String> rightNames = new ArrayList<String>(0);
//    ArrayList<Integer> rights = new ArrayList<Integer>(0);

//    ArrayList<ArrayList<Integer> > subjects = new ArrayList<ArrayList<Integer> >(0);

    Scanner input = new Scanner(System.in);


public int transfer(int me){
//    System.out.print("Are you transfering a right or an object?\nEnter 0 for right enter 1 to transfer database ownership: ");
//    int rightOrDatabase = in.nextInt();
        int toRemove = grant(me,true); 
        subjects.get(me).rights.add(toRemove, 0);

    
    return(0);
    }



    public int initializeAdmin(){
        rightNames.add("Create");
        rightNames.add("Drop");
        rightNames.add("Select");
        rightNames.add("Insert");
        rightNames.add("Delete");
        rightNames.add("Commit");
        rightNames.add("RollB");
        rightNames.add("Grant");
        rightNames.add("Revoke");
        Subjects admin = new Subjects(3,"admin",0);
        subjects.add(admin);
        
        admin = new Subjects(2,"sec",0);
        subjects.add(admin);
        
        admin = new Subjects(1,"reg",0);
        subjects.add(admin);
        return(0);
    }
    public int grant(int me,boolean transfer){
        
        int whichRight = -1;
        
        
        int lvl = subjects.get(me).myLvl;
        if(lvl == 2 || lvl == 3){
            int which;
            if(transfer == false){
                System.out.print("\nEnter 0 to grant a right\nEnter 1 to grant access to a database: ");
                which = in.nextInt();
            }
            else{
                System.out.print("\nEnter 0 to transfer a right\nEnter 1 to transfer ownership of a database: ");
                which = in.nextInt();
            }
            
                    switch(which){
                    case(0):
                        int trans;
                        printSub();
                        System.out.print("\nEnter ID of user to receive right: ");
                        int toWhom = in.nextInt();
                        System.out.print("\nPlease enter the right to grant(0-8): ");
                        int right = in.nextInt();
                        whichRight = right;
                        int grant1 = subjects.get(me).getRights(right);
                        int pass = subjects.get(me).getPass(right);
                        if(transfer == true){
                            trans = 0;
                            
                        }
                        else{
                            System.out.print("\nAre you granting with the right to transfer? 0 = yes : 1 = no  ");
                            trans = in.nextInt();
                        }
                        
                        switch(trans){
                            case(0):
                                if (grant1 == 1 && pass ==1 ){
                                    subjects.get(toWhom).grantRight(right);
                                    subjects.get(toWhom).canTransfer.set(right, 1);
                                }
                                else{
                                    System.out.print("\nInsufficient Permissions");
                                }

                                break;
                            case(1):
                                 if (grant1 == 1 && pass ==1 ){
                                    subjects.get(toWhom).grantRight(right);
                                    subjects.get(toWhom).canTransfer.set(right, 0);
                                }
                                 else{
                                    System.out.print("\nInsufficient Permissions");
                                }
                                break;
                        }



                        break;
                    case(1):
                        subjects.get(me).printObjects();
                        System.out.print("\nPlease enter name of database to which you are wanting to grant access: ");
                        String whichDB = in.next();
                        int theDB = findDB(whichDB);
//                        int theDB = objects.indexOf(whichDB);
//                        boolean does = objects.contains(theDB);
                        System.out.print("\nIndex of database to transfer is "+theDB+"\n"+objects);
//                        whichRight = theDB;
                        int ownerID = objects.get(theDB).ownerID;
                        if(ownerID == me){
                            for(int f = 0; f < subjects.size();f++){
                                System.out.print("\nID: "+f+" Name: "+subjects.get(f).name);
                            }
                            System.out.print("\nPlease enter ID of user to which you're granting access: ");
                            int toWhom1 = in.nextInt();
                            Object give = objects.get(theDB);
                            subjects.get(toWhom1).myObjects.add(give);
//                            New code for owner change
                            int where = subjects.get(toWhom1).myObjects.indexOf(give);
                            subjects.get(toWhom1).myObjects.get(where).ownerID = toWhom1;
                            subjects.get(toWhom1).myObjects.get(where).owner = subjects.get(toWhom1).name;
                            
                            if(transfer == true){
                                subjects.get(me).myObjects.remove(give);
                            }
                        }
                        else{
                            System.out.print("\nInsuficient permissions");
                            
                        }
                        break;
                }
        }
        else{
            System.out.print("\nRegular users cannot grant access");
        }

        if(transfer == true && whichRight != -1){
            return(whichRight);
        }
        else{
            return(0);
        }
    }
    
    
    
    public int detete(int me){
//        System.out.print("\nMade it");
        int lvl = subjects.get(me).myLvl;
        int owner;
        int whichRight = -1;
        System.out.print("\nAre you deleting a right (0) or a database access (1)?");
        int rOrD = in.nextInt();
        switch (lvl) {
            case 3:
                {
                    if(rOrD == 0){
                        printSub();
                        System.out.print("\nEnter ID of user to loose right: ");
                        int toWhom = in.nextInt();
                        System.out.print("\nPlease enter the right to delete(0-8): ");
                        int right = in.nextInt();
                        whichRight = right;
                        subjects.get(toWhom).removeRight(right);
                    }
                    else{
                        printSub();
                        System.out.print("\nEnter ID of user to loose right: ");
                        int toWhom = in.nextInt();
                        printObj();
                        System.out.print("\nPlease enter the index of database to remove access: ");
                        int right = in.nextInt();
                        Object that = objects.get(right);
                        subjects.get(toWhom).myObjects.remove(that);
                    }
                    }
                    
                    break;
//                }
            case 2:
                {
                    if(rOrD == 0){
                        printSub();
                        System.out.print("\nEnter ID of user to loose right: ");
                        int toWhom = in.nextInt();
                        Subjects test = subjects.get(toWhom);
                        if(test.myOwner==me){
                            System.out.print("\nPlease enter the right to delete(0-8): ");
                            int right = in.nextInt();
                            whichRight = right;
                            subjects.get(toWhom).removeRight(right);
                        }
                    }
                    else{
                         printSub();
                        System.out.print("\nEnter ID of user to loose right: ");
                        int toWhom = in.nextInt();
                        printObj();
                        System.out.print("\nPlease enter the index of database to remove access: ");
                        int right = in.nextInt();
                        Object that = objects.get(right);
                        String creater = that.owner;
                        String c2 = subjects.get(me).name;
                        
                        if(creater.equals(c2)){
                            subjects.get(toWhom).myObjects.remove(that);
                        }
                        
                    }
                    
                    break;
                }
                
            default:
                System.out.print("\nInsuficient permissions");
                break;
        }
            
        return(0);
    }
        
    
//    public int read(){
//        return(0);
//    }
    
    public int createObject(String n, int id){
//        int me = subjects.
        Subjects sub = subjects.get(id);
        int create = sub.getRights(0);
        if(create == 1){
            System.out.print("\nWould you like to grant access to all users or "
                    + "keep private and assign access later? (0 = private : 1 = public): ");
            int access = in.nextInt();
            if(access == 0){
                Object obj = new Object(n,id);
                objects.add(obj);
                subjects.get(id).myObjects.add(obj);
                System.out.print("\nDatabase " + obj.name + " has been created");
   //        System.out.print("\nAdding " + obj.name + " to " + subjects.get(id+1).getName()+ "\n");
            }
            else if(access == 1){
                Object obj = new Object(n,id);
                objects.add(obj);
                for(int i = 0; i < subjects.size();i++){
                    Subjects innerSub = subjects.get(i);
                    subjects.get(i).myObjects.add(obj);
                }
                System.out.print("Database " + obj.name + " has been created");
            }
           
                 }
         else{
             System.out.print("\nYou do not have the necessary permissions to create a database.\n");
         }         
        
        return(0);
    }
    
    public int destroyObject(int id){
   
        Subjects sub = subjects.get(id);
        int create = sub.getRights(1);
        if(create==1){
            System.out.print("\nHere are all databases\n");
            for(int i = 0;i<objects.size();i++){
                Object obj = objects.get(i);
                System.out.print("\nID: "+i+" Name: "+obj.name);
            }
            System.out.print("\nPlease enter database ID to delete: ");
            int toDelete = in.nextInt();
            Object obj = objects.get(toDelete);
            int ownerId = obj.getOwner();
            if(id==ownerId){
                    for(int y = 0; y<subjects.size();y++){
                        subjects.get(y).deleteSubDb(obj.name);
                   }
                objects.remove(toDelete);
                sub.deleteSubDb(obj.name);
               
//                System.out.print("\nDetete success");
            }
            else{
                System.out.print("\nYou don't have permission to delete this file");
            }
            
            
        }
        
        
        
        
        return(0);
    }



    public int destroyWithUser(int id){
        Object obj = objects.get(id);
        objects.remove(id);
            
        
        return(0);
    }
    public int createSubject(String name,int id){
        System.out.print("Is the user a: \n1. Regular User\n2. Security Officer\n3. Admin\n");
        int toPass = in.nextInt();
        Subjects sub = new Subjects(toPass, name, id);
        subjects.add(sub);
        subjects.get(id).mySub.add(sub);
        return(0);
    }
    
    public int destroySubject(int x){
        subjects.remove(x);
        for(int i = 0; i < objects.size();i++){
            int owner = objects.get(i).getOwner();
            if(owner == x){
                destroyWithUser(i);
                
            }
        }
        return(0);
    }
    
    
    
    
    
    
    private int find(String me){
//        Subjects sub;
        int toReturn = 0;
        boolean found = false;
        
        for(Subjects sub1 : subjects){
                 String name = sub1.getName();
                 if(name.equals(me)){

                     toReturn = subjects.indexOf(sub1);
                     found = true;
                     break;
                 }

             }
        
        if(found == false){
            toReturn = -1;
            System.out.print("\nUser not found");
        }

        return(toReturn);
    }
    
    
    public int login(){
        System.out.print("\nPlease enter your username: ");
        String me = input.next();
        int myIndex = find(me);
        int toReturn = -100;
        
        if(myIndex == -1){
            System.out.print("\nThis user does not exist");
            toReturn = -1;
        }
        else if( myIndex != -1){
            System.out.print("\nWelcome "+me+"\n");
        Subjects sub = subjects.get(myIndex);
            System.out.print("\nPlease enter your password: ");
            String pass = input.next();
            int login = sub.checkPassword(pass);
            if(login==0){
                System.out.print("\033[H\033[2J");
                System.out.flush();
                toReturn = subjects.indexOf(sub);
            }
            else{
                System.out.print("\nPassword not correct!\n");
                System.out.print("\033[H\033[2J");
                System.out.flush();
                toReturn = -1;
            }
        }

            return(toReturn);
    }

    public int printACM(int id){

        
        if(checkSecLvl(id) == 3){
            System.out.print("\nRights [create,drop,select,insert,delete,commit,rollback,grant,revoke]\n1 = has right\n2 = does not have right\n"
                    + "------------------------------------\n");
                for(int i = 0; i < this.subjects.size();i++){

               System.out.print("\nID: "+i+" Name: "+subjects.get(i).getName() + " Rights: "+subjects.get(i).rights);
   //            System.out.print("\nRights:"+subjects.get(i).rights);
               System.out.print("\nObjects Owned: ");
               subjects.get(i).printObjects();    
               System.out.print("\n");


           }
        }
        else{
            System.out.print("\nInsufficient permissions to view all\n");
        }
        
       
        
        
        
        return(0);
    }
    
    
//    This is the equivilent of Read()
    public int printMe(int id){
        Subjects me = subjects.get(id);
        System.out.print("\nID: "+id+" Name: "+subjects.get(id).getName() + " Rights: "+subjects.get(id).rights);
        me.printObjects();
        System.out.print("\n");
        

        return(0);
    }
    
    public int checkRight(int id, int right){
        Subjects me = subjects.get(id);
        int answer = me.rights.get(right);
        
        return(answer);
    }
    
    public int checkSecLvl(int id){
         Subjects me = subjects.get(id);
         int lvl = me.getLvl();
         
         switch(lvl){
             case (1):
                 return(1);
             case (2):
                 return(2);
             case (3):
                 return(3);
         }
                 
        return(0);
    }
    
    public int printSub(){
        for(int i = 0; i < this.subjects.size();i++){
            
            System.out.print("\n\n"+subjects.get(i).getName() + " Index: " + i+"\n");
      
        }
        
        return(0);
    }
    
     public int printObj(){
        for(int i = 0; i < this.objects.size();i++){
            
            System.out.print("\n\n"+objects.get(i).name + " Index: " + i+"\n");
      
        }
        
        return(0);
    }
    
     public int printSubName(){
        for(int i = 0; i < this.subjects.size();i++){
            
            System.out.print("\n\n"+subjects.get(i).getName());
      
        }
        
        
        
        
        return(0);
    }

      private int findDB(String me){
        int toReturn = 0;
        for(Object obj : objects){
                 String name = obj.name;
                 if(name.equals(me)){
                     toReturn = objects.indexOf(obj);
                 }
             }
        return(toReturn);
    }
     
//     *****************************************************************
//     Database Commands Section
//     ***************************************************************** 
     
     
//Create and drop are covered above ^ in object creation
      
      public int create(int id){
          int yes = subjects.get(id).rights.get(0);
          if(yes == 1){
              System.out.print("\n1. Please enter database name: ");
                            String objectName = input.next()+".db";       
                            createObject(objectName, id);
          }
          return(0);
      }
      
      public int drop(int id){
          int yes = subjects.get(id).rights.get(1);
          if(yes==1){
              destroyObject(id);
          }
          return(0);
      }
     
     public int select(int id){
         int yes = subjects.get(id).rights.get(2);
         switch(yes){
             
             case(1):
                 System.out.print("\nYou have permission to select but it's not implmented\n");
                 break;
             case(0):
                 System.out.print("\nYou don't have permission to select\n");
                 break;
         }
                
         return(0);
     }
     
     public int insert(int id){
         int yes = subjects.get(id).rights.get(3);
         switch(yes){
             
             case(1):
                 System.out.print("\nYou have permission to insert but it's not implmented\n");
                 break;
             case(0):
                 System.out.print("\nYou don't have permission to insert\n");
                 break;
         }
         return(0);
         
     }
     
     public int DBdelete(int id){
         int yes = subjects.get(id).rights.get(4);
         switch(yes){
             
             case(1):
                 System.out.print("\nYou have permission to delete but it's not implmented\n");
                 break;
             case(0):
                 System.out.print("\nYou don't have permission to delete\n");
                 break;
         }
         return(0);
         
     }
     
     public int commit(int id){
         int yes = subjects.get(id).rights.get(5);
         switch(yes){
             
             case(1):
                 System.out.print("\nYou have permission to commit but it's not implmented\n");
                 break;
             case(0):
                 System.out.print("\nYou don't have permission to commit\n");
                 break;
         }
         return(0);
         
     }
     
     public int rollback(int id){
         int yes = subjects.get(id).rights.get(6);
         switch(yes){
             
             case(1):
                 System.out.print("\nYou have permission to rollback but it's not implmented\n");
                 break;
             case(0):
                 System.out.print("\nYou don't have permission to rollback\n");
                 break;
         }
         return(0);
         
     }
     
     public int grant(int id){
         int yes = subjects.get(id).rights.get(7);
         grant(id,false);
//         switch(yes){
//             
//             case(1):
//                 System.out.print("\nYou have permission to grant but it's not implmented");
//                 break;
//             case(0):
//                 System.out.print("\nYou don't have permission to grant");
//                 break;
//         }
         return(0);
         
     }
     
     public int revoke(int id){
         int yes = subjects.get(id).rights.get(8);
         revoke(id);
//         switch(yes){
//             
//             case(1):
//                 System.out.print("\nYou have permission to revoke but it's not implmented");
//                 break;
//             case(0):
//                 System.out.print("\nYou don't have permission to revoke");
//                 break;
//         }
         return(0);
         
     }
     
     
     
//     *****************************************************************
//     End Database Commands Section
//     ***************************************************************** 
//     
//     [row][col]
     public int printMatrix(int id){
         
         if(checkSecLvl(id) == 3 || checkSecLvl(id) == 2){
               int row = subjects.size()+1;
               int col = objects.size();
//               col += 9;
//               col += subjects.size();
//               System.out.print("\nCol size:"+col);
               String[][] matrix = new String[row][col+(subjects.size()+9)];

      //         fill object names & right names
      //         NEW add owned subjects
      
      //            System.out.print("The name is"+objects.get(0).name);
               for(int i = 0; i < objects.size();i++){
//                   System.out.print("\n"+objects.get(i).name);
                   matrix[0][i+1]=objects.get(i).name;
               }
               for(int i = objects.size()+1;i<col+9;i++){
//                   matrix[0][i]="Right # "+(i-objects.size());
//                    System.out.print("\nLOOK HERE!"+rightNames.get(i-objects.size()-1));
                   matrix[0][i]=(rightNames.get(i-objects.size()-1))+"\t";

               }
               for(int i = 0;i<subjects.size();i++){
//                   System.out.print("\nLOOK HERE!"+subjects.get(i).name);
                   matrix[0][col+9+i]=subjects.get(i).name+"\t";
               }


      //         fill subject names
               matrix[0][0]="Sub";
               for(int i = 1; i < row;i++){
                   matrix[i][0] = subjects.get(i-1).name;
               }


      //         fill access
              int colCount = 0;
              for(int i = 1; i < row;i++){
                  colCount = 0;
                  for(int u = 0; u < col+subjects.size()+9;u++){
//                      System.out.print("\ncolCount="+colCount+" : rights size="+rightNames.size()+" : u="+u);
                      if(colCount < objects.size()){
      //                    String dbCompare = objects.get(u-col).name;
      //                    System.out.print("\nBefore it");
                          Object test = objects.get(u);
      //                    System.out.print("\nPassed it");
                          boolean doesIt = subjects.get(i-1).myObjects.contains(test);
//                          System.out.print("\nSubject # "+(i-1)+" Does it = "+doesIt);
//                          System.out.print("\nThis is run "+u+" inside 1\n");

                          if(doesIt==true){
                              matrix[i][u+1]= "x\t";
                          }
                          else{
                              matrix[i][u+1]=" \t";

                          }
                          colCount++;
                      }
                      else if(colCount<=objects.size()+rightNames.size()-1){
//                          System.out.print("\nThis is run "+u+" inside 2\n");
                          String doThey = subjects.get(i-1).rights.get(u-objects.size()).toString();
                          matrix[i][u+1]=doThey+"\t";
                          colCount++;
      //                    colCount = 0;
                      }
                      else{
//                          System.out.print("\nThis is run "+u+" inside 3\n");
                          Subjects sub = subjects.get(i-1);
                          int id1 = subjects.indexOf(sub);
//                          System.out.print("\n"+((u-(col+9)))+"\n");
                    Subjects sub2 = subjects.get((u-(col+9)));
                          int id2 = sub2.myOwner;
                          if(id1 == id2){
//                              System.out.print("\nCol size:\n"+col);
                              matrix[i][u]="x\t";
                          }
                          else{
                              matrix[i][u]=" \t";
                          }
                          
                      }


                  }
              }







      //         Print it out
              for (int i = 0; i < row; i++)
                      {
                          System.out.print("\n");
                          for(int k = 0; k < col+subjects.size()+9;k++){
                              System.out.print("================");
                          }
                          System.out.print("\n");

                          for (int j = 0; j < col+subjects.size()+9; j++)
                          {
                              if(j==1){
                                  System.out.print("\t");
                              }

                                  System.out.print("|");
                              System.out.print(matrix[i][j]+"\t");
                          }
                          System.out.println();
                      }         
         }
         else{
            System.out.print("\nInsufficient permissions to view all\n");
        }

                 return(0);
     }
     
     
    
}
