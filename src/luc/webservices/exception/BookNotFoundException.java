package luc.webservices.exception;

public class BookNotFoundException extends Exception
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private static final long serialVersionUID = -8846103067629854319L;
	private String message = null;

	// ----------------------------
	// Constructor
	// ----------------------------

	public BookNotFoundException( )
	{
		super( );
	}

	public BookNotFoundException( String message )
	{
		super( message );
		this.message = message;
	}

	public BookNotFoundException( Throwable cause )
	{
		super( cause );
	}

	// ----------------------------
	// Methods
	// ----------------------------

	@Override
	public String toString( )
	{
		return message;
	}

	@Override
	public String getMessage( )
	{
		return message;
	}
}