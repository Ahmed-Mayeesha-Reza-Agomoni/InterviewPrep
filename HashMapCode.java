import java.util.*;

public class HashMapCode{
    static class HashMap<K,V>{//generics; we dont know K and V's type so generic type
        private class Node{

            K key;
            V value;

            public Node(K key,V value){
                this.key=key;
                this.value=value;
            }
        }


        private int n;//n->nodes
        private int N;//N->buckets
        private LinkedList<Node> buckets[];//N=buckets.length

        @SuppressWarnings("unchecked")//to remove warnings(a lot of lines)
        public HashMap(){
            this.N=4;
            this.buckets=new LinkedList[4];

            for (int i=0;i<4;i++){
                this.buckets[i]=new LinkedList<>();//EMPTY linkedlist -> new LinkedList()
                //we do not want 'null' values; we want empty values
            }
        }

        private int hashFunction(K key){

            //our bucket_index will lie[0,N-1]
            int bucket_index=key.hashCode();
            //the above hashCode() can return any integer
            //we want +ve values
            return Math.abs(bucket_index)%N;
        }

        private int searchinLL(K key, int bucket_index){

            LinkedList<Node> ll=buckets[bucket_index];

            for (int i=0;i<ll.size();i++){
                if(ll.get(i).key==key){
                    return i;//data_index
                }
            }

            //key does not exist
            return -1;
        }

        @SuppressWarnings("unchecked")
        private void rehash(){

            LinkedList<Node> oldBucket[]=buckets;
            buckets=new LinkedList[N*2];

            //empty LinkedList; initialization
            for(int i=0;i<N*2;i++){
                buckets[i]=new LinkedList<>();
            }

            for(int i=0;i<oldBucket.length;i++){
                LinkedList<Node> ll=oldBucket[i];

                for(int j=0;j<ll.size();j++){
                    Node node=ll.get(j);
                    put(node.key,node.value);
                }
            }
        }

        public void put(K key, V value){

            int bucket_index=hashFunction(key);

            //this searches if in the LL of b_i, our key exists
            int data_index=searchinLL(key,bucket_index);

            if (data_index==-1){
                //key does not exist
                //hence, create new node
                buckets[bucket_index].add(new Node(key,value));
                n++;
            }
            else{
                //key exists
                Node node=buckets[bucket_index].get(data_index);
                node.value=value;
            }

            double lambda = (double)n/N;
            if(lambda>2.0){
                //rehashing
                //create buckets of double the initial size
                rehash();
            }
        }

        public V get(K key){
            int bucket_index=hashFunction(key);

            //this searches if in the LL of b_i, our key exists
            int data_index=searchinLL(key,bucket_index);

            if (data_index==-1){
                //key does not exist
                //hence, create new node
                return null;
            }
            else{
                //key exists
                Node node=buckets[bucket_index].get(data_index);
                return node.value;
            }
        }


        public boolean containsKey(K key){
            int bucket_index=hashFunction(key);

            int data_index=searchinLL(key,bucket_index);

            if (data_index==-1){
                //key does not exist
                //hence, create new node
                return false;
            }
            else{
                //key exists
                return true;
            }
        }


        public V remove(K key){

            int bucket_index=hashFunction(key);

            //this searches if in the LL of b_i, our key exists
            int data_index=searchinLL(key,bucket_index);

            if (data_index==-1){
                return null;
            }
            else{
                //key exists
                //now, remove
                Node node=buckets[bucket_index].remove(data_index);
                n--;
                return node.value;
            }
        }

        public boolean isEmpty(){
            
            return n==0;
        }


        public ArrayList<K> keySet(){

            ArrayList<K> keys=new ArrayList<>();

            for (int i=0;i<buckets.length;i++){
                LinkedList<Node> ll=buckets[i];//b_i

                for(int j=0;j<ll.size();j++){
                    //data_index is j
                    Node node=ll.get(j);
                    keys.add(node.key);
                }
            }

            return keys;
        }


    }

    public static void main(String[] args){
        HashMap<String, Integer> map=new HashMap<>();

        map.put("India",190);
        map.put("China",200);
        map.put("US",250);
        map.put("Bangladesh",50);

        ArrayList<String> keys=map.keySet();

        for(int i=0;i<keys.size();i++){
            System.out.println(keys.get(i)+" : "+map.get(keys.get(i)));
        }
    }
}