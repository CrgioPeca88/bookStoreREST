package luc.webservices.domain;

import java.util.ArrayList;
import java.util.List;

import luc.webservices.domain.Order;
import luc.webservices.domain.Status;
import luc.webservices.exception.OrderNotFoundException;
import luc.webservices.service.representation.OrderRequest;

public class OrderDAO
{

	// ----------------------------
	// Attributes
	// ----------------------------

	private final static List< Order > orders = new ArrayList< Order >( );

	// ----------------------------
	// Constructor
	// ----------------------------

	public OrderDAO( )
	{

	}

	// ----------------------------
	// Methods
	// ----------------------------

	// Add an order when client buys book
	public Integer addOrder( Order order )
	{
		Integer orderID = -1;
		orders.add( order );
		orderID = orders.indexOf( order );
		order.setId( orderID );
		return orderID;
	}

	// Confirm the order after client checks out
	public Status placeOrder( Integer orderId )
	{
		// Find the order by orderId among the existing orders
		for ( Order order : orders )
		{
			// If you find the order in the list
			if ( order.getId( ).equals( orderId ) )
			{
				order.setStatus( Status.CONFIRMED );
				return Status.CONFIRMED;
			}
		}
		return Status.UNCONFIRMED; // if you don't find the order

	}

	public Order getOrder( Integer orderID ) // Find order by order ID
	{
		for ( Order order : orders )
		{
			if ( order.getId( ).equals( orderID ) )
			{
				return order;
			}
		}
		return null;
	}

	// Get orders based on Date
	public List< Order > getOrders( OrderRequest orderRequest )
	{
		String date = orderRequest.getDate( );
		List< Order > foundOrders = new ArrayList< Order >( );

		try
		{
			if ( validDate( date ) && !orders.isEmpty( ) )
			{
				return getOrderByDate( date );
			}
			else if ( orders.isEmpty( ) )
			{
				throw new OrderNotFoundException( "You have not placed any orders yet." );
			}
			else
			{
				throw new OrderNotFoundException( "Please search for an order by date in the following format: YYYY-MM-DD." );
			}
		}
		catch ( OrderNotFoundException e )
		{
			return foundOrders;
		}
	}

	// ----------------------------
	// Private Methods
	// ----------------------------

	private static List< Order > getOrderByDate( String date )
	{
		List< Order > foundOrders = new ArrayList< Order >( );
		for ( Order order : orders )
		{
			if ( equalDates( date, order.getDate( ) ) )
			{
				foundOrders.add( order );
			}
		}
		return foundOrders;
	}

	private static boolean validDate( String date )
	{
		// Pattern "YYYY-MM-DD" or "YYYY MM DD" (separated by white space)
		String datePattern = "\\d{4}([,\\s])\\d{2}([,\\s])\\d{2}";
		return ( null != date && !date.isEmpty( ) && date.matches( datePattern ) );
	}

	private static boolean equalDates( String date1, String date2 )
	{
		String date1Year = date1.substring( 0, 4 );
		String date1Month = date1.substring( 5, 7 );
		String date1Day = date1.substring( 8 );
		String date2Year = date2.substring( 0, 4 );
		String date2Month = date2.substring( 5, 7 );
		String date2Day = date2.substring( 8 );

		return date1Year.equals( date2Year ) && date1Month.equals( date2Month ) && date1Day.equals( date2Day );
	}
}