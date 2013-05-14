package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Lago implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unit� sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della lago
	private final static int costo= 0; // non verr� mai attraversato
	
	public Lago(){
		try {
			URL imgUrl=getClass().getResource("/view/Icon pack/Land Pack/Lago_Icon.png");
			bImg = ImageIO.read(imgUrl);
          
       } catch (Exception e) {
    	   System.out.println(e.toString());
       }
	}
	
	public String getNome() {
		return "Lago";
	}

	public int getCosto() {
		return costo;
	}
	
	public int getBonus(){
		return BONUS;
	}

	
	public Image getImage() {
		return bImg;
	}
	
}