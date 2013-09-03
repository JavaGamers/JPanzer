package view;

import java.awt.Color;
import java.net.URL;

import javax.swing.ImageIcon;
import controller.LandListener;

public class LandPanel extends javax.swing.JPanel {

	private javax.swing.JButton collina;
	private javax.swing.JButton foresta;
	private javax.swing.JButton lago;
	private javax.swing.JButton montagna;
	private javax.swing.JButton pianura;
	private javax.swing.JButton salva;
	private javax.swing.JButton mainMenu;
	private javax.swing.JButton zoom;

	public LandPanel() {
		initComponents();
	}

	// disabilita tutti i bottoni
	public void silenceAll() {
		this.collina.setEnabled(false);
		this.foresta.setEnabled(false);
		this.montagna.setEnabled(false);
		this.lago.setEnabled(false);
		this.mainMenu.setEnabled(false);
		this.salva.setEnabled(false);
		this.pianura.setEnabled(false);
		this.zoom.setEnabled(false);
	}

	// abilita tutti i bottoni
	public void enableAll() {
		this.collina.setEnabled(true);
		this.foresta.setEnabled(true);
		this.montagna.setEnabled(true);
		this.lago.setEnabled(true);
		this.mainMenu.setEnabled(true);
		this.salva.setEnabled(true);
		this.pianura.setEnabled(true);
		this.zoom.setEnabled(true);
	}

	private void initComponents() {

		LandListener lL = new LandListener();
		Color background = new Color(241, 157, 30);
		setBackground(background);

		pianura = new javax.swing.JButton();
		collina = new javax.swing.JButton();
		foresta = new javax.swing.JButton();
		montagna = new javax.swing.JButton();
		lago = new javax.swing.JButton();
		salva = new javax.swing.JButton();
		mainMenu = new javax.swing.JButton();
		zoom = new javax.swing.JButton();

		ImageIcon imgIcon = null;

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/pianura_mini.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		pianura.setBackground(background);
		pianura.setIcon(imgIcon);
		pianura.setText("Pianura");
		pianura.addActionListener(lL);
		pianura.setActionCommand("pianura");
		pianura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		pianura.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/collina_mini.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		collina.setBackground(background);
		collina.setIcon(imgIcon);
		collina.setText("Collina");
		collina.addActionListener(lL);
		collina.setActionCommand("collina");
		collina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		collina.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/foresta_mini.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		foresta.setBackground(background);
		foresta.setIcon(imgIcon);
		foresta.setText("Foresta");
		foresta.addActionListener(lL);
		foresta.setActionCommand("foresta");
		foresta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		foresta.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/montagna_mini.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		montagna.setBackground(background);
		montagna.setIcon(imgIcon);
		montagna.setText("Montagna");
		montagna.addActionListener(lL);
		montagna.setActionCommand("montagna");
		montagna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		montagna.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/lago_mini.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		lago.setBackground(background);
		lago.setIcon(imgIcon);
		lago.setText("Lago");
		lago.addActionListener(lL);
		lago.setActionCommand("lago");
		lago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lago.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		salva.setBackground(background);
		salva.setText("Salva");
		salva.addActionListener(lL);
		salva.setActionCommand("salva");

		mainMenu.setBackground(background);
		mainMenu.setText("Menù principale");
		mainMenu.addActionListener(lL);
		mainMenu.setActionCommand("main");

		zoom.setBackground(background);
		zoom.setText("Zoom");
		zoom.setActionCommand("zoom");
		zoom.addActionListener(lL);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														pianura,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														collina,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														foresta,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														montagna,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														lago,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														salva,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														mainMenu,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														zoom,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(21, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(pianura)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(collina)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(foresta)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(montagna)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lago)
								.addGap(18, 18, 18)
								.addComponent(zoom)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										36, Short.MAX_VALUE)
								.addComponent(salva).addGap(18, 18, 18)
								.addComponent(mainMenu).addContainerGap()));
	}

}
