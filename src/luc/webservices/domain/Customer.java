package luc.webservices.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private String name;
	private Integer id;
	private Address address;
	private List< Integer > phones;

	// ----------------------------
	// Constructor
	// ----------------------------

	public Customer( )
	{

	}

	public Customer( String name, Integer id, Address address, List< Integer > phones )
	{
		super( );
		this.name = name;
		this.id = id;
		this.address = address;
		this.phones = phones;
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public String getName( )
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public Integer getId( )
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public Address getAddress( )
	{
		return address;
	}

	public void setAddress( Address address )
	{
		this.address = address;
	}

	public List< Integer > getPhones( )
	{
		return phones;
	}

	public void setPhones( List< Integer > phones )
	{
		this.phones = phones;
	}

	public void setDummyData( )
	{
		this.name = "Jhon Smith";
		this.id = 123456;
		this.address = new Address( );
		this.address.setDummyData( );
		this.phones = new ArrayList< Integer >( );
		this.phones.add( 6797245 );
	}
}