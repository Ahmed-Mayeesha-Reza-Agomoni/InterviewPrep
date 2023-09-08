import java.util.*;

public class HashMapProblem3{

    public static String getStart(HashMap<String, String> tickets){
        HashMap<String, String> reverseMap=new HashMap<>();

        for(String key: tickets.keySet()){
            reverseMap.put(tickets.get(key),key);
            //from becomes to and to becomes from
        }

        for(String key: tickets.keySet()){
            if(!reverseMap.containsKey(key)){
                return key;
            }
        }

        return null;
        
    }
    public static void main(String[] args){

        HashMap<String, String> tickets=new HashMap<>();

        tickets.put("Chennai","Bengaluru");
        tickets.put("Mumbai","Delhi");
        tickets.put("Goa","Chennai");
        tickets.put("Delhi","Goa");

        String start=getStart(tickets);

        while(tickets.containsKey(start)){
            System.out.print(start+"->");
            start=tickets.get(start);
        }

        System.out.println(start);
    }
}