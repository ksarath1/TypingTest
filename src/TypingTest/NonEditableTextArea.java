package TypingTest;

/**
 * NonEditableTextArea is the class that extends MyTextArea
 * and is the class that takes care of the textArea with the 
 * test String
 * 
 * @author Karthika
 *
 */
public class NonEditableTextArea extends MyTextArea {

	private static final long serialVersionUID = 1L;

	/**
	 * NonEditableTextArea is the constructor for the 
	 * text area and since it shouldnt be edited by the
	 * user, is set as non editable also takes the text 
	 * that will be displayed in the text area
	 * 
	 * @param textIn
	 */
	public NonEditableTextArea(String textIn) {
		super(textIn);
		setEditable(false);
	}

	/**
	 * overridden method that returns the length of the
	 * text in the text area
	 * 
	 * @return int
	 */
	@Override
	public int getTextLength() {
		return this.getText().length();
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "NonEditableTextArea [getTextLength()=" + getTextLength() + "]";
	}

	/**
	 * getter for the serialversionuid
	 * 
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}




