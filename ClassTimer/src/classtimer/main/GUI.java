package classtimer.main;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class GUI implements ActionListener
{
	private JFrame mainFrame;
	private JPanel controlPanel;
	private JLabel timeRemaining;
	private Timer timer;
	private JTextField timeEntry;
	private JButton start, stop, reset, pickSound, testSound;
	boolean running;
	
	public GUI()
	{
		createGUI();
		timer = new Timer(mainFrame);
		running = true;
		update();
	}
	
	private void createGUI()
	{
		mainFrame = new JFrame("Timer Window");
	    mainFrame.setSize(400,400);    
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mainFrame.setResizable(true);
	    
	    controlPanel = new JPanel();
	    controlPanel.setLayout(new GridLayout(1, 6));

	    timeRemaining = new JLabel("15:00.000");
	    
	    start = new JButton("Start");
	    stop  = new JButton("Stop");
	    reset = new JButton("Reset");
	    pickSound = new JButton("Pick Sound");
	    testSound = new JButton("Test Sound");
	    timeEntry = new JTextField("900");
	    
	    start.addActionListener(this);
	    stop.addActionListener(this);
	    reset.addActionListener(this);
	    pickSound.addActionListener(this);
	    testSound.addActionListener(this);
	    
	    controlPanel.add(start);
	    controlPanel.add(stop);
	    controlPanel.add(reset);
	    controlPanel.add(pickSound);
	    controlPanel.add(testSound);
	    controlPanel.add(timeEntry);

	    mainFrame.add(timeRemaining,BorderLayout.CENTER);
	    mainFrame.add(controlPanel,BorderLayout.SOUTH);
	    mainFrame.setVisible(true); 
	}
	
	private void update()
	{
		while(running)
		{
			timer.update();
			timeRemaining.setText(timer.getFormattedTime());
			
			Font timeRemainingFont = timeRemaining.getFont();
		    String timeRemainingText = timeRemaining.getText();
		    int stringWidth = timeRemaining.getFontMetrics(timeRemainingFont).stringWidth(timeRemainingText);
		    int componentWidth = timeRemaining.getWidth();
		    double widthRatio = (double)componentWidth / (double)stringWidth;
		    int newFontSize = (int)(timeRemainingFont.getSize() * widthRatio);
		    int componentHeight = timeRemaining.getHeight();
		    int fontSizeToUse = Math.min(newFontSize, componentHeight);
		    timeRemaining.setFont(new Font(timeRemainingFont.getName(), Font.PLAIN, fontSizeToUse));
		    
			mainFrame.revalidate();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String action = e.getActionCommand();
		if(action.equals("Start"))
		{
			System.out.println(e);
			timer.start();
		}
		if(action.equals("Stop"))
		{
			System.out.println(e);
			timer.pause();
		}
		if(action.equals("Reset"))
		{
			System.out.println(e);
			timer.setTimeToCount(Integer.parseInt(timeEntry.getText()));
			timer.reset();
		}
		if(action.equals("Pick Sound"))
		{
			System.out.println(e);
			timer.pickSound();
		}
		if(action.equals("Test Sound"))
		{
			System.out.println(e);
			timer.player.playLoadedSound();
		}
	}
}
