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


## Learn about Authorization Server
* Component who is aware of the users 
* To manage users Authorization server might need a data base , in memory Data etc 

OCTA is one authorization server 

## URLs that Auth server exposes 
* Token URL:  /oauth/token
*  



Token Url:
`localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read`

Response :
````json
{
    "access_token": "fdf2a0c1-c30f-46fb-a170-cf3652ddf58f",
    "token_type": "bearer",
    "expires_in": 43199,
    "scope": "read"
}
````


