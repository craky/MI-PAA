package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class knapsackProblemTest {

	@Test
	public void testFilledArr() {
		int[][] array = knapsackProblem.filledArr(5,5);
		assertEquals(array.length,5);
		assertEquals(array[1].length,5);
		
		int[][] array2 = knapsackProblem.filledArr(113,52);
		assertSame(array2.length,113);
		assertSame(array2[0].length,52);
		
		for(int i = 0; i < 113; i++){
			for(int j = 0; j < 52; j++){
				assertSame(array2[i][j],0);
			}
		}
		
		assertSame(knapsackProblem.filledArr(113,-52), null);
		assertSame(knapsackProblem.filledArr(-1,0), null);
		assertSame(knapsackProblem.filledArr(-9,-5), null);
	}

}
