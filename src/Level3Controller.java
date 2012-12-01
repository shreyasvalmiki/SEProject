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
    	if(result == 5 && turnCount == 2){
    		isLoser = true;
    		result = 2;
    	}
    	if(result == 0)
    	{
    		updateAnimalWeaponMap(animals.getNameToIndexList().get(animal), weapons.getNameToIndexList().get(weapon));
    		updateHuntMap(animal,weapon);
    		if(hasWon()&& time > 60){
    			result = 1;
    		}
    		else if(hasWon() && time <= 60)
    		{
    			result = 3;
    		}
    		else if(hasLost()){
    			result = 2;
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
