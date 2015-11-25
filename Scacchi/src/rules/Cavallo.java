package rules;
import java.util.ArrayList;
import model.ScacchiModel;
import model.punto;



public class Cavallo implements Pedina{

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
	public Cavallo(int g, int x, int y){
		this.g=g;
		this.x=x;
		this.y=y;
	
	}
	public Cavallo(int g){
		this.g=g;
		this.x=0;
		this.y=0;
	
	}

	public ArrayList<punto> preemption(){
		punto[] arrayp = {new punto(x+1,y-2), new punto(x+1,y+2), 
				     new punto(x+2,y-1), new punto(x+2,y+1), 
				     new punto(x+2,y-1), new punto(x-1,y-2), 
				     new punto(x-1,y+2), new punto(x-2,y-1),
				     new punto(x-2,y+1), new punto(x-2,y-1)
		};
		
		ArrayList<punto> arr = new ArrayList<punto>(0);
		
		for(int i=0; i<arrayp.length; i++){
			if(ScacchiModel.isValid(arrayp[i].getX(), arrayp[i].getY() ) )
					arr.add(arrayp[i]);
		}
		
		ArrayList<punto> p = new ArrayList<punto>(0);
		
		for(int i=0; i < arr.size(); i++){
			if(ScacchiModel.get(arr.get(i).getX(),arr.get(i).getY())==null)
				p.add(new punto(arr.get(i).getX(),arr.get(i).getY()));
			if(ScacchiModel.get(arr.get(i).getX(),arr.get(i).getY())!=null && ScacchiModel.get(arr.get(i).getX(),arr.get(i).getY()).getGiocatore()!=g){
				p.add(new punto(arr.get(i).getX(),arr.get(i).getY()));
				
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
			return "img/cavallob.png";
		else 
			return "img/cavallon.png";
	}

	@Override
	public boolean isRe(int g) {
		// TODO Auto-generated method stub
		return false;
	}
	public Boolean hasRePreemption(punto g) {
		ArrayList<punto> p= preemption();
		if( g!=null ){
		for(int i=0;i<p.size();i++)
			if(p.get(i).getX()==g.getX() && p.get(i).getY()==g.getY())
				return true;
		}
		return false;
	}
	@Override
	public ArrayList<punto> preemptionRe(punto g) {
		ArrayList<punto> p = new ArrayList<punto>(0);
		p.add( new punto(g.getX(), g.getY()) );
		return p;
	}

}
