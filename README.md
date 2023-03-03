# Backend
## Installation guide
1. Download IntelliJ IDEA community edition from [here](https://www.jetbrains.com/idea/download/?_gl=1*phc1uq*_ga*MjEyMDkxOTk2Ni4xNjc1MDAyNTg1*_ga_9J976DJZ68*MTY3Nzc1NDE1NS4yNC4wLjE2Nzc3NTQxNTUuNjAuMC4w&_ga=2.93546653.182904658.1677590773-2120919966.1675002585#section=windows). 
2. After the installation, clone this repository with the following command:
```
git clone <repository_link>
```
You can get the link by pressing the green button that says <>code. Picture below
![image](https://user-images.githubusercontent.com/92360393/222408541-e32050a1-fe4e-45da-967d-38c425578467.png)

3. Open the cloned project in IntelliJ IDEA.
4. Wait for the dependencies to download. You can track the process in the bottom right corner.
5. After the dependencies have downloaded, you can run the application with going to the DiscgolfApplication.kt file and by pressing green arrow next to main function.

![image](https://user-images.githubusercontent.com/92360393/222409966-0fe0b2ee-3931-4589-8e16-1b8cacf0488f.png)


6. When the application has opened you can open the next address in your web browser: http://localhost:8080/. There should be a text saying "Welcome to disc-golf app back-end!!"

## Problems with running the application
### Wrong SDK
#### Problem
If the application is not starting you can check that you have a correct SDK selected. 
These are the signs that you have wrong SDK version.
![image](https://user-images.githubusercontent.com/92360393/222414193-10214288-db2c-438d-ae44-00eaeefb22cc.png) 

Trying to run the application with wrong SDK version:

![image](https://user-images.githubusercontent.com/92360393/222414244-7dabc41c-d882-46ba-b8fb-0664e2c6ca96.png)

#### Possible solution

In the IntelliJ IDEA go to the file > Project Structure. 

If there is no SDK selected, the application won't work. The Kotlin SDK doesn't work.

![image](https://user-images.githubusercontent.com/92360393/222412647-305678cb-4bbd-4840-960b-da8d06afde3d.png)
 
 In this picture below, there is a working SDK version. 

![image](https://user-images.githubusercontent.com/92360393/222412782-b5a2fd8e-bb37-4833-9278-b9ef80530601.png)

If there are no options for SDK or there is only Kotlin SDK, you have to download the SDK.

## Api documentation
Please format JSON here: https://jsonformatter.curiousconcept.com/
### Deployments
- dev URL: https://dev-discgolf.herokuapp.com/
- prod URL: https://disc-golf-database.herokuapp.com/
### User
#### Get all users from DB
GET Endpoint: /users

Format:
```
[
   {
      "userId":1,
      "username":"Keijo",
      "email":"keijonen@gmail.com",
      "password":"enolekeijo",
      "role":"USER",
      "gamesPlayed":0,
      "totalTimePlayed":"00:00:00",
      "totalThrowsThrown":0,
      "totalSteps":0,
      "scores":{ }
   },
   {
      "userId":2,
      "username":"Maija",
      "email":"maijakas@hotmail.com",
      "password":"olenmaija",
      "role":"USER",
      "gamesPlayed":0,
      "totalTimePlayed":"00:00:00",
      "totalThrowsThrown":0,
      "totalSteps":0,
      "scores":{ }
   },
   {
      "userId":3,
      "username":"admin",
      "email":"admin@discgolf.com",
      "password":"admin",
      "role":"ADMIN",
      "gamesPlayed":0,
      "totalTimePlayed":"00:00:00",
      "totalThrowsThrown":0,
      "totalSteps":0,
      "scores":{ }
   },
   {...}
]
```
#### Find user by user id

Endpoint: /users/:userId

Format:
```
{
   "userId":1,
   "username":"Keijo",
   "email":"keijonen@gmail.com",
   "password":"enolekeijo",
   "role":"USER",
   "gamesPlayed":2,
   "totalTimePlayed":"04:50:00",
   "totalThrowsThrown":38,
   "totalSteps":9500,
   "scores":{
      "ACE":4,
      "ALBATROSS":1,
      "EAGLE":5,
      "BIRDIE":8,
      "PAR":8,
      "BOGEY":7,
      "DOUBLE_BOGEY":3,
      "TRIPLE_BOGEY":2,
      "OVER_TRIPLE_BOGEY":0
   }
}
```

### Game
#### Get all games from DB

GET Endpoint: /games

Format: 
```
[
   {
      "gameId":1,
      "user":{
         "userId":1,
         "username":"Keijo",
         "email":"keijonen@gmail.com",
         "password":"enolekeijo",
         "role":"USER"
      },
      "strokes":[
         {
            "strokeId":1,
            "hole":{
               "holeId":1,
               "holePar":3,
               "holeLength":82,
               "holeNumber":1
            },
            "score":3
         },
         {
            "strokeId":2,
            "hole":{
               "holeId":2,
               "holePar":3,
               "holeLength":86,
               "holeNumber":2
            },
            "score":3
         },
         {...}
      ],
      "steps":5000,
      "startingDatetime":"2023-02-13T14:28:54.360072",
      "endingDatetime":"2023-02-13T15:58:54.360072"
   } ,
   {...}
]
```

### Course
#### Get all courses from DB.

GET Endpoint: /courses

Format:
```
[
   {
      "courseId":1,
      "holes":[
         {
            "holeId":1,
            "holePar":3,
            "holeLength":82,
            "holeNumber":1
         },
         {
            "holeId":2,
            "holePar":3,
            "holeLength":86,
            "holeNumber":2
         },
         {...}
      ],
      "courseName":"Puolarmaari",
      "courseStreetaddress":"Puolarmaari 3",
      "courseTown":"Espoo",
      "coursePostalcode":"02210"
   } ,
   {...}
]
```
#### Get course by id

GET Endpoint: /courses/:courseId

Format:
```
{
   "courseId":1,
   "holes":[
      {
         "holeId":1,
         "holePar":3,
         "holeLength":42,
         "holeNumber":1
      },
      {...}
   ],
   "courseName":"Puolarmaari",
   "courseStreetaddress":"Puolarmaari 3",
   "courseTown":"Espoo",
   "coursePostalcode":"02210"
}
```

### Hole
#### Get all holes from DB

GET Endpoint: /holes

Format:
```
[
   {
      "holeId":1,
      "holePar":3,
      "holeLength":82,
      "holeNumber":1
   },
   {
      "holeId":2,
      "holePar":3,
      "holeLength":86,
      "holeNumber":2
   },
   {...}
]
```

### Stroke
#### Get all strokes from DB

Get Endpoint: /strokes

Format:
```
[
   {
      "strokeId":1,
      "hole":{
         "holeId":1,
         "holePar":4,
         "holeLength":49,
         "holeNumber":1
      },
      "game":{
         "gameId":1,
         "user":{
            "userId":1,
            "username":"Keijo",
            "email":"keijonen@gmail.com",
            "password":"enolekeijo",
            "role":"USER",
            "gamesPlayed":0,
            "totalTimePlayed":"00:00:00",
            "totalThrowsThrown":0,
            "totalSteps":0,
            "scores":{
               
            }
         },
         "steps":5000,
         "startingDatetime":"2023-03-03T09:56:16.485351",
         "endingDatetime":"2023-03-03T11:26:16.485351"
      },
      "score":1
   },
   {...}
]

