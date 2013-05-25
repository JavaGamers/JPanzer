package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;

import view.MappaGrafica;

public class MappaListener extends MouseAdapter{

	public static GameMode gameMode = GameMode.getGameMode();
	
	public void mouseClicked(MouseEvent mE){
		// codice relativo al selezionamento dell'esagono
		double x = mE.getX();
		double y = mE.getY();
		MappaGrafica mG =gameMode.getMappaGrafica();
		Mappa m = gameMode.getMappa();
		Esagono e=null;
		Graphics2D g2 = (Graphics2D) mG.getGraphics();
		g2.setColor(Color.BLACK);

		EsagonoGrafico eG = new EsagonoGrafico(0, mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
		if(m.getSelezionato()!=-1){
			e = m.getComponent()[m.getSelezionato()];
			if(gameMode.isSelecionUnitMode()){
				int turno =gameMode.getTurno();
				int settore = e.getCoordinate()[0];
				int posizione = e.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					g2.setColor(Color.BLUE);
				}
			}
			
			eG.newSet(m.getSelezionato(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		}
		
		e = mG.contains(x, y);
		
		if(e!=null){
			g2.setColor(Color.RED);
			m.setSelezionato(e.getId());
			eG.newSet(e.getId(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		
		}
	}
}
