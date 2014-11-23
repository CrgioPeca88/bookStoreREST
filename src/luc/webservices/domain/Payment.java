package luc.webservices.domain;

public class Payment
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private String type;
	private Integer cardNumber;
	private String expirationDate;
	private Integer securityCode;
	private Double cost;

	// ----------------------------
	// Constructor
	// ----------------------------

	public Payment( )
	{

	}

	public Payment( Double cost )
	{
		this.type = "VISA";
		this.cardNumber = 99988765;
		this.expirationDate = "03/18";
		this.securityCode = 123;
		this.cost = cost;
	}

	public Payment( String type, Integer cardNumber, String expirationDate, Integer securityCode, Double cost )
	{
		super( );
		this.type = type;
		this.cardNumber = cardNumber;
		this.expirationDate = expirationDate;
		this.securityCode = securityCode;
		this.cost = cost;
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public String getType( )
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public Integer getCardNumber( )
	{
		return cardNumber;
	}

	public void setCardNumber( Integer cardNumber )
	{
		this.cardNumber = cardNumber;
	}

	public String getExpirationDate( )
	{
		return expirationDate;
	}

	public void setExpirationDate( String expirationDate )
	{
		this.expirationDate = expirationDate;
	}

	public Integer getSecurityCode( )
	{
		return securityCode;
	}

	public void setSecurityCode( Integer securityCode )
	{
		this.securityCode = securityCode;
	}

	public Double getCost( )
	{
		return cost;
	}

	public void setCost( Double cost )
	{
		this.cost = cost;
	}
}