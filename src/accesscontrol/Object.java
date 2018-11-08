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
public class Object {
    String owner;
    int ownerID;
    String name;
//    protected boolean canGive;
    
    
    public Object(String n,int id){
        this.name = n;
//        this.owner = o;
        this.ownerID = id;
//        canGive = true;
        
//        System.out.print("\nObject " + name + " has been created.\n\n\n");
        
    }
    
    public int getOwner(){
        return(ownerID);
    }
    
  
    
}
