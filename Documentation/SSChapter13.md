# Chapter 13 : Token implementations in OAuth 2 systems

## Learn about Multiple Types of Token Implementation


## Backgorund
OAuth 2.0 is a specification to obtain AccessToken from the authorization Server by a credible Client. The process of Obtaining _AcccessToken_ depdends on _Grant Types_. 
*   _Implicit Grant Type_ is easy to implement, requires less round trips between Authorization Server, Client, User but less secure and thus deprecated 
* _Authorization Code_ Grant Type requires more roundtrips ; but very secure . It requires Seperate communication between the User <--> Authorization Server , Client <--> Authorization Server to obtain the Access Token. _Authorization Server_ seperately validtes  _Client_ and _User_ and this is more secure approach.
* other types....

### Note
__OAuth 2 is a framework for Authentication ; JWT is a Token Implementation__

The _AccessToken_ can be 
* Opaque : does not contain any other information ; it just like a password . For Example _1a08630d-eef7-4bbd-b922-41c3901a1037_
	*	This is like a string 
	*   It is called Opaque because, it does not contain any other information 
	*	When Resource Server gets the token, it needs to do something additional to verify if the token is valid
* non Opaque : Contains Information  ( for example: JWT)
	*	They contain more information 
	* 	They are encrypted and signed using a key in the Authoirzation Server, resource server can decrypt it directly and verify the authenticity


## How does the Resource Server validate the Access Token
* The Authorization server Issues an _AccessToken_
* The _ResourceServer_ gets teh _AccessToken_ ; How does the Resource Server know if the Access Token is valid / invalid? 

The answer depends on the type of Token .

    * For Opaque Token : 
        *   Simplest approach is that the Resource Server calls the _AuthorizationServer_ to check the Access token . the URL for token validation is _/oauth/check_token?token=<token>_
        *   Using a blackboarding approach : use common DB between the Resource Server and the Authorization server ( however a shared DB might be a anti pattern )
        * Both Resource Server and Auth Server are same application [ not a real life scenario ; only for theoritical exercise]
        
        
        
    * For JWT Token
    		* The token can be decrypted using a key known to the resource server 
    		* We ensure teh validity of the token using its signature 
    		* The token contains relevant data 
    		
## Opaque vs non opaque token 
What this  https://developer.okta.com/blog/2020/08/07/spring-boot-remote-vs-local-tokens 

As per OAuth specification , oAuth access tokens are Opaque, but many OAuth Provider support JWT 
JWT tokens can be validated locally ; Opaque tokens needs to be validated remotely 




   		

## What we do in this demo 
