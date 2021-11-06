# Chapter 18

## Learn about Non Opaque JWT Token signed by Symmetric Key ter

## Backgorund
Read Private Key

````
keytool -genkeypair -alias ssia -keyalg RSA -keypass ssia123 -keystore ssia.jks -storepass ssia123
````


Read Public Key : run this in the folder where the private key is located

````
keytool -list  -rfc --keystore ssia.jks | openssl x509 -inform pem  -pubkey 
````

## What we do in this demo 


Accesstoken Fetching 

POST
localhost:8080/oauth/token?grant_type=password&username=john&password=12345&scope=read
client1
secret1