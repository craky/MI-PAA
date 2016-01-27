package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class BruteForceTest {

	@Test
	public void test() {
		BruteForce b = new BruteForce("/home/craa/3rd/eclipse/workspaces/MI-PAA/bool_form/src/test/cz/cvut/fit/krakovoj/testData/example.dat");
		Individual i;
		assertSame(b.bestSolution(),0);
		assertSame(b.chromoSize(),4);
		i = new Individual(b.chromoSize());
		b.solveBruteForce(i,b.chromoSize());
		b.bestSolution();
	}

}
