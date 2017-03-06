package problem_110101_3N_plus_1;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	private Main main;

	@Before
	public void setup() {
		main = new Main();
	}

	@Test
	public void testMultiplyByThreeOrDivideByTwo() {
		assertEquals(-2, main.multiplyByThreeOrDivideByTwo(-1));
		assertEquals(0, main.multiplyByThreeOrDivideByTwo(0));
		assertEquals(4, main.multiplyByThreeOrDivideByTwo(1));
		assertEquals(1, main.multiplyByThreeOrDivideByTwo(2));
		assertEquals(10, main.multiplyByThreeOrDivideByTwo(3));
		assertEquals(2, main.multiplyByThreeOrDivideByTwo(4));
		assertEquals(16, main.multiplyByThreeOrDivideByTwo(5));
		assertEquals(3, main.multiplyByThreeOrDivideByTwo(6));
		assertEquals(22, main.multiplyByThreeOrDivideByTwo(7));
		assertEquals(4611686018427387903l,
				main.multiplyByThreeOrDivideByTwo(Long.MAX_VALUE - 1));
		assertEquals(-4611686018427387904l,
				main.multiplyByThreeOrDivideByTwo(Long.MIN_VALUE));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStepsToReduceToOneNegativeArugment() {
		assertEquals(16, main.countStepsToReduceToOne(-1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCountStepsToReduceToOneZeroArugment() {
		assertEquals(16, main.countStepsToReduceToOne(0));
	}

	@Test
	public void testCountStepsToReduceToOne() {
		assertEquals(1, main.countStepsToReduceToOne(1));
		assertEquals(2, main.countStepsToReduceToOne(2));
		assertEquals(8, main.countStepsToReduceToOne(3));
		assertEquals(3, main.countStepsToReduceToOne(4));
		assertEquals(6, main.countStepsToReduceToOne(5));
		assertEquals(4, main.countStepsToReduceToOne(8));
		assertEquals(5, main.countStepsToReduceToOne(16));
		assertEquals(6, main.countStepsToReduceToOne(32));
		assertEquals(7, main.countStepsToReduceToOne(64));
		assertEquals(8, main.countStepsToReduceToOne(128));
		assertEquals(9, main.countStepsToReduceToOne(256));
		assertEquals(10, main.countStepsToReduceToOne(512));
		assertEquals(11, main.countStepsToReduceToOne(1024));
		assertEquals(12, main.countStepsToReduceToOne(2048));
		assertEquals(32, main.countStepsToReduceToOne(2147483648l)); // (2^31)
		assertEquals(513, main.countStepsToReduceToOne(Long.MAX_VALUE)); // (2^63)
	}

	@Test
	public void testfindLengthOfLongestCycle() {
		assertEquals(20, main.findLengthOfLongestCycleInRange(1, 10));
		assertEquals(20, main.findLengthOfLongestCycleInRange(10, 1));
		assertEquals(125, main.findLengthOfLongestCycleInRange(100, 200));
		assertEquals(125, main.findLengthOfLongestCycleInRange(200, 100));
		assertEquals(89, main.findLengthOfLongestCycleInRange(201, 210));
		assertEquals(89, main.findLengthOfLongestCycleInRange(210, 201));
		assertEquals(174, main.findLengthOfLongestCycleInRange(900, 1000));
		assertEquals(174, main.findLengthOfLongestCycleInRange(1000, 900));
	}
}
