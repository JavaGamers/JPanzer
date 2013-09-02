package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import model.Player;
import model.Unità;
import controller.CommandListener;
import controller.GameMode;

public class CommandPanel extends javax.swing.JPanel {

	private javax.swing.JButton abbandona;
	private javax.swing.JButton accorpa;
	private javax.swing.JButton attacca;
	private javax.swing.JButton carica;
	private javax.swing.JButton muovi;
	private javax.swing.JButton passa;
	private javax.swing.JButton salva;
	private javax.swing.JButton scorpora;
	private javax.swing.JButton shop;
	private javax.swing.JButton zoom;

	private javax.swing.JLabel esperienza;
	private javax.swing.JLabel infoBattle1;
	private javax.swing.JLabel infoBattle2;
	private javax.swing.JLabel infoBattle3;
	private javax.swing.JLabel infoBattle4;
	private javax.swing.JLabel infoUnità;
	private javax.swing.JLabel soldi;
	private javax.swing.JLabel numUnits;
	private javax.swing.JLabel player;
	private javax.swing.JLabel passi;

	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JSeparator jSeparator5;

	public static GameMode gameMode = GameMode.getGameMode();

	private static final String ESPERIENZATXT = "Esperienza: ";
	private static final String NUMUNITSTXT = "Numero: ";
	private static final String PASSITXT = "Passi rimanenti: ";
	private static final String PLAYERTXT = "Giocatore: ";
	private static final String SOLDITXT = "Soldi: ";
	private static final String NOMEUNITATXT = "Unità: ";

	public CommandPanel() {
		initComponents();
	}

	// disabilita bottoni: option = 0 tutti, option = 1 tutti tranne lo zoom
	public void silenceAll() {
		this.abbandona.setEnabled(false);
		this.accorpa.setEnabled(false);
		this.attacca.setEnabled(false);
		this.carica.setEnabled(false);
		this.muovi.setEnabled(false);
		this.passa.setEnabled(false);
		this.salva.setEnabled(false);
		this.scorpora.setEnabled(false);
		this.shop.setEnabled(false);

	}

	public void enableAll() {
		this.abbandona.setEnabled(true);
		this.accorpa.setEnabled(true);
		this.attacca.setEnabled(true);
		this.carica.setEnabled(true);
		this.muovi.setEnabled(true);
		this.passa.setEnabled(true);
		this.salva.setEnabled(true);
		this.scorpora.setEnabled(true);
		this.shop.setEnabled(true);
	}

	public void setUnitLabel(Unità u) {
		if (u != null) {
			this.infoUnità.setText(NOMEUNITATXT + u.getNome());
			this.esperienza.setText(ESPERIENZATXT + (int)(u.getEsp() * 10));
			this.passi.setText(PASSITXT + u.getPassi());
			this.numUnits.setText(NUMUNITSTXT + u.getNumUnits());
		} else {
			this.infoUnità.setText(NOMEUNITATXT);
			this.esperienza.setText(ESPERIENZATXT);
			this.passi.setText(PASSITXT);
			this.numUnits.setText(NUMUNITSTXT);
		}
	}

	public void setPlayerLabel(Player p) {
		this.soldi.setText(SOLDITXT + p.getSoldi());
		this.player.setText(PLAYERTXT + p.getNome());
	}

	// disabilita bottoni: option = 0 tutti, option = 1 tutti tranne lo zoom

	public void setInfoBattleLabel(Unità att, Unità def) {

		Player playerAtt = gameMode.getPlayer(att.getPlayer());
		Player playerDef = gameMode.getPlayer(def.getPlayer());

		this.infoBattle1.setText(att.getNome() + " di " + playerAtt.getNome()
				+ " attacca con " + att.getAtt() * att.getNumUnits()
				+ " potenza di fuoco ");

		this.infoBattle3
				.setText(def.getNome() + " di " + playerDef.getNome()
						+ " difende con " + def.getDef() * def.getNumUnits()
						+ " scudo");
	}

	public void setBattleStatsLabel(Unità att, Unità def) {
		this.infoBattle2.setText("Dopo la battaglia resta con "
				+ att.getNumUnits() + " unità");
		this.infoBattle4.setText("Dopo la battaglia resta con "
				+ def.getNumUnits() + " unità");
	}

