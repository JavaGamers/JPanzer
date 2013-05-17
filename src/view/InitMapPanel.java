package view;

import javax.swing.JList;

import controller.InitMapPanelListener;

public class InitMapPanel extends javax.swing.JPanel {

                       
    private javax.swing.JButton back;
    private javax.swing.JList dimList;
    private javax.swing.JButton editMap;
    private javax.swing.JButton forward;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newMap;
    private javax.swing.JLabel titleLabel;
      
    
    public InitMapPanel() {
        initComponents();
    }
    
    public boolean setDimListEnable(){
    	boolean ok = false;
    	if(!this.dimList.isEnabled()){
    		this.dimList.setEnabled(true);
    		ok=true;
    	}
    	return ok;
    }
    
    public JList getDimList(){
    	return this.dimList;
    }
                        
    private void initComponents() {
    	
    	InitMapPanelListener impl = new InitMapPanelListener();

        titleLabel = new javax.swing.JLabel();
        editMap = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dimList = new javax.swing.JList();
        newMap = new javax.swing.JButton();
        back = new javax.swing.JButton();
        forward = new javax.swing.JButton();

        titleLabel.setFont(new java.awt.Font("Monotype Corsiva", 0, 48)); // NOI18N
        titleLabel.setText("Cosa vuoi fare?");

        editMap.setText("Edita preesistente");
        editMap.addActionListener(impl);
        editMap.setActionCommand("edit");

        dimList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Piccola", "Media", "Grande", "Epica"};
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        dimList.setEnabled(false);
        dimList.addListSelectionListener(impl);
        
        jScrollPane1.setViewportView(dimList);

        newMap.setText("Nuova Mappa");
        newMap.addActionListener(impl);
        newMap.setActionCommand("new");

        back.setText("Indietro");
        back.addActionListener(impl);
        back.setActionCommand("back");

        forward.setText("Avanti");
        forward.addActionListener(impl);
        forward.setActionCommand("forward");

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
