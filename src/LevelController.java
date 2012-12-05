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
    protected boolean isLoser = false;
    protected boolean isWinner = false;
    protected int wrongAttemptCount;
    
    public HashMap<Integer,ArrayList<Integer>> animalWeaponMap = new HashMap<Integer, ArrayList<Integer>>();
    public HashMap<String,ArrayList<String>> huntMap = new HashMap<String, ArrayList<String>>();
    
    public HashMap<Integer,ArrayList<Integer>> initMap = new HashMap<Integer, ArrayList<Integer>>();
    protected int animalsLeft;
    protected boolean isTimeNeeded;
    
    public LevelController(){   
        mapper = new AnimalWeaponMapper();
    }
    @SuppressWarnings("unchecked")
	protected void initLists(){
    	isLoser = false;
    	isWinner = false;
    	animalsLeft = 5;
    	wrongAttemptCount = 0;
        animalWeaponMap = mapper.getAnimalWeaponMap();
        huntMap = mapper.getHuntMap();
        initMap = (HashMap<Integer, ArrayList<Integer>>) animalWeaponMap.clone();
        animalNameList = animals.getNameToIndexList();
        animalIndexList = animals.getAnimalList();
        weaponNameList = weapons.getNameToIndexList();
        weaponIndexList = weapons.getWeaponList();
    }
    public boolean hasWon(){
    	if(animalWeaponMap.size() == 0){
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
    public boolean hasLost(){
    	for(ArrayList<Integer> i:animalWeaponMap.values()){
    		if(i.size() == 0){
    			return true;
    		}
    	}
    	return isLoser;
    }
    
    public int hunt(String animal,String weapon, long time, int turnCount){
    	ArrayList<Integer> currChecklist = new ArrayList<Integer>();
    	ArrayList<String> strChecklist = new ArrayList<String>();
    	currChecklist = animalWeaponMap.get(animals.getNameToIndexList().get(animal));
    	strChecklist = huntMap.get(animal);
    	if(!strChecklist.contains(weapon)){
    		return Constants.WEAPON_NOT_FOUND;
    	}
    	else if(!currChecklist.contains(weapons.getNameToIndexList().get(weapon)))
    	{
    		++wrongAttemptCount;
    		return Constants.WEAPON_USED;
    	}
    	else
    	{
    		--animalsLeft;
    		return Constants.CONTINUE;
    	}
    }
    
    protected void updateAnimalWeaponMap(Integer animal,Integer weapon){
    	animalWeaponMap.remove(animal);
    	ArrayList<Integer> weaponCheck = new ArrayList<Integer>();
    	for(Integer i:animalWeaponMap.keySet()){
    		weaponCheck = animalWeaponMap.get(i);
    		if(weaponCheck.contains(weapon)){
    			weaponCheck.remove(weapon);
    			animalWeaponMap.put(i, weaponCheck);
    		}
    	}
    }
    
    protected void updateHuntMap(String animal, String weapon){
    	huntMap.remove(animal);
    	ArrayList<String> weaponCheck = new ArrayList<String>();
    	for(String s: huntMap.keySet()){
    		weaponCheck = huntMap.get(s);
    		if(weaponCheck.contains(weapon)){
    			weaponCheck.set(weaponCheck.indexOf(weapon), "");
    			huntMap.put(s, weaponCheck);
    		}
    	}
    }
    public boolean isTimeNeeded(){
    	return isTimeNeeded;
    }
}
