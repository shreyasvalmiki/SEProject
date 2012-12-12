
public class Level1Controller extends LevelController{
    public Level1Controller(){
        super.mapper = new AnimalWeaponMapper(0,3,2);
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
