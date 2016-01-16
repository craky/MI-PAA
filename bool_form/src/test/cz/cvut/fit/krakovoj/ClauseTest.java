package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ClauseTest {

	@Test
	public void test() {
		Clause c = new Clause();
		c.addLiteral(1);
		c.addLiteral(-2);
		c.addLiteral(-3);
		c.addLiteral(5);
		
		assertEquals(c.getSize(),4);
		assertEquals(c.getLiteral(0),1);
		assertEquals(c.getLiteral(1),-2);
		assertEquals(c.getLiteral(2),-3);
		assertEquals(c.getLiteral(3),5);
		
		c.addLiteral(0);
		assertEquals(c.getSize(),4);
		
		List<Integer> evaulation = new ArrayList<Integer>();
		evaulation.add(1);
		evaulation.add(0);
		evaulation.add(0);
		evaulation.add(0);
		evaulation.add(0);
		// 1 + 0 + 0 + 0 + 0
		assertTrue(c.eval(evaulation));
		evaulation.set(0, 0);
		evaulation.set(1,1);
		// 0 + 1 + 0 + 0 + 0
		assertTrue(c.eval(evaulation));
		evaulation.set(1, 0);
		evaulation.set(4,1);
		// 0 + 0 + 0 + 1 + 0
		assertTrue(c.eval(evaulation));
		evaulation.set(0, 0);
		evaulation.set(1,1);
		evaulation.set(2, 1);
		// 0 + 1 + 1 + 1 + 0
		assertFalse(c.eval(evaulation));
	}

}
