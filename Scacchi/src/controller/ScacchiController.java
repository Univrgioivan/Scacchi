package controller;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;

import rules.Pedina;
import rules.Pedone;
import rules.Re;
import model.ScacchiModel;
import model.ScaccoDialog;
import model.SolvedDialog;
import model.punto;
import view.ScacchiView;
import controller.*;



public class ScacchiController {
	private ScacchiModel m = null;
	private ScacchiView v = null;
	 int x1=0,y1=0,x2=0,y2=0,t=0,giocatore=0;
	 private ArrayList<punto> p;

	//alla creazione del controller vanno passati model e view
	public ScacchiController(ScacchiModel m, ScacchiView v){
		
		this.m = m;
		this.v = v;
		
	}
	
	public void add(int x, int y){
		
		if(t==0){
			
			//v.colorizeG(arrayListScaccoRe(giocatore));
			if( m.get(x, y)!=null && m.get(x, y).getGiocatore() == giocatore ){
				//registro il primo click x e y
				x1=x;
				y1=y;
				
				//controllo il sucidio
				Pedina pedina = ScacchiModel.get(x, y);
				t++;
				/*
				ScacchiModel.set(x, y, null);
				
				//controllo scacco
				if( scacco(giocatore) ){
					t=0;
				} else {
					//incremento il contatore del click
					t++;
				}
				
				ScacchiModel.set(x, y, pedina);*/
				
				if( scacco(giocatore) ){
					
					ArrayList<punto> prev;
					if(pedina instanceof Re){
						prev = intersezioneInv(pedina.preemption(), arrayListPreeTutti(giocatore==0 ? 1 : 0));
					}else{
						prev = intersezione(pedina.preemption(), arrayListScaccoRe(giocatore==0 ? 1 : 0));

					}
					if(prev!=null){
						v.colorizeRed(prev);
						t++;
					} else{
						//v.setBackground(x, y);
						t=0;
					}
					
				} else{
					v.colorizeRed(pedina.preemption());
 				}
			}
			
			
			//v.colorizeG( arrayListScaccoRe(0) );
			ArrayList<punto> o = arrayListScaccoRe(0);
			
			//Pedina pedina = ScacchiModel.get(x1, y1);
			//v.colorizeG( intersezione(pedina.preemption(), arrayListScaccoRe(giocatore==0 ? 1 : 0) ) );
			Pedina re = returnRe(giocatore);
			ArrayList <punto> p=intersezioneInv(re.preemption(), arrayListPreeTutti(giocatore==0 ? 1 : 0));
			//v.colorizeG(p);
			
			//SECONDO CLICK
		} else {
			
			if( suicidio(x1,y1,x,y) || (x1==x && y1==y) ){
				
				t=0;
				
				
					
			} else {
				ArrayList<punto> o = arrayListScaccoRe(0);
				
				System.out.println("Dimensione arraylistscaccore" + o.size() );
			//m.scambio(x,y,x1,y1);
			System.out.println("okkkkkkkk");
			ArrayList<punto> pree;
			if( scacco(giocatore) ){
				//System.out.println("scacco");
				Pedina pedina = ScacchiModel.get(x1, y1);
				if(pedina instanceof Re){
					pree = intersezioneInv(pedina.preemption(), arrayListScaccoRe(giocatore==0 ? 1 : 0));
				}else{
					pree = intersezione(pedina.preemption(), arrayListScaccoRe(giocatore==0 ? 1 : 0) );
				}
				
				if( controlloPuntoArray(pree,new punto(x,y)) ){
					m.scambio(x1,y1,x,y);
					t=0;
					scaccoMatto(giocatore == 0 ? 1 : 0);
					scaccoMatto(giocatore);
					cambioTurno();

				}
				else
					t=0;
			} else{
				//System.out.println("non scacco");
				Pedina pedina = ScacchiModel.get(x1, y1);
				pree = pedina.preemption();
				if( controlloPuntoArray(pree,new punto(x,y)) ){
					m.scambio(x1,y1,x,y);
					t=0;
					
					scaccoMatto(giocatore == 0 ? 1 : 0);
					scaccoMatto(giocatore);
					cambioTurno();
					
				} else{
					t=0;
				}
			}
			}
			
			
			v.onConfigurationChange();
			//v.colorizeG(arrayListScaccoRe(giocatore==0 ? 1 : 0));
			//v.colorizeG( intersezione( arrayListScaccoRe(giocatore), arrayListPreeTutti(giocatore==0 ? 1 : 0)) );
			v.colorizeG( intersezione( arrayListScaccoRe(giocatore==0 ? 1 : 0), arrayListPreeTuttiNoRe(giocatore)) );
		}
		
			
		}
		
	
	
