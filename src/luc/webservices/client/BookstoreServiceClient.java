package luc.webservices.client;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.ws.Response;

import luc.webservices.domain.Address;
import luc.webservices.domain.Customer;
import luc.webservices.domain.Payment;
import luc.webservices.service.representation.BookRepresentation;
import luc.webservices.service.representation.BookRequest;
import luc.webservices.service.representation.OrderRepresentation;
import luc.webservices.service.representation.OrderRequest;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class BookstoreServiceClient
{

	public BookstoreServiceClient( )
	{
		super( );
	}

	public static void main( String[ ] args )
	{
		getBooks( );
		buyCancelTolkienBooks( );
		buyMarquezBooks( );
	}

	private static void getBooks( )
	{
		try
		{
			List< Object > providers = new ArrayList< Object >( );
			JacksonJsonProvider provider = new JacksonJsonProvider( );
			provider.addUntouchable( Response.class );
			providers.add( provider );
			WebClient client = WebClient.create( "http://localhost:8081", providers );

			String response = "";
			ObjectMapper mapper = new ObjectMapper( );

			System.out.println( "\n..........................................GET BOOKS...................................." );
			client = WebClient.create( "http://localhost:8081", providers );

			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/books" );

			response = client.get( String.class );
			BookRepresentation[ ] books = mapper.readValue( response, BookRepresentation[ ].class );
			System.out.println( "Total Number of books=" + books.length );
		}
		catch ( JsonParseException e )
		{
			e.printStackTrace( );
		}
		catch ( JsonMappingException e )
		{
			e.printStackTrace( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
	}

	private static void buyMarquezBooks( )
	{
		try
		{
			List< Object > providers = new ArrayList< Object >( );
			JacksonJsonProvider provider = new JacksonJsonProvider( );
			provider.addUntouchable( Response.class );
			providers.add( provider );
			WebClient client = WebClient.create( "http://localhost:8081", providers );

			String response = "";
			ObjectMapper mapper = new ObjectMapper( );

			System.out.println( "\n\n\n.........................................SEARCH MARQUEZ BOOKS.................................." );

			client = WebClient.create( "http://localhost:8081", providers );
			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );

			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/foundedBooks" );

			String getRequestURI = client.getCurrentURI( ).toString( );
			System.out.println( "Client POST METHOD Request URI:  " + getRequestURI );
			String getRequestHeaders = client.getHeaders( ).toString( );
			System.out.println( "Client POST METHOD Request Headers:  " + getRequestHeaders );

			BookRequest bookRequest = new BookRequest( );
			bookRequest.setAuthor( "Marquez" );

			response = client.post( bookRequest, String.class );
			System.out.println( response );

			BookRepresentation[ ] books = mapper.readValue( response, BookRepresentation[ ].class );
			List< BookRepresentation > foundBooks = new ArrayList< BookRepresentation >( );
			for ( int i = 0; i < books.length; i++ )
			{
				foundBooks.add( books[ i ] );
			}

			DecimalFormat money = new DecimalFormat( "\u00A4#.00" );
			Double cost = 0.0;
			System.out.println( "Search for author \"" + bookRequest.getAuthor( ) + "\"==================================================" );
			for ( BookRepresentation book : foundBooks )
			{
				System.out.println( "Book name: " + book.getName( ) + ", " + book.getType( ) + ", ISBN: " + book.getIsbn( ) + ", price: " + money.format( book.getPrice( ) ) );
				cost += book.getPrice( );
			}

			System.out.println( "\n\n\n.........................................ORDER BOOKS.................................." );
			OrderRequest orderRequest = new OrderRequest( );
			// Buy the search result
			orderRequest.setBooks( foundBooks );
			Customer customer = new Customer( );
			customer.setDummyData( );
			orderRequest.setCustomer( customer );
			orderRequest.setPayment( new Payment( cost ) );
			Address address = new Address( );
			address.setDummyData( );
			orderRequest.setAddress( address );
			orderRequest.setDate( new Date( ).toString( ) );

			client = WebClient.create( "http://localhost:8081", providers );
			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders" );

			getRequestURI = client.getCurrentURI( ).toString( );
			System.out.println( "Client POST METHOD Request URI:  " + getRequestURI );
			getRequestHeaders = client.getHeaders( ).toString( );
			System.out.println( "Client POST METHOD Request Headers:  " + getRequestHeaders );

			response = client.post( orderRequest, String.class );
			System.out.println( response );
			OrderRepresentation orderRepresentation = mapper.readValue( response, OrderRepresentation.class );
			System.out.println( "OrderID=" + orderRepresentation.getId( ) );

			System.out.println( "\n\n\n..........................................GET ORDER AND STATUS...................................." );
			client = WebClient.create( "http://localhost:8081", providers );

			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders/" + orderRepresentation.getId( ) );

			response = client.get( String.class );
			orderRepresentation = mapper.readValue( response, OrderRepresentation.class );
			System.out.println( "Status for orderID=" + orderRepresentation.getId( ) + " is " + orderRepresentation.getStatus( ) );

		}
		catch ( JsonParseException e )
		{
			e.printStackTrace( );
		}
		catch ( JsonMappingException e )
		{
			e.printStackTrace( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
	}

	private static void buyCancelTolkienBooks( )
	{
		try
		{
			List< Object > providers = new ArrayList< Object >( );
			JacksonJsonProvider provider = new JacksonJsonProvider( );
			provider.addUntouchable( Response.class );
			providers.add( provider );
			WebClient client = WebClient.create( "http://localhost:8081", providers );

			String response = "";
			ObjectMapper mapper = new ObjectMapper( );

			System.out.println( "\n\n\n.........................................SEARCH TOLKIEN BOOKS.................................." );

			client = WebClient.create( "http://localhost:8081", providers );
			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );

			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/foundedBooks" );

			String getRequestURI = client.getCurrentURI( ).toString( );
			System.out.println( "Client POST METHOD Request URI:  " + getRequestURI );
			String getRequestHeaders = client.getHeaders( ).toString( );
			System.out.println( "Client POST METHOD Request Headers:  " + getRequestHeaders );

			BookRequest bookRequest = new BookRequest( );
			bookRequest.setAuthor( "Tolkien" );

			response = client.post( bookRequest, String.class );
			System.out.println( response );

			BookRepresentation[ ] books = mapper.readValue( response, BookRepresentation[ ].class );
			List< BookRepresentation > foundBooks = new ArrayList< BookRepresentation >( );
			for ( int i = 0; i < books.length; i++ )
			{
				foundBooks.add( books[ i ] );
			}

			DecimalFormat money = new DecimalFormat( "\u00A4#.00" );
			Double cost = 0.0;
			System.out.println( "Search for author \"" + bookRequest.getAuthor( ) + "\"==================================================" );
			for ( BookRepresentation book : foundBooks )
			{
				System.out.println( "Book name: " + book.getName( ) + ", " + book.getType( ) + ", ISBN: " + book.getIsbn( ) + ", price: " + money.format( book.getPrice( ) ) );
				cost += book.getPrice( );
			}

			System.out.println( "\n\n\n.........................................ORDER BOOKS.................................." );
			OrderRequest orderRequest = new OrderRequest( );
			// Buy the search result
			orderRequest.setBooks( foundBooks );
			Customer customer = new Customer( );
			customer.setDummyData( );
			orderRequest.setCustomer( customer );
			orderRequest.setPayment( new Payment( cost ) );
			Address address = new Address( );
			address.setDummyData( );
			orderRequest.setAddress( address );
			orderRequest.setDate( new Date( ).toString( ) );

			client = WebClient.create( "http://localhost:8081", providers );
			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders" );

			getRequestURI = client.getCurrentURI( ).toString( );
			System.out.println( "Client POST METHOD Request URI:  " + getRequestURI );
			getRequestHeaders = client.getHeaders( ).toString( );
			System.out.println( "Client POST METHOD Request Headers:  " + getRequestHeaders );

			response = client.post( orderRequest, String.class );
			System.out.println( response );
			OrderRepresentation orderRepresentation = mapper.readValue( response, OrderRepresentation.class );
			System.out.println( "OrderID=" + orderRepresentation.getId( ) );

			System.out.println( "\n\n\n..........................................GET ORDER AND STATUS...................................." );
			client = WebClient.create( "http://localhost:8081", providers );

			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders/" + orderRepresentation.getId( ) );

			response = client.get( String.class );
			orderRepresentation = mapper.readValue( response, OrderRepresentation.class );
			System.out.println( "Status for orderID=" + orderRepresentation.getId( ) + " is " + orderRepresentation.getStatus( ) );

			System.out.println( "\n\n\n..........................................CANCEL ORDER...................................." );
			client = WebClient.create( "http://localhost:8081", providers );

			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders/" + orderRepresentation.getId( ) );
			client.delete( );

			System.out.println( "\n\n\n..........................................GET ORDER AND STATUS...................................." );
			client = WebClient.create( "http://localhost:8081", providers );

			WebClient.getConfig( client ).getOutInterceptors( ).add( new LoggingOutInterceptor( ) );
			WebClient.getConfig( client ).getInInterceptors( ).add( new LoggingInInterceptor( ) );
			client = client.accept( "application/json" ).type( "application/json" ).path( "/bookStoreService/orders/" + orderRepresentation.getId( ) );

			response = client.get( String.class );
			orderRepresentation = mapper.readValue( response, OrderRepresentation.class );
			System.out.println( "Status for orderID=" + orderRepresentation.getId( ) + " is " + orderRepresentation.getStatus( ) );

		}
		catch ( JsonParseException e )
		{
			e.printStackTrace( );
		}
		catch ( JsonMappingException e )
		{
			e.printStackTrace( );
		}
		catch ( IOException e )
		{
			e.printStackTrace( );
		}
	}
}