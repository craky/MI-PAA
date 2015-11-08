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
	
	@Test
	public void testdynamicProgramming() {
		Knapsack knapsack = new Knapsack();
		assertSame(knapsack.getSize(),0);
		knapsack.addItem(1, 5);
		assertSame(knapsack.getSize(),1);
		knapsack.addItem(3, 1);
		assertSame(knapsack.getSize(),2);
		knapsack.addItem(7, 3);
		assertSame(knapsack.getSize(),3);
		knapsack.setCapacity(6);
		assertSame(knapsack.getCapacity(),6);
		
		knapsackProblem.dynamicProgramming(knapsack);
		assertSame(knapsack.getSolutionCost(), 10);
	}

}
