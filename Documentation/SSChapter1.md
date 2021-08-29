# Chapter 1

* Starter Project.
* Overrides default User and Password coming out of SpringSecurity 
* Provides own 
	* `UserDetailsService` 
	* `PasswordEncoder` 

## Important Notes
* We are using `InMemoryUserDetailsManager` which is also a `UserDetailsService`
* We are using `NoPasswordEncoder` 

![image](images\chapter1\ClassDiagram.png)