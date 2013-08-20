package view;

import controller.LoadWinListener;

public class LoadWin extends javax.swing.JFrame {
	
	private javax.swing.JButton annulla;
	private javax.swing.JLabel exitMessage;
	private javax.swing.JButton no;
	private javax.swing.JButton yes;


    public LoadWin() {
        initComponents();
    }

                        
    private void initComponents() {
    	
    	LoadWinListener lWL = new LoadWinListener();
  
        exitMessage = new javax.swing.JLabel();
        yes = new javax.swing.JButton();
        no = new javax.swing.JButton();
        annulla = new javax.swing.JButton();

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        exitMessage.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); 
        exitMessage.setText("Stai abbandonando la partita corrente: vuoi salvare prima di uscire?");

        yes.setText("SI");
        yes.addActionListener(lWL);
        yes.setActionCommand("si");
        
        no.setText("NO");
        no.addActionListener(lWL);
        no.setActionCommand("no");

        annulla.setText("ANNULLA");
        annulla.addActionListener(lWL);
        annulla.setActionCommand("annulla");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exitMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(yes)
                        .addGap(18, 18, 18)
                        .addComponent(no)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(annulla)
                        .addGap(25, 25, 25))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(exitMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yes)
                    .addComponent(no)
                    .addComponent(annulla))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }                                 
}
