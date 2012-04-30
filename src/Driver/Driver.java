package Driver;

import javax.swing.JFrame;

import TypingTest.MyFrame;
/**
 * The Driver class for the project which contains the main method.
 * Invokes the MyFrame class's instance to start the project
 * 
 * @author Karthika
 *
 */
public class Driver {
	/**
	 * The main method creates a new instance of the MYFrame class and 
	 * starts the typing test
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame myFrame = new MyFrame();
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setSize(550,400);
		myFrame.setVisible(true);
	}
	/**
	 * The overridden toString method
	 */
	@Override
	public String toString() {
		return "Driver [getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
