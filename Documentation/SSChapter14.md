# Chapter 14

## Learn about Separate Authorization Server and Resource Server and introspection 
In case of Opaque Token ; the Resource server needs to validate the access token. 
This can happen via 
*   Blackboarding (using same db by auth server and resource server )
*   by calling token introspection   URL from the auth server   ; we shall use this approach in this example 

## Authorization Server Config Details 

*   Setting the __AuthenticationManager__ and __UserDetailsService__. To will see 2 clients. 

*   Setting Clients 
    *   _client1_ we are using to access the authorization server endpoint from POSTMAN. HEnce we are adding scopes and grant types 
    *   _resourceserver_ client will be used by the  Resource Server for Introspection of token, it is a special cleint so we do not need to set up grant types and scope
````java
clients.inMemory()
                .withClient("client1").secret(pe.encode("secret1")).scopes("read")
				.authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(5000)
		.and()
				// Resource Server client will access only for Check Token, it is not an ordinary cleint; so we do  not need scope, grant types etc
				.withClient("resourceserver")
				.secret(pe.encode("12345"));
````
*   Setting Security for Token Aceess endpoints  

## Resource Server Config Details 
* Enable the server as Resource Server 
* Make teh Resource Server aware of the Authorization Server ; here Resource Server will act as an OAuth Client in the eyes of the Authorization server. 

````java
security.oauth2.resource.token-info-uri=http://localhost:8080/oauth/check_token
security.oauth2.client.client-id=resourceserver
security.oauth2.client.client-secret=12345
````

## What we do in this demo 
