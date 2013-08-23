package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import controller.GameMode;
import controller.MappaListener;
import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;

public class MappaGrafica extends JPanel {

	private int xC;
	private int yC;
	public double raggio;
	private Mappa mappa;
	public static GameMode gameMode = GameMode.getGameMode();

	public static final double STDRAGGIO = 70;
	public static final double ZOOMRAGGIO = 30;
	public static final double PREVIEWRAGGIO = 8;

	public MappaGrafica(Mappa m, int x, int y) {
		this.addMouseListener(new MappaListener());
		this.raggio = STDRAGGIO;
		this.xC = x;
		this.yC = y;
		this.mappa = m;

		EsagonoGrafico eG = new EsagonoGrafico(0, 0, 0, STDRAGGIO);

		int height = (int) ((m.getDim() * 2 + 1) * 2 * eG.getApotema());
		int width = 0;
		if (m.getDim() % 2 == 0) {
			width = (int) (3 * m.getDim() * STDRAGGIO + 2 * STDRAGGIO);
		} else {
			width = (int) (3 * m.getDim() * STDRAGGIO + 4 * STDRAGGIO);
		}

		Dimension d = new Dimension(width, height);
		this.setPreferredSize(d);

	}

	public void paint(Graphics g) {

		g.setColor(Color.BLACK);
		EsagonoGrafico eG;
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		Esagono e = this.mappa.getComponent()[0];
		Image imgLand = null;
		Image imgUnit = null;

		// zoom in
		if (!gameMode.isZoomOutMode()) {

			// disegno del 1° esagono

			eG = new EsagonoGrafico(e.getId(), this.xC, this.yC, raggio);

			if (e.getTerritorio() != null) {
				imgLand = e.getTerritorio().getImage();
			}
			if (e.getUnit() != null) {
				imgUnit = e.getUnit().getImage();
			}
			this.paintImage(g2, eG, imgLand);
			this.paintImage(g2, eG, imgUnit);
			g2.draw(eG);

			// disegno degli altri esagoni
			for (int i = 1; i < this.mappa.getComponent().length; i++) {
				g.setColor(Color.BLACK);
				e = this.mappa.getComponent()[i];
				if (gameMode.isSelecionUnitMode()) {
					int turno = gameMode.getTurno();
					int settore = e.getCoordinate()[0];
					int posizione = e.getCoordinate()[2];
					if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
							&& !(settore == 4 && posizione == 0)
							&& !(settore == 1 && posizione == 0)) {
						g.setColor(Color.BLUE);
					}
				}
				eG.newSet(e.getId(), this.xC, this.yC, raggio);

				if (e.getTerritorio() != null) {
					imgLand = e.getTerritorio().getImage();
				} else {
					imgLand = null;
				}

				if (e.getUnit() != null) {
					imgUnit = e.getUnit().getImage();
				} else {
					imgUnit = null;
				}
				this.paintImage(g2, eG, imgLand);
				this.paintImage(g2, eG, imgUnit);
				g2.draw(eG);
			}
		}
		// zoom out
		else {

			// disegno del 1° esagono

			eG = new EsagonoGrafico(e.getId(), this.xC, this.yC, raggio);

			if (e.getTerritorio() != null) {
				imgLand = e.getTerritorio().getImage();
			}
			if (e.getUnit() != null) {
				imgUnit = e.getUnit().getXImage();
			}
			this.paintImage(g2, eG, imgLand);
			this.paintImage(g2, eG, imgUnit);
			g2.draw(eG);

			// disegno degli altri esagoni
			for (int i = 1; i < this.mappa.getComponent().length; i++) {
				g.setColor(Color.BLACK);
				e = this.mappa.getComponent()[i];
				if (gameMode.isSelecionUnitMode()) {
					int turno = gameMode.getTurno();
					int settore = e.getCoordinate()[0];
					int posizione = e.getCoordinate()[2];
					if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
							&& !(settore == 4 && posizione == 0)
							&& !(settore == 1 && posizione == 0)) {
						g.setColor(Color.BLUE);
					}
				}
				eG.newSet(e.getId(), this.xC, this.yC, raggio);

				if (e.getTerritorio() != null) {
					imgLand = e.getTerritorio().getImage();
				} else {
					imgLand = null;
				}

				if (e.getUnit() != null) {
					imgUnit = e.getUnit().getXImage();
				} else {
					imgUnit = null;
				}
				this.paintImage(g2, eG, imgLand);
				this.paintImage(g2, eG, imgUnit);
				g2.draw(eG);
			}
		}
		// super.paintComponent(g2);
	}

	public void paintImage(Graphics g, EsagonoGrafico eG, Image img) {
		Graphics2D g2 = (Graphics2D) g;
		int height = 0;
		int width = 0;
		if (img != null) {
			height = img.getHeight(null);
			width = img.getWidth(null);
		}

		g2.setClip(eG);
		g2.clip(eG);
		g2.drawImage(img, (int) (eG.getX() - width / 2),
				(int) (eG.getY() - height / 2), null);
	}

	public Esagono contains(double x, double y) {

		Esagono e = null;

		boolean trovato = false;
		EsagonoGrafico eG = new EsagonoGrafico(0, this.xC, this.yC, raggio);

		for (int i = 0; i < this.mappa.getComponent().length && !trovato; i++) {

			eG.newSet(i, this.xC, this.yC, raggio);

			if (eG.contains(x, y)) {

				e = this.mappa.getComponent()[i];
				trovato = true;
			}
		}
		return e;
	}

	public int getXCentro() {
		return this.xC;
	}

	public int getYCentro() {
		return this.yC;
	}

	public Mappa getMappa() {
		return this.mappa;
	}

	public double getRaggio() {
		return this.raggio;
	}

	public void newSet(int x, int y) {
		this.xC = x;
		this.yC = y;
	}

	public void setRaggio(double r) {
		this.raggio = r;
	}

	public void setMappa(Mappa m) {
		this.mappa = m;
	}
}
