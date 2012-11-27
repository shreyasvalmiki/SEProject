/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.HashMap;

/**
 *
 * @author Shreyas Valmiki
 */
public class Animals {
    
    private HashMap<Integer,String> animalList = new HashMap<Integer, String>();
    private HashMap<String,Integer> nameToIndexList = new HashMap<String, Integer>();
    
    public Animals(){
        animalList.put(1,"Bear");
        animalList.put(2,"Deer");
        animalList.put(3,"Hare");
        animalList.put(4,"Rabbit");
        animalList.put(5,"Boar");
        
        nameToIndexList.put("Bear",1);
        nameToIndexList.put("Deer",2);
        nameToIndexList.put("Hare",3);
        nameToIndexList.put("Rabbit",4);
        nameToIndexList.put("Boar",5);
    }
    public HashMap<Integer,String> getAnimalList(){
        return animalList;
    }
    public HashMap<String,Integer> getNameToIndexList(){
        return nameToIndexList;
    }
}
