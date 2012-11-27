import java.util.*;
public class DisplayController {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        // TODO code application logic here
        DisplayController proj = new DisplayController();
        boolean isExitGame = false;
        String command = new String();
        Scanner input = new Scanner(System.in);
        boolean hasWon = false;
        boolean hasLost = false;
        LevelController level = new LevelController();
        int levelNo = 1;
        Animals animals = new Animals();
        Weapons weapons = new Weapons();
        while(!isExitGame)
        {
            do
            {
                if(levelNo != 1 && levelNo != 2 && levelNo!=3)
                {
                    System.out.println("Enter Game Level 1, 2 or 3\n");
                }
                else
                {
                    System.out.println("Enter Game Level\n");
                }
                try
                {
                    levelNo = Integer.parseInt(input.next());                
                }
                catch(Exception e)
                {
                    levelNo = 0;
                }
            }while(levelNo != 1 && levelNo != 2 && levelNo!=3);
            
            level = LevelFactory.getController(levelNo);
            
            boolean startedHunting = false;
            GameTimer timer = new GameTimer();
            while(!hasLost && !hasWon){
                if(command.equalsIgnoreCase("list animals"))
                {
                    proj.displayList(animals.getNameToIndexList());
                }
                else if(command.equalsIgnoreCase("list weapons"))
                {
                    proj.displayList(weapons.getNameToIndexList());
                }
                else if(command.equalsIgnoreCase("hunt"))
                {
                    if (!startedHunting)
                    {
                        startedHunting = true;
                        timer.start();
                    }
                }
            }    
        }
    }
    
    public void displayList(HashMap<String,Integer> map){
        System.out.println();
        for(String s: map.keySet())
        {
            System.out.println(s);
        }
        System.out.println();
    }
    
    public void hunt(){
        
    }
    
    public void displayHuntGrid(){
        
    }

}
