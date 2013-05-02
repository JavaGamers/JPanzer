package view;

public class UnitPanel extends javax.swing.JPanel {
	
	                     
    private javax.swing.JButton aereo;
    private javax.swing.JButton artiglieria;
    private javax.swing.JButton fanteriaLegg;
    private javax.swing.JButton fanteriaPes;
    private javax.swing.JButton panzer;
    private javax.swing.JButton start;
      


    public UnitPanel() {
        initComponents();
    }
                          
    private void initComponents() {

        fanteriaLegg = new javax.swing.JButton();
        fanteriaPes = new javax.swing.JButton();
        panzer = new javax.swing.JButton();
        aereo = new javax.swing.JButton();
        artiglieria = new javax.swing.JButton();
        start = new javax.swing.JButton();

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

        start.setText("Start Game");

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
                    .addComponent(start, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(start)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                       
                     
}
