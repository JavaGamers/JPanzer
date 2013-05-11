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

public class MappaListener extends MouseAdapter implements ActionListener {
	
	public static final String UPOPT = "up";
	public static final String DOWNOPT = "down";
	public static final String LEFTOPT = "left";
	public static final String RIGHTOPT = "right";

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		
		if(com.equals(UPOPT)){
			upOpt();
		}
		else if(com.equals(DOWNOPT)){
			downOpt();
		}
		else if(com.equals(LEFTOPT)){
			leftOpt();
		}
		else if(com.equals(RIGHTOPT)){
			leftOpt();
		}
	}
	
	public void mouseClicked(MouseEvent mE){
		
		// codice relativo al selezionamento dell'esagono
		double x = mE.getX();
		double y = mE.getY();
		MappaGrafica mG =(MappaGrafica) mE.getSource();
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
			GameWin gW = (GameWin)SwingUtilities.getRoot(mG);
			CommandPanel cP = gW.getCommandPanel();
			if(e.getUnit()!=null){
				cP.setUnitLabel(e.getUnit());
			}
		}
		System.out.println(mG.getSelezionato());
	}

	private void leftOpt() {
		// TODO Auto-generated method stub
		
	}

	private void downOpt() {
		// TODO Auto-generated method stub
		
	}

	private void upOpt() {
		// TODO Auto-generated method stub
		
	}

}
