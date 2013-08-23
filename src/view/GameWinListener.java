package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import controller.GameMode;

import model.Esagono;
import model.EsagonoGrafico;
import model.Unità;

public class GameWinListener extends MouseAdapter {

	public static GameMode gameMode = GameMode.getGameMode();

	public void mouseMoved(MouseEvent mE) {
		double x = mE.getX();
		double y = mE.getY();

		// mappaGrafica e suoi attributi
		MappaGrafica mappaGrafica = gameMode.getMappaGrafica();
		if (mappaGrafica != null) {
			int xC = mappaGrafica.getXCentro();
			int yC = mappaGrafica.getYCentro();
			double raggio = mappaGrafica.getRaggio();

			// esagono su cui è presente il mouse(in questo momento)
			Esagono e = mappaGrafica.contains(x, y);
			if (e != null) {
				System.out.println("entra");
				EsagonoGrafico eG = new EsagonoGrafico(e.getId(), xC, yC,
						raggio);
				int xLabel = eG.xpoints[4];
				int yLabel = eG.ypoints[4];
				Unità u = e.getUnit();
				if (u != null) {
					JLabel label = new JLabel("Hello, World");
					PopupFactory factory = PopupFactory.getSharedInstance();
					final Popup popup = factory.getPopup(mappaGrafica, label,
							xLabel, yLabel);
					popup.show();
				}
			}
		}
	}
}
