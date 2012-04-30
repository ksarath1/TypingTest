package Util;

import java.awt.Color;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CounterThread extends JPanel implements Runnable {


	private static final long serialVersionUID = 1L;
	private Timer counter;
	private JLabel label;

	/**
	 * default constructor
	 */
	public CounterThread(){}

	/**
	 * constructor that takes in the label to be updated
	 * @param labelIn
	 */
	public CounterThread(JLabel labelIn){
		this.label = labelIn;
	}

	/**
	 * new thread doe the stattistics panel
	 */
	public void startThread(){
		Thread panelThread = new Thread(this);
		panelThread.start();
	}

	/**
	 * run methof for the thread
	 */
	@Override
	public void run() {
		label.setText("");
		label.setForeground(Color.RED);
		counter = new Timer();
		counter.schedule(new Counter(label), 0, 1000);
	}

	/**
	 * getter for the label
	 * 
	 * @return JLabel
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * setter for the JLabel
	 * 
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * getter for the serialversionuid
	 * 
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * setter for counter
	 * 
	 * @param counter
	 */
	public void setCounter(Timer counter) {
		this.counter = counter;
	}

	/**
	 * getter for private variable counter
	 * 
	 * @return Timer
	 */
	public Timer getCounter() {
		return counter;
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "CounterThread [counter=" + counter + ", label=" + label + "]";
	}

}
