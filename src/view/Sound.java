package view;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import java.io.*;

public class Sound {

	public static void startMusic() {
		try {
			AudioInputStream audio1 = AudioSystem
					.getAudioInputStream(new File(
							"C:/Users/Nhemesi/Documents/GitHub/JPanzer/src/view/Icon pack/prova1.wav"));

			Clip clip = AudioSystem.getClip();
			clip.open(audio1);
			clip.loop(1);

		} catch (IOException error) {
			System.out.println(error.getMessage());
		} catch (UnsupportedAudioFileException e) {
			System.out.println(e.getMessage());
		} catch (LineUnavailableException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void stopMusic() {
		try {
			AudioInputStream audio = AudioSystem
					.getAudioInputStream(new File(
							"C:/Users/Nhemesi/Documents/GitHub/JPanzer/src/view/Icon pack/prova.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.stop();

		} catch (IOException error) {
			System.out.println("0");
		} catch (UnsupportedAudioFileException e) {
			System.out.println("1");
		} catch (LineUnavailableException e) {
			System.out.println("2");
		}
	}
}