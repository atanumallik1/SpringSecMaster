# Chapter 11 : Authorization Server

## Authorization Server
* 

## Backgorund

## What we do in this demo 

# OAuth 2.0 

**Actors** <br>
* User 
* Client 
* Authorization Server
* Resource Server


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
        *   username : Client ID
        *   password : Client Pass 
        
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
This flow ocnsiste fo 2 parts
#### Part 1
* `Client`  
* In this part the `user` needs to be authorized from by the `AuthenticationServer`  
#### Part 2
