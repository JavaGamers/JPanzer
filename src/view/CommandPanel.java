package view;

import model.Unità;
import controller.CommandListener;

public class CommandPanel extends javax.swing.JPanel {
	
    
	private javax.swing.JButton abbandona;
	private javax.swing.JButton accorpa;
	private javax.swing.JButton attacca;
	private javax.swing.JButton carica;
	private javax.swing.JLabel esperienza;
	private javax.swing.JLabel infoUnità;
	private javax.swing.JLabel punteggio;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JButton muovi;
	private javax.swing.JLabel numUnits;
	private javax.swing.JButton passa;
	private javax.swing.JLabel player;
	private javax.swing.JButton salva;
	private javax.swing.JButton scorpora;
	private javax.swing.JButton shop;
	private javax.swing.JButton zoom;
	private javax.swing.JLabel passi;
	
    private static final String ESPERIENZATXT = "Esperienza: ";
    private static final String NUMUNITSTXT = "Numero: ";
    private static final String PASSITXT = "Passi rimanenti: ";
    private static final String PLAYERTXT = "Giocatore: ";

    

    public CommandPanel() {
        initComponents();
    }
    
    public void setUnitLabel(Unità u){
    	
    	this.esperienza.setText(ESPERIENZATXT+u.getEsp());
    	
    	this.passi.setText(PASSITXT+u.getPassi());
    	
    	this.numUnits.setText(NUMUNITSTXT+u.getNumUnits());
    }
                         
