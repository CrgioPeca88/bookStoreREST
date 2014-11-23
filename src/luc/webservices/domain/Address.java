package luc.webservices.domain;

public class Address
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private String street;
	private String city;
	private String state;
	private Integer zipCode;
	private String country;

	// ----------------------------
	// Constructor
	// ----------------------------

	public Address( )
	{

	}

	public Address( String street, String city, String state, Integer zipCode, String country )
	{
		super( );
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public String getStreet( )
	{
		return street;
	}

	public void setStreet( String street )
	{
		this.street = street;
	}

	public String getCity( )
	{
		return city;
	}

	public void setCity( String city )
	{
		this.city = city;
	}

	public String getState( )
	{
		return state;
	}

	public void setState( String state )
	{
		this.state = state;
	}

	public Integer getZipCode( )
	{
		return zipCode;
	}

	public void setZipCode( Integer zipCode )
	{
		this.zipCode = zipCode;
	}

	public String getCountry( )
	{
		return country;
	}

	public void setCountry( String country )
	{
		this.country = country;
	}

	public void setDummyData( )
	{
		this.street = "742 Evergreen Terrace";
		this.city = "Springfield";
		this.state = "MO";
		this.zipCode = 65801;
		this.country = "USA";
	}
}