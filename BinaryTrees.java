import java.util.*;
public class BinaryTrees{
    
    static class Node{
        int data;
        Node leftchild;
        Node rightchild;

        //constructor
        Node(int data){
            this.data=data;
            this.leftchild=null;
            this.rightchild=null;
        }
    }


    static class BinaryTree{
        //idx will be the iterator for the node list
        static int idx=-1;

        public static Node buildTree(int nodes[]) {
            //this will return our root node

            //creating our root node which will be at the beginning of the list
            idx++;

            if(nodes[idx]==-1){
                //null node
                return null;
            }

            Node newNode= new Node(nodes[idx]);
            newNode.leftchild=buildTree(nodes);
            newNode.rightchild=buildTree(nodes);
             
            return newNode;
        }
    }

    public static void preorder(Node root){
        //root,left subtree, right subtree

        //base case
        if (root==null){
            return;
        }

        System.out.print(root.data+"  ");
        preorder(root.leftchild);
        preorder(root.rightchild);

        //takes O(n) time
    }

    public static void inorder(Node root){
        //left subtree, root, right subtree

        if (root==null){
            return;
        }

        inorder(root.leftchild);
        System.out.print(root.data+"  ");
        inorder(root.rightchild);

        //takes O(n) time
    }

    public static void postorder(Node root){
        //root, right subtree, left subtree

        if (root==null){
            return;
        }

        postorder(root.leftchild);
        postorder(root.rightchild);
        System.out.print(root.data+"  ");

        //takes O(n) time
    }

    public static void levelorder(Node root){
        //we will use Queue's FIFO structure
        //put null in the queue to indicate that we will print next line
        if (root==null){
            //empty tree
            System.out.println("The tree is empty");
            return;
        }
        Queue<Node> q=new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()){
            Node currentNode=q.remove();

            if (currentNode==null){
                //we print the next line
                //a.k.a the current level has ended
                System.out.println();
                if (q.isEmpty()){
                    //we have traversed the tree in its entirety
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                System.out.print(currentNode.data+"  ");

                if (currentNode.leftchild!=null){
                    q.add(currentNode.leftchild);
                }

                if (currentNode.rightchild!=null){
                    q.add(currentNode.rightchild);
                }
            }

        }
    }

    public static int countofNodes(Node root){
        //we will call this function recurisvely
        //go to the very bottom/leaf of left subtree
        //go to the very bottom/leaf or right subtree
        //leftcount+rightcount+1

        if (root==null){
            //we have reached the very end
            return 0;
        }

        int leftcount=countofNodes(root.leftchild);
        int rightcount=countofNodes(root.rightchild);

        return leftcount+rightcount+1;
        //Time Complexity -> O(n)
    }

    public static int sumofNodes(Node root){

        if (root==null){
            //we have reached the very end
            return 0;
        }

        int leftSum=sumofNodes(root.leftchild);
        int rightSum=sumofNodes(root.rightchild);

        return leftSum+rightSum+root.data;
        //Time Complexity -> O(n)
    }

    public static int height(Node root){

        if (root==null){
            return 0;
        }

        int leftheight=height(root.leftchild);
        int rightheight=height(root.rightchild);

        int myHeight=Math.max(leftheight,rightheight)+1;

        return myHeight;
        //Time Complexity -> O(n)
    }

    public static int diameter(Node root){
        
        if (root==null){
            return 0;
        }

        //we have touched each node once for the next 2 lines
        int diam1=diameter(root.leftchild);
        int diam2=diameter(root.rightchild);
        //we have touched each node once for the next line
        int diam3=height(root.leftchild)+height(root.rightchild)+1;
        //hence, time complexity is O(n^2)

        return Math.max(diam3,Math.max(diam1,diam2));
        //takes O(n^2)
    }

    static class TreeInfo{
        int ht;
        int diam;

        TreeInfo(int ht,int diam){
            this.ht=ht;
            this.diam=diam;
        }
    }

    public static TreeInfo linearTimeDiameter(Node root){

        if (root==null){
            return new TreeInfo(0,0);
        }

        TreeInfo left=linearTimeDiameter(root.leftchild);
        TreeInfo right=linearTimeDiameter(root.rightchild);

        int myHeight=Math.max(left.ht,right.ht)+1;

        int diam1=left.diam;
        int diam2=right.diam;
        int diam3=left.ht+right.ht+1;

        int myDiam=Math.max(diam3,Math.max(diam1,diam2));

        TreeInfo myInfo=new TreeInfo(myHeight, myDiam);

        return myInfo;

        //takes O(n) time

    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(){

        }

        TreeNode(int val){
            this.val=val;
        }

        TreeNode(int val, TreeNode left, TreeNode right){
            this.val=val;
            this.left=left;
            this.right=right;
        }
    }

    public boolean isIdentical(TreeNode root, TreeNode subroot){
        if (root==null && subroot==null){
            //have reached both leaves
            return true;
        }

        if (root==null || subroot==null){
            //have reached only one tree's leaves
            return false;
            //bcs they should reach the leaf node at the same time
        }

        if (root.val==subroot.val){
            return isIdentical(root.left, subroot.left) &&
        isIdentical(root.right, subroot.right);
        }
        else{
            return false;
        }
        
    }

    public boolean isSubtree(TreeNode root, TreeNode subroot){

        if (subroot==null){
            return true;
        }

        if (root==null){
            //empty tree
            return false;
        }

        if (root.val==subroot.val){
            if(isIdentical(root, subroot)){
                return true;
            }
        }

        return isSubtree(root.left, subroot) || isSubtree(root.right, subroot)

    }


    public static void main(String[] args) {

        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        BinaryTree tree=new BinaryTree();
        Node root = tree.buildTree(nodes);
        
        //System.out.println(root.data);
        //preorder(root);
        //inorder(root);
        //postorder(root);
        //levelorder(root);
        //System.out.println(countofNodes(root));
        //System.out.println(sumofNodes(root));
        //System.out.println(height(root));
        //System.out.println(diameter(root));
        System.out.println(linearTimeDiameter(root).diam);
        
    }

}