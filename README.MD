**************************************************************
Details : This application exposes the spring boot CRUD application and Hibernate for Ingredients API
**************************************************************


******
Note :
****** 
- This spring boot application is used to perform multiple operations on a user entity
and exposes API for CRUD operations

- Can also build UI with React JS for this.

******************************************************************
Procedure to bring the service UP
******************************************************************

1) Import the DB file (/db/recipe.sql) to database with name "recipe"

2) Perform mvn clean install and Start the spring boot app 

Below are endpoints exposed : 

3) Health Check Api 
   URL : http://localhost:1432/
   Type : GET
   Reponse: {
	"status":"SUCCESS",
	"errors":null,
	"data":"server is running"
	}
4)To get the list of user information by firstname
   URL : http://localhost:1432/users/matt
   Type : GET
   Response :{
    "status": "SUCCESS",
    "errors": null,
    "data": [
        {
            "id": 9,
            "email": "mattwood@gmail.com",
            "firstName": "matt",
            "lastName": "wood",
            "mocktails": [],
            "permissions": []
        }
    ]
}
5)To get the list of all user information
URL : http://localhost:5252/users/
   Type : GET
   Response :{
    "status": "SUCCESS",
    "errors": null,
    "data": [
        {
            "id": 3,
            "email": "dog@gmail.com",
            "firstName": "monkey",
            "lastName": "donkey",
            "mocktails": [
                {
                    "id": 4,
                    "name": "moscow mule",
                    "serving": "3",
                    "duration": "2"
                }
            ],
            "permissions": [
                {
                    "id": 1,
                    "name": "CREATE",
                    "description": "This permission will help the user to only create a mocktail "
                }
            ]
        },
        {
            "id": 5,
            "email": "dog@gmail.com",
            "firstName": "monkey",
            "lastName": "donkey",
            "mocktails": [
                {
                    "id": 7,
                    "name": "Blue berries",
                    "serving": "3",
                    "duration": "3"
                },
                {
                    "id": 6,
                    "name": "moscow mule",
                    "serving": "3",
                    "duration": "2"
                }
            ],
            "permissions": [
                {
                    "id": 1,
                    "name": "CREATE",
                    "description": "This permission will help the user to only create a mocktail "
                },
                {
                    "id": 2,
                    "name": "EDIT",
                    "description": "This permission will help the user to only update a mocktail"
                }
            ]
        }
  }
6) API to create a user
URL : http://localhost:5252/users
Type : POST
Request Body: 
{"email": "haydun@gmail.com",
 "firstName": "aar",
 "lastName": "wood"}
 Response: {
    "status": "SUCCESS",
    "errors": null,
    "data": {
        "id": 21,
        "email": "haydun@gmail.com",
        "firstName": "aar",
        "lastName": "wood",
        "mocktails": null,
        "permissions": []
    }
}
7) API to create a mocktail with userid
URL : http://localhost:1432/users/21/mocktails
Type: POST
Request: Request Body: 
{
"name": "Blue berries",
"serving": "3",
"duration": "3" }
 Response: {
    "status": "SUCCESS",
    "errors": null,
    "data": [
        {
            "id": 22,
            "name": "Blue berries",
            "serving": "3",
            "duration": "3"
        }
    ]
}
8) API to get mocktails with UserId
   URL : http://localhost:1432/users/3/mocktails
   Type : GET
   Reponse: {
    "status": "SUCCESS",
    "errors": null,
    "data": [
        {
            "id": 4,
            "name": "moscow mule",
            "serving": "3",
            "duration": "2"
        }
    ]
}
9) API to create Permissions
    URL : http://localhost:1432/users/3/permissions
    Type : POST
    RequestBody: {
		"name": ["CREATE","EDIT"]
	}
    Response : {
    "status": "SUCCESS",
    "errors": null,
    "data": {
        "id": 3,
        "email": "dog@gmail.com",
        "firstName": "monkey",
        "lastName": "donkey",
        "mocktails": [
            {
                "id": 4,
                "name": "moscow mule",
                "serving": "3",
                "duration": "2"
            }
        ],
        "permissions": [
            {
                "id": 2,
                "name": "EDIT",
                "description": "This permission will help the user to only update a mocktail"
            },
            {
                "id": 1,
                "name": "CREATE",
                "description": "This permission will help the user to only create a mocktail "
            }
        ]
    }
}

