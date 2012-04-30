package Util;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Highlighter;
import TypingTest.MyTextArea;

/**
 * 
 * @author Karthika
 * 
 * The UpdateHelper class is where all the string and length manipulations
 * are performed for updating the timer panel, the statistics and the error 
 * matching. 
 */

public class UpdateHelper {

	private int currentWordCount = 0;
	private String[] words;
	private String[] testWords;
	private String regex = "[\\s]+";
	private MyTextArea nonEditable;
	private MyTextArea editable;
	private JLabel  timerLabel;
	private JLabel statisticsLabel;
	private CounterThread thread = new CounterThread();
	private Integer wordCount = new Integer(0);
	private Integer wordsPerMinute = new Integer(0);
	private boolean isWhiteSpace = false;
	private boolean firstTime = true;
	private Map<String,ArrayList<Integer>> errorList = new HashMap<String,ArrayList<Integer>>();

	private ArrayList<Integer> positionList ;
	/**
	 * constructor for the UpdateHelper class
	 * @param nonEditableIn
	 * @param editableIn
	 * @param timerPanelIn
	 * @param timerLabelIn
	 * @param statisticsPanelIn
	 * @param statisticsLabelIn
	 */
	public UpdateHelper(MyTextArea nonEditableIn,MyTextArea editableIn,JLabel timerLabelIn,JLabel statisticsLabelIn){
		nonEditable = nonEditableIn;
		editable = editableIn;
		timerLabel = timerLabelIn;
		statisticsLabel = statisticsLabelIn;
		thread = new CounterThread(timerLabel);
	}
	/**
	 * The startTimer method initiates the panel thread that updates the 
	 * timer panel and the timer label 
	 */
	public void startTimer(){
		thread.startThread();
	}

	/**
	 * The stopTimer method is used for stopping the panel thread once
	 * the text in the editable text area reaches the same length as the
	 * text in the non editable text area
	 */
	public void stopTimer(){
		if(thread.getCounter() != null){
			editable.setEditable(false);
			thread.getCounter().cancel();
			thread.getCounter().purge();
		}
	}

	/**
	 * This method is used for detecting the typing errors in the user entered 
	 * text
	 */
	public void findErrors(){
		int editablePos = 0;
		int nonEditablePos = 0;
		int errorCount = 0;
		String nonEditableInput = nonEditable.getText()+ " ";
		String editableInput = editable.getText() + " ";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcherNonEditable = pattern.matcher(nonEditableInput);
		Matcher matcherEditable = pattern.matcher(editableInput);

		Highlighter hilite = editable.getHighlighter();
		Highlighter.Highlight[] hilites = hilite.getHighlights();

		for (int i = 0; i < hilites.length; i++) {
			if (hilites[i].getPainter() instanceof ErrorHighlighter) {
				hilite.removeHighlight(hilites[i]);
			}
		}

		while(matcherNonEditable.find() && matcherEditable.find()){
			if(!(editableInput.substring(editablePos,matcherEditable.start())).equals(nonEditableInput.substring(nonEditablePos,matcherNonEditable.start()))){
				errorCount++;
				highlight(hilite,editablePos,matcherEditable.start());
			}
			editablePos = matcherEditable.end();
			nonEditablePos = matcherNonEditable.end();
		}

		while(matcherEditable.find()){
			errorCount++;
			highlight(hilite,editablePos,matcherEditable.start());
			editablePos = matcherEditable.end();
		}
		statisticsLabel.setText("<html>TEST STATISTICS <br> "  + "Word Count : " + wordCount + "<br> Words-Per-Min : " + wordsPerMinute + "<br>Error Count : " + errorCount + "<br>Error Percentage : " +((errorCount*100)/wordCount) +"</html>");
	}

