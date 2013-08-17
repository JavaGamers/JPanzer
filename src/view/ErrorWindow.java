package view;

public class ErrorWindow extends javax.swing.JFrame {
                    
    private javax.swing.JLabel errorMessage;
    public static final String DEFTEXT = "C'è qualcosa che non va: ";
   
    public ErrorWindow() {
        initComponents();
    }
    
    public void setErrorLabel(String s){
    	this.errorMessage.setText(DEFTEXT+s);
    }
                         
    private void initComponents() {

        errorMessage = new javax.swing.JLabel();

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);

        errorMessage.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); 
        errorMessage.setText(DEFTEXT);
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(errorMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(errorMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }
                 
}