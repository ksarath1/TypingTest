package Util;

import java.awt.Font;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 * The class that takes care of the counter updation
 * 
 * @author Karthika
 *
 */
public class Counter extends TimerTask{

	private Integer count = new Integer(0);
	private JLabel label;

	/**
	 * The constructor that takes in the label that will be updated
	 * by the counter
	 * @param labelIn
	 */
	public Counter(JLabel labelIn) {
		this.label = labelIn;
		label.setFont(new Font("Times new Roman", Font.BOLD, 50));
	}

	/**
	 * The run method for the timer thread
	 */
	@Override
	public void run() {
		label.setText(count.toString());
		count++;
	}

	/**
	 * getter for the private variable count
	 * 
	 * @return Integer
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * the setter for the count variable
	 * 
	 * @param count
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * getter for the private varibale label
	 * 
	 * @return JLabel
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * the setter for the private variable label
	 * 
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * overridden tostring method
	 */
	@Override
	public String toString() {
		return "Counter [count=" + count + ", label=" + label + "]";
	}

}
