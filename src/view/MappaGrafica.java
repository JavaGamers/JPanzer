package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Esagono;
import model.Mappa;

public class MappaGrafica extends JPanel {
	
	private int xC;
	private int yC;
	private Mappa mappa;
	
	public MappaGrafica(Mappa m, int x, int y){
		super();
		this.xC=x;
		this.yC=y;
		this.mappa=m;
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.black);
		EsagonoGrafico eG;
		double raggio = 100; //raggio degli esagoni
		int s = this.mappa.getComponent()[0].getSettore();
		int l = this.mappa.getComponent()[0].getLivello();
		int p = this.mappa.getComponent()[0].getPosizione();
		eG = new EsagonoGrafico(s,l,p,this.xC, this.yC, raggio);
		eG.paint(g,this.mappa.getComponent()[0]);
		
		for(int i=1;i<this.mappa.getComponent().length;i++){
			s = this.mappa.getComponent()[i].getSettore();
			l = this.mappa.getComponent()[i].getLivello();
			p = this.mappa.getComponent()[i].getPosizione();
			eG.newSet(s,l,p,this.xC,this.yC,raggio); // il segno "-" indica il cambio di coordinate (asse y invertito)
			eG.paint(g,this.mappa.getComponent()[i]);
		}

	}
	
	public Esagono contains(double x, double y){
		Esagono e=null;
		int s = this.mappa.getComponent()[0].getSettore();
		int l = this.mappa.getComponent()[0].getLivello();
		int p = this.mappa.getComponent()[0].getPosizione();
		EsagonoGrafico eG= new EsagonoGrafico(s,l,p,this.xC,this.yC,1.5);
		for(int i=0; i<this.mappa.getComponent().length;i++){
			s = this.mappa.getComponent()[i].getSettore();
			l = this.mappa.getComponent()[i].getLivello();
			p = this.mappa.getComponent()[i].getPosizione();
			eG.newSet(s,l,p,this.xC,this.yC,1.5);
			if(eG.contains(x,y)){
				e=this.mappa.getComponent()[i];
			}
		}
		return e;
	}

}
