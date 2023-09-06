import java.util.ArrayList;

public class BST{

    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
        }
    }

    public static Node insert(Node root, int val){

        if (root==null){
            root=new Node(val);
            return root;
        }

        //assuming no duplicates

        //now,comparisons for insertion
        if(root.data>val){
            root.left=insert(root.left,val);
        }
        else{
            root.right=insert(root.right,val);
        }

        return root;
        
    }

    public static void inorder(Node root){
        //left subtree, root, right subtree

        if (root==null){
            return;
        }

        inorder(root.left);
        System.out.print(root.data+"  ");
        inorder(root.right);
    }

    public static boolean search(Node root,int key){

        if (root==null){
            return false;
        }


        if (root.data>key){
            //search in the left subtree
            return search(root.left, key);
        }
        else if (root.data==key){
            return true;
        }
        else{
            //search in the right subtree
            return search(root.right,key);
        }
        //takes O(H) as in height of the tree

    }

    //delete node
    public static Node delete(Node root,int val){

        if (root.data>val){
            root.left=delete(root.left, val);
        }
        else if (root.data<val){
            root.right=delete(root.right,val);
        }
        //uptil now we have been searching for our node
        else{
            //3 cases

            //c1: leaf node; attach null
            if (root.left==null && root.right==null){
                return null;
            }

            //c2: has only one child and so we return that child
            if (root.left==null){
                return root.right;
            }
            else if (root.right==null){
                return root.left;
            }


            //c2: attach the leftmost elem of right subtree
            //a.k.a. inorder successor; the next element of the deleted node's in ascending order
            Node IS = inorderSuccessor(root.right);
            //^ we will first go to the right child
            //and then the left most element of the right subtree
            //update root's data with IS's data as IS's new pos is the deleted node's place
            root.data=IS.data;
            //delete the inorder succ from its o.g. pos
            root.right=delete(root.right, IS.data);

        }

        return root;
    }

    public static Node inorderSuccessor(Node root){

        //finding the leftmost elem in the right subtree
        while (root.left!=null){
            root=root.left;
        }

        return root;
    }

    public static void printInRange(Node root, int x, int y){

        if (root==null){
            return;
        }
        //case 1: between range
        if (root.data>=x && root.data<=y){
            printInRange(root.left, x, y);
            System.out.print(root.data+"  ");
            printInRange(root.right, x, y);
        }

        else if (root.data>=y){
            //c2: print left subtree
            printInRange(root.left, x, y);
        }
        else{
            //c3:root.data<=x
            printInRange(root.right, x, y);
        }
    }

    public static void printRootToLeaf(Node root, ArrayList<Integer> path){

        if (root==null){
            return;
        }

        path.add(root.data);

        //leafnode: print path
        if(root.left==null && root.right==null){
            printPath(path);
        }
        else{
            //for non leaf nodes
            printRootToLeaf(root.left, path);
            printRootToLeaf(root.right, path);
        }

        path.remove(path.size()-1);
    }

    public static void printPath(ArrayList<Integer> path){

        for (int i=0; i<path.size();i++){
            System.out.print(path.get(i)+"->");
        }
        System.out.println();
    }

    public static void main(String args[]){
        int values[]={8,5,3,1,4,6,10,11,14};
        Node root=null;

        for (int i=0;i<values.length;i++){
            root=insert(root,values[i]);
        }

        inorder(root);
        System.out.println();

        // if (search(root, 7)){
        //     System.out.println("They key exists in the tree");
        // }
        // else{
        //     System.out.println("The key does not exist in the tree");
        // }

        // delete(root,10);
        // inorder(root);
        // System.out.println();
        //printInRange(root, 3, 12);
        printRootToLeaf(root, new ArrayList<>());
    }
}