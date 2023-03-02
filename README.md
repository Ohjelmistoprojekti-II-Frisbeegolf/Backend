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
      "totalSteps":0
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
      "totalSteps":0
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
      "totalSteps":0
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
   "totalThrowsThrown":205,
   "totalSteps":9500
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
         {
            "strokeId":3,
            "hole":{
               "holeId":3,
               "holePar":2,
               "holeLength":48,
               "holeNumber":3
            },
            "score":7
         },
         {
            "strokeId":4,
            "hole":{
               "holeId":4,
               "holePar":4,
               "holeLength":110,
               "holeNumber":4
            },
            "score":8
         },
         {
            "strokeId":5,
            "hole":{
               "holeId":5,
               "holePar":2,
               "holeLength":72,
               "holeNumber":5
            },
            "score":3
         },
         {
            "strokeId":6,
            "hole":{
               "holeId":6,
               "holePar":3,
               "holeLength":74,
               "holeNumber":6
            },
            "score":5
         },
         {
            "strokeId":7,
            "hole":{
               "holeId":7,
               "holePar":3,
               "holeLength":120,
               "holeNumber":7
            },
            "score":1
         },
         {
            "strokeId":8,
            "hole":{
               "holeId":8,
               "holePar":4,
               "holeLength":73,
               "holeNumber":8
            },
            "score":7
         },
         {
            "strokeId":9,
            "hole":{
               "holeId":9,
               "holePar":4,
               "holeLength":61,
               "holeNumber":9
            },
            "score":10
         },
         {
            "strokeId":10,
            "hole":{
               "holeId":10,
               "holePar":4,
               "holeLength":107,
               "holeNumber":10
            },
            "score":3
         },
         {
            "strokeId":11,
            "hole":{
               "holeId":11,
               "holePar":4,
               "holeLength":47,
               "holeNumber":11
            },
            "score":5
         },
         {
            "strokeId":12,
            "hole":{
               "holeId":12,
               "holePar":4,
               "holeLength":93,
               "holeNumber":12
            },
            "score":3
         },
         {
            "strokeId":13,
            "hole":{
               "holeId":13,
               "holePar":4,
               "holeLength":38,
               "holeNumber":13
            },
            "score":6
         },
         {
            "strokeId":14,
            "hole":{
               "holeId":14,
               "holePar":3,
               "holeLength":38,
               "holeNumber":14
            },
            "score":6
         },
         {
            "strokeId":15,
            "hole":{
               "holeId":15,
               "holePar":2,
               "holeLength":111,
               "holeNumber":15
            },
            "score":9
         },
         {
            "strokeId":16,
            "hole":{
               "holeId":16,
               "holePar":4,
               "holeLength":56,
               "holeNumber":16
            },
            "score":3
         },
         {
            "strokeId":17,
            "hole":{
               "holeId":17,
               "holePar":4,
               "holeLength":30,
               "holeNumber":17
            },
            "score":10
         },
         {
            "strokeId":18,
            "hole":{
               "holeId":18,
               "holePar":2,
               "holeLength":44,
               "holeNumber":18
            },
            "score":3
         },
         {
            "strokeId":19,
            "hole":{
               "holeId":19,
               "holePar":2,
               "holeLength":40,
               "holeNumber":19
            },
            "score":3
         },
         {
            "strokeId":20,
            "hole":{
               "holeId":20,
               "holePar":4,
               "holeLength":89,
               "holeNumber":20
            },
            "score":1
         }
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
         {
            "holeId":3,
            "holePar":2,
            "holeLength":48,
            "holeNumber":3
         },
         {
            "holeId":4,
            "holePar":4,
            "holeLength":110,
            "holeNumber":4
         },
         {
            "holeId":5,
            "holePar":2,
            "holeLength":72,
            "holeNumber":5
         },
         {
            "holeId":6,
            "holePar":3,
            "holeLength":74,
            "holeNumber":6
         },
         {
            "holeId":7,
            "holePar":3,
            "holeLength":120,
            "holeNumber":7
         },
         {
            "holeId":8,
            "holePar":4,
            "holeLength":73,
            "holeNumber":8
         },
         {
            "holeId":9,
            "holePar":4,
            "holeLength":61,
            "holeNumber":9
         },
         {
            "holeId":10,
            "holePar":4,
            "holeLength":107,
            "holeNumber":10
         },
         {
            "holeId":11,
            "holePar":4,
            "holeLength":47,
            "holeNumber":11
         },
         {
            "holeId":12,
            "holePar":4,
            "holeLength":93,
            "holeNumber":12
         },
         {
            "holeId":13,
            "holePar":4,
            "holeLength":38,
            "holeNumber":13
         },
         {
            "holeId":14,
            "holePar":3,
            "holeLength":38,
            "holeNumber":14
         },
         {
            "holeId":15,
            "holePar":2,
            "holeLength":111,
            "holeNumber":15
         },
         {
            "holeId":16,
            "holePar":4,
            "holeLength":56,
            "holeNumber":16
         },
         {
            "holeId":17,
            "holePar":4,
            "holeLength":30,
            "holeNumber":17
         },
         {
            "holeId":18,
            "holePar":2,
            "holeLength":44,
            "holeNumber":18
         },
         {
            "holeId":19,
            "holePar":2,
            "holeLength":40,
            "holeNumber":19
         },
         {
            "holeId":20,
            "holePar":4,
            "holeLength":89,
            "holeNumber":20
         }
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
      {
         "holeId":2,
         "holePar":3,
         "holeLength":109,
         "holeNumber":2
      },
      {
         "holeId":3,
         "holePar":2,
         "holeLength":109,
         "holeNumber":3
      },
      {
         "holeId":4,
         "holePar":4,
         "holeLength":72,
         "holeNumber":4
      },
      {
         "holeId":5,
         "holePar":3,
         "holeLength":109,
         "holeNumber":5
      },
      {
         "holeId":6,
         "holePar":4,
         "holeLength":113,
         "holeNumber":6
      },
      {
         "holeId":7,
         "holePar":2,
         "holeLength":70,
         "holeNumber":7
      },
      {
         "holeId":8,
         "holePar":3,
         "holeLength":89,
         "holeNumber":8
      },
      {
         "holeId":9,
         "holePar":2,
         "holeLength":61,
         "holeNumber":9
      },
      {
         "holeId":10,
         "holePar":4,
         "holeLength":61,
         "holeNumber":10
      },
      {
         "holeId":11,
         "holePar":3,
         "holeLength":111,
         "holeNumber":11
      },
      {
         "holeId":12,
         "holePar":3,
         "holeLength":37,
         "holeNumber":12
      },
      {
         "holeId":13,
         "holePar":2,
         "holeLength":63,
         "holeNumber":13
      },
      {
         "holeId":14,
         "holePar":2,
         "holeLength":101,
         "holeNumber":14
      },
      {
         "holeId":15,
         "holePar":2,
         "holeLength":36,
         "holeNumber":15
      },
      {
         "holeId":16,
         "holePar":4,
         "holeLength":64,
         "holeNumber":16
      },
      {
         "holeId":17,
         "holePar":4,
         "holeLength":81,
         "holeNumber":17
      },
      {
         "holeId":18,
         "holePar":2,
         "holeLength":80,
         "holeNumber":18
      },
      {
         "holeId":19,
         "holePar":4,
         "holeLength":48,
         "holeNumber":19
      },
      {
         "holeId":20,
         "holePar":3,
         "holeLength":60,
         "holeNumber":20
      }
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
   {
      "holeId":3,
      "holePar":2,
      "holeLength":48,
      "holeNumber":3
   },
   {
      "holeId":4,
      "holePar":4,
      "holeLength":110,
      "holeNumber":4
   },
   {
      "holeId":5,
      "holePar":2,
      "holeLength":72,
      "holeNumber":5
   },
   {
      "holeId":6,
      "holePar":3,
      "holeLength":74,
      "holeNumber":6
   },
   {
      "holeId":7,
      "holePar":3,
      "holeLength":120,
      "holeNumber":7
   },
   {
      "holeId":8,
      "holePar":4,
      "holeLength":73,
      "holeNumber":8
   },
   {
      "holeId":9,
      "holePar":4,
      "holeLength":61,
      "holeNumber":9
   },
   {
      "holeId":10,
      "holePar":4,
      "holeLength":107,
      "holeNumber":10
   },
   {...}
]
```

