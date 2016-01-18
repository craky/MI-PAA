package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticAlgorithmTest {

	@Test
	public void test() throws Exception {
		GeneticAlgorithm g = new GeneticAlgorithm("src/test/cz/cvut/fit/krakovoj/testData/example.dat");
		//assertEquals(g.getPopulationSize(),20);
		g.run();
	}

}
