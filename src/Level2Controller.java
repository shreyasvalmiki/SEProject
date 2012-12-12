

public class Level2Controller extends LevelController{
    public Level2Controller(){
        super.mapper = new AnimalWeaponMapper(2,3,0);
        super.isTimeNeeded = false;
        super.initLists();
    }
    public int hunt(String animal,String weapon, long time, int turnCount){
    	int result = super.hunt(animal, weapon, time, turnCount);
    	if(result == Constants.CONTINUE)
    	{
    		updateAnimalWeaponMap(animals.getNameToIndexList().get(animal), weapons.getNameToIndexList().get(weapon));
    		updateHuntMap(animal,weapon);
    		if(hasWon()){
    			result = Constants.WON;
    		}
    		else if(hasLost()){
    			result = Constants.LOST;
    		}
    	}
    	return result;
    }
}
