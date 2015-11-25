package rules;
import java.util.ArrayList;

import view.ScacchiView;
import model.ScacchiModel;
import model.punto;


public class Pedone implements Pedina{
	
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
	
	public Pedone(int g, int x, int y){
		this.g=g;
		this.x=x;
		this.y=y;
	}
	public Pedone(int g){
		this.g=g;
		this.x=0;
		this.y=0;
	
	}

	public int getGiocatore(){
		return g;
	}
	
	public ArrayList<punto> preemption(){
		ArrayList<punto> p= new ArrayList<punto>(0);
		
			
			if( ScacchiModel.isValid(x+2, y) && g==0 && x==1 && ScacchiModel.get(x+2, y) == null){
				p.add(new punto(x+2, y));
			}
			if( ScacchiModel.isValid(x-2, y) && g==1 && x==6 && ScacchiModel.get(x-2, y) == null){
				p.add(new punto(x-2, y));
			}
			if( ScacchiModel.isValid( ( g==0) ? x+1 : x-1, y ) && ScacchiModel.get( ( g==0) ? x+1 : x-1, y)==null){
				p.add(new punto( ( g==0) ? x+1 : x-1, y));
			}
			
				
			if(ScacchiModel.isValid(x+1,y-1) && ScacchiModel.get( ( g==0) ? x+1 : x-1, y-1)!=null && ScacchiModel.get( ( g==0) ? x+1 : x-1, y-1).getGiocatore()!=g) 
				p.add(new punto( ( g==0) ? x+1 : x-1 ,y-1));
			if(ScacchiModel.isValid(x+1,y+1) && ScacchiModel.get( ( g==0) ? x+1 : x-1, y+1)!=null && ScacchiModel.get( ( g==0) ? x+1 : x-1, y+1).getGiocatore()!=g) 
				p.add(new punto( ( g==0) ? x+1 : x-1 ,y+1));
				
		
	
		return p;
	}
/*
	@Override
	public boolean moveTo(int c, int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return false;
	}
*/
	
	public String getImg() {
		if(g==0)
			return "img/pedoneb.png";
		else 
			return "img/pedonen.png";
	}

	@Override
	public boolean isRe(int g) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Boolean hasRePreemption(punto g) {
		ArrayList<punto> p= preemptionRe(new punto(0,0));
		if( g!=null ){
		for(int i=0;i<p.size();i++)
				if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
					return true;
		}
		return false;
		}
	
	@Override
	public ArrayList<punto> preemptionRe(punto k) {
		
		ArrayList<punto> p = new ArrayList<punto>(0);
		
		if(ScacchiModel.isValid(x+1,y-1) ) 
			p.add(new punto( ( g==0) ? x+1 : x-1 ,y-1));
		if(ScacchiModel.isValid(x+1,y+1) ) 
			p.add(new punto( ( g==0) ? x+1 : x-1 ,y+1));
		//ScacchiView.colorizeG(p);
		//p.add( new punto( g.getX(), g.getY() ) );
		return p;
		
	}



}
