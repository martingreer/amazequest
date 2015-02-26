package Model;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundManager {
	
	static File AudioFile;
	
	public static synchronized void playSound(String file) {
		
		AudioFile = new File("./res/Sounds/" + file);
		
		new Thread(new Runnable() {
			public void run(){
				try{
					Clip clip = AudioSystem.getClip();
					clip.open(AudioSystem.getAudioInputStream(AudioFile));
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
					clip.start();
					Thread.sleep(clip.getMicrosecondLength()/1000);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}