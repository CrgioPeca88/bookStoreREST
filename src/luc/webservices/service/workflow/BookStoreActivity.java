package luc.webservices.service.workflow;

import java.util.ArrayList;
import java.util.List;

import luc.webservices.domain.Book;
import luc.webservices.domain.BookDAO;
import luc.webservices.domain.Order;
import luc.webservices.domain.OrderDAO;
import luc.webservices.domain.Status;
import luc.webservices.service.representation.BookRepresentation;
import luc.webservices.service.representation.OrderRepresentation;

public class BookStoreActivity
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private static final BookDAO bookDAO = new BookDAO( );
	private static final OrderDAO orderDAO = new OrderDAO( );

	// ----------------------------
	// Constructor
	// ----------------------------

	// ----------------------------
	// Methods
	// ----------------------------

	public List< BookRepresentation > getBooks( )
	{
		List< BookRepresentation > bookRepresentations = new ArrayList< BookRepresentation >( );

		for ( Book book : bookDAO.getBooks( ) )
		{
			BookRepresentation br = new BookRepresentation( );
			br.setName( book.getName( ) );
			br.setAuthor( book.getAuthor( ) );
			br.setIsbn( book.getIsbn( ) );
			br.setType( book.getType( ) );
			br.setPrice( book.getPrice( ) );
			bookRepresentations.add( br );
		}
		return bookRepresentations;
	}

	public List< BookRepresentation > getBooksByRequest( String author, String name, Integer isbn )
	{
		List< BookRepresentation > foundBookRepresentations = new ArrayList< BookRepresentation >( );

		for ( Book book : bookDAO.getBooks( author, name, isbn ) )
		{
			BookRepresentation br = new BookRepresentation( );
			br.setName( book.getName( ) );
			br.setAuthor( book.getAuthor( ) );
			br.setIsbn( book.getIsbn( ) );
			br.setType( book.getType( ) );
			br.setPrice( book.getPrice( ) );
			foundBookRepresentations.add( br );
		}
		return foundBookRepresentations;
	}

	public OrderRepresentation createOrder( Order order )
	{
		Integer orderId = addOrder( order );
		placeOrder( orderId );
		return getOrder( orderId );
	}

	public Integer addOrder( Order order )
	{
		Integer orderID = -1;
		orderID = orderDAO.addOrder( order );
		return orderID;
	}

	// Check out process
	public Status placeOrder( Integer id )
	{
		return orderDAO.placeOrder( id );
	}

	// Set status of specific order, we can CANCEL order here
	public Status setOrderStatus( Integer orderId, Status status )
	{
		Order order = orderDAO.getOrder( orderId ); // Search for order by ID

		// If found
		if ( order != null )
		{
			order.setStatus( status );
			return order.getStatus( );
		}
		// if not found
		return Status.NONEXISTENT;
	}

	public OrderRepresentation getOrder( Integer orderId )
	{
		Order order = orderDAO.getOrder( orderId );
		OrderRepresentation orderRepresentation = new OrderRepresentation( );
		orderRepresentation.setId( order.getId( ) );
		orderRepresentation.setCustomer( order.getCustomer( ) );
		orderRepresentation.setBooks( order.getBooks( ) );
		orderRepresentation.setAddress( order.getAddress( ) );
		orderRepresentation.setPayment( order.getPayment( ) );
		orderRepresentation.setDate( order.getDate( ) );
		orderRepresentation.setStatus( order.getStatus( ) );
		return orderRepresentation;
	}

	public Status getOrderStatus( Integer orderId )
	{
		OrderRepresentation orderRepresentation = getOrder( orderId );
		if ( orderRepresentation != null )
			return orderRepresentation.getStatus( );
		return Status.NONEXISTENT;
	}
}