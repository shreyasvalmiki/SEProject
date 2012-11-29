/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author Shreyas Valmiki
 */
public class LevelController {
    protected AnimalWeaponMapper mapper;
    protected Animals animals = new Animals();
    protected Weapons weapons = new Weapons();
    protected HashMap<String,Integer> animalNameList = new HashMap<String, Integer>();
    protected HashMap<Integer,String> animalIndexList = new HashMap<Integer, String>();
    protected HashMap<String,Integer> weaponNameList = new HashMap<String, Integer>();
    protected HashMap<Integer,String> weaponIndexList = new HashMap<Integer, String>();
    
    public HashMap<Integer,ArrayList<Integer>> animalWeaponMap = new HashMap<Integer, ArrayList<Integer>>();
    public HashMap<String,ArrayList<String>> huntMap = new HashMap<String, ArrayList<String>>();
    public LevelController(){   
        mapper = new AnimalWeaponMapper();
    }
    protected void initLists(){
        animalWeaponMap = mapper.getAnimalWeaponMap();
        huntMap = mapper.getHuntMap();
        animalNameList = animals.getNameToIndexList();
        animalIndexList = animals.getAnimalList();
        weaponNameList = weapons.getNameToIndexList();
        weaponIndexList = weapons.getWeaponList();
        
    }
    public boolean isWinner(){
        return true;
    }
    
    
    public boolean hunt(){
        
       
       return false;
    }
    
}
