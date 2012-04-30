package Util;

import java.util.HashMap;
import java.util.Map;
/**
 * The TestSampleTextGenerator class is used for populating the
 * non editable text box according to the user selection
 * 
 * @author Karthika
 *
 */
public class TestSampleTextGenerator {

	private Map<Integer, String> shortMap = new HashMap<Integer,String>();
	private Map<Integer, String> mediumMap = new HashMap<Integer,String>();
	private Map<Integer, String> longMap = new HashMap<Integer,String>();

	/**
	 * populateHashMaps method populates the three hash maps
	 */
	public void populateHashMaps(){
		populateShortMap();
		populateMediumMap();
		populateLongMap();
	}

	/**
	 * populateShortMap method populates the shortMap
	 */
	public void populateShortMap(){
		shortMap.put(0, "Once upon a time there lived an unhappy young girl. Her mother was dead and her father had married a widow with two daughters.");
		shortMap.put(1, "Once upon a time in mid winter, when the snowflakes were falling like feathers from heaven, a beautiful queen sat sewing at her window");
		shortMap.put(2, "Once upon a time there lived in a certain village a little country girl, the prettiest creature who was ever seen.");
		shortMap.put(3, "Once upon a time there lived a woman who had no children. She dreamed of having a little girl, but time went by, and her dream never came true.");
	}

	/**
	 * populateShortMap method populates the mediumMap
	 */
	public void populateMediumMap(){
		mediumMap.put(0,"Once upon a time there lived an unhappy young girl. Her mother was dead and her father had married a widow with two daughters. Her stepmother didn't like her one little bit. All her kind thoughts and loving touches were for her own daughters. Nothing was too good for them - dresses, shoes, delicious food, soft beds, and every home comfort. But, for the poor unhappy girl, there was nothing at all.");
		mediumMap.put(1,"Once upon a time in mid winter, when the snowflakes were falling like feathers from heaven, a beautiful queen sat sewing at her window, which had a frame of black ebony wood. As she sewed, she looked up at the snow and pricked her finger with her needle. Three drops of blood fell into the snow. The red on the white looked so beautiful, that she thought, \"If only I had a child as white as snow, as red as blood, and as black as this frame.\"");
		mediumMap.put(2,"Once upon a time there lived in a certain village a little country girl, the prettiest creature who was ever seen. Her mother was excessively fond of her; and her grandmother doted on her still more. This good woman had a little red riding hood made for her.");
		mediumMap.put(3,"Once upon a time there lived a woman who had no children. She dreamed of having a little girl, but time went by, and her dream never came true.She then went to visit a witch, who gave her a magic grain of barley. She planted it in a flower pot. And the very next day, the grain had turned into a lovely flower, rather like a tulip.");
	}

	/**
	 * populateShortMap method populates the longMap
	 */
	public void populateLongMap(){
		longMap.put(0,"Once upon a time there lived an unhappy young girl. Her mother was dead and her father had married a widow with two daughters. Her stepmother didn't like her one little bit. All her kind thoughts and loving touches were for her own daughters. Nothing was too good for them - dresses, shoes, delicious food, soft beds, and every home comfort. But, for the poor unhappy girl, there was nothing at all. No dresses, only her stepsisters’ hand-me-downs. No lovely dishes, nothing but scraps. No rest and no comfort. She had to work hard all day. Only when evening came was she allowed to sit for a while by the fire, near the cinders. That’s why everybody called her Cinderella.");
		longMap.put(1,"Once upon a time in mid winter, when the snowflakes were falling like feathers from heaven, a beautiful queen sat sewing at her window, which had a frame of black ebony wood. As she sewed, she looked up at the snow and pricked her finger with her needle. Three drops of blood fell into the snow. The red on the white looked so beautiful, that she thought, \"If only I had a child as white as snow, as red as blood, and as black as this frame.\" Soon afterward she had a little daughter that was as white as snow, as red as blood, and as black as ebony wood, and therefore they called her Little Snow-White.");
		longMap.put(2,"Once upon a time there lived in a certain village a little country girl, the prettiest creature who was ever seen. Her mother was excessively fond of her; and her grandmother doted on her still more. This good woman had a little red riding hood made for her. It suited the girl so extremely well that everybody called her Little Red Riding Hood.");
		longMap.put(3,"Once upon a time there lived a woman who had no children. She dreamed of having a little girl, but time went by, and her dream never came true.She then went to visit a witch, who gave her a magic grain of barley. She planted it in a flower pot. And the very next day, the grain had turned into a lovely flower, rather like a tulip. The woman softly kissed its half-shut petals. And as though by magic, the flower opened in full blossom. Inside sat a tiny girl, no bigger than a thumb.");
	}

	/**
	 * generateString method gets data from the correct map based
	 * on the randomly generated key and the user selection  for 
	 * short, medium or long input test string
	 * 
	 * @param testType
	 * @param key
	 * @return String
	 */
	public String generateString(String testType,int key){
		if(testType.equals("Short")){
			return shortMap.get(key);
		}if(testType.equals("Medium")){
			return mediumMap.get(key);
		}if(testType.equals("Long")){
			return longMap.get(key);
		}
		return null;
	}

	/**
	 * getter for the shortMap instance
	 * 
	 * @return Map
	 */
	public Map<Integer, String> getShortMap() {
		return shortMap;
	}

	/**
	 * setter for shortMap instance
	 * 
	 * @param shortMap
	 */
	public void setShortMap(Map<Integer, String> shortMap) {
		this.shortMap = shortMap;
	}

	/**
	 * getter for the mediumMap instance
	 * 
	 * @return Map
	 */
	public Map<Integer, String> getMediumMap() {
		return mediumMap;
	}

	/** setter for mediummap
	 * 
	 * @param mediumMap
	 */
	public void setMediumMap(Map<Integer, String> mediumMap) {
		this.mediumMap = mediumMap;
	}

	/**
	 * getter for the mediumMap
	 * 
	 * @return Map
	 */
	public Map<Integer, String> getLongMap() {
		return longMap;
	}

	/**
	 * setter for the longMap instance
	 * 
	 * @param longMap
	 */
	public void setLongMap(Map<Integer, String> longMap) {
		this.longMap = longMap;
	}

	/**
	 * overridden toString method
	 */
	@Override
	public String toString() {
		return "TestSampleTextGenerator [shortMap=" + shortMap + ", mediumMap="
				+ mediumMap + ", longMap=" + longMap + "]";
	}
}
