package cz.cvut.fit.krakovoj;

import static org.junit.Assert.*;

import org.junit.Test;

public class KnapsackItemTest {

	@Test
	public void testKnapsackItemIntInt() {
		KnapsackItem item = new KnapsackItem(50,60);
		assertSame(item.getCost(),50);
		assertSame(item.getWeight(),60);
	}

	@Test
	public void testKnapsackItem() {
		KnapsackItem item = new KnapsackItem();
		assertSame(item.getCost(),0);
		assertSame(item.getWeight(),0);
		assertSame(item.getCost(),item.getWeight());
	}

	@Test
	public void testCost() {
		KnapsackItem item = new KnapsackItem();
		assertSame(item.getCost(),0);
		item = new KnapsackItem(50,60);
		assertSame(item.getCost(),50);
		item.setCost(80);
		assertSame(item.getCost(),80);
		item.setCost(1);
		assertSame(item.getCost(),1);
		item.setCost(33);
		assertSame(item.getCost(),33);
		item.setCost(0);
		assertSame(item.getCost(),0);
	}

	@Test
	public void testWeight() {
		KnapsackItem item = new KnapsackItem();
		assertSame(item.getWeight(),0);
		item = new KnapsackItem(50,60);
		assertSame(item.getWeight(),60);
		item.setWeight(80);
		assertSame(item.getWeight(),80);
		item.setWeight(1);
		assertSame(item.getWeight(),1);
		item.setWeight(33);
		assertSame(item.getWeight(),33);
		item.setWeight(0);
		assertSame(item.getWeight(),0);
	}

	@Test
	public void testIncreaseCost() {
		KnapsackItem item = new KnapsackItem();
		assertSame(item.getCost(),0);
		item.increaseCost(60);
		assertSame(item.getCost(),60);
	}

	@Test
	public void testIncreaseWeight() {
		KnapsackItem item = new KnapsackItem();
		assertSame(item.getWeight(),0);
		item.increaseWeight(60);
		assertSame(item.getWeight(),60);
	}

	@Test
	public void testClear() {
		KnapsackItem item = new KnapsackItem(80,60);
		assertSame(item.getCost(),80);
		assertSame(item.getWeight(),60);
		item.clear();
		assertSame(item.getCost(),0);
		assertSame(item.getWeight(),0);
	}

	@Test
	public void testHeuristic() {
		KnapsackItem item = new KnapsackItem(800,400);
		assertEquals(item.heuristic(),2.0,0.0001);
		item.setCost(417);
		item.setWeight(50);
		assertEquals(item.heuristic(),8.34,0.0001);
	}

	@Test
	public void testCompareTo() {
		KnapsackItem item = new KnapsackItem(800,400);
		KnapsackItem item2 = new KnapsackItem(800,400);
		
		assertEquals(item.compareTo(item2),0);
		item2.setCost(100);
		item2.setWeight(50);		
		assertEquals(item.compareTo(item2),0);
		
		item2.setCost(150);
		assertTrue((item.compareTo(item2) < 0));
		
		item2.setCost(75);
		assertTrue((item.compareTo(item2) > 0));
	}

}
