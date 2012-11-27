/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Shreyas Valmiki
 */
public class LevelFactory {
    public static LevelController getController(int level)
    {
        if(level==1)
        {
            return new Level1Controller();
        }
        else if(level==2)
        {
            return new Level2Controller();
        }
        else
        {
            return new Level3Controller();
        }
    }
}
