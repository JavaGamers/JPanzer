package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import controller.MappaListener;
import model.Esagono;
import model.Mappa;

public class MappaGrafica extends javax.swing.JPanel {
	                     
    private javax.swing.JButton down;
    private javax.swing.JButton left;
    private javax.swing.JButton right;
    private javax.swing.JButton up;
    
	private int selezionato; // indica l'id dell'esagono selezionato. -1 se nessun esagono selezionato
	private int xC;
	private int yC;
	public double raggio;
	private Mappa mappa;
	
	public static final double STDRAGGIO = 100;
	public static final double ZOOMRAGGIO = 30;
          

    public MappaGrafica(Mappa m, int x, int y) {
        initComponents();
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
		
		// disegno del 1° esagono (id=0)
		int s = e.getSettore();
		int l = e.getLivello();
		int p = e.getPosizione();
		eG = new EsagonoGrafico(s,l,p,this.xC, this.yC, raggio, Color.BLACK);
		
		if(e.getTerritorio()!=null){
			imgLand = e.getTerritorio().getImage();
		}
		if(e.getUnit()!=null){
			imgUnit = e.getUnit().getImage();
		}
		this.paintImage(g2, eG, imgLand);
		this.paintImage(g2, eG, imgUnit);
		eG.paint(g);
		
		// disegno degli altri esagoni
		for(int i=1;i<this.mappa.getComponent().length;i++){
			e= this.mappa.getComponent()[i];
			s = e.getSettore();
			l = e.getLivello();
			p = e.getPosizione();
			eG.newSet(s,l,p,this.xC,this.yC,raggio,Color.BLACK);
			
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
			eG.paint(g);
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
		int s = 0;
		int l = 0;
		int p = 0;
		boolean trovato = false;
		EsagonoGrafico eG= new EsagonoGrafico(s,l,p,this.xC,this.yC,raggio,Color.BLACK);
		
		for(int i=0; i<this.mappa.getComponent().length && !trovato;i++){
			
			s = this.mappa.getComponent()[i].getSettore();
			l = this.mappa.getComponent()[i].getLivello();
			p = this.mappa.getComponent()[i].getPosizione();
			eG.newSet(s,l,p,this.xC,this.yC,raggio,Color.BLACK);
			
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
                        
    private void initComponents() {
    	this.addMouseListener(new MappaListener());
        up = new javax.swing.JButton();
        right = new javax.swing.JButton();
        down = new javax.swing.JButton();
        left = new javax.swing.JButton();

        up.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Up.gif")); 

        right.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Right.gif")); 

        down.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Down.gif"));

        left.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Left.gif"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(up, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(right, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(left, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(down, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }
    
    
            
}
