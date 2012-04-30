package Util;

import java.awt.Color;

import javax.swing.text.DefaultHighlighter;
/**
 * Supporting class for the Highlighter
 * 
 * @author Karthika
 *
 */
public class ErrorHighlighter  extends DefaultHighlighter.DefaultHighlightPainter {
	/**
	 * Constructor that takes in the color and sets the highlighter's color
	 * 
	 * @param color
	 */
	public ErrorHighlighter(Color color) {
		super(color);
	}
	/**
	 * overridden tostring method
	 */
	@Override
	public String toString() {
		return "ErrorHighlighter []";
	}
}
