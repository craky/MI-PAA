package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class KnapsackGATest {

	@Test
	public void testDefaultConstants() {
		assertEquals(new KnapsackGA().POPULATION_SIZE,500);
		assertEquals(new KnapsackGA().CROSSOVER_PROBABILITY,0.7,0.00001);
		assertEquals(new KnapsackGA().MAX_GENERATION,100);
		assertEquals(new KnapsackGA().MAX_GENERATIONS_WITHOUT_IMPROVEMENT,25);
	}
	
	/**
	 * Only tests, that it can read from a file.
	 */
	@Test
	public void testGeneral(){
		KnapsackGA problem = new KnapsackGA();
		try {
			problem.solve("./data/knap_4.inst.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
