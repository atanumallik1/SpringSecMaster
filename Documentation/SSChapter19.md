# Chapter 19

##  Using asymmetric key pairs with JWT


## Backgorund
* JWT key is a non Opaque Token. It is generated in `Authorization` Server (n An Oauth Primitive concept) and is signed by the `Private Key` in the authorization Server. 
*  The Token is Received by the `Resource` Server ( another OAuth Primtive ) , now this Resource Server need to verify the authenticity of  the Token.
* The First approach is using Symmetric Key ; which means the Authorization Server Shares the key with the Resource Server. This is Symmetric Key approach as both the servers are ware of the same key. This has some problems ; Authorization Server  uses the Key to _Sign_ whereas Resource Sever use the key to _Verify_. However in this case the same key is used for both the purpose which can be misused; theoritically now the Resource Server can Create ans _Sign_ a Key whih whould not be allowed :( 

* The Second approach is using Asymmetric Key approach. This involves the existence of "Private - Public" key Pair. Both the Keys are known to the _Authorization Server_, but only the _Public Key_ will be shared with the _Resource Server_. Now Sharing the Public Key with the resource server can be done using 2 approaches 
	*   The _ResourceServer_ knows the Public Key ; like we have done in Chapter 18. In this case a Physical Key file is shared with the Resource Server. The Problem with this approach is , if Authorization Server changes key , all resource servers nees to update the public key. The benefit is , the Resource Servers has all necessary information to verify the signature  
	* The Second approach is, the Authorization Server publishes an endpoint for the Public Keys. The Resource Server can call this end point to get the Public key and then it can use the key t veify the the Access Token's correctness. The Pros/Cons are exactly the opposite of Approach 1.

## What we do in this demo 
