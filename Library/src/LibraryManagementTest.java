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
	
	@Test
	public void testBorrowReturn() throws Exception
	{
		// get the singleton transaction instance
		Transaction transaction = Transaction.getInstance();
		
		// create test book and member objects
		Book book = new Book(123, "testBook");
		Member member = new Member(4567, "testMember");
		
		// check that book starts as available
		assertTrue(book.isAvailable());

		// borrow the book as the member
		book.borrowBook();
		member.borrowBook(book);
		
		// check that the member has the book
		assertTrue(member.getBorrowedBooks().contains(book));
		
		// check that book is no longer available
		assertFalse(book.isAvailable());
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
