package luc.webservices.service.representation;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractRepresentation
{
	// ----------------------------
	// Attributes
	// ----------------------------

	protected List< Link > links;

	// ----------------------------
	// Methods
	// ----------------------------

	public List< Link > getLinks( )
	{
		return links;
	}

	public void setLinks( Link... links )
	{
		this.links = Arrays.asList( links );
	}
}