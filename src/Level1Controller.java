/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreyas Valmiki
 */
public class Level1Controller extends LevelController{
    public Level1Controller(){
        super.mapper = new AnimalWeaponMapper(0,3,2);
        super.isTimeNeeded = false;
        super.initLists();
    }
    public int hunt(String animal,String weapon, long time, int turnCount){
    	int result = super.hunt(animal, weapon, time, turnCount);
    	if(result == 0)
    	{
    		updateAnimalWeaponMap(animals.getNameToIndexList().get(animal), weapons.getNameToIndexList().get(weapon));
    		updateHuntMap(animal,weapon);
    		if(hasWon()){
    			result = 1;
    		}
    		else if(hasLost()){
    			result = 2;
    		}
    	}
    	return result;
    }
}
