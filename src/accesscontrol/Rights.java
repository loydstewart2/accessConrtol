/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package accesscontrol;

import java.util.ArrayList;

/**
 *
 * @author gladia2or
 */
public class Rights {
    
    ArrayList<Integer> rights = new ArrayList<Integer>(9);
    ArrayList<Integer> canTransfer = new ArrayList<Integer>(9);
//    int myLvlRights;
    
    public Rights(int lvl){
//        myLvlRights = lvl;
        switch(lvl){

//            regular user - rights (3,4,5)
            case 1:
                rights.add(0);
                rights.add(0);
                rights.add(1);
                rights.add(1);
                rights.add(1);
                rights.add(0);
                rights.add(0);
                rights.add(0);
                rights.add(0);
                for(int i = 0; i < 9; i++){
                    canTransfer.add(0);
                }
                break;




//                security officer
            case 2:
                rights.add(0);
                canTransfer.add(0);
                rights.add(0);
                canTransfer.add(0);
                for(int i = 2; i < 9; i++){
//                    rights[i]=1;
                    rights.add(1);
                    canTransfer.add(1);
                }
//                rights.add(0);
//                canTransfer.add(0);
//                rights.add(0);
//                canTransfer.add(0);
                break;



//                admin - rights (all)
            case 3:
                for(int i = 0; i < 9; i++){
                    rights.add(1);
                    canTransfer.add(1);
                }
                break;
        }
        
    }
    
    
    public ArrayList<Integer> getRights(){
        return(rights);
    }
    
    public ArrayList<Integer> getCanTransfer(){
        return(canTransfer);
    }
    
//    public int getLvl(){
//        return(myLvlRights);
//    }
    
}
