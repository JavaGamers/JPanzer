package view;

public class InitMapPanel extends javax.swing.JPanel {

                       
    private javax.swing.JButton back;
    private javax.swing.JList dimList;
    private javax.swing.JButton editMap;
    private javax.swing.JButton forward;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newMap;
    private javax.swing.JLabel titleLabel;
      
    
    public InitMapPanel() {
        initComponents();
    }
                        
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        editMap = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dimList = new javax.swing.JList();
        newMap = new javax.swing.JButton();
        back = new javax.swing.JButton();
        forward = new javax.swing.JButton();

        jButton1.setText("jButton1");

        titleLabel.setFont(new java.awt.Font("Monotype Corsiva", 0, 48)); // NOI18N
        titleLabel.setText("Cosa vuoi fare?");

        editMap.setText("Edita preesistente");

        dimList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Piccola", "Media", "Grande", "Epica"};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(dimList);

        newMap.setText("Nuova Mappa");

        back.setText("Indietro");

        forward.setText("Avanti");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                    .addComponent(editMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(forward, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(editMap)
                .addGap(18, 18, 18)
                .addComponent(newMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(back)
                    .addComponent(forward))
                .addContainerGap())
        );
    }                               
                 
}
