# DogBreeds

An app that displays dog breeds, built using Jetpack Compose and the TheDogApi

## Features 

The app consists in 3 main screens:
  - List screen: Here you can see a list of all breeds with use of pagination. Clicking on the top right icon it will be displayed a bottomsheet where you can change the list display type and chose how you want to filter the breeds alphabetically. There is also a pull to refresh feature. Clicking on an item will open the details of that breed. 
  - Search screen: Here you can search for dog breeds. The requests for searching are made while you type. Clicking on an item will open the details of that breed. 
  - Details screen: Here you can see more info about the breed you selected 
  
The app uses Room to cache locally the downloaded items. So you can see the breeds even if you have no internet connection. 
If there is no internet connection and no breeds stored in the local data base you will be prompted with an error message.

Unit test where covered on List Screen.

## Tech stack

- Kotlin
- Jetpack Compose, toolkit used for building native UI
- Coroutines for performing asynchronous work
- Dagger Hilt for dependency injection 
- Retrofit, used for building REST API clients.
- Coil, for image loading and caching
- Room, for caching 
- Architecture: 
  - MVI Architecture
  
 
## Architecture 


![](https://drive.google.com/uc?export=view&id=16ksMioJ_Fdu_6HkhnLXMsdGKTlwwCoNz)
