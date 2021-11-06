# Chapter 19

##  Using asymmetric key pairs with JWT


## Backgorund
* JWT key is a non Opaque Token. It is generated in `Authorization` Server (n An Oauth Primitive concept) and is signed by the `Private Key` in the authorization Server. 
*  The Token is Received by the `Resource` Server ( another OAuth Primtive ) , now this Resource Server need to verify the authenticity of  the Token.
* The First approach is using Symmetric Key ; which means the Authorization Server Shares the key with the Resource Server. This is Symmetric Key approach as both the servers are ware of the same key. This has some problems ; Authorization Server  uses the Key to _Sign_ whereas Resource Sever use the key to _Verify_. However in this case the same key is used for both the purpose which can be misused; theoritically now the Resource Server can Create ans _Sign_ a Key whih whould not be allowed :( 

* The Second approach is using Asymmetric Key approach. This involves the existence of "Private - Public" key Pair. Both the Keys are known to the _Authorization Server_, but only the _Public Key_ will be shared with the _Resource Server_. Now Sharing the Public Key with the resource server can be done using 2 approaches 
	*   The _ResourceServer_ knows the Public Key ; like we have done in Chapter 18. In this case a Physical Key file is shared with the Resource Server. The Problem with this approach is , if Authorization Server changes key , all resource servers nees to update the public key. The benefit is , the Resource Servers has all necessary information to verify the signature  
	* The Second approach is, the Authorization Server publishes an endpoint for the Public Keys. The Resource Server can call this end point to get the Public key and then it can use the key t veify the the Access Token's correctness. The Pros/Cons are exactly the opposite of Approach 1.

![image](https://user-images.githubusercontent.com/8110582/140611770-7925c81f-f1d4-4884-b7e1-085173782dd1.png)


## What we do in this demo 
* We have Enabled an authorization Server. This  authorization Server is aware of the folloiwng settings 
	*	Via `WebSecurityConfigurerAdapter`
		*	Users : one enduser `john`
		*	PasswordEncoder
		*	`AutheticationManager` bean
	*	Via `AuthorizationServerConfigurerAdapter`
		* possible oAuth cients  : we have configured 2 clients , `client1` we created for postman testing , `resourceserverclient` we created for communication by Resource Server [ ResourceServer also needs to be registered to AuthorizationServer as a client]
		````java
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("client1").secret(passwordEncoder.encode("secret1")).authorities("read")
				.authorizedGrantTypes("password").and()
				// this is special client does not need authorities
				.withClient("resourceserverclient").secret("rssecret") ;

		}
		````
		* Secured the Token Key Endpoint . Following endpoint is the token key endpoint. Resource Server calls this endpoint exposed from Authorization Servr to access the public key to verify the Access Token.

		`localhost:8080/oauth/token_key`
		````
			@Override
			public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
				security.tokenKeyAccess("isAuthenticated()") ;  // isAuthenticated(), permitAll()
			}
		````
		* Enabled the `jwtTokenStore`
		````
			@Bean
			public TokenStore tokenStore() {
				return new JwtTokenStore(jwtTokenStoreConv());
			}

			@Bean
			public JwtAccessTokenConverter jwtTokenStoreConv() {

				JwtAccessTokenConverter jat = new JwtAccessTokenConverter();
				// Configuring the private key to sign the token
				KeyStoreKeyFactory keyFactory = new KeyStoreKeyFactory(new ClassPathResource("ssia.jks"),
						"ssia123".toCharArray());
				jat.setKeyPair(keyFactory.getKeyPair("ssia"));

				return jat;
			}		
		````


* We have Enabled a Resource Server. This  Resource Server is aware of the folloiwng settings 
	*	`EnableResourceServer` annotation 
	````java
		@EnableResourceServer
		@Configuration
		public class ResourceServerConfig  {
	
		}
	````
	*	Passing client keys to access the `AuthorizationServer`, using the properties  
	````
	server.port=9090
	security.oauth2.resource.jwt.key-uri=http://localhost:8080/oauth/token_key
	security.oauth2.client.client-id=resourceserverclient
	security.oauth2.client.client-secret=rssecret
	````



##	Test
*	Auth Server Endpoints 
	*	Token Key 
		`GET localhost:8080/oauth/token_key`
	*	Access Token
		`POST  localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read    Credential: client1/secret1`
*	Resource Server Endpoints 
	`localhost:9090/hello` with bearer token