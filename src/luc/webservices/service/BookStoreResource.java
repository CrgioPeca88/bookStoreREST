package luc.webservices.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import luc.webservices.domain.Order;
import luc.webservices.domain.Status;
import luc.webservices.service.representation.BookRepresentation;
import luc.webservices.service.representation.BookRequest;
import luc.webservices.service.representation.OrderRepresentation;
import luc.webservices.service.representation.OrderRequest;
import luc.webservices.service.workflow.BookStoreActivity;

@Path ( "/bookStoreService/" )
public class BookStoreResource implements BookStoreService
{

	private static BookStoreActivity bookStoreActivity = new BookStoreActivity( );

	// ----------------------------
	// Methods
	// ----------------------------

	@GET
	@Produces (
	{ "application/xml", "application/json" } )
	@Path ( "/books" )
	public List< BookRepresentation > getBooks( )
	{
		return bookStoreActivity.getBooks( );
	}

	@POST
	@Produces (
	{ "application/xml", "application/json" } )
	@Path ( "/foundedBooks" )
	public List< BookRepresentation > searchBooksByRequest( BookRequest bookRequest )
	{
		return bookStoreActivity.getBooksByRequest( bookRequest.getAuthor( ), bookRequest.getName( ), bookRequest.getIsbn( ) );
	}

	@POST
	@Produces (
	{ "application/xml", "application/json" } )
	@Path ( "/orders" )
	public OrderRepresentation createOrder( OrderRequest orderRequest )
	{
		return bookStoreActivity.createOrder( new Order( orderRequest.getCustomer( ), orderRequest.getPayment( ), orderRequest.getBooks( ), orderRequest.getAddress( ), orderRequest.getDate( ), Status.UNCONFIRMED ) );
	}

	@GET
	@Produces (
	{ "application/xml", "application/json" } )
	@Path ( "/orders/{orderId}" )
	public OrderRepresentation getOrder( @PathParam ( "orderId" ) Integer orderId )
	{
		return bookStoreActivity.getOrder( orderId );
	}

	@DELETE
	@Produces (
	{ "application/xml", "application/json" } )
	@Path ( "/orders/{orderId}" )
	public OrderRepresentation cancelOrder( @PathParam ( "orderId" ) Integer orderId )
	{
		bookStoreActivity.setOrderStatus( orderId, Status.CANCELED );
		return bookStoreActivity.getOrder( orderId );
	}
}