# Chapter 20

## Learn about External Authorization Server : KeyCloak

### Client Setup : client name : example
Note: By default in keycloak clients are public, so when accessing token it does not need to pass the Client's password. 
![image](https://user-images.githubusercontent.com/8110582/141687011-572b21a4-dd75-4de3-a2d4-d4b30ffb82d4.png)
Hence the postman call looks like:
![image](https://user-images.githubusercontent.com/8110582/141687077-7e20cd1b-fb84-498a-98d8-38fccd262fe6.png)

If we change the client  configuration from `public` to `confidential` we need to set the password for teh client. In that case another tab will be visible in the keycloak.

### User Attribute Mapping
we have mapped user attribute in the Role-mapper Uder setting  : client Scope --> Role --> Mapper.
![image](https://user-images.githubusercontent.com/8110582/141687277-9376d1ce-5075-421b-a5ca-57bc03b84243.png)
This will bring a PRoperty called `user-name` in the JWT token.
![image](https://user-images.githubusercontent.com/8110582/141687312-baf7cca0-b8c8-426d-b696-684859e8e355.png)


## Backgorund

## What we do in this demo 
