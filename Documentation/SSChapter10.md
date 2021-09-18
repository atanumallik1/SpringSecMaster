# Chapter 10

## Learn about CORS


## Backgorund

## What we do in this demo 
We try to load `http://localhost:8080/` which works fine; but when we change the url to 
`http://127.0.0.1:8080/` it gives CORS issue

## Note
* Good to Know : we are using @RestController and @Controller in this example. The @Controller annotation returns the thymeleaf template 

@Controller + @Responsebody = @RestController 
* CORS issue does not stop the browser to call the url ; we can see the debug logs coming from MvcController->test(). But the browser does not receive teh response .

![image](images/Chapter10/p1.png)
![image](images/Chapter10/p2.png)
