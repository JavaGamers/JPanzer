package model;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Montagna extends Component implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unit� sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della montagna
	private final static int costo= 4; // da ridefinire
	
	public Montagna(){
		try {
	           bImg = ImageIO.read(new File("da inserire"));
	       } catch (IOException e) {
	    	   // da scrivere
	       }
	}

	public String getNome() {
		return "Montagna";
	}

	public int getCosto() {
		return costo;
	}
	
	public int getBonus(){
		return BONUS;
	}
	
}