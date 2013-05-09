package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import controller.MappaListener;
import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;

public class MappaGrafica extends javax.swing.JPanel {

	private int selezionato; // indica l'id dell'esagono selezionato. -1 se nessun esagono selezionato
	private int xC;
	private int yC;
	public double raggio;
	private Mappa mappa;
	
	public static final double STDRAGGIO = 70;
	public static final double ZOOMRAGGIO = 30;
          

    public MappaGrafica(Mappa m, int x, int y) {
        this.addMouseListener(new MappaListener());
        this.raggio=STDRAGGIO;
        this.xC=x;
		this.yC=y;
		this.mappa=m;
		this.selezionato=-1;
    }
    
    public void paint(Graphics g) {

		g.setColor(Color.black);
		EsagonoGrafico eG;
		Graphics2D g2 = (Graphics2D)g;
		Esagono e = this.mappa.getComponent()[0];
		Image imgLand = null;
		Image imgUnit = null;
		
		// disegno del 1° esagono 

		eG = new EsagonoGrafico(e.getId(),this.xC, this.yC, raggio, Color.BLACK);
		
		if(e.getTerritorio()!=null){
			imgLand = e.getTerritorio().getImage();
		}
		if(e.getUnit()!=null){
			imgUnit = e.getUnit().getImage();
		}
		this.paintImage(g2, eG, imgLand);
		this.paintImage(g2, eG, imgUnit);
		g2.draw(eG);
		
		// disegno degli altri esagoni
		for(int i=1;i<this.mappa.getComponent().length;i++){
			e= this.mappa.getComponent()[i];

			eG.newSet(e.getId(),this.xC,this.yC,raggio,Color.BLACK);
			
			if(e.getTerritorio()!=null){
				imgLand = e.getTerritorio().getImage();
			}
			else{
				imgLand=null;
			}
			
			if(e.getUnit()!=null){
				imgUnit = e.getUnit().getImage();
			}
			else{
				imgUnit = null;
			}
			this.paintImage(g2, eG, imgLand);
			this.paintImage(g2, eG, imgUnit);
			g2.draw(eG);
		}
    	super.paintComponent(g);
	}
    
    public void paintImage(Graphics g, EsagonoGrafico eG, Image img){
    	Graphics2D g2 = (Graphics2D)g;
    	int height=0;
    	int width=0;
    	if(img!=null){
	    	height=img.getHeight(null);
			width=img.getWidth(null);
    	}
		
		g2.setClip(eG);
		g2.clip(eG);
		g2.drawImage(img,(int)(eG.getX()-width/2),(int)(eG.getY()-height/2), null);
    }
	
	public Esagono contains(double x, double y){
		
		Esagono e=null;

		boolean trovato = false;
		EsagonoGrafico eG= new EsagonoGrafico(0,this.xC,this.yC,raggio,Color.BLACK);
		
		for(int i=0; i<this.mappa.getComponent().length && !trovato;i++){

			eG.newSet(i,this.xC,this.yC,raggio,Color.BLACK);
			
			if(eG.contains(x,y)){
				
				e=this.mappa.getComponent()[i];
				trovato = true;
			}
		}
		return e;
	}

	public int getXCentro(){
		return this.xC;
	}
	
	public int getYCentro(){
		return this.yC;
	}
	
	public int getSelezionato(){
		return this.selezionato;
	}
	
	public Mappa getMappa(){
		return this.mappa;
	}
	
	public double getRaggio(){
		return this.raggio;
	}
	
	public void newSet(int x, int y){
		this.xC=x;
		this.yC=y;
	}
	
	public void setSelezionato(int id){
		if(id<-1 || id>this.mappa.getComponent().length)
			throw new IllegalArgumentException("Invalid given id: insert a valid id or -1 for nothing");
		this.selezionato=id;
	}
	
	public void setRaggio(double r){
		this.raggio=r;
		this.paintComponent(this.getGraphics());
	}
	
	public void setMappa(Mappa m){
		this.mappa=m;
		this.update(getGraphics());
	}
                        
 
    
    
            
}
