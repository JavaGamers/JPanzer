package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JSlider;
import javax.swing.JTextField;

import model.Mappa;
import model.Player;
import controller.GameMode;
import controller.InitGameListener;

public class InitGame extends javax.swing.JPanel {

	private MappaGrafica PreviewMap;

	private javax.swing.JButton back;
	private javax.swing.JButton chooseMap;
	private javax.swing.JButton forward;

	private javax.swing.JTextField p1Name;
	private javax.swing.JTextField p1initValueMoney;
	private javax.swing.JTextField p2Name;
	private javax.swing.JTextField p2initValueMoney;

	private javax.swing.JSlider p1Slider;
	private javax.swing.JSlider p2Slider;

	private javax.swing.JLabel p1NameLabel;
	private javax.swing.JLabel p1moneyLabel;
	private javax.swing.JLabel p2NameLabel;
	private javax.swing.JLabel p2moneyLabel;
	private javax.swing.JLabel player1Label;
	private javax.swing.JLabel player2Label;
	private javax.swing.JLabel titleLabel;

	public static final Mappa DEFMAP = new Mappa(Mappa.MEDIUM);
	public static GameMode gameMode = GameMode.getGameMode();

	public InitGame() {
		initComponents();
	}

	public MappaGrafica getPreviewMap() {
		return this.PreviewMap;
	}

	public JTextField getTextFieldNome(int num) {
		if (num < 1 || num > 2) {
			throw new IllegalArgumentException(
					"invalid number: there are only 2 textfields");
		}
		if (num == 1)
			return this.p1Name;
		else
			return this.p2Name;
	}

	public JTextField getTextFieldSoldi(int num) {
		if (num < 1 || num > 2) {
			throw new IllegalArgumentException(
					"invalid number: there are only 2 textfields");
		}
		if (num == 1)
			return this.p1initValueMoney;
		else
			return this.p2initValueMoney;
	}

	public JSlider getSlider(int num) {
		if (num < 1 || num > 2) {
			throw new IllegalArgumentException(
					"invalid number: there are only 2 textfields");
		}
		if (num == 1)
			return this.p1Slider;
		else
			return this.p2Slider;
	}