	private void cambioTurno() {
		if(giocatore==0)
			giocatore++;
		else
			giocatore=0;
		
	}

	public Boolean scaccoMatto(int g){
		Boolean test = true;
		Boolean test1 = false;
		ArrayList <punto> p = new ArrayList();
		ArrayList <punto> ree = new ArrayList();
		
			if( scacco(g) ){
				punto rePunto = getRe(g);
				Pedina re = returnRe(g);
				ree.add(rePunto);
				
				p=intersezioneInv(re.preemption(), arrayListPreeTutti(g==0 ? 1 : 0));
				
				for(int j=0; j<p.size(); j++){
					if (suicidio( rePunto.getX(), rePunto.getY(), p.get(j).getX(), p.get(j).getY() ) == false){
						System.out.println("NOO");
						test=false;
					}
				}
				
				if( test==true && intersezione( arrayListScaccoRe(g), arrayListPreeTuttiNoRe(g==0 ? 1 : 0)).size()==0 )
					test1=true;	
					
				} else{
					test=false;
					test1=false;
				}
		

		
		System.out.println( intersezione( arrayListScaccoRe(g), arrayListPreeTuttiNoRe(g==0 ? 1 : 0)).size() );
		//v.colorizeG(p);
		if( (test && test1) == true )
		System.out.println("---------------SCACCO MATTO------------"); 
		return test && test1;
		
	}
	
	//click -> add() -> click -> add() -> mossa() -> controllomossa()
	
	//ritorna vero quando il re è sotto scacco da un qualsiasi pezzo avversario
		public Boolean scacco(int g){
			if( (arrayListScaccoRe(g==0 ? 1 : 0)).size() != 0 ){
				
				System.out.println("scacco " + g);
				return true;
			}
			else{
				System.out.println("non scacco " + g);
		
				return false;
			}
			
		}
		
		
		/* 
		 *ritorna la preemption dei pezzi che mettono sotto scacco il re(solo le caselle tra re e pezzo, 
		 *non tutte le direzioni della preemption del pezzo che mette sotto scacco)
		 */
		
