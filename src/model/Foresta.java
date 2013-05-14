package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Foresta implements Territorio {

	private final static int BONUS=0; // bonus di permanenza delle unità sul territorio (DA RIDEFINIRE)
	private BufferedImage bImg; // immagine della foresta
	private final static int costo= 4; // da ridefinire
	
	public Foresta(){
		try {
			URL imgUrl=getClass().getResource("/view/Icon pack/Land Pack/Foresta_Icon.png");
			bImg = ImageIO.read(imgUrl);
          
       } catch (Exception e) {
    	   System.out.println(e.toString());
       }
	}
	
	public String getNome() {
		return "Foresta";
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