    private void initComponents() {
    	
    	CommandListener cL = new CommandListener();

        infoUnità = new javax.swing.JLabel();
        esperienza = new javax.swing.JLabel();
        numUnits = new javax.swing.JLabel();
        
        passi = new javax.swing.JLabel();
        passi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        passi.setText(PASSITXT);
        
        jSeparator1 = new javax.swing.JSeparator();
        
        zoom = new javax.swing.JButton();
        zoom.addActionListener(cL);
        zoom.setActionCommand("zoom");
        
        muovi = new javax.swing.JButton();
        muovi.addActionListener(cL);
        muovi.setActionCommand("muovi");
        
        attacca = new javax.swing.JButton();
        attacca.addActionListener(cL);
        attacca.setActionCommand("attacca");
        
        abbandona = new javax.swing.JButton();
        abbandona.addActionListener(cL);
        abbandona.setActionCommand("abbandona");
        
        salva = new javax.swing.JButton();
        salva.addActionListener(cL);
        salva.setActionCommand("salva");
        
        carica = new javax.swing.JButton();
        carica.addActionListener(cL);
        carica.setActionCommand("carica");
        
        scorpora = new javax.swing.JButton();
        scorpora.addActionListener(cL);
        scorpora.setActionCommand("scorpora");
        
        accorpa = new javax.swing.JButton();
        accorpa.addActionListener(cL);
        accorpa.setActionCommand("accorpa");
        
        jSeparator2 = new javax.swing.JSeparator();
        
        passa = new javax.swing.JButton();
        passa.addActionListener(cL);
        passa.setActionCommand("passa");
        
        punteggio = new javax.swing.JLabel();
        player = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        
        shop = new javax.swing.JButton();
        
        jSeparator4 = new javax.swing.JSeparator();

        setBackground(new java.awt.Color(241, 157, 30));

        infoUnità.setBackground(new java.awt.Color(241, 157, 30));
        infoUnità.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        infoUnità.setText("Unità: ");

        esperienza.setBackground(new java.awt.Color(241, 157, 30));
        esperienza.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        esperienza.setText(ESPERIENZATXT);

        numUnits.setBackground(new java.awt.Color(241, 157, 30));
        numUnits.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        numUnits.setText(NUMUNITSTXT);

        jSeparator1.setBackground(new java.awt.Color(241, 157, 30));
        jSeparator1.setForeground(new java.awt.Color(196, 68, 4));

        zoom.setBackground(new java.awt.Color(241, 157, 30));
        zoom.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        zoom.setText("ZOOM");

        muovi.setBackground(new java.awt.Color(241, 157, 30));
        muovi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        muovi.setText("MUOVI");
        muovi.setMaximumSize(new java.awt.Dimension(89, 25));
        muovi.setMinimumSize(new java.awt.Dimension(89, 25));
        muovi.setPreferredSize(new java.awt.Dimension(89, 25));

        attacca.setBackground(new java.awt.Color(241, 157, 30));
        attacca.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        attacca.setText("ATTACCA");

        abbandona.setBackground(new java.awt.Color(241, 157, 30));
        abbandona.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        abbandona.setText("ABBANDONA");
        abbandona.setMaximumSize(new java.awt.Dimension(89, 25));
        abbandona.setMinimumSize(new java.awt.Dimension(89, 25));
        abbandona.setPreferredSize(new java.awt.Dimension(89, 25));

        salva.setBackground(new java.awt.Color(241, 157, 30));
        salva.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        salva.setText("SALVA");
        salva.setMaximumSize(new java.awt.Dimension(89, 25));
        salva.setMinimumSize(new java.awt.Dimension(89, 25));
        salva.setPreferredSize(new java.awt.Dimension(89, 25));

        carica.setBackground(new java.awt.Color(241, 157, 30));
        carica.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        carica.setText("CARICA");
        carica.setMaximumSize(new java.awt.Dimension(89, 25));
        carica.setMinimumSize(new java.awt.Dimension(89, 25));
        carica.setPreferredSize(new java.awt.Dimension(89, 25));

        scorpora.setBackground(new java.awt.Color(241, 157, 30));
        scorpora.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        scorpora.setText("SCORPORA");
        scorpora.setMaximumSize(new java.awt.Dimension(89, 25));
        scorpora.setMinimumSize(new java.awt.Dimension(89, 25));
        scorpora.setPreferredSize(new java.awt.Dimension(89, 25));


        accorpa.setBackground(new java.awt.Color(241, 157, 30));
        accorpa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        accorpa.setText("ACCORPA");

        jSeparator2.setBackground(new java.awt.Color(241, 157, 30));
        jSeparator2.setForeground(new java.awt.Color(196, 68, 4));

        passa.setBackground(new java.awt.Color(241, 157, 30));
        passa.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        passa.setText("PASSA TURNO");
        passa.setMaximumSize(new java.awt.Dimension(89, 25));
        passa.setMinimumSize(new java.awt.Dimension(89, 25));
        passa.setPreferredSize(new java.awt.Dimension(89, 25));

        punteggio.setBackground(new java.awt.Color(241, 157, 30));
        punteggio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        punteggio.setText("Punteggio:  ");

        player.setBackground(new java.awt.Color(241, 157, 30));
        player.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        player.setText(PLAYERTXT);

        jSeparator3.setBackground(new java.awt.Color(241, 157, 30));
        jSeparator3.setForeground(new java.awt.Color(196, 68, 4));

        shop.setBackground(new java.awt.Color(241, 157, 30));
        shop.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        shop.setText("SHOP");

        jSeparator4.setBackground(new java.awt.Color(241, 157, 30));
        jSeparator4.setForeground(new java.awt.Color(196, 68, 4));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(salva, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(abbandona, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passa, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(carica, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(player)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(punteggio)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(accorpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(muovi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zoom, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(scorpora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(attacca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shop, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passi)
                        .addGap(37, 37, 37)
                        .addComponent(esperienza)
                        .addGap(37, 37, 37)
                        .addComponent(numUnits)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jSeparator4)
            .addGroup(layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(infoUnità)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(punteggio)
                    .addComponent(player))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoUnità)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(esperienza)
                    .addComponent(numUnits)
                    .addComponent(passi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zoom)
                    .addComponent(shop))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(muovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(attacca))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(accorpa)
                    .addComponent(scorpora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(carica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(abbandona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }                                              

}
