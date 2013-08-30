package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Popup;

import model.Player;

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
    private static Popup popup = null;
    private static JLabel popupLabel = new JLabel();
    
    public static final String SOLDITXT = "Soldi rimanenti: ";
    
    public UnitPanel() {
        initComponents();
    }
    
    public void setSoldiLabel(int n){
    	this.soldi.setText(SOLDITXT+n);
    }
    
    public void updateLabel(){
    	this.soldi.setText(SOLDITXT+gameMode.getPlayer(gameMode.getTurno()).getSoldi());
    	Player p = gameMode.getPlayer(gameMode.getTurno());
    	this.playerTxt.setText(p.getNome());
    }
    
    public static Popup getPopup(){
    	return popup;
    }
    
    public static JLabel getPopupLabel(){
    	return popupLabel;
    }
    
    public static void setPopup(Popup p){
    	popup = p;
    }
    private void initComponents() {
  
    	UnitListener uL = new UnitListener();

        fanteriaLegg = new javax.swing.JButton();
        fanteriaLegg.addActionListener(uL);
        fanteriaLegg.setActionCommand("fanteriaLegg");
        fanteriaLegg.addMouseListener(uL);
        
        fanteriaPes = new javax.swing.JButton();
        fanteriaPes.addActionListener(uL);
        fanteriaPes.setActionCommand("fanteriaPes");
        fanteriaPes.addMouseListener(uL);
        
        panzer = new javax.swing.JButton();
        panzer.addActionListener(uL);
        panzer.setActionCommand("panzer");
        panzer.addMouseListener(uL);
        
        aereo = new javax.swing.JButton();
        aereo.addActionListener(uL);
        aereo.setActionCommand("aereo");
        aereo.addMouseListener(uL);
        
        artiglieria = new javax.swing.JButton();
        artiglieria.addActionListener(uL);
        artiglieria.setActionCommand("artiglieria");
        artiglieria.addMouseListener(uL);
        
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
        
        
        Player p = gameMode.getPlayer(gameMode.getTurno());
        playerTxt = new JLabel();
        playerTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerTxt.setFont(new java.awt.Font("Tahoma", 1, 18));
        playerTxt.setText(p.getNome());
        
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(aereo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(panzer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fanteriaPes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(fanteriaLegg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(artiglieria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(gioca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(soldi)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zoom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playerTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(playerTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fanteriaLegg)
                .addGap(18, 18, 18)
                .addComponent(fanteriaPes)
                .addGap(18, 18, 18)
                .addComponent(panzer)
                .addGap(18, 18, 18)
                .addComponent(aereo)
                .addGap(18, 18, 18)
                .addComponent(artiglieria)
                .addGap(18, 18, 18)
                .addComponent(soldi)
                .addGap(18, 18, 18)
                .addComponent(zoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(gioca)
                .addContainerGap())
        );
    }            
}
