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
Development deployment: https://dev-discgolf.herokuapp.com/swagger-ui/index.html

Production deployment: https://disc-golf-database.herokuapp.com/swagger-ui/index.html

## Deployments
Development deployment: https://dev-discgolf.herokuapp.com/

Production deployment: https://disc-golf-database.herokuapp.com/

## Mobile-App
Link to mobile-app repository is [here](https://github.com/Ohjelmistoprojekti-II-Frisbeegolf/Mobile-app)
