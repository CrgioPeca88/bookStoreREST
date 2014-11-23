package luc.webservices.service.representation;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import luc.webservices.domain.Address;
import luc.webservices.domain.Customer;
import luc.webservices.domain.Payment;
import luc.webservices.domain.Status;

@XmlRootElement ( name = "Order" )
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "" )
public class OrderRepresentation
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private Integer id;
	private Customer customer;
	private List< BookRepresentation > books;
	private Address address;
	private Payment payment;
	private String date; // Pattern YYYY-MM-DD
	private Status status;

	// ----------------------------
	// Constructor
	// ----------------------------

	public OrderRepresentation( )
	{

	}

	public OrderRepresentation( Customer customer, Payment payment, List< BookRepresentation > books, Address address, String date, Status status )
	{
		super( );
		this.customer = customer;
		this.payment = payment;
		this.books = books;
		this.setAddress( address );
		this.date = date;
		// this.status = status;
		// this.id = id;
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public void setId( Integer id )
	{
		this.id = id;
	}

	public Integer getId( )
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