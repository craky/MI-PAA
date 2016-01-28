package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class BruteForceTest {

	@Test
	public void test() {
		BruteForce b = new BruteForce("/home/craa/aktualni_semestr/mi-paa/5_ukol/ration/20_30_5.dat");
		Individual i;
		assertSame(b.bestSolution(),0);
		//assertSame(b.chromoSize(),10);
		i = new Individual(b.chromoSize());
		b.solveBruteForce(i,b.chromoSize());
		b.bestSolution();
	}

}
