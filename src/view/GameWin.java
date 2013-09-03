package view;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import model.SoundEffect;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import controller.GameMode;

public class GameWin extends JFrame {
	public static GameMode gameMode = GameMode.getGameMode();
	private BufferedImage backgroundImg;

	public GameWin(String title) {
		super(title);

		try {
			URL imgUrl = getClass().getResource(
					"/view/Icon pack/JPanzer_background.png");
			backgroundImg = ImageIO.read(imgUrl);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		/*
		 * impostiamo la dimensione della finestra in modo tale da coincidere
		 * con la dimensione dell'immagine backgroundImg
		 */
		Dimension size = new Dimension(backgroundImg.getWidth(null),
				backgroundImg.getHeight(null));
		setPreferredSize(size);
		setSize(size);

		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setResizable(false);
		super.setLayout(new BorderLayout());
		super.pack();
		SoundEffect sound = gameMode.getSound();
		sound.startThemeMusic();
	}

	public static void main(String[] args) {
		GameWin gameWin = new GameWin("JPanzer");
		gameMode.setGameWin(gameWin);
		Container c = gameWin.getContentPane();

		c.add(gameMode.getStartPanel());
		gameWin.setVisible(true);

	}

	public Image getBackgroundImage() {
		return this.backgroundImg;
	}

	public void paint(Graphics g) {
		if (gameMode.getMappaGrafica() != null) {
			MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
			mappaGrafica.newSet(mappaGrafica.getWidth() / 2,
					mappaGrafica.getHeight() / 2);
		}
		super.paint(g);
	}
}
