package view2;

public class CommandPanel extends javax.swing.JPanel {
	                    
    private javax.swing.JButton abbandona;
    private javax.swing.JButton accorpa;
    private javax.swing.JButton attacca;
    private javax.swing.JButton carica;
    private javax.swing.JButton muovi;
    private javax.swing.JButton passa;
    private javax.swing.JButton salva;
    private javax.swing.JButton scorpora;
    private javax.swing.JButton undo;
    private javax.swing.JButton zoom;

    public CommandPanel() {
        initComponents();
    }
                         
    private void initComponents() {

        passa = new javax.swing.JButton();
        accorpa = new javax.swing.JButton();
        scorpora = new javax.swing.JButton();
        carica = new javax.swing.JButton();
        salva = new javax.swing.JButton();
        abbandona = new javax.swing.JButton();
        undo = new javax.swing.JButton();
        attacca = new javax.swing.JButton();
        muovi = new javax.swing.JButton();
        zoom = new javax.swing.JButton();

        passa.setText("PASSA");

        accorpa.setText("ACCORPA");

        scorpora.setText("SCORPORA");

        carica.setText("CARICA");

        salva.setText("SALVA");

        abbandona.setText("ABBANDONA");

        undo.setText("UNDO");

        attacca.setText("ATTACCA");

        muovi.setText("MUOVI");

        zoom.setText("ZOOM");
        zoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(abbandona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(muovi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(attacca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(undo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(carica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scorpora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(accorpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(zoom)
                .addGap(18, 18, 18)
                .addComponent(muovi)
                .addGap(18, 18, 18)
                .addComponent(attacca)
                .addGap(18, 18, 18)
                .addComponent(undo)
                .addGap(18, 18, 18)
                .addComponent(abbandona)
                .addGap(18, 18, 18)
                .addComponent(salva)
                .addGap(18, 18, 18)
                .addComponent(carica)
                .addGap(18, 18, 18)
                .addComponent(scorpora)
                .addGap(18, 18, 18)
                .addComponent(accorpa)
                .addGap(18, 18, 18)
                .addComponent(passa)
                .addContainerGap())
        );
    }// </editor-fold>                        

    private void zoomActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    
              
}
