import java.util.*;

public class HashMapProblem1{

    public static void majorityElement(int nums[]){
        HashMap<Integer,Integer> map = new HashMap<>();

        int n=nums.length;
        for (int i=0;i<n;i++){
            //if key exists, increment value by 1
            if(map.containsKey(nums[i])){
                map.put(nums[i],map.get(nums[i])+1);
            }
            else{
                //put key with frequency 1
                map.put(nums[i],1);
            }
        }

        //map.keySet() returns all the keys only
        for(int key:map.keySet()){
            if (map.get(key)>n/3){
                System.out.println(key);
            }
        }
    }
    public static void main(String[] args){

        int nums[] = {1,3,2,5,1,3,1,5,1};
        int nums1[]={1,2};
        // will take O(n) time

        majorityElement(nums1);

    }
}