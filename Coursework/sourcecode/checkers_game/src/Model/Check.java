package Model;

public class Check {

	Piece checkPiece;
	private int lineNumber;
	private int colomnNumber;
	Gameboard gameboard;

        public Check(int lineNumber, int colomnNumber, Gameboard gameboard) {
            //this.checkPiece = checkPiece;
            this.lineNumber = lineNumber;
            this.colomnNumber = colomnNumber;
            this.gameboard = gameboard;
            
            Piece checkPiece = null;
            /*
            Test Code            
            */
            /*if(lineNumber==3){
                if((colomnNumber==6)||(colomnNumber==8)){
                    checkPiece = new Man(this,"black",0);
                    this.checkPiece=checkPiece;
                }
            }else if(lineNumber==1){
                if(colomnNumber==4){
                    checkPiece = new Man(this,"black",0);
                    this.checkPiece=checkPiece;
                }
            } else if((lineNumber==2)&&(colomnNumber==9)){
                checkPiece = new Man(this,"white",0);
                this.checkPiece=checkPiece;
            } else {
                this.checkPiece=checkPiece;
            }*/
            /*
            End test code
            */            
            if((lineNumber>=0)&&(lineNumber<=3)){
                if(((lineNumber%2)==0)&&((colomnNumber%2)==1)){
                    checkPiece = new Man(this,"black",0);
                    this.checkPiece=checkPiece;
                } else if(((lineNumber%2)==1)&&((colomnNumber%2)==0)){
                    checkPiece = new Man(this,"black",0);
                    this.checkPiece=checkPiece;
                } else {
                    this.checkPiece=checkPiece;
                }                
            } else if((lineNumber>=6)&&(lineNumber<=9)){
                if(((lineNumber%2)==0)&&((colomnNumber%2)==1)){
                        checkPiece = new Man(this,"white",1);
                        this.checkPiece=checkPiece;
                    } else if(((lineNumber%2)==1)&&((colomnNumber%2)==0)){
                        checkPiece = new Man(this,"white",1);
                        this.checkPiece=checkPiece;
                    } else {
                        this.checkPiece=checkPiece;
                    }
            } else {
                this.checkPiece=checkPiece;
            }
        }

        public Piece getcheckPiece() {
            return checkPiece;
        }

        public void setcheckPiece(Piece checkPiece) {
            this.checkPiece = checkPiece;
        }       
        

        public Gameboard getGameboard() {
            return gameboard;
        }      
        

	public int getLineNumber() {
		return this.lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColomnNumber() {
		return this.colomnNumber;
	}

	public void setColomnNumber(int colomnNumber) {
		this.colomnNumber = colomnNumber;
	}

	public boolean isOccupied() {
		if(this.getcheckPiece()==null){
                    return false;
                } else {
                    return true;
                }                
	}

}