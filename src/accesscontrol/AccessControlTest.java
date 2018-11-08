/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesscontrol;
import java.util.Scanner;
/**
 *
 * @author gladia2or
 */
public class AccessControlTest {

    /**
     * @param args the command line arguments
     */
    public static int clear(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        return(0);
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        ACM test = new ACM();

        test.initializeAdmin();

        

        String theName;
        int fileNum = 0;
        int subjectNum = 0;
        String fileName;
        boolean run = true; 
        boolean loggedIn = true;
        boolean on = true;

        
        while(on){
            
            clear();
            
            int go = test.login();
//            System.out.print("\nHi there"+go);
            if(go >= 0){
//                System.out.print("Shut up Loyd");
                loggedIn = true;
                run = true;
            }
            else if(go<0){
                loggedIn = false;
            }

            while(loggedIn){
                int myUserId = go;
                Scanner input = new Scanner(System.in);
                while(run){
//                    clear();
                    System.out.print("\nOPTIONS:\n0. QUIT\n1. View all users/rights/objects\n2. View my rights/objects \n3. Add a user\n4. Remove a user\n"
                            + "5. Add a database\n6. Remove a database\n7. Grant (right or database access)\n8. Transfer a right/database ownership\n9. Remove a right\n10. Switch User\n11. Enter Database\n");
                    int choice = input.nextInt();
                    clear();

                    switch(choice){
                        case(0):
//                            test.printMatrix();
                            run = false;
                            loggedIn = false;
                            on = false;
                            break;
                        case(1):
//                            test.printACM(myUserId);
                            test.printMatrix(myUserId);
                            break;
                        case(2):
                            test.printMe(myUserId);
                            break;
                        case(3):
                            clear();
                            System.out.print("Please enter subject name: ");
//                            int lvl = input.nextInt();
                            String name = input.next();
                            test.createSubject(name,myUserId);
                            clear();
                            break;
                        case(4):
                            clear();
                            test.printSub();
                            System.out.print("\nPlease enter the index of the user to remove:\n");
                            int toRemove = input.nextInt();
                            test.destroySubject(toRemove);
                            break;
                        case(5):
                            clear();
                            System.out.print("\n1. Please enter database name: ");
                            String objectName = input.next()+".db";       
                            test.createObject(objectName, myUserId);
                            break;
                        case(6):
                            test.destroyObject(myUserId);
                            break;
                        case(7):
                            test.grant(myUserId,false);
                            break;
                        case(8):
                            test.transfer(myUserId);
                            break;
                        case(9):
                            System.out.print("\nHI");
                            test.detete(myUserId);
                            break;
                        case(10):
                            run = false;
                            loggedIn = false;
                            break;
                        case(11):
                            clear();
                            System.out.print("Welcome to the loyds database manager!\n");
                            boolean dBRun = true;
                            while(dBRun){
                                System.out.print("\nWhat would you like to do today?\n");
                                System.out.print("0. Exit data base mode\n1. Create\n2. Drop\n3. Select\n4. Insert\n5. Delete\n6. Commit\n7. "
                                        + "Rollback\n8. Grant\n9. Revoke\n");
                                int dbChoice = input.nextInt();
                                switch(dbChoice){
                                    case(0):
                                        dBRun = false;
                                        break;
                                    case(1):
                                        test.create(myUserId);
                                        break;
                                    case(2):
                                        test.drop(myUserId);
                                        break;
                                    case(3):
                                        test.select(myUserId);
                                        break;
                                    case(4):
                                        test.insert(myUserId);
                                        break;
                                    case(5):
                                        test.DBdelete(myUserId);
                                        break;
                                    case(6):
                                        test.commit(myUserId);
                                        break;
                                    case(7):
                                        test.rollback(myUserId);
                                        break;
                                    case(8):
                                        test.grant(myUserId);
                                        break;
                                    case(9):
                                        test.revoke(myUserId);
                                        break;
                                }
                            }
                                






                    }




                }

            }
        }
        
//        Scanner input = new Scanner(System.in);
//        while(run){
//            System.out.print("\nOPTIONS:\n0. QUIT\n1. View all users/rights/objects\n2. Add a user\n3. Remove a user\n"
//                    + "4. Add an object\n5. Remove an object\n6. Grant a right\n7. Transfer a right\n");
//            int choice = input.nextInt();
//            
//            switch(choice){
//                case(0):
//                    run = false;
//                    break;
//                case(1):
//                    test.printACM();
//                    break;
//                case(2):
//                    System.out.print("Please enter:\n1. permission lvl (1-3)\n2. Name\n");
//                    int lvl = input.nextInt();
//                    String name = input.next();
//                    test.createSubject(lvl, name);
//                    break;
//                case(3):
//                    test.printSub();
//                    System.out.print("\nPlease enter the index of the user to remove:\n");
//                    int toRemove = input.nextInt();
//                    test.destroySubject(toRemove);
//                    break;
//                case(4):
//                    System.out.print("\n1. Please enter object name: ");
//                    String objectName = input.next();
//                    System.out.print("\n3. 1Please enter your ID: \n");
//                    test.printSub();
//                    int ownerId = input.nextInt();          
//                    test.createObject(objectName, ownerId);
//                    break;
//                case(5):
//                    break;
//                case(6):
//                    break;
//                case(7):
//                    break;
//                        
//
//                        
//                    
//                    
//                    
//            }
//                        
//                        
//                    
//            
//        }
    }
    
}
