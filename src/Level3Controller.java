import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreyas Valmiki
 */
public class Level3Controller extends LevelController {
    public Level3Controller(){
        super.mapper = new AnimalWeaponMapper(2,3,0);
        super.isTimeNeeded = true;
        super.initLists();
    }
    
    public int hunt(String animal,String weapon, long time, int turnCount){
    	int result = super.hunt(animal, weapon, time, turnCount);
    	//if(result == Constants.WEAPON_USED && turnCount == 2){
    	if(result == Constants.WEAPON_USED && wrongAttemptCount == 2){
    		isLoser = true;
    		result = Constants.LOST;
    	}
    	if(result == Constants.CONTINUE)
    	{
    		updateAnimalWeaponMap(animals.getNameToIndexList().get(animal), weapons.getNameToIndexList().get(weapon));
    		updateHuntMap(animal,weapon);
    		if(hasWon()&& time > 60){
    			result = Constants.WON;
    		}
    		else if(hasWon() && time <= Constants.ONE_MINUTE)
    		{
    			result = Constants.WON_WITH_TIME;
    		}
    		else if(hasLost()){
    			result = Constants.LOST;
    		}
    	}
    	return result;
    }
//    public boolean hasLost(){
//    	boolean res = super.hasLost();
//    	return res || isLoser;
//    }
    
    protected void updateHuntMap(String animal, String weapon){
    	huntMap.remove(animal);
    }
}
