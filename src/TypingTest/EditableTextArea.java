package TypingTest;

/**
 * The class EditableTextArea is the TextArea for the
 * editable text, that is where the user types in for 
 * the typing test
 * 
 * @author Karthika
 *
 */
public class EditableTextArea extends MyTextArea {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that takes in the text to put in the 
	 * text area and since the textarea should be editable
	 * setEditable is set to true
	 * 
	 * @param textIn
	 */
	public EditableTextArea(String textIn) {
		super(textIn);
	}
	
	/**
	 * The getTextLength method returns the length of the text
	 * in the text area
	 */
	@Override
	public int getTextLength() {
		return this.getText().length();
	}
	
	/**
	 * append method is used for appending the user enetered text to the 
	 * already existing text, each time user types in a new character.
	 * Also the method sets the caret position accordingly so that the user
	 * need not scroll down the text area as he enters new tet.
	 */
	
	@Override
	public void append(String text) {
		super.append(text);
		this.setCaretPosition(this.getCaretPosition()+text.length());
	}

	/**
	 * The overridden toString method
	 */
	@Override
	public String toString() {
		return "EditableTextArea [getTextLength()=" + getTextLength() + "]";
	}
}
