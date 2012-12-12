
import java.util.HashMap;

public class Animals {
    
    private HashMap<Integer,String> animalList = new HashMap<Integer, String>();
    private HashMap<String,Integer> nameToIndexList = new HashMap<String, Integer>();
    
    public Animals(){
        animalList.put(1,"bear");
        animalList.put(2,"deer");
        animalList.put(3,"hare");
        animalList.put(4,"rabbit");
        animalList.put(5,"boar");
        
        nameToIndexList.put("bear",1);
        nameToIndexList.put("deer",2);
        nameToIndexList.put("hare",3);
        nameToIndexList.put("rabbit",4);
        nameToIndexList.put("boar",5);
    }
    public HashMap<Integer,String> getAnimalList(){
        return animalList;
    }
    public HashMap<String,Integer> getNameToIndexList(){
        return nameToIndexList;
    }
}
