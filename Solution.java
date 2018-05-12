import java.util.*;

public class Solution {
	public static String getResults(String word) {


		String result = null;
		StringBuilder str = new StringBuilder(word);

		if (word.matches(word.charAt(0) + "+") || ((str.length() == 2 && str.charAt(0) > str.charAt(1)))) {
			result = "no answer";
			return result;
		} else {

			int indexToTheLeft = 0;
			int indexToTheRight = str.length() - 1;

			outerloop: for (int i = str.length() - 1; i >= 0; i--) {
				for (int j = i - 1; j >= 0; j--) {
					if (str.charAt(i) > str.charAt(j)) {
						indexToTheLeft = j;
						indexToTheRight = i;
						for (int subSet_i = indexToTheRight - 1; subSet_i >= indexToTheLeft + 1; subSet_i--) {
							for (int subSet_j = subSet_i - 1; subSet_j >= indexToTheLeft + 1; subSet_j--) {
								if (str.charAt(subSet_i) > str.charAt(subSet_j)) {

									if (str.lastIndexOf(Character.toString(str.charAt(subSet_j))) > indexToTheLeft) {

										indexToTheLeft = subSet_j;
										indexToTheRight = subSet_i;

									}
								}
							}
						}

						break outerloop;

					}
				}
			}

			char one = str.charAt(indexToTheLeft);
			char two = str.charAt(indexToTheRight);
			str.setCharAt(indexToTheRight, one);
			str.setCharAt(indexToTheLeft, two);
			char[] elAfterExchange = str.substring(indexToTheLeft + 1, str.length()).toCharArray();
			Arrays.sort(elAfterExchange);
			String elExchangeToString = Arrays.toString(elAfterExchange).replaceAll("\\[", "").replaceAll("\\]", "")
					.replaceAll("[,]", "").replaceAll(" ", "");
			result = str.substring(0, indexToTheLeft + 1) + elExchangeToString;

			if (str.length() > 2 && indexToTheLeft == 0 && indexToTheRight == str.length() - 1
					&& result.equals(new StringBuilder(word).reverse().toString())) {
				result = "no answer";
				// System.out.println(indexToTheLeft+"result " + result);
				// System.out.println(indexToTheRight+"reverse " + new
				// StringBuilder(word).reverse().toString());
				return result;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int testCases = reader.nextInt();

		String[] total = new String[testCases];
		for (int i = 0; i < testCases; i++) {
			String input = reader.next();
			String result = getResults(input);
			total[i] = result;
		}
		for (int i = 0; i < total.length; i++) {
			System.out.println(total[i]);
		}

	}
}
