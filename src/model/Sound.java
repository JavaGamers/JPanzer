package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.IOException;
import java.net.URL;

public class Sound {

	private AudioInputStream themeMusic;
	private AudioInputStream moveMusic;
	private AudioInputStream attackMusic;

	public Sound() {

		// musicTheme
		try {
			URL songUrl = getClass().getResource(
					"/view/Icon pack/musicTheme.wav");
			this.themeMusic = AudioSystem.getAudioInputStream(songUrl);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e.getMessage());
		}

		// moveMusic
		try {
			URL songUrl = getClass().getResource(
					"/view/Icon pack/moveMusic.wav");
			this.moveMusic = AudioSystem.getAudioInputStream(songUrl);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e.getMessage());
		}

		// attackMusic
		try {
			URL songUrl = getClass().getResource(
					"/view/Icon pack/attackMusic.wav");
			this.attackMusic = AudioSystem.getAudioInputStream(songUrl);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e.getMessage());
		}

	}

	public void startThemeMusic() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(this.themeMusic);
			FloatControl gainControl = (FloatControl) clip
					.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10.0f);
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}

	public void startMoveMusic() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(this.moveMusic);
			clip.loop(0);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}

	public void startAttackMusic() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(this.attackMusic);
			clip.loop(0);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}
}