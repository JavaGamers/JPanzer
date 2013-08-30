package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

import controller.GameMode;
import controller.InitMapPanelListener;

public class InitMapPanel extends javax.swing.JPanel {

	private javax.swing.JButton back;
	private javax.swing.JList<String> dimList;
	private javax.swing.JButton editMap;
	private javax.swing.JButton forward;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton newMap;
	private javax.swing.JLabel titleLabel;
	public static GameMode gameMode = GameMode.getGameMode();

	public InitMapPanel() {
		initComponents();
	}

	public boolean setDimListEnable() {
		boolean ok = false;
		if (!this.dimList.isEnabled()) {
			this.dimList.setEnabled(true);
			ok = true;
		}
		return ok;
	}

	public JList getDimList() {
		return this.dimList;
	}

	private void initComponents() {
		
		Color buttonFore = new Color(240,180,0);
		Color buttonBack = new Color(161,47,14);

		InitMapPanelListener impl = new InitMapPanelListener();

		Image img = gameMode.getGameWin().getBackgroundImage();

		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);

		titleLabel = new javax.swing.JLabel();
		editMap = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		dimList = new javax.swing.JList();
		newMap = new javax.swing.JButton();
		back = new javax.swing.JButton();
		forward = new javax.swing.JButton();

		titleLabel.setFont(new java.awt.Font("Monotype Corsiva", 0, 60));
		titleLabel.setForeground(buttonFore);
		titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		titleLabel.setText("Cosa vuoi fare?");

		editMap.setText("Edita preesistente");
		editMap.setForeground(buttonFore);
		editMap.setBackground(buttonBack);
		editMap.addActionListener(impl);
		editMap.setActionCommand("edit");

		dimList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Piccola", "Media", "Grande", "Epica" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		dimList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dimList.setSelectedIndex(1);
		dimList.setEnabled(false);
		dimList.setVisibleRowCount(4);
		dimList.addListSelectionListener(impl);
		dimList.setBackground(buttonBack);
		dimList.setForeground(buttonFore);

		jScrollPane1.setViewportView(dimList);

		newMap.setText("Nuova Mappa");
		newMap.setForeground(buttonFore);
		newMap.setBackground(buttonBack);
		newMap.addActionListener(impl);
		newMap.setActionCommand("new");

		back.setText("Indietro");
		back.setForeground(buttonFore);
		back.setBackground(buttonBack);
		back.addActionListener(impl);
		back.setActionCommand("back");

		forward.setText("Avanti");
		forward.setForeground(buttonFore);
		forward.setBackground(buttonBack);
		forward.addActionListener(impl);
		forward.setActionCommand("forward");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(141, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		back,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		90,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		forward,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		90,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														titleLabel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														337, Short.MAX_VALUE)
												.addComponent(
														editMap,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														newMap,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(jScrollPane1))
								.addContainerGap(125, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(titleLabel)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										72, Short.MAX_VALUE)
								.addComponent(editMap,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										51,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, Short.MAX_VALUE)
								.addComponent(newMap,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										51,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										62, Short.MAX_VALUE)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										82,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, Short.MAX_VALUE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														back,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														30, Short.MAX_VALUE)
												.addComponent(
														forward,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(94, Short.MAX_VALUE)));
	}
	
	public void paintComponent(Graphics g) {
		Image img = gameMode.getGameWin().getBackgroundImage();
	    g.drawImage(img, 0, 0, null);
	  }

}
