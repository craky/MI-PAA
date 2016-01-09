package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IndividualTest {
	@Rule public ExpectedException thrown= ExpectedException.none();
		
	@Test
	public void testChromosomeLength() {
		Individual i = new Individual(5);
		Individual j = new Individual(157);
		j.setRandomChromosome(); // need to set it because it is in 'l'
		Individual k = new Individual(-7);
		Individual l = new Individual(j);
		assertEquals(i.getChromosomeLength(),5);
		assertEquals(j.getChromosomeLength(),157);
		assertEquals(k.getChromosomeLength(),0);
		assertEquals(l.getChromosomeLength(),157);
	}
	
	@Test(expected=Exception.class)
	public void testGetChromosomeElem() throws Exception{
		Individual i = new Individual(5);
		i.setRandomChromosome();
		i.getChromosomeElem(i.getChromosomeLength());
	}
	
	@Test
	public void testClone() throws Exception{
		Individual i = new Individual(5);
		i.setRandomChromosome();
		Individual j = i.clone();
		assertEquals(i.getChromosomeElem(0),j.getChromosomeElem(0));
		assertEquals(i.getChromosomeElem(1),j.getChromosomeElem(1));
		assertEquals(i.getChromosomeElem(2),j.getChromosomeElem(2));
		assertEquals(i.getChromosomeElem(3),j.getChromosomeElem(3));
		assertEquals(i.getChromosomeElem(4),j.getChromosomeElem(4));
	}

}
