package luc.webservices.service;

import java.util.List;

import javax.jws.WebService;

import luc.webservices.service.representation.BookRepresentation;
import luc.webservices.service.representation.BookRequest;
import luc.webservices.service.representation.OrderRepresentation;
import luc.webservices.service.representation.OrderRequest;

@WebService
public interface BookStoreService
{
	List< BookRepresentation > getBooks( );

	List< BookRepresentation > searchBooksByRequest( BookRequest bookRequest );

	OrderRepresentation createOrder( OrderRequest OrderRequest );

	OrderRepresentation getOrder( Integer orderId );

	OrderRepresentation cancelOrder( Integer orderId );
}