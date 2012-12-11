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
	private HashMap<Integer,Integer> animalToWeaponNoList = new HashMap<Integer, Integer>();
	//number of animals per weapon
	private HashMap<Integer,Integer> weaponToAnimalNoList = new HashMap<Integer, Integer>();
    
    //same as animalWeaponMap but as a matrix with names instead of numbers
    private HashMap<String,ArrayList<String>> huntMap = new HashMap<String, ArrayList<String>>();
	private HashMap<Integer,Integer> mandatoryList = new HashMap<Integer, Integer>();
	
	//Number of animals hunted per weapon and number of weapons assigned per weapon
	private int huntedWith2;
	private int huntedWith3;
	private int huntedWith4;
    
    private Animals animals = new Animals();
    private Weapons weapons = new Weapons();
    
    private HashMap<Integer,String> animalList = new HashMap<Integer, String>();
    private HashMap<Integer,String> weaponList = new HashMap<Integer, String>();
    
    //checker for values added in animalToWeaponNo
    private ArrayList<Integer> indexInsertedList = new ArrayList<Integer>(); 
    private int animalListSize = 0;
    private int weaponListSize = 0;
    
    //set if a possibility of infinite loop exists
    private boolean isInfiniteLoop = false;

	public AnimalWeaponMapper(){}

	AnimalWeaponMapper(int huntedWith2,int huntedWith3,int huntedWith4){
		this.huntedWith2 = huntedWith2;
		this.huntedWith3 = huntedWith3;
		this.huntedWith4 = huntedWith4;   
		initAll();
	}
	
	private void initAll(){
		do{
			mandatoryList.clear();
			animalWeaponMap.clear();
			animalToWeaponNoList.clear();
			weaponToAnimalNoList.clear();
			huntMap.clear();
			isInfiniteLoop = false;
			initMap();   
			setAnimalToWeaponRatio(huntedWith2,huntedWith3,huntedWith4);
			setWeaponToAnimalRatio(huntedWith2,huntedWith3,huntedWith4);
			computeAndSetHuntMap();
		}while(isInfiniteLoop);
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

		int total = huntedWith2 + huntedWith3 + huntedWith4;
        //huntedwith2 - how many animals with 2 weapons
        //huntedwith3 - how many animals with 3 weapons
        //...		

		if(total < animalListSize)
		{
			huntedWith3 += animalList.size() - total;
		}
		indexInsertedList.clear();
		fillAtoWRatioList(2,huntedWith2); //choose which animals get 2 weapons
		fillAtoWRatioList(3,huntedWith3); //choose which animals get 3 weapons
		fillAtoWRatioList(4,huntedWith4); //...

	}

	//Set up how many animals each weapon can be associated with
	private void setWeaponToAnimalRatio(int hunt2,int hunt3,int hunt4){

		int total = hunt2 + hunt3 + hunt4;
		//hunt2 - how many weapons which can be used for 2 animals
        //hunt3 - how many weapons which can be used for 3 animals
        //...

		if(total < weaponListSize)
		{
			hunt3 += weaponList.size() - total;
		}
		indexInsertedList.clear();
		fillWtoARatioList(2,hunt2); //choose which weapons get 2 animals
		fillWtoARatioList(3,hunt3); //choose which weapons get 3 animals
		fillWtoARatioList(4,hunt4); //...
	}

	//randomly select the weapons which get animalToWeaponNoList of animals
    //huntTimes represents the number of weapons to select
	private void fillAtoWRatioList(int weaponToAnimalNo,int huntTimes){
		Random rand = new Random();
		for(int i = 0; i<huntTimes;i++){
			boolean hasInserted = false;
			while(!hasInserted){
				int key = rand.nextInt(animalListSize)+1;
				if(!indexInsertedList.contains(key)){
					indexInsertedList.add(key);
					animalToWeaponNoList.put(key, weaponToAnimalNo);
					hasInserted = true;
				}
			}
		}
	}

	//randomly select the animals which get weaponToAnimalNoList of weapons
    //huntTimes represents the number of animals to select
	private void fillWtoARatioList(int animalToWeaponNo,int huntTimes){
		Random rand = new Random();

		for(int i = 0; i<huntTimes;i++){
			boolean hasInserted = false;
			while(!hasInserted){
				int key = rand.nextInt(weaponListSize)+1;
				if(!indexInsertedList.contains(key)){
					indexInsertedList.add(key);
					weaponToAnimalNoList.put(key, animalListSize - animalToWeaponNo);
					hasInserted = true;
				}
			}
		}
	}

	//first get the animalWeaponMap then set the huntMap from it
	private void computeAndSetHuntMap(){
		boolean isRowDone = false;
		///Random rand = new Random();
		Random randMand = new Random();
		int ratio = 0;
		int randIndex;

		int count = 0;
		while(mandatoryList.size() < weaponListSize){
			int randNo = randMand.nextInt(weaponListSize)+1;
			if(!mandatoryList.keySet().contains(randNo)){
				mandatoryList.put(randNo,++count);
			}
		}
		for(int i: animalToWeaponNoList.keySet()){
			ratio = animalToWeaponNoList.get(i);
			ArrayList<Integer> randList = new ArrayList<Integer>();
			ArrayList<Integer> wList = new ArrayList<Integer>();
			wList = animalWeaponMap.get(i);
			isRowDone = false;
			while(!isRowDone){
				//randIndex = rand.nextInt(weaponToAnimalNoList.size()) + 1;
				List<Object> arr = new ArrayList<Object>();
				arr = Arrays.asList(weaponToAnimalNoList.keySet().toArray());
				Collections.shuffle(arr);
				randIndex = (Integer) arr.get(0);
				if(isInfiniteLoop(randList,i)){
					isInfiniteLoop = true;
					return;
				}
				//if(!randList.contains(randIndex) && (weaponListSize-randList.size()) < ratio && mandatoryList.get(i) != randIndex){
				if(weaponToAnimalNoList.get(randIndex)>0 &&!randList.contains(randIndex) && (weaponListSize-randList.size()) > ratio && mandatoryList.get(i) != randIndex){
					randList.add(randIndex);
					wList.set(wList.indexOf(randIndex), -1);
					weaponToAnimalNoList.put(randIndex, weaponToAnimalNoList.get(randIndex)-1);
					if(weaponToAnimalNoList.get(randIndex) == 0){
						weaponToAnimalNoList.remove(randIndex);
					}
				}
				if((weaponListSize-randList.size()) == ratio){
					isRowDone = true;
				}
			}
		}
		setHuntMap();
	}

	//create the huntMap which probably is used for display
	private void setHuntMap(){
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
	
	//checks if there is a possibility of an infinite loop a point in time
	private boolean isInfiniteLoop(ArrayList<Integer> randList, int animalId){
		for(int i:weaponToAnimalNoList.keySet()){
			if(!randList.contains(i) && !(mandatoryList.get(animalId) == i && weaponToAnimalNoList.get(i)<2)&& !(isOtherElemsFilled(randList,i)&& mandatoryList.get(animalId) == i)){
				return false;
			}
		}
		return true;
	}
	
	//Checks if the rest of the weapons in the array are filled other than the weapon sent
	private boolean isOtherElemsFilled(ArrayList<Integer>randList, int weaponId){
		for(int i:weaponToAnimalNoList.keySet()){
			if(i!=weaponId && !randList.contains(i)){
				return false;
			}
		}
		return true;
	}
	
	public HashMap<Integer,ArrayList<Integer>> getAnimalWeaponMap(){
		return animalWeaponMap;
	}

	public HashMap<String,ArrayList<String>> getHuntMap(){
		return huntMap;
	}
}