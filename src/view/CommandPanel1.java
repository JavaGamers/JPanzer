package view;

import controller.CommandListener;

public class CommandPanel1 extends javax.swing.JPanel {
	                    
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

    public CommandPanel1() {
        initComponents();
    }
                         
    private void initComponents() {
    	
    	CommandListener cL = new CommandListener();

        passa = new javax.swing.JButton();
        passa.setText("PASSA");
        passa.addActionListener(cL);
        passa.setActionCommand("passa");
        
        accorpa = new javax.swing.JButton();
        accorpa.setText("ACCORPA");
        accorpa.addActionListener(cL);
        accorpa.setActionCommand("accorpa");
        
        scorpora = new javax.swing.JButton();
        scorpora.setText("SCORPORA");
        scorpora.addActionListener(cL);
        scorpora.setActionCommand("scorpora");
        
        carica = new javax.swing.JButton();
        carica.setText("CARICA");
        carica.addActionListener(cL);
        carica.setActionCommand("carica");
        
        salva = new javax.swing.JButton();
        salva.setText("SALVA");
        salva.addActionListener(cL);
        salva.setActionCommand("salva");
        
        abbandona = new javax.swing.JButton();
        abbandona.setText("ABBANDONA");
        abbandona.addActionListener(cL);
        abbandona.setActionCommand("abbandona");
        
        undo = new javax.swing.JButton();
        undo.setText("UNDO");
        undo.addActionListener(cL);
        undo.setActionCommand("undo");
        
        attacca = new javax.swing.JButton();
        attacca.setText("ATTACCA");
        attacca.addActionListener(cL);
        attacca.setActionCommand("attacca");
        
        muovi = new javax.swing.JButton();
        muovi.setText("MUOVI");
        muovi.addActionListener(cL);
        muovi.setActionCommand("muovi");
        
        zoom = new javax.swing.JButton();
        zoom.setText("ZOOM");
        zoom.addActionListener(cL);
        zoom.setActionCommand("zoom");
        
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
    }                     

}
