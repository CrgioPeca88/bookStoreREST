package luc.webservices.service.representation;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement ( name = "Link" )
public class Link
{
	// ----------------------------
	// Attributes
	// ----------------------------

	private String action;
	private String url;

	// ----------------------------
	// Constructor
	// ----------------------------

	public Link( )
	{
		super( );
	}

	public Link( String action, String url )
	{
		super( );
		this.action = action;
		this.url = url;
	}

	// ----------------------------
	// Methods
	// ----------------------------

	public String getAction( )
	{
		return action;
	}

	public void setAction( String action )
	{
		this.action = action;
	}

	public String getUrl( )
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}
}