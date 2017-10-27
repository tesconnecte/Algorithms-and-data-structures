/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 40326915
 */
public class Node {
    public String word;
    public int count;
    public Node left;
    public Node right;
    
    public Node(String word, int count ) {
        this.word = word;
        this.count = count;
        this.left = null;
        this.right = null;       
        
    }

    public Node(String word, int count, Node left, Node right) {
        this.word = word;
        this.count = count;
        this.left = left;
        this.right = right;       
        
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
    /*public Node search(String stringToInsert){
        Node currentNode = this;
        Node foundNode = null;
        while(currentNode!=null){
            if(currentNode.getWord().equals(word)){
                foundNode=
            }
            
        }
    }*/
    
    public void insert(Node nodeToInsert){
        
    }
    
    public void addLeftChild(Node leftChild){
        this.setLeft(leftChild);
    }
    
    public void addRightChild(Node rightChild){
        this.setRight(rightChild);
    }
    
    
    
    
    
    
}
