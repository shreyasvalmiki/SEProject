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
		setWeaponToAnimalRatio(huntedWith2,huntedWith3,huntedWith4);
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
		int total = huntedWith2 + huntedWith3 + huntedWith4;


		if(total < animalListSize)
		{
			huntedWith3 += animalList.size() - total;
		}
		indexInsertedList.clear();
		fillAtoWRatioList(2,huntedWith2);
		fillAtoWRatioList(3,huntedWith3);
		fillAtoWRatioList(4,huntedWith4);

	}

	private void setWeaponToAnimalRatio(int hunt2,int hunt3,int hunt4){

		int total = hunt2 + hunt3 + hunt4;


		if(total < weaponListSize)
		{
			hunt3 += weaponList.size() - total;
		}
		indexInsertedList.clear();
		fillWtoARatioList(2,hunt2);
		fillWtoARatioList(3,hunt3);
		fillWtoARatioList(4,hunt4);
	}


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


	private void computeAndSetHuntMap(){
		HashMap<Integer,Integer> mandatoryList = new HashMap<Integer, Integer>();
		boolean isRowDone = false;
		Random rand = new Random();
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
				randIndex = rand.nextInt(weaponToAnimalNoList.size()) + 1; 
				//if(!randList.contains(randIndex) && (weaponListSize-randList.size()) < ratio && mandatoryList.get(i) != randIndex){
				if(weaponToAnimalNoList.get(randIndex)>0 &&!randList.contains(randIndex) && (weaponListSize-randList.size()) > ratio && mandatoryList.get(i) != randIndex){
					randList.add(randIndex);
					wList.set(wList.indexOf(randIndex), -1);
					weaponToAnimalNoList.put(randIndex, weaponToAnimalNoList.get(randIndex)-1);
				}
				if((weaponListSize-randList.size()) == ratio){
					isRowDone = true;
				}
			}
		}
		setHuntMap();
	}

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

