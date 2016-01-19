package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticAlgorithmTest {

	@Test
	public void test() throws Exception {		
		int sum = 0;
		double mutation = 0.001;
		//assertEquals(g.getPopulationSize(),20);
		for(int j = 1; j < 50; j++){
			for(int i = 0; i < 100; i++){
				GeneticAlgorithm g = new GeneticAlgorithm("bool_form/data/unsat_1_/40_125_25.dat");
				sum += g.run(mutation);				
			}
			System.out.println( mutation + " " + sum/100);
			mutation += 0.001;
			sum = 0;
		}
		
		
	}

}
