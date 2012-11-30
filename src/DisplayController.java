import java.util.*;
public class DisplayController {

	/**
	 * @param args
	 */
	
	private static boolean isExitGame = false;
	private static int command = 0;
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
            //disp.displayList(animals.getNameToIndexList());
            //disp.displayList(weapons.getNameToIndexList());
            boolean startedHunting = false;
            GameTimer timer = new GameTimer();
            while(!hasLost && !hasWon){
            	System.out.println("Enter 1 to list animals");
            	System.out.println("Enter 2 to list weapons");
            	System.out.println("Enter 3 to hunt");
            	System.out.println("Enter 4 to exit");
            	command = input.nextInt();
            	
            	
                //if(command.equalsIgnoreCase("list animals"))
            	if(command == 1)
                {
                    disp.displayList(animals.getNameToIndexList());
                }
                //else if(command.equalsIgnoreCase("list weapons"))
            	else if(command == 2)
                {
                    disp.displayList(weapons.getNameToIndexList());
                }
                //else if(command.equalsIgnoreCase("hunt"))
            	else if(command == 3)
                {
                    if (!startedHunting)
                    {
                        startedHunting = true;
                        timer.start();
                    }
                    disp.hunt();
                }
            	else if(command == 4)
            	{
            		System.exit(1);
            	}
                else
                {
                	System.out.println("Enter 1, 2 or 3");
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
        		if(s.length()<=7)
        		{
        			System.out.print("\t\t"+s+"\t");
        		}
        		else
        		{
        			System.out.print("\t\t"+s);
        		}
        	}
        	else
        	{
        		System.out.print("\t\t\t");
        	}
        }
        System.out.println("\n\t\t------------------------------------------------------------------------------------------------------");
        for(int i = 0; i<weapons.getWeaponCount();++i){
        	for(String s: level.huntMap.keySet()){
            	if(level.animalWeaponMap.keySet().contains(animalToIndex.get(s))){
            		if(level.huntMap.get(s).get(i).length()<=7)
            		{
            			System.out.print("\t\t"+level.huntMap.get(s).get(i)+"\t");
            		}
            		else
            		{
            			System.out.print("\t\t"+level.huntMap.get(s).get(i));
            		}
            	}
            	else
            	{
            		System.out.print("\t\t\t");
            	}
            }
        	System.out.println();
        }
    }

}
