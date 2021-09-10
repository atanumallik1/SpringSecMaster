# Chapter 5
 
## Learn about _Custom_ `AuthenticationFilter` 

## Background 
![image](images/chapter5/FC.png)<br>
The above picture explains certain important concepts. The flow describes the interactions between different SpringBoot compoenents starting from  Authentication Filter.


1.  `AuthenticationFilter` is a central SpringBoot component of type `Filter`. It is one of the filters in the `filterChain`. This filter intercepts the HTTP requests to the applicatin and activates necessery steps to authenticate the user by delegating the call to a `AuthenticationManager`.
2. `AuthenticationManager` interface only one method `authenticate` , which is the olny expectation from the `AuthenticationFilter`'s side .

````java
public interface AuthenticationManager {
Authentication authenticate(Authentication authentication) throws AuthenticationException;
}
````
However the `AuthenticationManager` does not know how to authenticate. The logic for Authentication can be diverse an complex. A credential based authentication is difefrent from a token based authentication. This logic is known to `AuthenticationProvider`. So a `AuthenticationManager` _**finds**_  and _**delegates**_ the resposibility of _authentication_ to a suitable `AuthenticationProvider` 

3. `AuthenticationProvider` knows how to authenticate for a given type of `Authentication`.




![image](images/chapter5/FC2.png)<br>


## What we do in this demo 
* We want to configure a `CustomFilter` for our application  

## How 


### Introduce a _Custom_ `Authentication` type 

### Introduce  _Custom_ `AuthenticationProvider` which can Authenticate the _Custom_`Authentication`

### Introduce a custom `AuthenticationFilter` 

### Configure Customfilter in your application's `filterChain`

### Make the `CustomAuthenticationProvider` available to the Spring Bean context 



