package TypingTest;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 * MyTextArea is an customized text area which is an 
 * abstract class that extends JTextArea
 * 
 * @author Karthika
 *
 */
public abstract class MyTextArea extends JTextArea {

	private static final long serialVersionUID = 1L;

	/**
	 * Construct used for setting up the properties of the 
	 * textArea
	 * 
	 * @param textIn
	 */
	public 	MyTextArea(String textIn){

		Font font = new Font("Times New Roman", Font.PLAIN, 15);
		setLineWrap(true);
		setWrapStyleWord(true);
		setFont(font);
		setForeground(Color.black);
		setAutoscrolls(true);
		setSize(350,200);
		//setPreferredSize(new Dimension(350,200));
		setText(textIn);
		setFocusable(true);
	}

	/**
	 * abstarct method getTextLength which will return
	 * the length of the text in the textArea
	 * 
	 * @return int
	 */
	public abstract int getTextLength();

	/**
	 * getSerialversionuid returns the serialversionuid
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "MyTextArea [getTextLength()=" + getTextLength() + "]";
	}
}