	private void initComponents() {

		
		Color background = new Color(241, 157, 30);
		Color foreground = new Color(196, 68, 4);
		Dimension stdDim = new Dimension(89, 25);
		Font font = new Font("Arial", 1, 14);

		CommandListener cL = new CommandListener();
		setBackground(background);

		infoUnità = new javax.swing.JLabel();
		infoUnità.setBackground(background);
		infoUnità.setFont(font);
		infoUnità.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		infoUnità.setText(NOMEUNITATXT);
		
		esperienza = new javax.swing.JLabel();
		esperienza.setBackground(background);
		esperienza.setFont(font);
		esperienza.setText(ESPERIENZATXT);
		
		numUnits = new javax.swing.JLabel();
		numUnits.setBackground(background);
		numUnits.setFont(font);
		numUnits.setText(NUMUNITSTXT);

		passi = new javax.swing.JLabel();
		passi.setFont(font);
		passi.setText(PASSITXT);

		jSeparator1 = new javax.swing.JSeparator();
		jSeparator1.setBackground(background);
		jSeparator1.setForeground(foreground);

		zoom = new javax.swing.JButton();
		zoom.addActionListener(cL);
		zoom.setActionCommand("zoom");
		zoom.setBackground(background);
		zoom.setFont(font);
		zoom.setText("ZOOM");
		

		muovi = new javax.swing.JButton();
		muovi.addActionListener(cL);
		muovi.setActionCommand("muovi");
		muovi.setBackground(background);
		muovi.setFont(font);
		muovi.setText("MUOVI");
		muovi.setMaximumSize(stdDim);
		muovi.setMinimumSize(stdDim);
		muovi.setPreferredSize(stdDim);

		attacca = new javax.swing.JButton();
		attacca.addActionListener(cL);
		attacca.setActionCommand("attacca");
		attacca.setBackground(background);
		attacca.setFont(font);
		attacca.setText("ATTACCA");

		abbandona = new javax.swing.JButton();
		abbandona.addActionListener(cL);
		abbandona.setActionCommand("abbandona");
		abbandona.setBackground(background);
		abbandona.setFont(font);
		abbandona.setText("ABBANDONA");
		abbandona.setMaximumSize(stdDim);
		abbandona.setMinimumSize(stdDim);
		abbandona.setPreferredSize(stdDim);
		
		salva = new javax.swing.JButton();
		salva.addActionListener(cL);
		salva.setActionCommand("salva");
		salva.setBackground(background);
		salva.setFont(font);
		salva.setText("SALVA");
		salva.setMaximumSize(stdDim);
		salva.setMinimumSize(stdDim);
		salva.setPreferredSize(stdDim);
		
		carica = new javax.swing.JButton();
		carica.addActionListener(cL);
		carica.setActionCommand("carica");
		carica.setBackground(background);
		carica.setFont(font);
		carica.setText("CARICA");
		carica.setMaximumSize(stdDim);
		carica.setMinimumSize(stdDim);
		carica.setPreferredSize(stdDim);
		
		scorpora = new javax.swing.JButton();
		scorpora.addActionListener(cL);
		scorpora.setActionCommand("scorpora");
		scorpora.setBackground(background);
		scorpora.setFont(font);
		scorpora.setText("SCORPORA");
		scorpora.setMaximumSize(stdDim);
		scorpora.setMinimumSize(stdDim);
		scorpora.setPreferredSize(stdDim);

		accorpa = new javax.swing.JButton();
		accorpa.addActionListener(cL);
		accorpa.setActionCommand("accorpa");
		accorpa.setBackground(background);
		accorpa.setFont(font);
		accorpa.setText("ACCORPA");

		jSeparator2 = new javax.swing.JSeparator();
		jSeparator2.setBackground(background);
		jSeparator2.setForeground(foreground);	

		passa = new javax.swing.JButton();
		passa.addActionListener(cL);
		passa.setActionCommand("passa");
		passa.setBackground(background);
		passa.setFont(font);
		passa.setText("PASSA TURNO");
		passa.setMaximumSize(stdDim);
		passa.setMinimumSize(stdDim);
		passa.setPreferredSize(stdDim);

		soldi = new javax.swing.JLabel();
		soldi.setBackground(background);
		soldi.setFont(font);
		soldi.setText(SOLDITXT);
		
		player = new javax.swing.JLabel();
		player.setBackground(background);
		player.setFont(font);
		player.setText(PLAYERTXT);
		
		jSeparator3 = new javax.swing.JSeparator();
		jSeparator3.setBackground(background);
		jSeparator3.setForeground(foreground);

		shop = new javax.swing.JButton();
		shop.addActionListener(cL);
		shop.setActionCommand("shop");
		shop.setBackground(background);
		shop.setFont(font);
		shop.setText("SHOP");

		jSeparator4 = new javax.swing.JSeparator();
		jSeparator4.setBackground(background);
		jSeparator4.setForeground(foreground);	
		
		jSeparator5 = new javax.swing.JSeparator();
		jSeparator5.setBackground(background);
		jSeparator5.setForeground(foreground);

		infoBattle1 = new javax.swing.JLabel();
		infoBattle1.setText("");

		infoBattle2 = new javax.swing.JLabel();
		infoBattle2.setText("");

		infoBattle3 = new javax.swing.JLabel();
		infoBattle3.setText("");

		infoBattle4 = new javax.swing.JLabel();
		infoBattle4.setText("");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jSeparator1)
				.addComponent(jSeparator2)
				.addComponent(jSeparator3)
				.addComponent(jSeparator4)
				.addComponent(jSeparator5)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														infoUnità,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														infoBattle1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						salva,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						136,
																						Short.MAX_VALUE)
																				.addComponent(
																						abbandona,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						passa,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						136,
																						Short.MAX_VALUE)
																				.addComponent(
																						carica,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(26, 26,
																		26)
																.addComponent(
																		player,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		soldi,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		94,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						accorpa,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						muovi,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						zoom,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						136,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		97,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																				.addComponent(
																						scorpora,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						attacca,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						shop,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						136,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		passi,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		145,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		esperienza,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		numUnits,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		84,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														infoBattle2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														infoBattle3,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														infoBattle4,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(soldi)
												.addComponent(player))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator3,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(16, 16, 16)
								.addComponent(infoUnità)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(esperienza)
												.addComponent(numUnits)
												.addComponent(passi))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(zoom)
												.addComponent(shop))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(17, 17, 17)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														muovi,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(attacca))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(accorpa)
												.addComponent(
														scorpora,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jSeparator2,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														salva,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														carica,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														abbandona,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														passa,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(jSeparator5,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										10,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(infoBattle1).addGap(18, 18, 18)
								.addComponent(infoBattle2).addGap(18, 18, 18)
								.addComponent(infoBattle3).addGap(18, 18, 18)
								.addComponent(infoBattle4)
								.addContainerGap(38, Short.MAX_VALUE)));
	}
}
