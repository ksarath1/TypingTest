package TypingTest;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;


import Util.TestSampleTextGenerator;
import Util.UpdateHelper;
/**
 * MyItemListener implements the ItemListener interface.
 * used to detect the change in user selection for the
 * combo box
 * 
 * @author Karthika
 *
 */
public class MyItemListener implements ItemListener {

	private MyTextArea textArea;
	private TestSampleTextGenerator stringGenerator;
	private UpdateHelper helper;

	/**
	 * public constructor for the MyItemListener class
	 * 
	 * @param textArea
	 * @param stringGenerator
	 * @param helper
	 */
	public MyItemListener(MyTextArea textArea, TestSampleTextGenerator stringGenerator,UpdateHelper helper ){
		this.helper = helper;
		this.textArea = textArea;
		this.stringGenerator = stringGenerator;
	}

	/**
	 * overridden itemStateChanged method.
	 * will generate a random number and based on the user input 
	 * gets the new string for the test String.
	 * stops the prevviously running timer and reset the test
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == 1) {
			Random randomGenerator = new Random();
			textArea.setText((String)stringGenerator.generateString((String)e.getItem(),randomGenerator.nextInt(4)));
			textArea.setCaretPosition(0);
			helper.stopTimer();
			helper.resetTest();

		}
	}

	/**
	 * getter for the textArea
	 * 
	 * @return MyTextArea
	 */
	public MyTextArea getTextArea() {
		return textArea;
	}

	/**
	 * setter for the textArea
	 * 
	 * @param textArea
	 */
	public void setTextArea(MyTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * getter for the TestSampleTextGenerator instance
	 * 
	 * @return TestSampleTextGenerator
	 */
	public TestSampleTextGenerator getStringGenerator() {
		return stringGenerator;
	}

	/**
	 * setter for the TestSampleTextGenerator instance
	 * @param stringGenerator
	 */
	public void setStringGenerator(TestSampleTextGenerator stringGenerator) {
		this.stringGenerator = stringGenerator;
	}

	/**
	 * getter for the UpdateHelper instance
	 * 
	 * @return UpdateHelper
	 */
	public UpdateHelper getHelper() {
		return helper;
	}

	/**
	 * setter for the UpdateHelper instance
	 * 
	 * @param helper
	 */
	public void setHelper(UpdateHelper helper) {
		this.helper = helper;
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "MyItemListener [textArea=" + textArea + ", stringGenerator="
				+ stringGenerator + ", helper=" + helper + "]";
	}


}



