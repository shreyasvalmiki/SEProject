import java.util.*;
public class DisplayController {

	/**
	 * @param args
	 */
	
	private static boolean isExitGame = false;
	private static String command = new String();
	private static Scanner input = new Scanner(System.in);
	private static boolean hasWon = false;
	private static boolean hasLost = false;
	private static LevelController level = new LevelController();
	private static int levelNo = 1;
	private static Animals animals = new Animals();
	private static Weapons weapons = new Weapons();
	
	public static void main(String[] args) {
        // TODO code application logic here
        DisplayController disp = new DisplayController();
        
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
            
            //testing
            disp.displayList(animals.getNameToIndexList());
            disp.displayList(weapons.getNameToIndexList());
            
            command = input.next();
            boolean startedHunting = false;
            GameTimer timer = new GameTimer();
            while(!hasLost && !hasWon){
                if(command.equalsIgnoreCase("list animals"))
                {
                    disp.displayList(animals.getNameToIndexList());
                }
                else if(command.equalsIgnoreCase("list weapons"))
                {
                    disp.displayList(weapons.getNameToIndexList());
                }
                else if(command.equalsIgnoreCase("hunt"))
                {
                    if (!startedHunting)
                    {
                        startedHunting = true;
                        timer.start();
                    }
                    disp.hunt();
                }
                else
                {
                	System.out.println("I did not understand");
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
        displayHuntGrid();
        //test
        hasLost=true;
    }
    
    public void displayHuntGrid(){
    	HashMap<String,Integer> animalToIndex = new HashMap<String, Integer>();
    	animalToIndex = animals.getNameToIndexList();
        for(String s: level.huntMap.keySet()){
        	if(level.animalWeaponMap.keySet().contains(animalToIndex.get(s))){
        		System.out.print("\t"+s);
        	}
        	else
        	{
        		System.out.print("\t");
        	}
        }
        
        for(int i = 0; i<weapons.getWeaponCount();++i){
        	for(String s: level.huntMap.keySet()){
            	if(level.animalWeaponMap.keySet().contains(animalToIndex.get(s))){
            		System.out.print("\t"+level.huntMap.get(s).get(i));
            	}
            	else
            	{
            		System.out.print("\t");
            	}
            }
        }
    }

}
