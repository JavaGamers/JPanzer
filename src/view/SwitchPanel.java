package view;

import model.Player;
import controller.GameMode;
import controller.SwitchPanelListener;

public class SwitchPanel extends javax.swing.JPanel {

                  
    private javax.swing.JLabel day;
    private javax.swing.JLabel playerLabel;
    private static final String DAY = "Giorno ";
    private static final String TURNO = "E' il turno di ";
    public static GameMode gameMode = GameMode.getGameMode();
 
    
    public SwitchPanel() {
        initComponents();
    }
    
    public void updateLabel(){
    	this.day.setText(DAY+gameMode.getTurnCounter());
    	Player p = gameMode.getPlayer(gameMode.getTurno());
    	this.playerLabel.setText(TURNO+p.getNome());
    }
                       
    private void initComponents() {
    	SwitchPanelListener spl = new SwitchPanelListener();
    	this.addMouseListener(spl);

        day = new javax.swing.JLabel();
        playerLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        day.setForeground(new java.awt.Color(255, 255, 255));
        day.setFont(new java.awt.Font("Monotype Corsiva", 0, 72));
        day.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        day.setText(DAY);

        playerLabel.setForeground(new java.awt.Color(255, 255, 255));
        playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerLabel.setFont(new java.awt.Font("Monotype Corsiva", 0, 72));
        playerLabel.setText(TURNO);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(day, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(playerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(day, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }                                       
}