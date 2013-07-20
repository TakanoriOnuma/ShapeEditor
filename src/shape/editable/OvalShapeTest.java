package shape.editable;

import static org.junit.Assert.*;

import org.junit.Test;


public class OvalShapeTest {

	@Test
	public void testIsIncluding() {
		OvalShape oval = new OvalShape(0, 0, 20, 10);

		assertTrue(oval.isIncluding(0, 5));
		assertTrue(oval.isIncluding(10, 0));
		assertTrue(oval.isIncluding(20, 5));
		assertTrue(oval.isIncluding(10, 10));

		assertTrue(oval.isIncluding(2, 3));
		assertTrue(oval.isIncluding(13, 7));

		assertFalse(oval.isIncluding(0, 0));
		assertFalse(oval.isIncluding(0, 10));
		assertFalse(oval.isIncluding(20, 0));
		assertFalse(oval.isIncluding(20, 10));


		oval.move(20, 30);

		assertTrue(oval.isIncluding(30, 30));
		assertTrue(oval.isIncluding(40, 35));
		assertTrue(oval.isIncluding(30, 40));
		assertTrue(oval.isIncluding(20, 35));

		assertTrue(oval.isIncluding(33, 34));
		assertTrue(oval.isIncluding(27, 38));

		assertFalse(oval.isIncluding(20, 30));
		assertFalse(oval.isIncluding(20, 40));
		assertFalse(oval.isIncluding(40, 40));
		assertFalse(oval.isIncluding(40, 30));
	}

}
