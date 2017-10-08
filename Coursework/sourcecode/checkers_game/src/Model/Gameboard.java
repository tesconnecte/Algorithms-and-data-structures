package Model;

import java.util.*;

public class Gameboard {

	private int nbLines;
	private int nbColomns;
	private Check[][] gameboardChecks;

        public Gameboard() {
            this.nbColomns=10;
            this.nbLines=10;
            this.gameboardChecks = new Check[nbLines][nbColomns];
            Check currentCheck;
            for(int i=0;i<nbLines;i++){
                for(int j=0;j<nbColomns;j++){
                    currentCheck = new Check(i, j, this);
                    this.gameboardChecks[i][j]=(currentCheck);
                }
            }
            
        }
        
        

	public int getNbLines() {
		return this.nbLines;
	}

	public int getNbColomns() {
		return this.nbColomns;
	}

        public Check[][] getGameboardChecks() {
            return gameboardChecks;
        }
        
        public Check getCheckByLineColomn(int line,int colomn){            
                return this.getGameboardChecks()[line][colomn];            
        }
        
        

	public void drawGameboard(){            
            //System.out.println("Nombre de lignes :"+mainGameboard.getNbLines());
            //System.out.println("Nombre de colonnes :"+mainGameboard.getNbColomns());
            System.out.print("  |");
            Check[][] checksList = this.getGameboardChecks();            
            for(int i=0;i<10;i++){
                if(i==9){
                    System.out.println((i+1)+"|");
                } else {
                    System.out.print((i+1)+" |");
                }                
            }
            
            for(int i=0;i<this.getNbLines();i++){
                if(i<9){
                    System.out.print((i+1)+" ");
                } else {
                    System.out.print(i+1);
                }
                for(int j=0;j<this.getNbColomns();j++){                    
                    System.out.print("|");
                    if(checksList[i][j].isOccupied()==true){
                        if(checksList[i][j].getcheckPiece().getColor()=="black"){
                            System.out.print("Bl");
                        } else {
                            System.out.print("Wh");
                        }
                        //System.out.println("Case de la ligne "+(currentCheck.getLineNumber()+1)+" et de colonne "+(currentCheck.getColomnNumber()+1)+ " occupé par un pion "+currentCheck.getcheckPiece().getColor());
                    } else {                  
                        System.out.print("  ");
                    }                
                }
                System.out.println("|");
            }
	}

}