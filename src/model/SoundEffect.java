package model;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoundEffect {

	private URL themeMusic;
	private URL attackMusic;
	private URL moveMusic;

	public SoundEffect() {
		this.themeMusic = getClass().getResource("Sound/musicTheme.wav");
		this.moveMusic = getClass().getResource("Sound/moveMusic.wav");
		this.attackMusic = getClass().getResource("Sound/attackMusic.wav");

	}

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
			// whatevers
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
			// whatevers
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
			// whatevers
		}
	}
}