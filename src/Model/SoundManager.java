package Model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

/**
 * Used for reading sound files and playing them. It is required that the sound file is
 * of type "wav" and has a maximum of 16-bit quality in order to be played.
 * The sound file must be located in the "res/Sounds/" folder inside the application
 * folder. 
 * 
 * The engine for playing the sound is inside a thread so that the game doesn't pause
 * while the sound is playing.
 * 
 * @author Martin Greer
 * @version 2015-03-03
 * 
 */
public class SoundManager {
	
	/**
	 * The audio file as a File object.
	 */
	static File AudioFile;
	
	/**
	 * The function to call for playing a sound. 
	 * 
	 * @param file The name of the file to play, including file type. Example: "sound.wav"
	 */
	public static synchronized void playSound(String file) {
		
		AudioFile = new File("./res/Sounds/" + file);
		
		new Thread(new Runnable() {
			public void run(){
				try{
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(AudioFile));
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-20.0f); // Reduce volume by 20 decibels.
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}