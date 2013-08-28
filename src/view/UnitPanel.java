package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.GameMode;
import controller.UnitListener;

public class UnitPanel extends javax.swing.JPanel {
	public static GameMode gameMode = GameMode.getGameMode();
	                     
	private javax.swing.JButton aereo;
    private javax.swing.JButton artiglieria;
    private javax.swing.JButton fanteriaLegg;
    private javax.swing.JButton fanteriaPes;
    private javax.swing.JButton gioca;
    private javax.swing.JButton panzer;
    private javax.swing.JButton zoom;
    private javax.swing.JLabel soldi;
    private javax.swing.JLabel playerTxt;
    
    
    public static final String SOLDITXT = "Soldi rimanenti: ";
    public static final String PLAYERTXT = "Giocatore ";
    
    public UnitPanel() {
        initComponents();
    }
    
    public void setSoldiLabel(int n){
    	this.soldi.setText(SOLDITXT+n);
    }
    
    public void updateLabel(){
    	this.soldi.setText(SOLDITXT+gameMode.getPlayer(gameMode.getTurno()).getSoldi());
    	this.playerTxt.setText(PLAYERTXT+gameMode.getTurno());
    }
                             
    private void initComponents() {
    	
    	UnitListener uL = new UnitListener();

        fanteriaLegg = new javax.swing.JButton();
        fanteriaLegg.addActionListener(uL);
        fanteriaLegg.setActionCommand("fanteriaLegg");
        
        fanteriaPes = new javax.swing.JButton();
        fanteriaPes.addActionListener(uL);
        fanteriaPes.setActionCommand("fanteriaPes");
        
        panzer = new javax.swing.JButton();
        panzer.addActionListener(uL);
        panzer.setActionCommand("panzer");
        
        aereo = new javax.swing.JButton();
        aereo.addActionListener(uL);
        aereo.setActionCommand("aereo");
        
        artiglieria = new javax.swing.JButton();
        artiglieria.addActionListener(uL);
        artiglieria.setActionCommand("artiglieria");
        
        gioca = new javax.swing.JButton();
        gioca.setText("Gioca");
        gioca.addActionListener(uL);
        gioca.setActionCommand("gioca");
        
        zoom = new javax.swing.JButton();
        zoom.setText("Zoom");
        zoom.addActionListener(uL);
        zoom.setActionCommand("zoom");
        
        ImageIcon imgIcon= null;

        try {
			URL imgUrl=getClass().getResource("Icon pack/Unit Pack/Fanteria Leggera1_Icon.png");
			imgIcon = new ImageIcon(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
        fanteriaLegg.setIcon(imgIcon);
        fanteriaLegg.setText("Fanteria Leggera");
        fanteriaLegg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaLegg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);


        try {
			URL imgUrl=getClass().getResource("Icon pack/Unit Pack/Fanteria Pesante1_Icon.png");
			imgIcon = new ImageIcon(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
        fanteriaPes.setIcon(imgIcon);
        fanteriaPes.setText("Fanteria Pesante");
        fanteriaPes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaPes.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        try {
			URL imgUrl=getClass().getResource("Icon pack/Unit Pack/Panzer1_Icon.png");
			imgIcon = new ImageIcon(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
        panzer.setIcon(imgIcon);
        panzer.setText("Panzer");
        panzer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panzer.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        try {
			URL imgUrl=getClass().getResource("Icon pack/Unit Pack/Aereo1_Icon.png");
			imgIcon = new ImageIcon(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
        aereo.setIcon(imgIcon);
        aereo.setText("Aereo");
        aereo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aereo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        try {
			URL imgUrl=getClass().getResource("Icon pack/Unit Pack/Artiglieria1_Icon.png");
			imgIcon = new ImageIcon(imgUrl);
	          
	       } catch (Exception e) {
	    	   System.out.println(e.toString());
	       }
        artiglieria.setIcon(imgIcon);
        artiglieria.setText("Artiglieria");
        artiglieria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        artiglieria.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        
        soldi = new JLabel();
        soldi.setText(SOLDITXT+gameMode.getPlayer(gameMode.getTurno()).getSoldi());
        
        playerTxt = new JLabel();
        playerTxt.setText(PLAYERTXT+gameMode.getTurno());
        
        
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(aereo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(panzer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fanteriaPes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fanteriaLegg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(artiglieria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(gioca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(soldi)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(playerTxt)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(zoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(playerTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fanteriaLegg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fanteriaPes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panzer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aereo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artiglieria)
                .addGap(18, 18, 18)
                .addComponent(soldi)
                .addGap(18, 18, 18)
                .addComponent(zoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(gioca)
                .addContainerGap())
        );
    }            
}
