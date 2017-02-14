// import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// Must comments tests for judge to compile
// import org.junit.Test;

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
		final BufferedReader in = new BufferedReader(new InputStreamReader(
				System.in));
		String line;

		final Main mySolution = new Main();
		while ((line = in.readLine()) != null) {
			final StringTokenizer st = new StringTokenizer(line);
			final long first = Long.parseLong(st.nextToken());
			final long second = Long.parseLong(st.nextToken());
			final long third = mySolution.findLengthOfLongestCycleInRange(
					first, second);
			System.out.printf("%d %d %d\n", first, second, third);
		}
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
	/*
	 * @Test public void testMultiplyByThreeOrDivideByTwo() { assertEquals(-2,
	 * multiplyByThreeOrDivideByTwo(-1)); assertEquals(0,
	 * multiplyByThreeOrDivideByTwo(0)); assertEquals(4,
	 * multiplyByThreeOrDivideByTwo(1)); assertEquals(1,
	 * multiplyByThreeOrDivideByTwo(2)); assertEquals(10,
	 * multiplyByThreeOrDivideByTwo(3)); assertEquals(2,
	 * multiplyByThreeOrDivideByTwo(4)); assertEquals(16,
	 * multiplyByThreeOrDivideByTwo(5)); assertEquals(3,
	 * multiplyByThreeOrDivideByTwo(6)); assertEquals(22,
	 * multiplyByThreeOrDivideByTwo(7)); assertEquals(4611686018427387903l,
	 * multiplyByThreeOrDivideByTwo(Long.MAX_VALUE - 1));
	 * assertEquals(-4611686018427387904l,
	 * multiplyByThreeOrDivideByTwo(Long.MIN_VALUE)); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testCountStepsToReduceToOneNegativeArugment() { assertEquals(16,
	 * countStepsToReduceToOne(-1)); }
	 * 
	 * @Test(expected = IllegalArgumentException.class) public void
	 * testCountStepsToReduceToOneZeroArugment() { assertEquals(16,
	 * countStepsToReduceToOne(0)); }
	 * 
	 * @Test public void testCountStepsToReduceToOne() { assertEquals(1,
	 * countStepsToReduceToOne(1)); assertEquals(2, countStepsToReduceToOne(2));
	 * assertEquals(8, countStepsToReduceToOne(3)); assertEquals(3,
	 * countStepsToReduceToOne(4)); assertEquals(6, countStepsToReduceToOne(5));
	 * assertEquals(4, countStepsToReduceToOne(8)); assertEquals(5,
	 * countStepsToReduceToOne(16)); assertEquals(6,
	 * countStepsToReduceToOne(32)); assertEquals(7,
	 * countStepsToReduceToOne(64)); assertEquals(8,
	 * countStepsToReduceToOne(128)); assertEquals(9,
	 * countStepsToReduceToOne(256)); assertEquals(10,
	 * countStepsToReduceToOne(512)); assertEquals(11,
	 * countStepsToReduceToOne(1024)); assertEquals(12,
	 * countStepsToReduceToOne(2048)); assertEquals(32,
	 * countStepsToReduceToOne(2147483648l)); // (2^31) assertEquals(513,
	 * countStepsToReduceToOne(Long.MAX_VALUE)); // (2^63) }
	 * 
	 * @Test public void testfindLengthOfLongestCycle() { assertEquals(20,
	 * findLengthOfLongestCycleInRange(1, 10)); assertEquals(20,
	 * findLengthOfLongestCycleInRange(10, 1)); assertEquals(125,
	 * findLengthOfLongestCycleInRange(100, 200)); assertEquals(125,
	 * findLengthOfLongestCycleInRange(200, 100)); assertEquals(89,
	 * findLengthOfLongestCycleInRange(201, 210)); assertEquals(89,
	 * findLengthOfLongestCycleInRange(210, 201)); assertEquals(174,
	 * findLengthOfLongestCycleInRange(900, 1000)); assertEquals(174,
	 * findLengthOfLongestCycleInRange(1000, 900)); }
	 */
}
