# Atipera-Excercise
Java17 + Springboot 3 app, using Mapstruct and Lombok libraries.

Projects uses external API -  https://developer.github.com/v3 as a backing API


This is a simple MVC Application has single API endpoint using POST method: http://localhost:8080/api/v1/repositories/{username}
It returns List of Repositories for given username, found in developer github API, according to Acceptance criteria, that can be found below.


Project Acceptance criteria:
 1.   As an api consumer, given username and header “Accept: application/json”, 
      I would like to list all his github repositories, which are not forks. 
      Information, which I require in the response, is:
      -Repository Name
      -Owner Login
      -For each branch it’s name and last commit sha
 2.   As an api consumer, given not existing github user, I would like to receive 404 response in such a format:
        {
        “status”: ${responseCode}
        “Message”: ${whyHasItHappened}
        }
 3.   As an api consumer, given header “Accept: application/xml”, I would like to receive 406 response in such a format:
        {
        “status”: ${responseCode}
        “Message”: ${whyHasItHappened}
        }

How to start project:

1. Download the project and open it.
2. Download Maven dependencies. 
3. Run ZadanieAtiperaApplication.java main method in you IDE.
4. You contact your API on http://localhost:8080/api/v1/repositories/{username}
5. To send requests to API, it is sugested ot use Postman App


Please consider that Github API has limit of requests per time period. 
This is what might be happening when if you get an internal server error as response



Use scenario examples:
1. Using invalid username, we get 404 response:
{
    "status": 404,
    "message": "User not found. Please give an existing github user."
}
<img width="1283" alt="image" src="https://github.com/AIski/Atipera-Excercise/assets/102914351/4735d928-68a3-4c57-ad91-45f095d047e5">

2. Using "Allow: application/xml" header request, we get 406 error:
 <ErrorResponse>
    <status>406</status>
    <message>Not acceptable header. Please use 'Accept= application/json' instead.</message>
  </ErrorResponse>
  
   <img width="1283" alt="image" src="https://github.com/AIski/Atipera-Excercise/assets/102914351/19b257b2-de18-4612-8f47-a4dc38473a55">

3. Using valid header "Allow: application/json", and valid username:
<img width="1283" alt="image" src="https://github.com/AIski/Atipera-Excercise/assets/102914351/9a609c3b-2310-4c3a-9f25-e3c1529275e3">



