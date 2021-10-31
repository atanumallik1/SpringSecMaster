package com.springsecurity.ch16.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.springsecurity.ch16.entities.Client;

public class SecurityClient implements ClientDetails{
	
	
	private final Client client ;
	public SecurityClient (Client client ) {
		this.client = client ;
		
	}

	@Override
	public String getClientId() {
         return client.getClientId() ; 
		
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}
	
	
	
	@Override
	public String getClientSecret() {
		return client.getSecret();
	}


	
	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		System.out.println("+++++++++++=  scope = "+client.getScope());
		//return Set.of( client.getScope());
		// BUG Here : not able to resolve scope from the DB
		
		return Set.of( "read");
	}
	
	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// need to implement both scope and authority
		return List.of( ( ) -> client.getScope());
	}
	
	@Override
	public Set<String> getResourceIds() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return Set.of( client.getGrantType());
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		// Only needed for Implicit / authorization_code grant types ; not for password grant type
				return Set.of("http://localhost:9090");
	}



	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}
