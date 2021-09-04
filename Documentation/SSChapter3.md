# Chapter 3

* Starter Project.


## Important Notes
* We shall discuss the following classes in more details
*	`PasswordEncoder`
* 	`UserDetailsManager` 


## Important classes 
* When user fires a request the request is _intercepted_  by `AuthenticationFilter`, this is one of the filters in the FilterChain 
* The authentication responsibility is delegated to an _AuthenticationManager_
* AuthenticationManager finds the right type of _AuthenticationProvider_ which implements the Authentication Logic
* AuthenticationProvider needs following 2 classes to solve 2 distinct responsibilities
	*	`UserDetailsService` Retrieves the User. UserDetailsService is like a contract whihc is normally implemented by `UserdetailsManager` 
	*	`PasswordEncoder` Validates the password



## UserDetailsService vs UeserDetailsManager 
* These 2 interfaces implement the SOLID principal ;  Responsibility Segregation Principal
* UserDetailsService only find a User : this is the only contract
* UserDetailsManager is a specific extension of UserDetailsService which can do all that the parent does, additionally offers the capabilities to manage Users  : i.e an extended contract 

### UserDetailsService
* Responsibility: to find a user 
```java
public interface UserDetailsService {

	/**
	 * Locates the user based on the username. In the actual implementation, the search
	 * may possibly be case sensitive, or case insensitive depending on how the
	 * implementation instance is configured. In this case, the <code>UserDetails</code>
	 * object that comes back may have a username that is of a different case than what
	 * was actually requested..
	 * @param username the username identifying the user whose data is required.
	 * @return a fully populated user record (never <code>null</code>)
	 * @throws UsernameNotFoundException if the user could not be found or the user has no
	 * GrantedAuthority
	 */
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
````

### UserDetailsManager
* 
```java
public interface UserDetailsManager extends UserDetailsService {

	/**
	 * Create a new user with the supplied details.
	 */
	void createUser(UserDetails user);

	/**
	 * Update the specified user.
	 */
	void updateUser(UserDetails user);

	/**
	 * Remove the user with the given login name from the system.
	 */
	void deleteUser(String username);

	/**
	 * Modify the current user's password. This should change the user's password in the
	 * persistent user repository (datbase, LDAP etc).
	 * @param oldPassword current password (for re-authentication if required)
	 * @param newPassword the password to change to
	 */
	void changePassword(String oldPassword, String newPassword);

	/**
	 * Check if a user with the supplied login name exists in the system.
	 */
	boolean userExists(String username);

}
```




* A `UseretailsService` has only one method `loadUserByUserName`. The job of this service is to load the User from somewhere; It does not do any operation around the User . Some Applications need to do something more like: CreateUser, Manager User, Does the user exist etc.. here we need `UserDetailsManager`
* `UserDetailsManager` is a `UserDetailsService`
* But it can also manage Users ( like : user creation , updation , activation .. )
* There are some standard implementation of UserDetailsManager .
	* `JDBCUserDetailsManager` is one such standard implementation. We can use it to fire up a pre configured userDetailsManager. Pros: It is create a UserDetailsManager without any other dependency to JPA, Hibernate etc, it can just start  Cons: One needs to creat ethe tables,schemas as per the specification , if your own table structures for User is different to need to change the queries.. 
	* `InMemoryUserDetailsManager` is another such implementation of UserDetailsManager 


in Summary the whole class hierarchy loos like 

![image](https://user-images.githubusercontent.com/8110582/132100852-e6135e50-8af5-4d28-aadb-d3fc4088e7e4.png)



<br>
<br>
<br>
-------

## Prerequisite 

* MYSQL DB installed and started 
* There is  a DB Schema Created  , in this case the Schema name is `spring1`
* `user` table created in the schema
![image](images\chapter2\db.png)


