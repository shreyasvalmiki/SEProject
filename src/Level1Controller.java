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
        super.initLists();
    }
}
