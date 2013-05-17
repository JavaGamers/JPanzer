package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Esagono;
import model.EsagonoGrafico;

import view.CommandPanel;
import view.MappaGrafica;

public class MappaListener extends MouseAdapter{

	public static GameMode gameMode = GameMode.getGameMode();
	
	public void mouseClicked(MouseEvent mE){
		// codice relativo al selezionamento dell'esagono
		double x = mE.getX();
		double y = mE.getY();
		MappaGrafica mG =gameMode.getMappaGrafica();
		if(mG==null)
			System.out.println("la mappa è nulla");
		Esagono e=null;
		Graphics2D g2 = (Graphics2D) mG.getGraphics();
		g2.setColor(Color.BLACK);

		EsagonoGrafico eG = new EsagonoGrafico(0, mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
		System.out.println(""+mG.getSelezionato());
		if(mG.getSelezionato()!=-1){
			e = mG.getMappa().getComponent()[mG.getSelezionato()];
			
			eG.newSet(mG.getSelezionato(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		}
		
		e = mG.contains(x, y);
		
		if(e!=null){
			System.out.println("trovato");
			g2.setColor(Color.RED);
			mG.setSelezionato(e.getId());
			eG.newSet(e.getId(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		
		
			// codice relativo all'update dell'infoPanel
			/*
			CommandPanel cP = gameMode.getCommandPanel();
			if(e.getUnit()!=null){
				cP.setUnitLabel(e.getUnit());    
			}  */
		}
	}
}
