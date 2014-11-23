package luc.webservices.exception;

public class OrderNotFoundException extends Exception
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private static final long serialVersionUID = 8900434114744580191L;
	private String message = null;

	// ----------------------------
	// Constructor
	// ----------------------------

	public OrderNotFoundException( )
	{
		super( );
	}

	public OrderNotFoundException( String message )
	{
		super( message );
		this.message = message;
	}

	public OrderNotFoundException( Throwable cause )
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