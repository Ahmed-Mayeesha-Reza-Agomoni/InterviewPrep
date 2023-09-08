import java.util.*;

public class HashMapProblem2{

    public static int union(int arr1[],int arr2[]){
        
        HashSet<Integer> set=new HashSet<>();

        for (int i=0;i<arr1.length;i++){
            set.add(arr1[i]);
        }

        for (int i=0;i<arr2.length;i++){
            set.add(arr2[i]);
        }

        return set.size();
        //O(n)
    }

    public static int intersection(int arr1[],int arr2[]){
        
        HashSet<Integer> set=new HashSet<>();

        for (int i=0;i<arr1.length;i++){
            set.add(arr1[i]);
        }

        int count=0;

        for (int i=0;i<arr2.length;i++){
            if(set.contains(arr2[i])){
                count+=1;
                //we will remove the elem to avoid
                //duplicate counts
                set.remove(arr2[i]);
            }
        }

        return count;
        //O(n)
    }
    public static void main(String[] args){

        int arr1[]={7,3,9};
        int arr2[]={6,3,9,2,9,4};

        System.out.println(union(arr1,arr2));
        System.out.println(intersection(arr1,arr2));

    }
}