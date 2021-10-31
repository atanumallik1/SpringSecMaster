# Chapter 17

## Learn about Non Opaque JWT Token signed by Symmetric Key 


## Backgorund

## What we do in this demo 
*   We shall Implement an _AuthorizationServer_ with following Capability 
    *   A __ClientDetailsService__ : This Decouples the responsibility of Client Management from the Authorization Server in to the `ClientDetailsService`.  In the Implementation of client Details Service , we are using a `JpaClientDetailsService`
    *   JWT Token Store  : By Implementing a JWT token store, our AuthServer will generate JWT token ( remember, of we do not implement this , by default it will generate an OPAQUE token)
        *   As JWT token is a non Opaque token, we need to create a Private Key, using which the Authorization Server will Sign the Token. This config is passed using `JWTAccessTokenConverter`  
        ````java
        public JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter conv = new JwtAccessTokenConverter();
		conv.setSigningKey("secret");
		return conv; 
	    }
        ````
    *   `JpaClientDetailsService` : We are creating JPA based Client details Service. The tables are created in My SQL.

    
    *       
