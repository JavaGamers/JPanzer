package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.BorderFactory;

import controller.GameMode;
import controller.StartPanelListener;

public class StartPanel extends javax.swing.JPanel {

	private javax.swing.JButton editMap;
	private javax.swing.JButton loadGame;
	private javax.swing.JButton newGame;
	private javax.swing.JLabel welcomeTxt;
	public static GameMode gameMode = GameMode.getGameMode();
	

	public StartPanel() {
		initComponents();
	}

	private void initComponents() {
		
		Image img = gameMode.getGameWin().getBackgroundImage();
		
		Color buttonFore = new Color(240,180,0);
		Color buttonBack = new Color(161,47,14);

		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);

		StartPanelListener spl = new StartPanelListener();

		newGame = new javax.swing.JButton();
		loadGame = new javax.swing.JButton();
		editMap = new javax.swing.JButton();
		welcomeTxt = new javax.swing.JLabel();

		newGame.setText("Nuova Partita");
		newGame.addActionListener(spl);
		newGame.setActionCommand("new");
		newGame.setForeground(buttonFore);
		newGame.setBackground(buttonBack);

		loadGame.setText("Carica Partita");
		loadGame.addActionListener(spl);
		loadGame.setActionCommand("load");
		loadGame.setForeground(buttonFore);
		loadGame.setBackground(buttonBack);

		editMap.setText("Editor di Mappa");
		editMap.addActionListener(spl);
		editMap.setActionCommand("edit");
		editMap.setForeground(buttonFore);
		editMap.setBackground(buttonBack);

		welcomeTxt.setFont(new java.awt.Font("Monotype Corsiva", 0, 72));
		welcomeTxt.setForeground(new Color(240,180,0));
		welcomeTxt.setText("Benvenuti in JPanzer!");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editMap, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(welcomeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(welcomeTxt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(newGame, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadGame, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(editMap, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );
    }
	
	public void paintComponent(Graphics g) {
		Image img = gameMode.getGameWin().getBackgroundImage();
	    g.drawImage(img, 0, 0, null);
	  }

}
