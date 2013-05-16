package view;

import javax.swing.JLabel;

import controller.UnitListener;

public class UnitPanel extends javax.swing.JPanel {
	
	                     
	private javax.swing.JButton aereo;
    private javax.swing.JButton artiglieria;
    private javax.swing.JButton fanteriaLegg;
    private javax.swing.JButton fanteriaPes;
    private javax.swing.JButton gioca;
    private javax.swing.JButton panzer;
    private javax.swing.JLabel soldi;
    
    public static final String SOLDITXT = "Soldi rimanenti: ";

    public UnitPanel() {
        initComponents();
    }
    
    public JLabel getSoldiLabel(){
    	return this.soldi;
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

        fanteriaLegg.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Leggera1_Icon.gif")); 
        fanteriaLegg.setText("Fanteria Leggera");
        fanteriaLegg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaLegg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        fanteriaPes.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Fanteria Pesante1_Icon.gif")); 
        fanteriaPes.setText("Fanteria Pesante");
        fanteriaPes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaPes.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        panzer.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Panzer1_Icon.gif")); 
        panzer.setText("Panzer");
        panzer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panzer.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        aereo.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Aereo1_Icon.gif")); 
        aereo.setText("Aereo");
        aereo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aereo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        artiglieria.setIcon(new javax.swing.ImageIcon("C:/Users/Federico/Documents/GitHub/JPanzer/src/view/Icon pack/Unit Pack/Artiglieria1_Icon.gif")); 
        artiglieria.setText("Artiglieria");
        artiglieria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        artiglieria.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        
        soldi = new JLabel();
        soldi.setText(SOLDITXT);
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                    .addComponent(soldi))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
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
                .addComponent(gioca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }            
}
