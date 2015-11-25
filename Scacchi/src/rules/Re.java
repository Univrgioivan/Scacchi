package rules;
import java.util.ArrayList;


import model.ScacchiModel;
import model.punto;
import view.ScacchiView;


public class Re implements Pedina{
	 int g;
	int x;
	int y;
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public Re(int g, int x, int y){
		this.g=g;
		this.x=x;
		this.y=y;
	}
	public Re(int g){
		this.g=g;
		this.x=0;
		this.y=0;
	
	}

	public boolean moveTo(int c, int x1, int y1, int x2, int y2) {/*
		//deve essere implementato il controllo di altri re vicini
		if(x1==x2 && y1==y2)
			return false;
		if((x1+1==x2 || x1-1==x2 || y1+1==y2 || y1-1==y2) && (c==0?ScacchiModel.get(x2,y2)>10 : (ScacchiModel.get(x2,y2)!=0 && ScacchiModel.get(x2,y2)<7)))
			return true;
		*/
		return false;
		
	}
	
	public  ArrayList<punto> preemption(){
		return a();
	}
	
	public  ArrayList<punto> a(){
		ArrayList<punto> p= new ArrayList<punto>(0);
		int x1=x-1;
		int y1=y-1;
		
		for(int a=0; a<3; a++)
			
			for(int i=0; i<3; i++)
				
				if(ScacchiModel.isValid(x1+a, y1+i) && !(x1+a==x && y1+i==y))
					
					if(  (ScacchiModel.get(x1+a,y1+i)==null || ScacchiModel.get(x1+a,y1+i).getGiocatore()!=g) )
						//if( !ScacchiController.hasRe(ScacchiController.arrayListNoRe( (g==0)?1:0 ), new punto(x,y)))
						
							p.add(new punto(x1+a,y1+i));
		
		/*ArrayList<punto> cont = ScacchiController.arrayListNoRe( (g==0)?1:0);
		//ScacchiView.colorizeG(cont);
		System.out.println("arry" + cont.size());
		
		for(int w=0; w<p.size(); w++)
			for(int g=0;g< cont.size(); g++)
				if( p.get(w).getX() == cont.get(g).getX() && p.get(w).getY() == cont.get(g).getY() )
					p.remove(w);
		*/
		return p;
				
		
	}
	
	
	public Boolean hasRePreemption(punto g){
		ArrayList<punto> p = preemption();
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
						return false;
	}
	/*
	public static int testFinish(int c){
		ArrayList<punto> t = new ArrayList<punto>(0);
		
		//controllo della vittoria dei bianchi
		System.out.println("entrato");
		for(int x=0; x<=7; x++)
			for(int y=0; y<=7; y++){
				
				if((c==0) ? (ScacchiModel.get(x, y)>10) : (ScacchiModel.get(x, y)<10 && ScacchiModel.get(x, y)!=0) ){
					//t=ScacchiFrame.type(ScacchiModel.get(x, y),x,y);
					for(int k=0;k<t.size();k++){
						System.out.println(k);
						punto w = t.get(k);
						if( (c==0) ? ScacchiModel.get(w.getX(), w.getY())==6 : ScacchiModel.get(w.getX(), w.getY())==16 )
						return 1;
					}
				}
			}
		
		return 0;		
		
	}
	
	public static punto searchKing(int c){
		
		return new punto(x,y);
	}*/

	@Override
	public int getGiocatore() {
		// TODO Auto-generated method stub
		return g;
	}
	
	public String getImg() {
		if(g==0)
			return "img/reb.png";
		else 
			return "img/ren.png";
	}

	@Override
	public boolean isRe(int g) {
		// TODO Auto-generated method stub
		if (this.g==g)
			return true;
		else
			return false;
	}
	@Override
	public ArrayList<punto> preemptionRe(punto g) {
		
		ArrayList<punto> p = new ArrayList<punto>(0);
		p.add( new punto(g.getX(), g.getY()) );
		return p;
		
	}
}
