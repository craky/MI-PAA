package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeneticAlgorithmTest {

	@Test
	public void test() {
		GeneticAlgorithm g = new GeneticAlgorithm("src/test/cz/cvut/fit/krakovoj/testData/example.dat");
		assertEquals(g.getPopulationSize(),20);
	}

}
