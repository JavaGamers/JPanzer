package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Esagono;

import view2.EsagonoGrafico;
import view2.MappaGrafica;

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
		double x = mE.getX();
		double y = mE.getY();
		MappaGrafica mG =(MappaGrafica) mE.getSource();
		Esagono e=null;
		int s=0;
		int l=0;
		int p=0;
		EsagonoGrafico eG = new EsagonoGrafico(s, l, p, mG.getXCentro(), mG.getYCentro(), MappaGrafica.RAGGIO, Color.BLACK);
		
		if(mG.getSelezionato()!=-1){
			e = mG.getMappa().getComponent()[mG.getSelezionato()];
			s = e.getSettore();
			l = e.getLivello();
			p = e.getPosizione();
			
			eG.newSet(s, l, p, mG.getXCentro(), mG.getYCentro(), MappaGrafica.RAGGIO, Color.BLACK);
			eG.paint(mG.getGraphics());
		}
		
		e = mG.contains(x, y);
		
		if(e!=null){
			s = e.getSettore();
			l = e.getLivello();
			p = e.getPosizione();
			eG.newSet(s, l, p, mG.getXCentro(), mG.getYCentro(), MappaGrafica.RAGGIO, Color.RED);
			eG.paint(mG.getGraphics());
			mG.setSelezionato(e.getId());
		}
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
