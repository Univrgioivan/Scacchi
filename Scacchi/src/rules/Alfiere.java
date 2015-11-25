package rules;

import java.util.ArrayList;
import model.ScacchiModel;
import model.punto;



public class Alfiere implements Pedina{
	int g,x,y;
	
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
	public Alfiere(int g, int x, int y){
		this.g=g;
		this.x=x;
		this.y=y;
	}
	public Alfiere(int g){
		this.g=g;
		this.x=0;
		this.y=0;
	
	}
	
	/*
	public boolean moveTo(int c, int x1, int y1, int x2, int y2){
		int y=y1;
		//implementa c 
		ArrayList<punto> p = new alfiere(g, x1,y1).preemption();
		for(int i=0;i<p.size();i++){
			punto p1 = p.get(i);
			if(p1.getX()==x2 && p1.getY()==y2)
				return true;
		}	
		
		return false;
		/*
		for(int i=x1; i<8 && y<8; i++, y++){
			if(x1+i<8 && y1+i<8)
				if(x1+i==x2 && y1+i==y2)
					return true;
			if(x1+i<8 && y1-i>=0)
				if(x1+i==x2 && y1-i==y2)
					return true;
			if(x1-i>=0 && y1-i>=0)
				if(x1-i==x2 && y1-i==y2)
					return true;
			if(x1-i>=0 && y1+i<8)
				if(x1-i==x2 && y1+i==y2)
					return true;
		}
		return false;
		
	}*/
	
	public ArrayList<punto> preemption(){
		ArrayList<punto> p= new ArrayList<punto>(0);
		for(int h=0;h<4;h++)
			p.addAll(ciclo(h,x,y));
			
		
			
		return p;
	}
	
	
	
	private ArrayList<punto> ciclo(int v, int x, int y){
		
	ArrayList<punto> p= new ArrayList<punto>(0);
	if(v==0){
		x--;
		y--;
	}
	if(v==1){
		x++;
		y++;
	}
	if(v==2){
		x++;
		y--;
	}
	if(v==3){
		x--;
		y++;
	}
	
	while((v==0) ? x>=0 && y>=0 : (v==1) ? x<=7 && y<=7 : (v==2) ? x<=7 && y>=0 : x>=0 && y<=7 ){
		if(ScacchiModel.get(x,y)==null)
			p.add(new punto(x,y));
		
		if(ScacchiModel.get(x,y)!=null && ScacchiModel.get(x,y).getGiocatore()!=g){
			p.add(new punto(x,y));
			return p;
		}
		if(ScacchiModel.get(x,y)!=null && ScacchiModel.get(x,y).getGiocatore()==g){
			return p;
		}
		
		if(v==0){
			x--;
			y--;
		}
		if(v==1){
			x++;
			y++;
		}
		if(v==2){
			x++;
			y--;
		}
		if(v==3){
			x--;
			y++;
		}
	}
	return p;	
}


	@Override
	public int getGiocatore() {
		return g;
	}


	public String getImg() {
		if(g==0)
			return "img/alfiereb.png";
		else 
			return "img/alfieren.png";
	}


	@Override
	public boolean isRe(int g) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Boolean hasRePreemption( punto g) {
		ArrayList<punto> p= preemption();
		if( g!=null ){
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
		}
						return false;
	}

	public ArrayList<punto> preemptionRe(punto g){
		ArrayList<punto> p =  new ArrayList<punto>(0);
		if(x>g.getX() && y>g.getY())
			p.addAll( ciclo(0, x,y) );
			
		if(x<g.getX() && y<g.getY())
			p.addAll( ciclo(1, x,y) );
			
		if(x>g.getX() && y<g.getY())
			p.addAll( ciclo(3, x,y) );
		
		if(x<g.getX() && y>g.getY())
			p.addAll( ciclo(2, x,y) );
		
		return p;
	}

}