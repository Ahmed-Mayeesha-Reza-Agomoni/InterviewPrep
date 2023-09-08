import java.util.*;

public class TrieDS{
    static class Node{
        Node[] children;
        boolean eow;//endofword

        public Node(){
            children=new Node[26];//26 alphabets for each node
            //initializing to book memory for the nodes
            for(int i=0;i<26;i++){
                children[i]=null;
            }

            eow=false;
        }
    }

    static Node root=new Node();
    //root will  be empty 

    public static void insert(String word){
        //O(L) -> length of the word

        Node current=root;
        int len=word.length();
        
        for(int i=0;i<len;i++){
            int idx=word.charAt(i)-'a';

            if(current.children[idx]==null){
                //add new node
                current.children[idx]=new Node();
            }
            
            if(i==len-1){
                current.children[idx].eow=true;
            }
            current=current.children[idx];
        }
    } 

    public static boolean search(String key){
        //O(L)

        Node current=root;

        for(int i=0;i<key.length();i++){

            int idx=key.charAt(i)-'a';

            if(current.children[idx]==null){
                //does not exist
                return false;
            }

            //if we are at the last char of the key
            //as well as the trie data structure
            //but the trie ds's eow is not true
            //key does not exist
            if(current.children[idx].eow==false && i==key.length()-1){
                return false;
            }

            current=current.children[idx];
        }

        return true;
    }

    public static boolean wordBreak(String key){

        //for empty string,  return true
        //as root is considered empty
        if(key.length()==0){
            return true;
        }

        for(int i=1;i<=key.length();i++){

            String firstPart=key.substring(0, i);
            String secondPart=key.substring(i);

            //directly search first part in trie
            //recursively cut secondpart in wordBreak(_)
            if(search(firstPart) && wordBreak(secondPart)){
                return true;
            }
        }

        return false;
    }

    public static boolean startsWith(String prefix){

        Node current=root;

        for(int i=0;i<prefix.length();i++){
            int idx=prefix.charAt(i)-'a';

            if(current.children[idx]==null){
                return false;
            }

            current=current.children[idx];
        }

        return true;
    }

    public static int countNode(Node root){

        if(root==null){
            return 0;
        }

        int count=0;
        for(int i=0;i<26;i++){
            if(root.children[i]!=null){
                count+=countNode(root.children[i]);
            }
        }

        return count+1;
    }

    public static void longestWord(Node root,StringBuilder temp){

        if(root==null){
            return;
        }

        for(int i=0;i<26;i++){
            if(root.children[i]!=null && root.children[i].eow==true){

                temp.append((char)(i+'a'));

                if(temp.length()>ans.length()){
                    ans=temp.toString();
                }

                longestWord(root.children[i], temp);

                temp.deleteCharAt(temp.length()-1);
            }
        }
    }

    public static void main(String[] args){
        // String words[]={"i","like","sam","samsung","mobile"};
        // String key="ilikesamsung";

        // for(int i=0;i<words.length;i++){
        //     insert(words[i]);
        // }

        // System.out.println(search("their"));
        // System.out.println(search("thor"));
        // System.out.println(search("an"));
        //System.out.println(wordBreak(key));
        //System.out.println(startsWith("mob"));

        String str="ababa";

        for(int i=0;i<str.length();i++){

            String suffix=str.substring(i);
            insert(suffix);

        }

        System.out.println(countNode(root));

    }
}