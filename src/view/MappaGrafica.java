package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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
		Graphics2D g2 = (Graphics2D)g;
		double raggio = 100; //raggio degli esagoni
		Esagono e = this.mappa.getComponent()[0];
		
		int s = e.getSettore();
		int l = e.getLivello();
		int p = e.getPosizione();
		eG = new EsagonoGrafico(s,l,p,this.xC, this.yC, raggio);
		
		Image img = null;
		int height = 0, width=0;
		if(e.getTerritorio()!=null){
			img = e.getTerritorio().getImage();
			height=img.getHeight(null);
			width=img.getWidth(null);
		}
		g2.setClip(eG);
		g2.clip(eG);
		g2.drawImage(img,(int)(eG.getX()-width/2),(int)(eG.getY()-height/2), null);
		
		eG.paint(g);
		
		for(int i=1;i<this.mappa.getComponent().length;i++){
			e= this.mappa.getComponent()[i];
			s = e.getSettore();
			l = e.getLivello();
			p = e.getPosizione();
			eG.newSet(s,l,p,this.xC,this.yC,raggio); // il segno "-" indica il cambio di coordinate (asse y invertito)
			
			if(e.getTerritorio()!=null){
				img = e.getTerritorio().getImage();
				height=img.getHeight(null);
				width=img.getWidth(null);
			}
			g2.setClip(eG);
			g2.clip(eG);
			g2.drawImage(img,(int)(eG.getX()-width/2),(int)(eG.getY()-height/2), null);
			
			eG.paint(g);
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
