package luc.webservices.service.representation;

import java.util.List;

import luc.webservices.domain.*;

public class OrderRequest
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private int id;
	private Customer customer;
	private List< BookRepresentation > books;
	private Payment payment;
	private String date; // Pattern YYYY-MM-DD
	private Status status;
	private Address address;

	// ----------------------------
	// Constructor
	// ----------------------------

	public OrderRequest( )
	{

	}

	// ----------------------------
	// Methods
	// ----------------------------

	public void setId( int id )
	{
		this.id = id;
	}

	public int getId( )
	{
		return id;
	}

	public Customer getCustomer( )
	{
		return customer;
	}

	public void setCustomer( Customer customer )
	{
		this.customer = customer;
	}

	public Payment getPayment( )
	{
		return payment;
	}

	public void setPayment( Payment payment )
	{
		this.payment = payment;
	}

	public List< BookRepresentation > getBooks( )
	{
		return books;
	}

	public void setBooks( List< BookRepresentation > books )
	{
		this.books = books;
	}

	public String getDate( )
	{
		return date;
	}

	public void setDate( String date )
	{
		this.date = date;
	}

	public Status getStatus( )
	{
		return status;
	}

	public void setStatus( Status status )
	{
		this.status = status;
	}

	public Address getAddress( )
	{
		return address;
	}

	public void setAddress( Address address )
	{
		this.address = address;
	}
}