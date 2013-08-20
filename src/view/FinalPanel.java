package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import model.Player;
import controller.FinalPanelListener;
import controller.GameMode;

public class FinalPanel extends javax.swing.JPanel {
	
    private javax.swing.JButton menù;
    private javax.swing.JLabel victoryMessage;
    public static GameMode gameMode = GameMode.getGameMode();
    private BufferedImage bImg;
    private final String DEFVICTORYMESSAGE = "Gloria al vincitore ";

    public FinalPanel() {
        initComponents();
    }
    
    public void setWinner(Player p){
    	this.victoryMessage.setText(DEFVICTORYMESSAGE+p.getNome()+"!");
    }
    
    public void setText(String s){
    	this.victoryMessage.setText(s);
    }
                    
    private void initComponents() {

        victoryMessage = new javax.swing.JLabel();
        menù = new javax.swing.JButton();

        victoryMessage.setFont(new java.awt.Font("Monotype Corsiva", 0, 48));
        victoryMessage.setText("Gloria al vincitore ");

        menù.setText("Menù Principale");
        menù.addActionListener(new FinalPanelListener());
        
        try {
			bImg = ImageIO.read(new File(
					"C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/vittoria.gif"));
          
        } catch (Exception e) {
    	   System.out.println(e.toString());
        } 

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(victoryMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(menù)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(victoryMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 194, Short.MAX_VALUE)
                .addComponent(menù)
                .addContainerGap())
        );
    }
    
    public void paint(Graphics g){
        
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(bImg, 500, 300, null);
        super.paint(g);
    }              
}
