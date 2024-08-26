package com.ecom_beauty.ecombeauty.auths;

public class JwtResponse
{
	private final String token;
	
	public JwtResponse(String token)
	{
		this.token = token;
	}
	
	public String getToken()
	{
		return token;
	}
}
