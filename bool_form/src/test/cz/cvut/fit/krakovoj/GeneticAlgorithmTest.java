package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticAlgorithmTest {

	@Test
	public void test() throws Exception {
		GeneticAlgorithm g = new GeneticAlgorithm("/home/craa/3rd/eclipse/workspaces/MI-PAA/bool_form/data/unsat_1_/40_125_25.dat");
		int sum = 0;
		//assertEquals(g.getPopulationSize(),20);
		//for(int j = 1; j < 40; j++){
			for(int i = 0; i < 10; i++){
				sum += g.run(0);
			}
			System.out.println( 0 + " " + sum/10);
			sum = 0;
		//}
		
		
	}

}
