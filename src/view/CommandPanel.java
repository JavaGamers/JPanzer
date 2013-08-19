package view;

import model.Player;
import model.Unità;
import controller.CommandListener;

public class CommandPanel extends javax.swing.JPanel {
	
    
	private javax.swing.JButton abbandona;
	private javax.swing.JButton accorpa;
	private javax.swing.JButton attacca;
	private javax.swing.JButton carica;
	private javax.swing.JLabel esperienza;
	private javax.swing.JLabel infoBattle;
    private javax.swing.JLabel infoAtt;
    private javax.swing.JLabel infoDef;
	private javax.swing.JLabel infoUnità;
	private javax.swing.JLabel soldi;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JSeparator jSeparator5;
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
    private static final String SOLDITXT = "Soldi: ";

    

    public CommandPanel() {
        initComponents();
    }
    
    public void setUnitLabel(Unità u){
    	if(u!=null){
	    	this.esperienza.setText(ESPERIENZATXT+u.getEsp());
	    	this.passi.setText(PASSITXT+u.getPassi());
	    	this.numUnits.setText(NUMUNITSTXT+u.getNumUnits());
    	}
    	else{
    		this.esperienza.setText(ESPERIENZATXT);
	    	this.passi.setText(PASSITXT);
	    	this.numUnits.setText(NUMUNITSTXT);
    	}
    }
    
    public void setPlayerLabel(Player p){
    	this.soldi.setText(SOLDITXT+p.getSoldi());
    	this.player.setText(PLAYERTXT+p.getNome());
    }
    
    // disabilita bottoni: option = 0 tutti, option = 1 tutti tranne lo zoom 
    public void silenceAll(int option){
    	this.abbandona.setEnabled(false);
    	this.accorpa.setEnabled(false);
    	this.attacca.setEnabled(false);
    	this.carica.setEnabled(false);
    	this.muovi.setEnabled(false);
    	this.passa.setEnabled(false);
    	this.salva.setEnabled(false);
    	this.scorpora.setEnabled(false);
    	this.shop.setEnabled(false);
    	if(option<0 || option>1){
    		throw new IllegalArgumentException("argomento errato del metodo silenceAll");
    	}
    	else if(option == 0){
    		this.zoom.setEnabled(false);
    	}
    }
    
    public void enableAll(){
    	this.abbandona.setEnabled(true);
    	this.accorpa.setEnabled(true);
    	this.attacca.setEnabled(true);
    	this.carica.setEnabled(true);
    	this.muovi.setEnabled(true);
    	this.passa.setEnabled(true);
    	this.salva.setEnabled(true);
    	this.scorpora.setEnabled(true);
    	this.shop.setEnabled(true);
    	this.zoom.setEnabled(true);
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
        
        soldi = new javax.swing.JLabel();
        player = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        
        shop = new javax.swing.JButton();
        shop.addActionListener(cL);
        shop.setActionCommand("shop");
        
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
        abbandona.setText("ABBANDONA PARTITA");
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

        soldi.setBackground(new java.awt.Color(241, 157, 30));
        soldi.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        soldi.setText(SOLDITXT);

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
        
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator5.setBackground(new java.awt.Color(241, 157, 30));
        jSeparator5.setForeground(new java.awt.Color(196, 68, 4));

        infoBattle = new javax.swing.JLabel();
        infoBattle.setText("jLabel1");

        infoAtt = new javax.swing.JLabel();
        infoAtt.setText("jLabel1");

        infoDef = new javax.swing.JLabel();
        infoDef.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoBattle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addComponent(player, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(soldi, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(accorpa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(muovi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(zoom, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(scorpora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(attacca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(shop, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(passi, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(esperienza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numUnits, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(infoUnità, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(infoAtt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoDef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soldi)
                    .addComponent(player))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(infoUnità)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(18, 18, 18)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoBattle)
                .addGap(18, 18, 18)
                .addComponent(infoAtt)
                .addGap(18, 18, 18)
                .addComponent(infoDef)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }                                              

}
