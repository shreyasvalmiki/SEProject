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
        super.initLists();
    }
    public boolean hunt(){
    	return true;
    }
    public boolean isLoser(){
    	return true;
    }
}
