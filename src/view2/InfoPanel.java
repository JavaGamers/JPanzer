package view2;

public class InfoPanel extends javax.swing.JPanel {
	                     
    private javax.swing.JLabel attacco;
    private javax.swing.JLabel bonus;
    private javax.swing.JLabel costo;
    private javax.swing.JLabel difesa;
    private javax.swing.JLabel esperienza;
    private javax.swing.JLabel infoTerritorio;
    private javax.swing.JLabel infoUnità;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel numUnits;
    private javax.swing.JLabel passi;
    private javax.swing.JLabel tipo;    

    public InfoPanel() {
        initComponents();
    }
                        
    private void initComponents() {

        jLabel9 = new javax.swing.JLabel();
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

        jLabel9.setText("jLabel9");

        tipo.setText("Tipo: ");

        costo.setText("Costo attraversamento: ");

        bonus.setText("Bonus: ");

        attacco.setText("Attacco: ");

        difesa.setText("Difesa: ");

        esperienza.setText("Esperienza: ");

        passi.setText("Passi rimanenti: ");

        numUnits.setText("Numero unità: ");

        infoTerritorio.setText("Caratteristiche Territorio");

        infoUnità.setText("Caratteristiche Unità");

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
    }// </editor-fold>                        
              
}
