package TypingTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Util.TestSampleTextGenerator;
import Util.UpdateHelper;

/**
 * The MyFrame class is extended from the JFrame 
 * super classa and this acts as the frame in which all the
 * panels, labels, text areas and combo boxes are placed.
 * 
 * @author Karthika
 *
 */
public class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private MyTextArea textArea;
	private MyTextArea editableTextArea;
	private JComboBox<String> box;
	private JLabel timerLabel;
	private JScrollPane scrollingAreaEdit;
	private JScrollPane scrollingAreaNonEdit;
	private JLabel label;
	private JLabel statisticLabel;
	private UpdateHelper helper;
	private String[] testType = {"Short","Medium","Long"};
	private TestSampleTextGenerator stringGenerator = new TestSampleTextGenerator();

	/**
	 * The constructor for the MyFrame class.
	 * Sets up the look for the Frame.
	 */
	public MyFrame(){

		super("Typing Test");

		stringGenerator.populateHashMaps();
		this.setLayout(new BorderLayout());

		textArea = new NonEditableTextArea("When Princess Aurora is born, the entire kingdom is delighted, except for the evil fairy Maleficent.");
		textArea.setTransferHandler(null);
		scrollingAreaNonEdit = new JScrollPane(textArea);
		scrollingAreaNonEdit.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		editableTextArea = new EditableTextArea("Click and start typing here... ");
		editableTextArea.setForeground(Color.gray);
		editableTextArea.setCaretColor(Color.white);
		editableTextArea.setTransferHandler(null);
		scrollingAreaEdit = new JScrollPane(editableTextArea);
		scrollingAreaNonEdit.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel testAreaPanel = new JPanel();
		testAreaPanel.setPreferredSize(new Dimension(350,400));
		testAreaPanel.setLayout(new GridLayout(2,1));
		testAreaPanel.add(scrollingAreaNonEdit);
		testAreaPanel.add(scrollingAreaEdit);
		testAreaPanel.setBorder(new LineBorder(Color.GRAY,1));
		add(testAreaPanel,BorderLayout.CENTER);


		JPanel statisticspanel = new JPanel();
		statisticspanel.setLayout(new BorderLayout());
		statisticspanel.setBackground(Color.WHITE);
		statisticspanel.setBorder(new LineBorder(Color.GRAY,1));
		statisticspanel.setPreferredSize(new Dimension(170,400));

		JPanel comboBoxPanel = new JPanel();
		comboBoxPanel.setLayout(new GridLayout(2,1));
		comboBoxPanel.setPreferredSize(new Dimension(170,50));

		label = new JLabel();
		label.setForeground(Color.RED);
		label.setText("<html>Select to change test string</html>");
		label.setPreferredSize(new Dimension(20,20));
		comboBoxPanel.add(label);

		box = new JComboBox<String>(testType);
		box.setFocusable(false);


		comboBoxPanel.add(box,BorderLayout.NORTH);
		statisticspanel.add(comboBoxPanel,BorderLayout.NORTH);

		timerLabel = new JLabel("<html>Start typing to begin test</html>",SwingConstants.CENTER);
		timerLabel.setForeground(Color.RED);
		statisticspanel.add(timerLabel,BorderLayout.CENTER);

		statisticLabel = new JLabel("<html> TEST STATISTICS <br> Word Count : 0 </html>",SwingConstants.CENTER);

		statisticspanel.add(statisticLabel,BorderLayout.SOUTH);

		add(statisticspanel,BorderLayout.EAST);
		pack();

		beginNewTest();

	}

	/**
	 * The beginNewTest method is invoked once all the look and feel of the
	 * frame is set up.
	 */
	public void beginNewTest(){
		helper = new UpdateHelper(textArea,editableTextArea,timerLabel,statisticLabel);
		box.addItemListener(new MyItemListener(textArea,stringGenerator,helper));
		editableTextArea.getDocument().addDocumentListener(new Listener(helper));
		editableTextArea.addKeyListener(new Listener(helper));
		editableTextArea.addMouseListener(new Listener(helper));
	}

	/**
	 * getter for the non editable textArea
	 * 
	 * @return MyTextArea
	 */
	public MyTextArea getTextArea() {
		return textArea;
	}

	/**
	 * setter for the non editable textArea
	 * @param textArea
	 */
	public void setTextArea(MyTextArea textArea) {
		this.textArea = textArea;
	}

	/**
	 * getter for editabletextArea
	 * 
	 * @return MyTextArea
	 */
	public MyTextArea getEditableTextArea() {
		return editableTextArea;
	}

	/**
	 * setter for editabletextArea
	 * 
	 * @param editableTextArea
	 */
	public void setEditableTextArea(MyTextArea editableTextArea) {
		this.editableTextArea = editableTextArea;
	}

	/**
	 * getter for comboBox box
	 * 
	 * @return JComboBox
	 */
	public JComboBox<String> getBox() {
		return box;
	}

	/**
	 * setter for ComboBo box
	 * 
	 * @param box
	 */
	public void setBox(JComboBox<String> box) {
		this.box = box;
	}

	/**
	 * getter for timerLabel
	 * 
	 * @return JLabel
	 */
	public JLabel getTimerLabel() {
		return timerLabel;
	}

	/**
	 * setter for timerLabel
	 * 
	 * @param timerLabel
	 */
	public void setTimerLabel(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

	/**
	 * getter for editable scrolling area
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane getScrollingAreaEdit() {
		return scrollingAreaEdit;
	}

	/**
	 * setter for editable scrolling area
	 * 
	 * @param scrollingAreaEdit
	 */
	public void setScrollingAreaEdit(JScrollPane scrollingAreaEdit) {
		this.scrollingAreaEdit = scrollingAreaEdit;
	}

	/**
	 * getter for non editable scroling area
	 * 
	 * @return JScrollPane
	 */
	public JScrollPane getScrollingAreaNonEdit() {
		return scrollingAreaNonEdit;
	}

	/**
	 * setter for non editable scrolling area
	 * 
	 * @param scrollingAreaNonEdit
	 */
	public void setScrollingAreaNonEdit(JScrollPane scrollingAreaNonEdit) {
		this.scrollingAreaNonEdit = scrollingAreaNonEdit;
	}

	/**
	 * getter for label
	 * 
	 * @return JLabel
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * setter for label
	 * 
	 * @param label
	 */
	public void setLabel(JLabel label) {
		this.label = label;
	}

	/**
	 * getter for statisticslabel
	 * 
	 * @return JLabel
	 */
	public JLabel getStatisticLabel() {
		return statisticLabel;
	}

	/**
	 * setter for statisticLabel
	 * 
	 * @param statisticLabel
	 */
	public void setStatisticLabel(JLabel statisticLabel) {
		this.statisticLabel = statisticLabel;
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
	 * setter for the Updatehelper instance
	 *  
	 * @param helper
	 */
	public void setHelper(UpdateHelper helper) {
		this.helper = helper;
	}

	/**
	 * getter for the combo box items
	 * 
	 * @return String[]
	 */
	public String[] getTestType() {
		return testType;
	}

	/**
	 * setter for the combo box items
	 * 
	 * @param testType
	 */
	public void setTestType(String[] testType) {
		this.testType = testType;
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
	 * 
	 * @param stringGenerator
	 */
	public void setStringGenerator(TestSampleTextGenerator stringGenerator) {
		this.stringGenerator = stringGenerator;
	}

	/**
	 * getter for serialversionuid
	 * @return long
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * overridden tostring method
	 */
	@Override
	public String toString() {
		return "MyFrame [textArea=" + textArea + ", editableTextArea="
				+ editableTextArea + ", box=" + box + ", timerLabel="
				+ timerLabel + ", scrollingAreaEdit=" + scrollingAreaEdit
				+ ", scrollingAreaNonEdit=" + scrollingAreaNonEdit + ", label="
				+ label + ", statisticLabel=" + statisticLabel + ", helper="
				+ helper + ", testType=" + Arrays.toString(testType)
				+ ", stringGenerator=" + stringGenerator + ", getTextArea()="
				+ getTextArea() + ", getEditableTextArea()="
				+ getEditableTextArea() + ", getBox()=" + getBox()
				+ ", getTimerLabel()=" + getTimerLabel()
				+ ", getScrollingAreaEdit()=" + getScrollingAreaEdit()
				+ ", getScrollingAreaNonEdit()=" + getScrollingAreaNonEdit()
				+ ", getLabel()=" + getLabel() + ", getStatisticLabel()="
				+ getStatisticLabel() + ", getHelper()=" + getHelper()
				+ ", getTestType()=" + Arrays.toString(getTestType())
				+ ", getStringGenerator()=" + getStringGenerator() + "]";
	}
}
