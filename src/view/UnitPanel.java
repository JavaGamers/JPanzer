package view;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.UnitListener;

public class UnitPanel extends javax.swing.JPanel {
	
	                     
    private javax.swing.JButton aereo;
    private javax.swing.JButton artiglieria;
    private javax.swing.JButton fanteriaLegg;
    private javax.swing.JButton fanteriaPes;
    private javax.swing.JButton panzer;
    private javax.swing.JButton start;
    private JLabel remainingUnits;
    private JTextField numUnits;
      


    public UnitPanel() {
        initComponents();
    }
    
    public JTextField getTextField(){
    	return this.numUnits;
    }
    
    private JLabel getLabel(){
    	return this.remainingUnits;
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
        
        start = new javax.swing.JButton();
        start.setText("Start Game");
        start.addActionListener(uL);
        start.setActionCommand("start");
        
        numUnits = new javax.swing.JTextField();
        numUnits.setText("0");
        numUnits.setEditable(true);
        
        remainingUnits = new javax.swing.JLabel();
        remainingUnits.setText("/0");

        fanteriaLegg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Icon pack/Fanteria Leggera_Icon.gif"))); // NOI18N
        fanteriaLegg.setText("Fanteria Leggera");
        fanteriaLegg.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaLegg.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        fanteriaPes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Icon pack/Fanteria Pesante_Icon02.gif"))); // NOI18N
        fanteriaPes.setText("Fanteria Pesante");
        fanteriaPes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fanteriaPes.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        panzer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Icon pack/Panzer_Icon.gif"))); // NOI18N
        panzer.setText("Panzer");
        panzer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        panzer.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        aereo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Icon pack/Aereo_Icon.gif"))); // NOI18N
        aereo.setText("Aereo");
        aereo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aereo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        artiglieria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/Icon pack/Artiglieria_Icon.gif"))); // NOI18N
        artiglieria.setText("Artiglieria");
        artiglieria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        artiglieria.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(aereo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panzer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fanteriaPes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fanteriaLegg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artiglieria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(numUnits)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(remainingUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numUnits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(remainingUnits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(start)
                .addContainerGap())
        );                       
    }            
}
