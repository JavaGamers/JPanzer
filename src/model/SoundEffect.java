package model;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

import java.net.URL;

public class SoundEffect {

	// URL dei temi musicali del gioco
	private URL themeMusic;
	private URL attackMusic;
	private URL moveMusic;

	public SoundEffect() {
		this.themeMusic = getClass().getResource("Sound/musicTheme.wav");
		this.moveMusic = getClass().getResource("Sound/moveMusic.wav");
		this.attackMusic = getClass().getResource("Sound/attackMusic.wav");

	}

	/*
	 * I seguenti metodi servono a creare delle clip musicali da degli
	 * AudioInputStream ottenuti dagli URL dei temi musicali. Il themeMusic dopo
	 * la chiamata del metodo corrispondente cicla all'infinito finchè il gioco
	 * è aperto.
	 */

	public void startThemeMusic() {
		try {

			AudioInputStream stream = AudioSystem
					.getAudioInputStream(this.themeMusic);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-5.0f);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void startMoveMusic() {
		try {

			AudioInputStream stream = AudioSystem
					.getAudioInputStream(this.moveMusic);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void startAttackMusic() {
		try {

			AudioInputStream stream = AudioSystem
					.getAudioInputStream(this.attackMusic);
			AudioFormat format = stream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			clip.start();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}