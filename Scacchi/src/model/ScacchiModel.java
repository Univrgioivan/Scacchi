package model;
import rules.*;
import view.ScacchiView;


public class ScacchiModel {
	
	ScacchiView view;
	
	
	
	//creazione e inizializzazione scacchiera
	//al costruttore dei pezzi viene passato il numero del giocatore 0 corrisponde ai bianchi 1 ai neri
	
	public static Pedina[][] Scacchiera = { {new Torre(0),new Cavallo(0),new Alfiere(0),new Donna(0),new Re(0),new Alfiere(0),new Cavallo(0),new Torre(0)},
		       {new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0)},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {null,null,null,null,null,null,null,null},
		       {new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1)},
		       {new Torre(1),new Cavallo(1),new Alfiere(1),new Donna(1),new Re(1),new Alfiere(1),new Cavallo(1),new Torre(1)} };
	
	//la var giocatore tiene il turno, viene inizializzata a 0 perchè i bianchi muovono per primi
	static int giocatore = 0;
	
	public ScacchiModel(){
		
		//assegno le coordinate alle pedine della scacchiera
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				if(Scacchiera[i][j]!=null){
					Scacchiera[i][j].setX(i);
					Scacchiera[i][j].setY(j);
				}
			}
		}
		
	}
	/*
	if(t==0){
		if((giocatore==0) ? (Scacchi.get(x, y)<10 && Scacchi.get(x, y)!=0 ) : (Scacchi.get(x, y)!=0 && (Scacchi.get(x, y)>10 && Scacchi.get(x, y)!=0) )){
			x1=x;
			y1=y;
			t++;
			System.out.println("1");
			p = type(Scacchi.get(x,y),x,y); 
			colorizeRed();
		}
	}
		
	
	else{
		x2=x;
		y2=y;
		if(x1==x2 && y1==y2){
			t=0;
			colourButton();
		}
		
		
		if(s.mossa(x1,y1,x2,y2,p)==1){
			System.out.println("2");
			t=0;
			if(giocatore==0)
				giocatore++;
			else
				giocatore=0;
			onConfigurationChange();
			isFinish();
			
		}
	}
	
	System.out.println(s);
		
}

	public static int mossa(int x1, int y1, int x2, int y2, ArrayList<punto> p){
		
				// "turno dell' avversario"
			if(Scacchiera[x1][y1]==null){}
				// "cella vuota"
			if(controlloMossa(p, x2, y2)){
				//bisogna controllare che la mossa sia valida tramite la classe della pedina
				Scacchiera[x2][y2]=Scacchiera[x1][y1];
				//System.out.println(y2);
				//System.out.println(Scacchiera[x2][y2]);
				Scacchiera[x1][y1]=null;
		
				return 1;
				
				
			}
		
		return 0;
	}
	
	private static boolean controlloMossa(ArrayList<punto> p, int x2, int y2){
		
		for(int i=0;i<p.size();i++){
			punto p1 = p.get(i);
			if(p1.getX()==x2 && p1.getY()==y2)
				return true;
		}	
		return false;
	}
	*/
	@Override
	public String toString(){
		String s="";
		for(int i=0;i<8;i++){
			for(int y=0; y<8; y++)
				s= s + " " + Scacchiera[i][y];
		s+="\n";
		}
		return s;
	}

	public static Pedina get(int x, int y){
		return Scacchiera[x][y];
	}
	
	public static boolean isValid(int x, int y){
		
		if(isValid(x) && isValid(y) )
				return true;
		
		return false;	
		
	}
	//controlla che non si esca dai limiti della scacchiera
	public static boolean isValid(int x){
		
		if(x>=0 && x<=7)
			return true;
		return false;
	}
	//inizializzazione per nuova partita
	public static void initialize(){
		Pedina[][] s = { {new Torre(0),new Cavallo(0),new Alfiere(0),new Donna(0),new Re(0),new Alfiere(0),new Cavallo(0),new Torre(0)},
			       {new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0),new Pedone(0)},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {null,null,null,null,null,null,null,null},
			       {new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1),new Pedone(1)},
			       {new Torre(1),new Cavallo(1),new Alfiere(1),new Re(1),new Donna(1),new Alfiere(1),new Cavallo(1),new Torre(1)} };
		
		for(int x=0;x<8;x++)
			for(int y=0; y<8; y++)
				Scacchiera[x][y]=s[x][y];
		giocatore=0;
		
	}
	
	
	public static void set(int x, int y, Pedina p) {
		
		if( x>=0 && x<=7 && y>=0 && y<=7 ){
			
			Scacchiera[x][y]=p;
			if(p!=null){
			Scacchiera[x][y].setX(x);
			Scacchiera[x][y].setY(y);
			}
		}
		
	}
	public void scambio(int x1, int y1, int x, int y) {
		Scacchiera[x][y]=Scacchiera[x1][y1];
		Scacchiera[x1][y1]=null;
		if(ScacchiModel.Scacchiera[x][y]!=null){
			Scacchiera[x][y].setX(x);
			Scacchiera[x][y].setY(y);
		}
		
	}

	
	
	
}
