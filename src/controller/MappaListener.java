package controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import model.Esagono;
import model.EsagonoGrafico;

import view.CommandPanel;
import view.GameWin;
import view.MappaGrafica;

public class MappaListener extends MouseAdapter{

	private GameMode gM = GameMode.getGameMode();

	
	public void mouseClicked(MouseEvent mE){
		
		// codice relativo al selezionamento dell'esagono
		double x = mE.getX();
		double y = mE.getY();
		GameWin gW = this.gM.getGameWin();
		MappaGrafica mG =gW.getMappaGrafica();
		Esagono e=null;
		Graphics2D g2 = (Graphics2D) mG.getGraphics();
		g2.setColor(Color.BLACK);

		EsagonoGrafico eG = new EsagonoGrafico(0, mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
		
		if(mG.getSelezionato()!=-1){
			e = mG.getMappa().getComponent()[mG.getSelezionato()];
			
			eG.newSet(mG.getSelezionato(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		}
		
		e = mG.contains(x, y);
		
		if(e!=null){
			
			g2.setColor(Color.RED);
			mG.setSelezionato(e.getId());
			eG.newSet(e.getId(), mG.getXCentro(), mG.getYCentro(), mG.getRaggio());
			g2.draw(eG);
		
		
			// codice relativo all'update dell'infoPanel
			
			CommandPanel cP = gW.getCommandPanel();
			if(e.getUnit()!=null){
				cP.setUnitLabel(e.getUnit());
			}
		}
	}
}
