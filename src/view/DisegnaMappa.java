package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;

import model.Esagono;
import model.Mappa;

public class DisegnaMappa extends Drawing {
	private int xC;
	private int yC;
	private Mappa mappa;
	private LinkedList<Esagono> esagoniDisegnati;
	
	public DisegnaMappa(Mappa m, int x, int y){
		this.xC=x;
		this.yC=y;
		this.mappa=m;
		this.esagoniDisegnati= new LinkedList<Esagono>();
	}

	public void paint(Graphics g) {
		g.setColor(Color.black);
		Graphics2D g2d= (Graphics2D) g;
		DisegnaEsagono centro, dE;
		double r = 1.5; //raggio degli esagoni
		centro = new DisegnaEsagono(this.xC, this.yC, r);
		for(int j=0; j<6; j++){
			switch(j){
				case(0):{ dE = new DisegnaEsagono(this.xC,this.yC+2*centro.getApotema(),r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
				
				case(1):{ dE = new DisegnaEsagono(this.xC+2*r,this.yC+r,r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
				
				case(2):{ dE = new DisegnaEsagono(this.xC+2*r,this.yC-r,r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
				
				case(3):{ dE = new DisegnaEsagono(this.xC,this.yC-2*r,r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
				
				case(4):{ dE = new DisegnaEsagono(this.xC-2*r,this.yC-r,r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
				
				case(5):{ dE = new DisegnaEsagono(this.xC-2*r,this.yC+r,r);
						this.esagoniDisegnati.add(this.mappa.getComponent()[j].getAdiacenze()[j]);
						break;
				}
			}
		}
		for(int i=1;i<this.mappa.getComponent().length;i++){
			if(!this.esagoniDisegnati.contains(this.mappa.getComponent()[i])){
				
			}
				
		}

	}

}
