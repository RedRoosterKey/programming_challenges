package problem_110101_3N_plus_1;
// You must remove this the package definition for this to run

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 * @author redroosterkey
 * 
 *         Solution for Programming Challenges: 110101 The 3n+1 problem
 * 
 */
public class Main {

	/**
	 * Handles input, tokenization and output
	 * 
	 * @param args
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	public static void main(String args[]) throws NumberFormatException,
			IOException {
		final Scanner input = new Scanner(System.in);
		String line;

		final Main mySolution = new Main();
		while ((line = input.nextLine()) != null) {
			final StringTokenizer st = new StringTokenizer(line);
			final long first = Long.parseLong(st.nextToken());
			final long second = Long.parseLong(st.nextToken());
			final long third = mySolution.findLengthOfLongestCycleInRange(
					first, second);
			System.out.printf("%d %d %d\n", first, second, third);
		}
		input.close();
	}

	/**
	 * Keeps a record of previously solved problems so we do not have to
	 * re-solve them The first long is the input integer and the second is the
	 * number of steps required to reduce that integer to 1
	 */
	private Map<Long, Long> reductionStepsRequiredCache = new HashMap<Long, Long>();

	/**
	 * Will perform one of two operations.
	 * 
	 * If the integer is even: returns number/2 If the integer is odd returns
	 * (3*number)+1
	 * 
	 * @param number
	 * @return (3*n)+1 or n/2
	 */
	public long multiplyByThreeOrDivideByTwo(final long number) {
		return (0 == number % 2 ? (number / 2) : (3 * number) + 1);
	}

	/**
	 * Continually applies the 3n+1|n/2 process until it gets to one, counting
	 * the number of steps along the way.
	 * 
	 * @param input
	 * @return the number of reduction steps required to get the number to 1
	 * @throws IllegalArgumentException
	 */
	public long countStepsToReduceToOne(final long input)
			throws IllegalArgumentException {
		if (input <= 0)
			throw new IllegalArgumentException(
					"Input must be greater than zero.");
		if (reductionStepsRequiredCache.containsKey(input))
			return reductionStepsRequiredCache.get(input);
		long number = input;

		long count = 1;
		while (1l != number) {
			number = multiplyByThreeOrDivideByTwo(number);
			if (reductionStepsRequiredCache.containsKey(number)) {
				return count + reductionStepsRequiredCache.get(number);
			}
			count++;
		}

		reductionStepsRequiredCache.put(input, count);
		return count;
	}

	/**
	 * 
	 * Counts the reduction steps needed for every integer within the range of
	 * the two numbers provided and returns the length of the longest chain.
	 * 
	 * @param lowerBound
	 * @param upperBound
	 * @return The length of the longest cycle
	 */
	public long findLengthOfLongestCycleInRange(final long lowerBound,
			final long upperBound) {
		final long low = Math.min(lowerBound, upperBound);
		final long high = Math.max(lowerBound, upperBound);

		long longestCycleLength = 0;
		for (long current = low; current <= high; current++) {
			final long currentLength = countStepsToReduceToOne(current);
			if (currentLength > longestCycleLength)
				longestCycleLength = currentLength;
		}

		return longestCycleLength;
	}
}
