/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;

/**
 *
 * @author Shreyas Valmiki
 */
public class Weapons {
    private HashMap<Integer,String> weaponList = new HashMap<Integer, String>();
    private HashMap<String,Integer> nameToIndexList = new HashMap<String, Integer>();
    public Weapons(){
        weaponList.put(1,"Pistol");
        weaponList.put(2,"Sling Shot");
        weaponList.put(3,"Rifle");
        weaponList.put(4,"Shot Gun");
        weaponList.put(5,"Bazooka");
        
        
        nameToIndexList.put("Pistol",1);
        nameToIndexList.put("Sling Shot",2);
        nameToIndexList.put("Rifle",3);
        nameToIndexList.put("Shot Gun",4);
        nameToIndexList.put("Bazooka",5);
    }
    
    public HashMap<Integer,String> getWeaponList(){
        return weaponList;
    }
    
    public HashMap<String,Integer> getNameToIndexList(){
        return nameToIndexList;
    }
    public int getWeaponCount(){
    	return weaponList.size();
    }
}
