package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class MemberGATest {

	@Test
	public void testChromoSize() {
		MemberGA mem = new MemberGA(5);
		assertSame(mem.getChromosomeSize(),5);
		assertSame(new MemberGA(15).getChromosomeSize(),15);
		assertSame(new MemberGA(71).getChromosomeSize(),71);
		assertSame(new MemberGA(84).getChromosomeSize(),84);
	}

}
