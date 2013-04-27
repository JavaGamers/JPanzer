package view2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import model.Esagono;
import model.Mappa;

public class MappaGrafica extends javax.swing.JPanel {
	                     
    private javax.swing.JButton down;
    private javax.swing.JButton left;
    private javax.swing.JButton right;
    private javax.swing.JButton up;
    
	
	private int xC;
	private int yC;
	private Mappa mappa;
          

    public MappaGrafica(Mappa m, int x, int y) {
        initComponents();
        this.xC=x;
		this.yC=y;
		this.mappa=m;
    }
    
    public void paintComponent(Graphics g) {
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
			super.paintComponents(g);
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
	
	public void newSet(int x, int y){
		this.xC=x;
		this.yC=y;
	}
                        
    private void initComponents() {

        up = new javax.swing.JButton();
        right = new javax.swing.JButton();
        down = new javax.swing.JButton();
        left = new javax.swing.JButton();

        up.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view2/Up.gif")); 

        right.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view2/Right.gif")); 

        down.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view2/Down.gif"));

        left.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view2/Left.gif"));

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
