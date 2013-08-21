package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.LandListener;

public class LandPanel extends javax.swing.JPanel {

	private javax.swing.JButton collina;
	private javax.swing.JButton foresta;
	private javax.swing.JPanel panel;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JButton lago;
	private javax.swing.JButton montagna;
	private javax.swing.JButton pianura;
	private javax.swing.JButton salva;
	private javax.swing.JButton mainMenu;

	public LandPanel() {
		initComponents();
	}

	public void silenceAll(int option) {
		this.collina.setEnabled(false);
		this.foresta.setEnabled(false);
		this.montagna.setEnabled(false);
		this.lago.setEnabled(false);
		this.mainMenu.setEnabled(false);
		this.salva.setEnabled(false);
		this.pianura.setEnabled(false);
	}

	public void enableAll() {
		this.collina.setEnabled(true);
		this.foresta.setEnabled(true);
		this.montagna.setEnabled(true);
		this.lago.setEnabled(true);
		this.mainMenu.setEnabled(true);
		this.salva.setEnabled(true);
		this.pianura.setEnabled(true);
	}

	private void initComponents() {

		LandListener lL = new LandListener();

		pianura = new javax.swing.JButton();
		collina = new javax.swing.JButton();
		foresta = new javax.swing.JButton();
		montagna = new javax.swing.JButton();
		lago = new javax.swing.JButton();
		salva = new javax.swing.JButton();
		mainMenu = new javax.swing.JButton();

		panel = new JPanel();
		scrollPane = new JScrollPane();

		ImageIcon imgIcon = null;
		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/pianura.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		pianura.setIcon(imgIcon);
		pianura.setText("Pianura");
		pianura.addActionListener(lL);
		pianura.setActionCommand("pianura");
		pianura.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		pianura.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/collina.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		collina.setIcon(imgIcon);
		collina.setText("Collina");
		collina.addActionListener(lL);
		collina.setActionCommand("collina");
		collina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		collina.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/foresta.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		foresta.setIcon(imgIcon);
		foresta.setText("Foresta");
		foresta.addActionListener(lL);
		foresta.setActionCommand("foresta");
		foresta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		foresta.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource(
					"Icon pack/Land Pack/montagna.png");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		montagna.setIcon(imgIcon);
		montagna.setText("Montagna");
		montagna.addActionListener(lL);
		montagna.setActionCommand("montagna");
		montagna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		montagna.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		try {
			URL imgUrl = getClass().getResource("Icon pack/Land Pack/lago.jpg");
			imgIcon = new ImageIcon(imgUrl);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		lago.setIcon(imgIcon);
		lago.setText("Lago");
		lago.addActionListener(lL);
		lago.setActionCommand("lago");
		lago.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		lago.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

		salva.setText("Salva");
		salva.addActionListener(lL);
		salva.setActionCommand("salva");

		mainMenu.setText("Menù principale");
		mainMenu.addActionListener(lL);
		mainMenu.setActionCommand("main");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				panel);
		panel.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(
																mainMenu,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																salva,
																javax.swing.GroupLayout.Alignment.LEADING,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																lago,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																montagna,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																foresta,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																collina,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																pianura,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																165,
																Short.MAX_VALUE))
										.addGap(73, 73, 73)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
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
										.addComponent(lago).addGap(18, 18, 18)
										.addComponent(salva).addGap(18, 18, 18)
										.addComponent(mainMenu)
										.addContainerGap(32, Short.MAX_VALUE)));

		scrollPane.setViewportView(panel);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				scrollPane, javax.swing.GroupLayout.Alignment.TRAILING,
				javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				scrollPane));
	}

}
