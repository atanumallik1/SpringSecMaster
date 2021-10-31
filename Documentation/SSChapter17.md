# Chapter 17

## Learn about Non Opaque JWT Token signed by Symmetric Key 

OAuth Tokens can be Opaque or Non Opaque. This token is issues by the _Authorization Server_ and consumed and validated by the _Resource Server_.  

For Opaque Tokens, the Resource server can validate the Token using
*   Introsepction Endpoint (Exposed by Authorization server)
*   Blackboarding approach  ( both Auth and Resource server use the same DB) 


For Non-Opaque Token ( Like : JWT), Following approaches can be used for Token Validation 
*   Symmetric Key : __[ Discussed in this example ]__
    *   Authorization Server and Resource Server both knows a single key ; Authorization server singns the token with this key, Resource server verifies with the same key
    *   __Drawback__ : 
        *   Single key is shared between 2 servers, probability of misusing is doubled
        *   Authorization Server is supposed to __Sign__ the token with the Key, whereas Resource Server is supposed to __verify__ the token using the key. Because it is the same key, Resource server can __sign__ and issue the token which we can not stop , this is bad 
*   non Symmetric Key 
    *   It is combination of Private  + Public key. Authorization server __Signs__ the token using __private Key__ and keeps it a secret. Resource server verifies the signature using the __public key__ 

## Backgorund
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
	DB Table Schema :
    	![image](https://user-images.githubusercontent.com/8110582/139584705-facde856-249e-4161-a7dc-feece04edcb7.png)
	
	    Table Content :
	    ![image](https://user-images.githubusercontent.com/8110582/139584747-7d75c2f6-59ec-4a0d-bb7f-b8039ae551e5.png)

        Here I have configured following 2 OAuth Clients.
        *   client 1: to use OAuth grant type Password
        *   client 2: to use OAuth GrantType authorization_code
    *   `Client` : Client entity need to configure `RedirectURI` to support _implicit_ or _authorization_code_ grant types ; for _password_ grant types it is not needed to be configured. In our example for _Client2_ thi config is needed 
        ````java
        @Override
        public Set<String> getRegisteredRedirectUri() {
            // Only needed for Implicit / authorization_code grant types ; not for password grant type
                    return Set.of("http://localhost:9090");
        }
        ````

    *       
## What we do in this demo 
OAuth client wants to get the __access token__ from the authorization Server. Depending on Grant types, the flow for acquisition of access tokns change. 

### __authorization_code__ grant types
*   In this example _client2_ is registered in the Authorization Server to use this grant type. Refer to the table data in the screenshot.

* For an overview of This grant type , refer to https://docs.pivotal.io/p-identity/1-14/grant-types.html 
![image](https://user-images.githubusercontent.com/8110582/139586817-6e649e1b-8994-4eb8-95d9-622900a03db8.png)
    *   Step 2 : OAuth Client will issue a re-direct URL, to the _User_. The redirect URL contains the client ID. The redirect URL looks like 
    `http://localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read`

        *   This URL cntains following important information,  
        *   Identification of the client as __client2__
        *   it is asking for an Authorization code ( not the access token)
        *   _Note: This url is `/oauth/authorize?response_type=code`_ ; this URL is different from access token fetching URL [ refe to Step 5 ] 


    *   Step 3 : The _User_ fires a call to authorization server using teh Redirect URL. Note that , it is not the client who fires the URL to the authorizatin server . Authorization server asks the user to enter the user credential ( john/12345)  directly and authorizes the client2 .
    *  Step 4:   Authorization Server issues an __authorization code__ (for example: `xWy4Wx`) and redirects to client2's redirection URL(refer to the `Background` section in this page)
    *   Step 5: client2 requests the AccessToken from the Authorization Server, by providing `AuthorizationCode`(received in previous step) and `Client secret` (maintained in the DB )


        URL : `localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code=xWy4Wx`
        HTTP Method : `POST`
        Authorization : `Basic auth / client 2 / pass: secret2`
    and here the authorization server issues teh access token.  
    ![image](https://user-images.githubusercontent.com/8110582/139588236-8a0b4879-5e09-4200-b2d1-8e0529ac4991.png)


    _Note : We can see , if the Authorization code is received by an unwanted client, it will not be able to get the access token in step 5 as the Authrization Server knows from Step 4 , that the authorization code was issued for which client. This is where `authorization_code` is better than `implicit` grant type_     

### __password__ grant types
*   In this example _client1_ is registered in the Authorization Server to use this grant type. Refer to the table data screenshot


