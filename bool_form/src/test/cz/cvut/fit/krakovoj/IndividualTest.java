package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void testFitness() throws IOException{
		Formula f = new Formula();
		List<Integer> chrom = new ArrayList<Integer>();
		f.readFromFile("src/test/cz/cvut/fit/krakovoj/testData/example.dat");
		chrom.add(1);
		chrom.add(0);
		chrom.add(0);
		chrom.add(1);
		Individual i = new Individual(chrom);
		assertEquals(i.getFitness(f),14);
	}
	
	@Test
	public void testChangingChromo() throws Exception{
		Individual i = new Individual(5);
		i.setChromosomeElem(2, 1);
		assertEquals(i.getChromosomeElem(2),1);
		i.setChromosomeElem(2, 0);
		assertEquals(i.getChromosomeElem(2),0);
	}

}
