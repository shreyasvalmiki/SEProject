/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreyas Valmiki
 */
public class Level2Controller extends LevelController{
    public Level2Controller(){
        super.mapper = new AnimalWeaponMapper(1,3,1);
        super.initLists();
    }
}
