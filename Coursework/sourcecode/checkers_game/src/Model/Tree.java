/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author alexi_000
 * @param <T>
 */
public class Tree<T> {
    
    private T data = null;
    private ArrayList<Tree> children = new ArrayList<>();
    private Tree parent = null;

    public Tree(T data) {
        this.data = data;
    }

    public void addChild(Tree child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(T data) {
        Tree<T> newChild = new Tree<>(data);
        newChild.setParent(this);
        children.add(newChild);
    }

    public void addChildren(ArrayList<Tree> children) {
        for(Tree t : children) {
            t.setParent(this);
        }
        this.children.addAll(children);
    }

    public ArrayList<Tree> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Tree parent) {
        this.parent = parent;
    }

    public Tree getParent() {
        return parent;
    }
    
    //public [][int,ArrayList<Check>]
    
    public void drawTree(){
        if(this.getData() instanceof Check){
            Check currentCheck = (Check)this.getData();
            System.out.println("Line : "+(currentCheck.getLineNumber()+1)+" | Colomn "+(currentCheck.getColomnNumber()+1));
        } else {
            System.out.println(this.getData());

        }
        ArrayList<Tree> children = this.getChildren();
        for(Tree currentChild : children){
            currentChild.drawTree();
        }
        
    }
    
}
