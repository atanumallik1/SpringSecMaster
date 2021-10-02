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
    * One need to access further information , and there are 3 possibilities to fetch further information 
* non Opaque : Contains Information  ( for example: JWT)


## What we do in this demo 
