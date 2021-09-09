# Chapter 4
aaaa
## CustomAuthenticationProvier 

## 
we need to setup the CustomAuthenticationProvider using `AuthenticationManagerBuilder`

````java
@Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }
````