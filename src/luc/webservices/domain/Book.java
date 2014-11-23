package luc.webservices.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement ( name = "Book" )
@XmlAccessorType ( XmlAccessType.FIELD )
@XmlType ( name = "" )
public class Book
{

	// ----------------------------
	// Attributes
	// ----------------------------

	private String name;
	private String author;
	private Integer isbn;
	private String type;
	private Double price;

	// ----------------------------
	// Constructor
	// ----------------------------

	public Book( )
	{

	}

	public Book( String name, String author, String type, Integer isbn, Double price )
	{
		super( );
		this.name = name;
		this.author = author;
		this.type = type;
		this.isbn = isbn;
		this.price = price;
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

	public String getAuthor( )
	{
		return author;
	}

	public void setAuthor( String author )
	{
		this.author = author;
	}

	public Integer getIsbn( )
	{
		return isbn;
	}

	public void setIsbn( Integer isbn )
	{
		this.isbn = isbn;
	}

	public Double getPrice( )
	{
		return price;
	}

	public void setPrice( Double price )
	{
		this.price = price;
	}

	public String getType( )
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}
}