	/**
	 * The highlight method is used for finding the erraneous words inside the
	 * editable text area and highlight it
	 * 
	 * @param editableText
	 */
	public void highlight(Highlighter hilite,int startIndex, int endIndex) {
		try {
			hilite.addHighlight(startIndex, endIndex, new ErrorHighlighter(Color.red));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The updateCurrentWordCount method is used for updating the word count
	 * in real time
	 */
	public void updateCurrentWordCount(){
		if(editable.isEditable() == true){
			currentWordCount++;
			statisticsLabel.setText("<html> TEST STATISTICS <br>Word Count : " + currentWordCount + "</html>");
		}
	}

	/**
	 * The updateCurrentWordCountOnBackSpace method is used for keeping the panel
	 * updated about the current word count even when the user hits the back space character
	 */
	public void updateCurrentWordCountOnBackSpace(){
		if(editable.isEditable() == true){
			String[] temp = editable.getText().split(regex);
			currentWordCount = temp.length-1;
			statisticsLabel.setText("<html> TEST STATISTICS <br>Word Count : " + currentWordCount + "</html>");
		}
	}

	/**
	 * The updateStatistics method is used for updating the statistics panel to show the word count
	 * and the word per minute count
	 */
	public void updateStatistics(){
		testWords = nonEditable.getText().split(regex);
		words = editable.getText().split(regex);
		wordCount = words.length;
		wordsPerMinute = ((wordCount * 60 )/Integer.parseInt(timerLabel.getText()));
		statisticsLabel.setText("<html>TEST STATISTICS <br> "  + "Word Count : " + wordCount + "<br> Words-Per-Min : " + wordsPerMinute +"</html>");
	}

	/**
	 * The getNextWordPosition method is used for getting the position of the next word in the text
	 * 
	 * @return int
	 */
	public int getNextWordPosition(){
		int temp = nonEditable.getCaretPosition();
		while(temp < nonEditable.getTextLength() && nonEditable.getText().charAt(temp)!= ' '){
			temp ++;
		}

		if(temp >= nonEditable.getTextLength()){
			return nonEditable.getCaretPosition();
		}
		else
			return temp+2;
	}

	/**
	 * The setCaretPositionOnAddition method adjusts the position of the 
	 * Caret on addition of new words
	 */
	public void setCaretPositionOnAddition(){
		int nextWordPosition = getNextWordPosition();
		if(nextWordPosition < nonEditable.getTextLength())
			nonEditable.setCaretPosition(getNextWordPosition());
	}

	/**
	 * textAreaClicked is invoked when the user first clicks
	 * on the editable text Area
	 */
	public void textAreaClicked(){
		editable.setText("");
		editable.setCaretColor(Color.RED);
		//editable.setCaretPosition(0);
		editable.setFocusable(true);
		editable.setForeground(Color.BLACK);
		editable.setEditable(true);
	}

	public String getEditableText(){
		return editable.getText();
	}
	/**
	 * The updateCaretPositionOnDelete method adjusts the position of the
	 * Caret on hitting back slash
	 */
	public void updateCaretPositionOnDelete(){
		if(nonEditable.getCaretPosition()-1 >= 0)
			nonEditable.setCaretPosition(nonEditable.getCaretPosition()-1);
	}

	public void updateCaretPositionOnAddition(){
		if(editable.getCaretPosition() + 1 < nonEditable.getTextLength()){
			//	editable.setCaretPosition(editable.getCaretPosition() + 1);
		}
	}
	/**
	 * The getCurrentWordCount method is the getter for the currentWordCount
	 * 
	 * @return int
	 */
	public int getCurrentWordCount() {
		return currentWordCount;
	}

	/**
	 * The setCurrentWordCount method is the setter for the currentWordCount
	 * @param currentWordCount
	 */

	public void setCurrentWordCount(int currentWordCount) {
		this.currentWordCount = currentWordCount;
	}

	/**
	 * The getNonEditableLength method returns the length of the text in
	 * the non editable text area
	 * 
	 * @return int
	 */
	public int getNonEditableLength(){
		return nonEditable.getTextLength();
	}

	/**
	 * The isWhiteSpace method is used for checking whether there are consecutive 
	 * white spaces and in case of consecutive white spaces all spaces except one 
	 * is discarded using this method
	 * 
	 * @return boolean
	 */
	public boolean isWhiteSpace() {
		return isWhiteSpace;
	}

	/**
	 * The setWhiteSpace method is used for setting the isWhiteSpace boolean which 
	 * is used for discarding consecutive white spaces
	 * 
	 * @param isWhiteSpace
	 */
	public void setWhiteSpace(boolean isWhiteSpace) {
		this.isWhiteSpace = isWhiteSpace;
	}

	/**
	 * The isFirstTime method is used to check whether the boolean variable used for checking 
	 * if the length of the editable text is becoming one for the first time
	 * 
	 * @return boolean
	 */
	public boolean isFirstTime() {
		return firstTime;
	}

	/**
	 * The getter method for the boolean variable to check whether the editable text is becoming one
	 * for the first time 
	 * 
	 * @param firstTime
	 */
	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

	/**
	 * The reset method is invoked once the user resets the test by choosing a
	 * different test input string
	 */
	public void resetTest(){
		editable.setEditable(true);
		editable.setText("");
		editable.setCaretColor(Color.WHITE);
		nonEditable.setCaretPosition(0);
		this.setWhiteSpace(false);
		this.firstTime = true;
		this.currentWordCount = 0;
		statisticsLabel.setText("<html> TEST STATISTICS <br> Word Count : 0 </html>");
		timerLabel.setText("<html>Start typing to begin test</html>");
		timerLabel.setFont(new Font("Times new Roman", Font.BOLD, 14));
		timerLabel.setForeground(Color.RED);
		this.words = null;
		testWords = null;
		wordsPerMinute = new Integer(0);
		errorList =  new HashMap<String,ArrayList<Integer>>();
		positionList = null ;
		//errorCount = 0;
		editable.setText("Click and start typing here... ");
		editable.setForeground(Color.gray);
		editable.setEditable(false);
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "UpdateHelper [currentWordCount=" + currentWordCount
				+ ", words=" + Arrays.toString(words) + ", testWords="
				+ Arrays.toString(testWords) + ", regex=" + regex
				+ ", nonEditable=" + nonEditable + ", editable=" + editable
				+ ", timerLabel=" + timerLabel + ", statisticsLabel="
				+ statisticsLabel + ", thread=" + thread + ", wordCount="
				+ wordCount + ", wordsPerMinute=" + wordsPerMinute
				+ ", isWhiteSpace=" + isWhiteSpace + ", firstTime=" + firstTime
				+ ", errorList=" + errorList + ", positionList=" + positionList
			    + "]";
	}

}
