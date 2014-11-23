package luc.webservices.service.representation;

public class BookRequest
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private String author;
	private String name;
	private Integer isbn;

	// ----------------------------
	// Constructor
	// ----------------------------

	public BookRequest( )
	{

	}

	// ----------------------------
	// Methods
	// ----------------------------
	public Integer getIsbn( )
	{
		return isbn;
	}

	public void setIsbn( Integer isbn )
	{
		this.isbn = isbn;
	}

	public String getAuthor( )
	{
		return author;
	}

	public void setAuthor( String author )
	{
		this.author = author;
	}

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

}