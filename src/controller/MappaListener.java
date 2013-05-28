package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

import model.Esagono;
import model.EsagonoGrafico;
import model.Mappa;
import model.Unità;

import view.MappaGrafica;

public class MappaListener extends MouseAdapter{

	public static GameMode gameMode = GameMode.getGameMode();
	
	public void mouseClicked(MouseEvent mE){
		// coordinate del click
		double x = mE.getX();
		double y = mE.getY();
		
		//mappaGrafica e suoi attributi
		MappaGrafica mappaGrafica =gameMode.getMappaGrafica();
		int xC = mappaGrafica.getXCentro();
		int yC = mappaGrafica.getYCentro();
		double raggio = mappaGrafica.getRaggio();
		
		Mappa m = gameMode.getMappa();
		Unità selectedUnit = null;
		Image img = null;
		
		// variabili puntatore
		Esagono nuovo = null;
		Esagono vecchio = null;
		int oldSelected = m.getSelezionato();
		int newSelected = Integer.MAX_VALUE;
		
		
		Graphics2D g2 = (Graphics2D) mappaGrafica.getGraphics();
		g2.setColor(Color.BLACK);
		EsagonoGrafico eG = new EsagonoGrafico(0,xC,yC, raggio);
		
		// se qualcuno era selezionato (prima di questo click)
		if(oldSelected!=-1){
			vecchio = m.getComponent()[oldSelected];
			eG.newSet(oldSelected, xC, yC, raggio);
			
			if(gameMode.isSelecionUnitMode()){
				int turno =gameMode.getTurno();
				int settore = vecchio.getCoordinate()[0];
				int posizione = vecchio.getCoordinate()[2];
				if(((turno==1 && settore>3) || (turno==2 && settore<4)) && !(settore==4 && posizione==0) && !(settore==1 && posizione==0)){
					g2.setColor(Color.BLUE);
				}
			}
			g2.draw(eG);
		}
		
		// esagono su cui si è cliccato (in questo momento)
		nuovo = mappaGrafica.contains(x, y);
		
		if(nuovo!=null){
			newSelected = nuovo.getId();
			m.setSelezionato(newSelected);
			eG.newSet(newSelected, xC, yC, raggio);
			
			if(gameMode.isMovingMode()){
				selectedUnit = vecchio.getUnit();
				List<Esagono> esagoniRaggiungibili = selectedUnit.getEsagoniRaggiungibili();
				if(esagoniRaggiungibili.contains(nuovo) && nuovo.getUnit()==null){
					
					// cancello l'unità dalla vecchia posizione
					vecchio.setUnit(null);
					img = vecchio.getTerritorio().getImage();
					eG.newSet(oldSelected, xC, yC, raggio);
					mappaGrafica.paintImage(g2, eG, img);
					
					// disegno l'unità sulla destinazione
					eG.newSet(newSelected, xC, yC, raggio);
					nuovo.setUnit(selectedUnit);
					img = selectedUnit.getImage();
					mappaGrafica.paintImage(g2, eG, img);
					// da controllare
					selectedUnit.aggiornaPassi((int) nuovo.getMinDistance());
				}
				gameMode.setMovingMode(false);
				
				// ridisegno i contorni dei vecchi esagoni raggiungibili
				Iterator<Esagono> it = esagoniRaggiungibili.iterator();
				int id =0;
				Esagono e = null;
				
			//	EsagonoGrafico eG2=new EsagonoGrafico(0,xC,yC, raggio);
				while(it.hasNext()){
					e = it.next();
					id = e.getId();
					eG.newSet(id, xC, yC, raggio);
					g2.setColor(Color.BLACK);
					g2.draw(eG);
				}
			}
			
			g2.setColor(Color.RED);
			g2.draw(eG);
		}
	}
}
