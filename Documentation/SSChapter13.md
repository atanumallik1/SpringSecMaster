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
* Opaque : does not contain any other information ; it just like a password 
* non Opaque : Contains Information  ( for example: JWT)


## How does the Resource Server validate the Access Token
* The Authorization server Issues an _AccessToken_
* The _ResourceServer_ gets teh _AccessToken_ ; How does the Resource Server know if the Access Token is valid / invalid? 
The answer depends on the type of Token .

    * For Opaque Token : 
        *   Simplest approach is that the Resource Server calls the _AuthorizationServer_ to check the Aceess token 
    * For JWT Token

## What we do in this demo 
