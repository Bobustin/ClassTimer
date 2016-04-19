package classtimer.main;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SoundPlayer 
{
	JFileChooser fc;
	File soundFile;
	JFrame frame;
	
	public SoundPlayer(JFrame frame)
	{
		this.frame = frame;
		fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("WAV Files", "wav");
		fc.setFileFilter(filter);
	}
	
	public void pickSound()
	{	    
	    int returnVal = fc.showOpenDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            soundFile = fc.getSelectedFile();
	}
	
	public void playLoadedSound()
	{
		if(soundFile == null)
			return;
		try
	    {
	        Clip clip = AudioSystem.getClip();
	        clip.open(AudioSystem.getAudioInputStream(soundFile));
	        clip.start();
	    }
	    catch (Exception exc)
	    {
	        exc.printStackTrace(System.out);
	    }
	}
}
