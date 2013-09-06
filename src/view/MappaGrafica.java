package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.Popup;

import controller.GameMode;
import controller.MappaListener;
import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;
import model.Unità;

public class MappaGrafica extends JPanel {

	private int xC; // ascissa centro della mappa
	private int yC; // ordinata centro della mappa
	public double raggio; /*
						 * raggio= raggio circonferenza circoscritta a ciascun
						 * esagono
						 */
	private Mappa mappa; // mappa logica da disegnare
	private final static Color BACKGROUND = new Color(116, 156, 44);
	private static final BasicStroke BASICSTROKE = new BasicStroke(3);

	private static Popup popup = null;/*
									 * popup utilizzato per mostrare il numero
									 * di unità presenti sull'esagono su cui è
									 * posizionato il mouse
									 */
	public static GameMode gameMode = GameMode.getGameMode();

	/*
	 * valori possibili dei raggi, rispettivamente: Standard, Zoom Out,
	 * Anteprima
	 */
	public static final double STDRAGGIO = 60;
	public static final double ZOOMRAGGIO = 25;
	public static final double PREVIEWRAGGIO = 8;

	public MappaGrafica(Mappa m, int x, int y) {
		MappaListener mL = new MappaListener();
		this.addMouseListener(mL);
		this.addMouseMotionListener(mL);
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

	public void paintComponent(Graphics g) {

		if (this.raggio != PREVIEWRAGGIO) {
			g.setColor(BACKGROUND);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
		}

		g.setColor(Color.BLACK);
		EsagonoGrafico eG;
		Graphics2D g2 = (Graphics2D) g;
		Esagono e = this.mappa.getComponent()[0];
		Image imgLand = null;
		Image imgUnit = null;
		Esagono selected = null;
		int turno;
		Unità unitSelected = null;
		Unità u = null;

		eG = new EsagonoGrafico(e.getId(), this.xC, this.yC, raggio);

		for (int i = 0; i < this.mappa.getComponent().length; i++) {
			g.setColor(Color.BLACK);
			g2.setStroke(BASICSTROKE);
			e = this.mappa.getComponent()[i];
			turno = gameMode.getTurno();
			if (this.mappa.getSelezionato() != -1) {
				selected = this.mappa.getComponent()[this.mappa
						.getSelezionato()];
				unitSelected = selected.getUnit();

				if (e.equals(selected)) {
					g.setColor(Color.RED);
				}
			}

			if (gameMode.isSelecionUnitMode()) {
				turno = gameMode.getTurno();
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if (((turno == 1 && settore > 3) || (turno == 2 && settore < 4))
						&& !(settore == 4 && posizione == 0)
						&& !(settore == 1 && posizione == 0)) {
					g.setColor(Color.BLUE);
				}
			}

			if (gameMode.isAttackMode()) {
				if (e.isAdiacente(selected)) {
					u = e.getUnit();
					if (u != null && u.getPlayer() != turno) {
						g.setColor(Color.MAGENTA);
					}
				}
			}

			if (gameMode.isAccorpaMode()) {

				LinkedList<Esagono> esagoniRaggiungibili = unitSelected
						.getEsagoniRaggiungibili();

				if (esagoniRaggiungibili.contains(e) && !e.equals(selected)
						&& e.isAdiacente(selected)) {
					u = e.getUnit();
					if (u != null && unitSelected.isSameUnitOf(u)
							&& u.getPlayer() == turno) {
						g.setColor(Color.MAGENTA);
					}
				}
			}

			if (gameMode.isScorporaMode()) {

				LinkedList<Esagono> esagoniRaggiungibili = unitSelected
						.getEsagoniRaggiungibili();

				if (esagoniRaggiungibili.contains(e) && e.isAdiacente(selected)) {
					u = e.getUnit();
					if (u == null) {
						g2.setColor(Color.MAGENTA);
					}
				}
			}

			if (gameMode.isMovingMode()) {

				LinkedList<Esagono> esagoniRaggiungibili = unitSelected
						.getEsagoniRaggiungibili();

				if (esagoniRaggiungibili.contains(e)) {
					g2.setColor(Color.YELLOW);
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

		super.paintComponents(g2);
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

	public static Popup getPopup() {
		return popup;
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

	public static void setPopup(Popup p) {
		popup = p;
	}
}
