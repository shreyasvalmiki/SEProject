
import java.util.HashMap;


public class Weapons {
    private HashMap<Integer,String> weaponList = new HashMap<Integer, String>();
    private HashMap<String,Integer> nameToIndexList = new HashMap<String, Integer>();
    public Weapons(){
        weaponList.put(1,"pistol");
        weaponList.put(2,"slingshot");
        weaponList.put(3,"rifle");
        weaponList.put(4,"shotgun");
        weaponList.put(5,"bazooka");
        
        
        nameToIndexList.put("pistol",1);
        nameToIndexList.put("slingshot",2);
        nameToIndexList.put("rifle",3);
        nameToIndexList.put("shotgun",4);
        nameToIndexList.put("bazooka",5);
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