	private void initComponents() {

		Image img = gameMode.getGameWin().getBackgroundImage();
		Color color = new Color(240, 180, 0);

		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		InitGameListener igl = new InitGameListener();
		titleLabel = new javax.swing.JLabel();

		PreviewMap = new MappaGrafica(DEFMAP, -50, 130);
		PreviewMap.setRaggio(MappaGrafica.PREVIEWRAGGIO);

		chooseMap = new javax.swing.JButton();
		player1Label = new javax.swing.JLabel();
		p1NameLabel = new javax.swing.JLabel();
		p1Name = new javax.swing.JTextField();
		p1moneyLabel = new javax.swing.JLabel();
		p1initValueMoney = new javax.swing.JTextField();
		p1Slider = new JSlider(JSlider.HORIZONTAL, Player.MINMONEY,
				Player.MAXMONEY, Player.STDMONEY);
		p2Slider = new JSlider(JSlider.HORIZONTAL, Player.MINMONEY,
				Player.MAXMONEY, Player.STDMONEY);
		p2initValueMoney = new javax.swing.JTextField();
		p2Name = new javax.swing.JTextField();
		p2moneyLabel = new javax.swing.JLabel();
		player2Label = new javax.swing.JLabel();
		p2NameLabel = new javax.swing.JLabel();
		back = new javax.swing.JButton();
		forward = new javax.swing.JButton();

		titleLabel.setFont(new java.awt.Font("Monotype Corsiva", 0, 60));
		titleLabel.setForeground(color);
		titleLabel.setText("Pronti per cominciare...");

		p1initValueMoney.setText("" + Player.STDMONEY);
		p2initValueMoney.setText("" + Player.STDMONEY);

		PreviewMap.setPreferredSize(new java.awt.Dimension(245, 245));

		javax.swing.GroupLayout PreviewMapLayout = new javax.swing.GroupLayout(
				PreviewMap);
		PreviewMap.setLayout(PreviewMapLayout);
		PreviewMapLayout.setHorizontalGroup(PreviewMapLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 245, Short.MAX_VALUE));
		PreviewMapLayout.setVerticalGroup(PreviewMapLayout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 245,
				Short.MAX_VALUE));

		chooseMap.setText("Scegli Mappa");
		chooseMap.addActionListener(igl);
		chooseMap.setActionCommand("chooseMap");

		p1Slider.addChangeListener(igl);
		p1Slider.setMajorTickSpacing(500);
		p1Slider.setMinorTickSpacing(100);
		p1Slider.setPaintTicks(true);

		p2Slider.addChangeListener(igl);
		p2Slider.setMajorTickSpacing(500);
		p2Slider.setMinorTickSpacing(100);
		p2Slider.setPaintTicks(true);

		player1Label.setText("Player 1");
		player1Label.setForeground(color);

		p1NameLabel.setText("Nome: ");
		p1NameLabel.setForeground(color);

		p1moneyLabel.setText("Soldi Iniziali: ");
		p1moneyLabel.setForeground(color);

		p2moneyLabel.setText("Soldi Iniziali: ");
		p2moneyLabel.setForeground(color);

		player2Label.setText("Player 2");
		player2Label.setForeground(color);

		p2NameLabel.setText("Nome: ");
		p2NameLabel.setForeground(color);

		back.setText("Indietro");
		back.addActionListener(igl);
		back.setActionCommand("back");

		forward.setText("Avanti");
		forward.addActionListener(igl);
		forward.setActionCommand("forward");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(41, Short.MAX_VALUE)
								.addComponent(titleLabel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										563,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(41, Short.MAX_VALUE))
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						player1Label)
																				.addGroup(
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														p2Slider,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						p2moneyLabel)
																																				.addComponent(
																																						p2NameLabel))
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																																.addGroup(
																																		layout.createParallelGroup(
																																				javax.swing.GroupLayout.Alignment.LEADING)
																																				.addComponent(
																																						p2Name,
																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																						119,
																																						javax.swing.GroupLayout.PREFERRED_SIZE)
																																				.addComponent(
																																						p2initValueMoney,
																																						javax.swing.GroupLayout.PREFERRED_SIZE,
																																						50,
																																						javax.swing.GroupLayout.PREFERRED_SIZE))))
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														player2Label)
																												.addComponent(
																														p1Slider,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														p1moneyLabel)
																												.addComponent(
																														p1NameLabel))
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														p1Name,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														119,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addComponent(
																														p1initValueMoney,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														50,
																														javax.swing.GroupLayout.PREFERRED_SIZE))))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										chooseMap)
																								.addContainerGap(
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										PreviewMap,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addContainerGap())))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		back,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		85,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		forward,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		86,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(44, 44,
																		44)))));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(titleLabel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										45,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(23, 23, 23)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		player1Label)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						p1NameLabel)
																				.addComponent(
																						p1Name,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						p1moneyLabel)
																				.addComponent(
																						p1initValueMoney,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18,
																		18)
																.addComponent(
																		p1Slider,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(18, 18,
																		18)
																.addComponent(
																		player2Label)
																.addGap(18, 18,
																		18)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						p2NameLabel)
																				.addComponent(
																						p2Name,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						p2moneyLabel)
																				.addComponent(
																						p2initValueMoney,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(18, 18,
																		18)
																.addComponent(
																		p2Slider,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		chooseMap)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		36,
																		Short.MAX_VALUE)
																.addComponent(
																		PreviewMap,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										15, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(back)
												.addComponent(forward))
								.addGap(33, 33, 33)));
	}

	public void paintComponent(Graphics g) {
		Image img = gameMode.getGameWin().getBackgroundImage();
		g.drawImage(img, 0, 0, null);
	}

}