		public  ArrayList<punto> arrayListScaccoRe(int s){
			ArrayList<punto> p = new ArrayList<punto>();
			//r=posizione del re avversario
			punto r =  getRe( (s==0)?1:0 );
			//preemption del re avversario
			
			ArrayList<punto> rePree;
			if(returnRe( (s==0)?1:0 )!=null)
				rePree = returnRe( (s==0)?1:0 ).preemption();
			 
			Pedina m;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					m=ScacchiModel.get(y,i);
					if(m!=null && m.getGiocatore() == s ) {
						//controllo se nella previsione del pezzo è presente il re avversario
							if( !(m instanceof Re) && m.hasRePreemption( r )){
								//se il re è presente aggiungo l'array ritornatomi da preemptionre()
								p.addAll( m.preemptionRe(r) );
								p.add( new punto(y,i) );
							}
							
							
							/*for(int h=0;h< rePree.size();h++){
									
									if( !(m instanceof re) && m.hasRePreemption( rePree.get(h) ) )
										p.add( rePree.get(h) );
								
								}
								
							*/
							
					}
							
				}
			}
			
		
			
			for(int i=0;i<p.size();i++)
				if( p.get(i).getX() == r.getX() && p.get(i).getY() == r.getY() )
					p.remove(i);
			
			//ScacchiView.colorizeG(p);	
			
			return p;
		}
		
		
		public  ArrayList<punto> arrayListPreeTutti(int s){
			ArrayList<punto> p = new ArrayList<punto>();
			//r=posizione del re avversario
			punto r =  getRe( s );
			//preemption del re avversario
			
			
			Pedina corr;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					corr=ScacchiModel.get(i,y);
					if(corr!=null && corr.getGiocatore() == s  ) {
						
						if(corr instanceof Pedone){
							p.addAll( corr.preemptionRe(null) );
							p.add( new punto(i,y) );
							
						}else{
							p.addAll( corr.preemption() );
							p.add( new punto(i,y) );
								
							}
						}
							
							
							
					}
							
				}
			
			return p;
		}
		
		
		public  ArrayList<punto> arrayListPreeTuttiNoRe(int s){
			ArrayList<punto> p = new ArrayList<punto>();
			//r=posizione del re avversario
			punto r =  getRe( s );
			//preemption del re avversario
			
			
			Pedina corr;
			
			//scorro la scacchiera, per ogni pezzo del giocatore s(non avversario)
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					corr=ScacchiModel.get(i,y);
					if(corr!=null && corr.getGiocatore() == s && !(corr instanceof Re) ) {
						
						if(corr instanceof Pedone){
							p.addAll( corr.preemptionRe(null) );
							p.add( new punto(i,y) );
							
						}else{
							p.addAll( corr.preemption() );
							p.add( new punto(i,y) );
								
							}
						}
							
							
							
					}
							
				}
			
			return p;
		}
		
		
		private  ArrayList<punto> intersezione( ArrayList<punto> p2, ArrayList<punto> o ) {
			ArrayList<punto> p = new ArrayList<punto>(0);
			for( int i=0; i<p2.size(); i++ )
				for( int h=0; h<o.size(); h++ )
					if( p2.get(i).getX() == o.get(h).getX() && p2.get(i).getY() == o.get(h).getY() )
						
						p.add(new punto( p2.get(i).getX(),  p2.get(i).getY()) );
			
			return p;
		}
		
		
		private boolean suicidio(int x1, int y1,int x2, int y2 ){
		
			Boolean test = false;
			Pedina p = m.get(x1,y1);
			Pedina p1 = m.get(x2, y2);
			m.scambio(x2,y2,x1,y1);
			if( scacco(giocatore) )
				test = true;
			m.set(x1,y1,p);
			m.set(x2,y2,p1);
			m.toString();
			return test;
			
		}
		
		
		//tutti i punti di p2 che non appaiono mai in o
		//scorre tutto p2 se c'è un elemento contenuto anche in o, lo rimuovere dal risultato p, se nella preemption p2
		//è presente un pezzo avversario non difeso, questo pezzo verrà aggiunto alla preemption.
		private  ArrayList<punto> intersezioneInv(ArrayList<punto> p2, ArrayList<punto> o) {
			ArrayList<punto> p = new ArrayList<punto>(0);
			Boolean test= false;
			for( int i=0; i<p2.size(); i++ ){
				test=false;
				for( int h=0; h<o.size(); h++ ){
					if( p2.get(i).getX() == o.get(h).getX() && p2.get(i).getY() == o.get(h).getY() )
						test=true;
					Pedina ped =  m.get( p2.get(i).getX(), p2.get(i).getY() );
					if( (h==o.size()-1 && test==false) || (ped!= null && ped.getGiocatore()!=giocatore) )
						p.add( new punto( p2.get(i).getX(),  p2.get(i).getY()) );
				}
			}
			return p;
		}
		
		private  Boolean controlloPuntoArray( ArrayList<punto> p2, punto g ) {
			ArrayList<punto> p = new ArrayList<punto>(0);
			for( int i=0; i<p2.size(); i++ )
				
					if( p2.get(i).getX() == g.getX() && p2.get(i).getY() == g.getY() )
						return true;
			
			return false;
		}
		
		
		private  Re returnRe(int g) {
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					
					if(ScacchiModel.get(y,i)!=null && ( ScacchiModel.get(y,i).isRe(g)) )
						return (Re) ScacchiModel.get(y,i);
						
				}
			}
			return null;
		    
		}
		
		private  punto getRe(int g) {
			for(int i=0;i<8;i++){
				for(int y=0; y<8; y++){
					
					if(ScacchiModel.get(y,i)!=null && ( ScacchiModel.get(y,i).isRe(g)) )
						return new punto(y,i);
						
				}
			}
			return null;
		    
		}
	
}