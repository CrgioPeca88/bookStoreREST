package luc.webservices.domain;

import java.util.ArrayList;
import java.util.List;

import luc.webservices.domain.Book;
import luc.webservices.exception.BookNotFoundException;

public class BookDAO
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private List< Book > books;

	// ----------------------------
	// Constructor
	// ----------------------------

	public BookDAO( )
	{
		this.books = new ArrayList< Book >( ); // Create some books
		this.books.add( new Book( "One Hundred Years of Solitude", "Gabriel Garcia Marquez", "Hardcover", 224618539, 35.0 ) );
		this.books.add( new Book( "The Lord of The Rings I", "J.R.R. Tolkien", "Paperback", 123456, 20.55 ) );
		this.books.add( new Book( "The Lord of The Rings II", "J.R.R. Tolkien", "Paperback", 123654, 28.25 ) );
		this.books.add( new Book( "The Lord of The Rings III", "J.R.R. Tolkien", "Paperback", 321456, 31.0 ) );
		this.books.add( new Book( "The Lord of The Rings III", "J.R.R. Tolkien", "Hardcover", 321456, 100.0 ) );
		this.books.add( new Book( "Love in the Time of Cholera", "Gabriel Garcia Marquez", "Hardcover", 978342311, 15.0 ) );
		this.books.add( new Book( "REST in Practice: Hypermedia and Systems Architecture", "Jim Webber", "Hardcover", 596805829, 44.99 ) );
		this.books.add( new Book( "1Q84", "Haruki Murakami", "Paperback", 11998844, 24.50 ) );
		this.books.add( new Book( "Infinite Jest", "David Foster Wallace", "Paperback", 99988800, 39.99 ) );
		this.books.add( new Book( "Dune", "Frank Herbert", "Paperback", 555555434, 9.99 ) );
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public List< Book > getBooks( )
	{
		return books;
	}

	// Find book by name, author or ISBN
	public List< Book > getBooks( String author, String name, Integer isbn )
	{
		List< Book > foundBooks = new ArrayList< Book >( );

		try
		{
			if ( validString( name ) && validString( author ) )
			{
				return getBooksByAuthorAndName( foundBooks, author, name );
			}
			else if ( validString( name ) )
			{
				return getBooksByName( foundBooks, name );
			}
			else if ( validString( author ) )
			{
				return getBooksByAuthor( foundBooks, author );
			}
			else if ( isbn != null )
			{
				return getBooksByISBN( foundBooks, isbn );
			}
			else
			{
				throw new BookNotFoundException( "Please specify the parameters." );
			}
		}
		catch ( BookNotFoundException e )
		{
			return foundBooks;
		}
	}

	// ----------------------------
	// Private Methods
	// ----------------------------

	// Find book by ISBN
	private List< Book > getBooksByISBN( List< Book > foundBooks, int isbn ) throws BookNotFoundException
	{
		for ( Book book : books )
		{
			if ( book.getIsbn( ).equals( isbn ) )
			{
				foundBooks.add( book );
			}
		}
		if ( !foundBooks.isEmpty( ) )
		{
			return foundBooks;
		}
		else
		{
			throw new BookNotFoundException( "There are no books with the given ISBN: " + isbn );
		}
	}

	// Find book by Author
	private List< Book > getBooksByAuthor( List< Book > foundBooks, String author ) throws BookNotFoundException
	{
		for ( Book book : books )
		{
			if ( containString( book.getAuthor( ), author ) )
			{
				foundBooks.add( book );
			}
		}
		if ( !foundBooks.isEmpty( ) )
		{
			return foundBooks;
		}
		else
		{
			throw new BookNotFoundException( "There are no books with the given author: " + author );
		}
	}

	// Find book by book name
	private List< Book > getBooksByName( List< Book > foundBooks, String name ) throws BookNotFoundException
	{
		for ( Book book : books )
		{
			if ( containString( book.getName( ), name ) )
			{
				foundBooks.add( book );
			}
		}
		if ( !foundBooks.isEmpty( ) )
		{
			return foundBooks;
		}
		else
		{
			throw new BookNotFoundException( "There are no books with the given name: " + name );
		}
	}

	private List< Book > getBooksByAuthorAndName( List< Book > foundBooks, String author, String name ) throws BookNotFoundException
	{
		for ( Book book : books )
		{
			if ( containString( book.getName( ), name ) && containString( book.getAuthor( ), author ) )
			{
				foundBooks.add( book );
			}
		}
		if ( !foundBooks.isEmpty( ) )
		{
			return foundBooks;
		}
		else
		{
			throw new BookNotFoundException( "There is not a book with the given name: " + name + " and author: " + author );
		}
	}

	private boolean containString( String string1, String string2 )
	{
		return string1.trim( ).toUpperCase( ).contains( string2.trim( ).toUpperCase( ) );
	}

	private boolean validString( String string )
	{
		return ( null != string && !string.isEmpty( ) );
	}
}