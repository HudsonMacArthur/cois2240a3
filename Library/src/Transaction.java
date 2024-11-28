import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {
	
	static private Transaction instance;
	
	// Return a reference to the singleton instance
	static public Transaction getInstance()
	{
		// create an instance if one does not yet exist
		if (instance == null)
			instance = new Transaction();
		
		// return the new or existing instance
		return instance;
	}
	
	// Read and print all transactions from file
	public void displayTransactionHistory()
	{
		try
		{
			BufferedReader reader = new BufferedReader( new FileReader("transactions.txt"));
			
			// read the first line
			String nextLine = reader.readLine();
			
			System.out.println("Transaction History:");
			
			// print all lines
			while (nextLine != null)
			{
				// print a line
				System.out.println("\t" + nextLine);
				
				// read the next line
				nextLine = reader.readLine();
			}
			
			reader.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}

    // Perform the borrowing of a book
    public boolean borrowBook(Book book, Member member) {
        if (book.isAvailable()) {
            book.borrowBook();
            member.borrowBook(book); 
            String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed " + book.getTitle();
            System.out.println(transactionDetails);
            
            // save this transaction to file
            saveTransaction(transactionDetails);
            
            return true;
        } else {
            System.out.println("The book is not available.");
            return false;
        }
    }

    // Perform the returning of a book
    public void returnBook(Book book, Member member) {
        if (member.getBorrowedBooks().contains(book)) {
            member.returnBook(book);
            book.returnBook();
            String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned " + book.getTitle();
            System.out.println(transactionDetails);
            
            // save this transaction to file
            saveTransaction(transactionDetails);
            
        } else {
            System.out.println("This book was not borrowed by the member.");
        }
    }

    // Get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
	
	// Save a transaction to file
	private void saveTransaction(String transactionDetails)
	{
		try
		{
			FileWriter writer = new FileWriter("transactions.txt", true);
			
			// write to transactions.txt
			writer.write(transactionDetails + "\n");
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
	}
}