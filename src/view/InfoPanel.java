package view;

import model.Territorio;
import model.Unità;

public class InfoPanel extends javax.swing.JPanel {
	                     
    private javax.swing.JLabel attacco;
    private javax.swing.JLabel bonus;
    private javax.swing.JLabel costo;
    private javax.swing.JLabel difesa;
    private javax.swing.JLabel esperienza;
    private javax.swing.JLabel infoTerritorio;
    private javax.swing.JLabel infoUnità;
    private javax.swing.JLabel numUnits;
    private javax.swing.JLabel passi;
    private javax.swing.JLabel tipo;    
    
    private static final String ATTACCOTXT = "Attacco: ";
    private static final String BONUSTXT = "Bonus: ";
    private static final String COSTOTXT = "Costo attraversamento: ";
    private static final String DIFESATXT = "Difesa: ";
    private static final String ESPERIENZATXT = "Esperienza: ";
    private static final String INFOUNITATXT = "Caratteristiche Unità: ";
    private static final String NUMUNITSTXT = "Numero unità: ";
    private static final String PASSITXT = "Passi rimanenti: ";
    private static final String TIPOTXT = "Tipo: ";
    private static final String INFOTERRITORIOTXT = "Tipo: ";

    public InfoPanel() {
        initComponents();
    }
    
    public void setUnitLabel(Unità u){
    	this.attacco.setText(ATTACCOTXT+u.getAtt());
    	
    	this.difesa.setText(DIFESATXT+u.getDif());
    	
    	this.difesa.setText(DIFESATXT+u.getEsp());
    	
    	this.esperienza.setText(ESPERIENZATXT+u.getEsp());
    	
    	this.passi.setText(PASSITXT+u.getPassi());
    	
    	this.numUnits.setText(NUMUNITSTXT+u.getNumUnits());
    	
    	this.infoUnità.setText(INFOUNITATXT+u.getNome());
    }
    
    public void setLandLabel(Territorio t){
    	this.tipo.setText(TIPOTXT+t.getNome());
    	
    	this.bonus.setText(BONUSTXT+t.getBonus());
    	
    	this.costo.setText(COSTOTXT+t.getCosto());
    }
                        
    private void initComponents() {

        tipo = new javax.swing.JLabel();
        costo = new javax.swing.JLabel();
        bonus = new javax.swing.JLabel();
        attacco = new javax.swing.JLabel();
        difesa = new javax.swing.JLabel();
        esperienza = new javax.swing.JLabel();
        passi = new javax.swing.JLabel();
        numUnits = new javax.swing.JLabel();
        infoTerritorio = new javax.swing.JLabel();
        infoUnità = new javax.swing.JLabel();

        tipo.setText(TIPOTXT);

        costo.setText(COSTOTXT);

        bonus.setText(BONUSTXT);

        attacco.setText(ATTACCOTXT);

        difesa.setText(DIFESATXT);

        esperienza.setText(ESPERIENZATXT);

        passi.setText(PASSITXT);

        numUnits.setText(NUMUNITSTXT);

        infoTerritorio.setText(INFOTERRITORIOTXT);

        infoUnità.setText(INFOUNITATXT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(costo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bonus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(infoTerritorio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoUnità)
                    .addComponent(esperienza)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(attacco)
                            .addComponent(difesa))
                        .addGap(97, 97, 97)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numUnits)
                            .addComponent(passi))))
                .addGap(64, 64, 64))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(infoTerritorio)
                    .addComponent(infoUnità))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo)
                    .addComponent(attacco)
                    .addComponent(passi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(costo)
                    .addComponent(difesa)
                    .addComponent(numUnits))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bonus)
                    .addComponent(esperienza))
                .addContainerGap())
        );
    }                       
              
}
