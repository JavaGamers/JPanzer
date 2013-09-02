package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

import model.Player;
import controller.FinalPanelListener;
import controller.GameMode;

public class FinalPanel extends javax.swing.JPanel {

	private javax.swing.JButton menù;
	private javax.swing.JLabel victoryMessage;
	private javax.swing.JLabel winner;
	public static GameMode gameMode = GameMode.getGameMode();
	private BufferedImage bImg;

	public FinalPanel() {
		initComponents();
	}

	public void setWinner(Player p) {
		this.winner.setText(p.getNome() + "!");
	}

	public void setText(String s) {
		this.victoryMessage.setText(s);
	}

	private void initComponents() {
		Color buttonFore = new Color(240, 180, 0);
		Color buttonBack = new Color(161, 47, 14);
		Font font = new Font("Monotype Corsiva", 3, 80);

		victoryMessage = new javax.swing.JLabel();
		victoryMessage.setFont(font);
		victoryMessage.setForeground(new Color(158, 39, 0));
		victoryMessage
				.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		victoryMessage.setText("Gloria al vincitore ");

		winner = new javax.swing.JLabel();
		winner.setFont(font);
		winner.setForeground(new Color(158, 39, 0));
		winner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		
		menù = new javax.swing.JButton();
		menù.setText("Menù Principale");
		menù.setBackground(buttonBack);
		menù.setForeground(buttonFore);
		menù.addActionListener(new FinalPanelListener());

		try {
			URL imgUrl = getClass().getResource(
					"/view/Icon pack/victory_image.jpg");
			bImg = ImageIO.read(imgUrl);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		Dimension size = new Dimension(bImg.getWidth(null),
				bImg.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														victoryMessage,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														464, Short.MAX_VALUE)
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		menù)))
								.addContainerGap())
				.addComponent(winner, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(victoryMessage,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										116,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(winner)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										34, Short.MAX_VALUE).addComponent(menù)
								.addContainerGap()));
	}

	public void paintComponent(Graphics g) {
		g.drawImage(bImg, 0, 0, null);
	}

	public Image getVictoryImage() {
		return this.bImg;
	}
}
