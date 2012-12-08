/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;

/**
 *
 * @author Shreyas Valmiki
 */
public final class AnimalWeaponMapper {
	//map of animals and their weapons
	//always up to date
    private HashMap<Integer,ArrayList<Integer>> animalWeaponMap = new HashMap<Integer, ArrayList<Integer>>();
    //number of weapons per animal
    //can leave out used weapons depending on level
    private HashMap<Integer,Integer> animalToWeaponNo = new HashMap<Integer, Integer>(); 
    
    //same as animalWeaponMap but as a matrix with names instead of numbers
    private HashMap<String,ArrayList<String>> huntMap = new HashMap<String, ArrayList<String>>();
    
    private Animals animals = new Animals();
    private Weapons weapons = new Weapons();
    
    private HashMap<Integer,String> animalList = new HashMap<Integer, String>();
    private HashMap<Integer,String> weaponList = new HashMap<Integer, String>();
    
    //checker for values added in animalToWeaponNo
    private ArrayList<Integer> indexInsertedList = new ArrayList<Integer>(); 
    private int animalListSize = 0;
    private int weaponListSize = 0;

    public AnimalWeaponMapper(){}
    
    AnimalWeaponMapper(int huntedWith2,int huntedWith3,int huntedWith4){
        initMap();   
        setAnimalToWeaponRatio(huntedWith2,huntedWith3,huntedWith4);
        computeAndSetHuntMap();
    }
    
    //Give each animal the complete list of weapons in animalWeaponMap
    private void initMap(){
        animalList = animals.getAnimalList();
        weaponList = weapons.getWeaponList();
        
        weaponListSize = weaponList.size();
        animalListSize = animalList.size();
        
        for(Integer i: animalList.keySet()){
            ArrayList<Integer> list = new ArrayList<Integer>();
            
            for(int j=1;j<=weaponListSize;j++){
                list.add(j);     
            }
            animalWeaponMap.put(i, list);
        }
    }
    
    //Set up how many weapons each animal can have
    private void setAnimalToWeaponRatio(int huntedWith2,int huntedWith3,int huntedWith4){
    	//test
    	//System.out.println("in setAnimalToWeaponRatio");
        int total = huntedWith2 + huntedWith3 + huntedWith4;
        //huntedwith2 - how many animals with 2 weapons
        //huntedwith3 - how many animals with 3 weapons
        //...
        
        if(total < animalListSize)
        {
            huntedWith3 += animalList.size() - total;
        }
        
        //test
    	//System.out.println("in setAnimalToWeaponRatio");
        fillRatioList(2,huntedWith2); //choose which animals get 2 weapons
        fillRatioList(3,huntedWith3); //choose which animals get 3 weapons
        fillRatioList(4,huntedWith4); //...
      //test
    	//System.out.println("out setAnimalToWeaponRatio");
    }
    
    //randomly select the animals which get weaponToAnimalNo of weapons
    //huntTimes represents the number of animals to select
    private void fillRatioList(int weaponToAnimalNo,int huntTimes){
    	 //test
    	//System.out.println("in fillRatioList");
        Random rand = new Random();
        for(int i = 0; i<huntTimes;i++){
            //int key = rand.nextInt(animalListSize)+1;
            boolean hasInserted = false;
            while(!hasInserted){
            	int key = rand.nextInt(animalListSize)+1;
                if(!indexInsertedList.contains(key)){
                    indexInsertedList.add(key);
                    animalToWeaponNo.put(key, weaponToAnimalNo);
                    hasInserted = true;
                }
            }
        }
        //test
    	//System.out.println("out fillRatioList");
    }
    
    //first get the animalWeaponMap then set the huntMap from it
    private void computeAndSetHuntMap(){ 
    	//test
    	//System.out.println("in computeAndSetHuntMap");
    	
        ArrayList<Integer> rowSumList = new ArrayList<Integer>();
        HashMap<Integer,Integer> mandatoryList = new HashMap<Integer, Integer>();
        int rowSum;
        boolean isRowDone = false;
        Random rand = new Random();
        Random randMand = new Random();
        int ratio = 0;
        int randIndex;
        
        int count = 0;
        
        //ensure at least 1 distinct solution?
        while(mandatoryList.size() < weaponListSize){ 
        	int randNo = randMand.nextInt(weaponListSize)+1;
        	if(!mandatoryList.keySet().contains(randNo)){
        		mandatoryList.put(randNo,++count);
        	}
        }
        //take out weapons randomly from each animal based on the number of weapons set for them
        for(int i: animalToWeaponNo.keySet()){ 
            ratio = animalToWeaponNo.get(i);
            ArrayList<Integer> randList = new ArrayList<Integer>();
            ArrayList<Integer> wList = new ArrayList<Integer>();
            wList = animalWeaponMap.get(i);
            isRowDone = false;
            rowSum = 0;
            while(!isRowDone){
               randIndex = rand.nextInt(weaponListSize) + 1; 
               //if(!randList.contains(randIndex) && (weaponListSize-randList.size()) < ratio && mandatoryList.get(i) != randIndex){
               if(!randList.contains(randIndex) && (weaponListSize-randList.size()) > ratio && mandatoryList.get(i) != randIndex){
                   randList.add(randIndex);
                   wList.set(wList.indexOf(randIndex), -1);
               }
               if((weaponListSize-randList.size()) == ratio){
            	   isRowDone = true;
               }
            }
        }
        setHuntMap();
        //test
        //System.out.println("in computeAndSetHuntMap");
    }
    
    
    //create the huntMap which probably is used for display
    private void setHuntMap(){ 
    	//test
    	//System.out.println("in setHuntMap");
        for(int key: animalWeaponMap.keySet()){
            ArrayList<String> huntWeaponNames = new ArrayList<String>();
            ArrayList<Integer> huntWeaponIndexes = new ArrayList<Integer>();
            huntWeaponIndexes = animalWeaponMap.get(key);
            for(int i: huntWeaponIndexes){
                if(i == -1)
                {
                    huntWeaponNames.add("");
                }
                else
                {
                    huntWeaponNames.add(weaponList.get(i));
                }
            }
            huntMap.put(animalList.get(key), huntWeaponNames);
        }
        cleanAnimalWeaponMap();
        //test
        //System.out.println("in setHuntMap");
        
    }
    
    //take out -1 from animalWeaponMap meaning only actual weapons remain
    private void cleanAnimalWeaponMap(){ 
    	ArrayList<Integer> tempArray = new ArrayList<Integer>();
    	for(Integer i:animalWeaponMap.keySet()){
    		tempArray = animalWeaponMap.get(i);
    		do
    		{
    			tempArray.remove(tempArray.indexOf(-1));
    		}while(tempArray.contains(-1));
 
    	}
    }
    public HashMap<Integer,ArrayList<Integer>> getAnimalWeaponMap(){
        return animalWeaponMap;
    }
    
    public HashMap<String,ArrayList<String>> getHuntMap(){
        return huntMap;
    }
}
