package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
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
	
	public Dimension getPreferredSize() {
        if (bImg == null) {
             return new Dimension(100,100); // da ridefinire
        } else {
           return new Dimension(bImg.getWidth(null), bImg.getHeight(null));
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
	
	public void paint(Graphics g) {
        g.drawImage(bImg, 0, 0, null);
    }

}