package TypingTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Util.UpdateHelper;

/**
 * Listener class implements the DocumentListener and KeListener
 * interfaces and is used in the project for detecting the changes in
 * the textAreas of the editable text area and also to detect the key 
 * strokes made by the user.
 * 
 * @author Karthika
 *
 */
public class Listener implements DocumentListener,KeyListener,MouseListener{

	private UpdateHelper helper;

	/**
	 * Constructor for the class Listener. Takes in an
	 * instance of the UpdateHelper class as parameter
	 * 
	 * @param helperIn
	 */
	public Listener(UpdateHelper helperIn){
		helper = helperIn;
	}

	/**
	 * The changedUpdate method - overridden method
	 */
	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	/**
	 * The insertUpdate method is used to start the timer thread
	 * and also when the user entered data becomes the same length 
	 * as the test string, the method stops the timer, calls the
	 * updateStatistics method and also invokes thefindError method 
	 * to detect the errors in the user typed text
	 */
	@Override
	public void insertUpdate(DocumentEvent e) {

		helper.updateCaretPositionOnAddition();

		if(e.getDocument().getLength() == 1 && helper.isFirstTime() == true){
			helper.setFirstTime(false);
			helper.startTimer();
		}

		if(e.getDocument().getLength() == helper.getNonEditableLength() ){
			helper.stopTimer();
			helper.updateStatistics();
			helper.findErrors();
		}
	}

	/**
	 * removeUpdate method udjusts the Caret position on removing a character 
	 * from the entered text
	 */
	@Override
	public void removeUpdate(DocumentEvent e) {
		helper.updateCaretPositionOnDelete();
	}

	/**
	 * keypressed method - overridden method
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * keyReleased method - overridden method
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub	
	}

	/**
	 * keyTyped method is used to detect whether the entered key
	 * is a back space if so it invokes the updateCurrentWordCountOnBackSpace
	 * method. also it checks for the repeatedly entered or consecutive
	 * white spaces
	 */
	@Override
	public void keyTyped(KeyEvent e) {

		if(e.getKeyChar() == 8){
			helper.updateCurrentWordCountOnBackSpace();
		}

		if((e.getKeyChar() == ' ' || e.getKeyChar() == '\n')&& helper.isWhiteSpace() == false){
			helper.setWhiteSpace(true);
			helper.setCaretPositionOnAddition();
			helper.updateCurrentWordCount();
		}else if(e.getKeyChar() != ' '){
			helper.setWhiteSpace(false);
		}
	}

	/**
	 * The getter method for the UpdateHelper instance
	 * 
	 * @return UpdateHelper
	 */
	public UpdateHelper getHelper() {
		return helper;
	}

	/**
	 * The setter method for the UpdateHelper instance 
	 * 
	 * @param helper
	 */
	public void setHelper(UpdateHelper helper) {
		this.helper = helper;
	}

	/**
	 * overridden method for detcting the user's first click on the 
	 * text Area
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(helper.getEditableText().equals("Click and start typing here... "))
			helper.textAreaClicked();
	}

	/**
	 * overridden method 
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * overridden method 
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * overridden method 
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * overridden method 
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**
	 * overridden toString method for the class
	 */
	@Override
	public String toString() {
		return "Listener [helper=" + helper + "]";
	}
}
