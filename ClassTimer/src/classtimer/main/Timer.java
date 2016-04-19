package classtimer.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;

public class Timer 
{
	int totalTime;
	int timeRemaining;
	long timeLast;
	boolean isRunning;
	SoundPlayer player;
	SimpleDateFormat df;
	TimeZone tz = TimeZone.getTimeZone("UTC");
	
	public Timer(JFrame frame)
	{
		player = new SoundPlayer(frame);
		isRunning = false;
		timeRemaining = 0;
		reset();
		df = new SimpleDateFormat("HH:mm:ss.SSS");
		df.setTimeZone(tz);
	}
	
	public void setTimeToCount(int time)
	{
		totalTime = time;
		timeRemaining = time * 1000;
	}
	
	public void start()
	{
		isRunning = true;
		timeLast = System.currentTimeMillis();
	}
	
	public void pause()
	{
		isRunning = false;
	}
	
	public void reset()
	{
		isRunning = false;
		setTimeToCount(totalTime);
	}
	
	public void update()
	{
		if(isRunning)
		{
			timeRemaining -= System.currentTimeMillis() - timeLast;
			timeLast = System.currentTimeMillis();
			if(timeRemaining <= 0)
			{
				playFinishedSound();
				reset();
			}
		}
	}
	
	public String getFormattedTime()
	{
		return df.format(new Date(timeRemaining));
	}
	
	public void pickSound()
	{
		player.pickSound();
	}
	
	private void playFinishedSound()
	{
		player.playLoadedSound();
	}
}
