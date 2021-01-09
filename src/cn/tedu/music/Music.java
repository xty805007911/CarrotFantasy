package cn.tedu.music;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Music{
	byte[] data;
	AudioFormat format;
	int length;

	//static Executor pool = Executors.newCachedThreadPool();

	public void play(){

		Runnable runner = new Runnable(){
			public void run() {
				AudioInputStream in;
				try {
					in = AudioSystem.getAudioInputStream(
							getClass().getResource("BGMusic.wav"));
					format = in.getFormat();
					length = in.available();
					data = new byte[length];
					in.read(data);
					in.close();
					Clip clip = AudioSystem.getClip();
					clip.open(format, data, 0, length);
					clip.loop(length);
				} catch (UnsupportedAudioFileException | IOException e) {
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		};
		new Thread(runner).start();
	}
}
