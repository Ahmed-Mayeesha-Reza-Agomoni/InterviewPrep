import java.util.*;

public class HashMapProblem4{
    public static void main(String[] args){

        //subarray of sum K
        int arr[] = {10,2,-2,-20,10};//ans=3
        int k=-10;

        HashMap<Integer,Integer> map = new HashMap<>();

        //empty subarray
        //map.put(sum,frequency)
        map.put(0,1);
        int sum=0;
        int ans=0;

        for(int i=0;i<arr.length;i++){
            sum+=arr[i];

            if(map.containsKey(sum-k)){
                ans+=map.get(sum-k);
            }

            if(map.containsKey(sum)){
                map.put(sum,map.get(sum)+1);
            }
            else{
                map.put(sum,1);
            }
        }

        System.out.println(ans);
    }
}