import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryManagementTest {

	@Test
	public void testBookId() throws Exception {
		
		// test valid ids
		assertTrue(tryBookId(100));
		assertTrue(tryBookId(999));
		
		// test invalid ids
		assertFalse(tryBookId(1000));
		assertFalse(tryBookId(64));
		assertFalse(tryBookId(1024));
	}
	
	// tests if a given book id throws an exception
	private boolean tryBookId(int id)
	{
		try
		{
			// return true if constructor exits successfully
			new Book(id, "test");
			return true;
		}
		catch (Exception e)
		{
			// print error message and return false if an exception is thrown
			System.out.println(e.getMessage());
			return false;
		}
	}
}
