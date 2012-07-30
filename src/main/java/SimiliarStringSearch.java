import org.apache.commons.lang.StringUtils;


public class SimiliarStringSearch {

	public static boolean match(String sentence, String keyword) {
		sentence = sentence.replaceAll("-|_", " ");
		keyword = keyword.replaceAll("-|_", " ");
		String[] keywordArr = keyword.split("\\s+");
		String keyPattern = ".*\\b" + StringUtils.join(keywordArr, "\\b") + "\\b.*";

		boolean isMatched = sentence.matches(keyPattern);
		if(!isMatched) {
			keyPattern = ".*\\b" + StringUtils.join(keywordArr, "") + "\\b.*";
			isMatched = sentence.matches(keyPattern);
		}
		return isMatched;
	}
}
