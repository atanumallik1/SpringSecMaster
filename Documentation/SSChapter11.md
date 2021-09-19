# Chapter 11 : Authorization Server

## Authorization Server
* 

## Backgorund

## What we do in this demo 

# OAuth 2.0 

**Actors** <br>
* User : Owns the data and knows the credentials
* Client : Client needs to get the `access_token` from the `Authorization Server` on behalf of teh `user` to access a resource from the `Resource Server`
* Authorization Server : Issues `access_token` to a client
* Resource Server : Owns the resource , provides teh resource to teh `client` if the `client` produce a valid `access_token`


**Grant Types**
* Influences the process of Obtaining `access_token`
* Influences how the user's credentials are received by the Authorization Server, and the client's awareness of it..
* Some of teh grant types 
    *   password --> not recommended [ client is aware of the user's credentials ]
    *   authorization_code / pkce ---> User directly passes teh credentials t Auth Server, client is not aware 
    *   client Credential
    *   refresh token
    *   implicit -> Obsolete



## Learn about Authorization Server
* Component who is aware of the users 
* To manage users Authorization server might need a data base , in memory Data etc 

OCTA is one authorization server 

## URLs that Auth server exposes 
* Token URL:  /oauth/token
*  


## Grant Types
### Password 
Client (for example Postman) should fire the following POST url to receive the access token. 
<br>
* Request Details 
    * Token Url: 
`localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read`, 
    * Http Method : `POST` 
    * Authorization Headers
        *   username : Client ID   [ in this case `client1` ]
        *   password : Client Pass [ in this case `secret1` ]
        
<br> **As you can see the client is constructuting the URL for the authorization Server and it needs to pass the username and password of the user, so user needs to share teh username and password to the client, this is not good and that is why it is not recommended to use this grant type**  
<br>

* Response <br>
    ````json
    {
        "access_token": "fdf2a0c1-c30f-46fb-a170-cf3652ddf58f",
        "token_type": "bearer",
        "expires_in": 43199,
        "scope": "read"
    }
    ````

### Authorization code
This flow consists of  2 parts
#### Part 1
* `Client` fires a call to the authorization server and asks the user to authenticate directly by providing the User credentials to the authorization server. The uRL to be fired by teh client would look like the below one. 
    *   Request Details
        * Url `localhost:8080/oauth/authorize?response_type=code&client_id=client2&scope=read`
        * Http Method: `GET`
    *   The authorization server asks the user to login using user's credential 
    *   Once authenticated , teh Authorization server redirects with a **one time usable** code.     
    *   Response Details 
        *   Response Status : `302`
        *   Location Header : `Location: http://localhost:8080/redirect?code=e3XByW
`

**Note**
*   In this case the client does not know about user's credentials , as user directly gives it to Authorization Server
*  On success, the Client wants a `code` back to it. I can also be PKCE token as well.   
_localhost:8080/oauth/authorize?response_type=**code**&**client_id**=client2&scope=read_

* Now the `user` needs to be authenticated   by the `AuthenticationServer`  
* On success, the auth server returns a `code` ; for example : let the code be `3RX3UY`
* This code is a ONE TIME ONLY usable code , to be user by the client to obtain the access token. 

#### Part 2 : Obtaining access token 

* Request Details 
    * Token Url: 
`localhost:8080/oauth/token?grant_type=authorization_code&scope=read&code=3RX3UY`, 
    * Http Method : `POST` 
    * Authorization Headers
        *   username : Client ID   [ in this case `client2` ]
        *   password : Client Pass [ in this case `secret2` ]
        
<br>  One code can be used only once ... 
<br>

* Response <br>
    ````json
    {
        "access_token": "fdf2a0c1-c30f-46fb-a170-cf3652ddf58f",
        "token_type": "bearer",
        "expires_in": 43199,
        "scope": "read"
    }
    ````