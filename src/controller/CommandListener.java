package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import view.GameWin;
import view.MappaGrafica;

public class CommandListener implements ActionListener {
	public final static String ZOOMOPT = "zoom";
	public final static String MUOVIOPT = "muovi";
	public final static String ATTACCAOPT = "attacca";
	public final static String UNDOOPT = "undo";
	public final static String ABBANDONAOPT = "abbandona";
	public final static String SALVAOPT = "salva";
	public final static String CARICAOPT = "carica";
	public final static String SCORPORAOPT = "scorpora";
	public final static String ACCORPAOPT = "accorpa";
	public final static String PASSAOPT = "passa";
	

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		JButton source = (JButton) e.getSource();
		
		if(com.equals(ZOOMOPT)){
			zoomOpt(source);
		}
		else if(com.equals(MUOVIOPT)){
			muoviOpt();
		}
		else if(com.equals(ABBANDONAOPT)){
			abbandonaOpt();
		}
		else if(com.equals(ACCORPAOPT)){
			accorpaOpt();
		}
		else if(com.equals(ATTACCAOPT)){
			attaccaOpt();
		}
		else if(com.equals(CARICAOPT)){
			caricaOpt();
		}
		else if(com.equals(PASSAOPT)){
			passaOpt();
		}
		else if(com.equals(SALVAOPT)){
			salvaOpt();
		}
		else if(com.equals(SCORPORAOPT)){
			scorporaOpt();
		}
		else if(com.equals(UNDOOPT)){
			undoOpt();
		}
		
		
			
	}


	private void undoOpt() {
		// TODO Auto-generated method stub
		
	}


	private void scorporaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void salvaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void passaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void caricaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void attaccaOpt() {
		
		
	}


	private void accorpaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void abbandonaOpt() {
		// TODO Auto-generated method stub
		
	}


	private void muoviOpt() {
		// TODO Auto-generated method stub
		
	}


	private void zoomOpt(JButton b) {
		// potenzialmenta dannoso!!
		GameWin dW = (GameWin) b.getParent().getParent().getParent().getParent().getParent();
		MappaGrafica mG = dW.getMappaGrafica();
		if(mG.getRaggio()== MappaGrafica.STDRAGGIO)
			mG.setRaggio(MappaGrafica.ZOOMRAGGIO);
		else
			mG.setRaggio(MappaGrafica.STDRAGGIO);
			mG.update(mG.getGraphics());
	}

}
