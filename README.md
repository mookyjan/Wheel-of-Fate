# Wheel-of-Fate

### Structure of the code ###
Simple Android Application written in Kotlin.
This project follows Clean Architecture with MVVM

# Main libraries used

* Data Binding
* Dagger2
* RxJava2
* Retrofit2
* Glide
* Timber
* Android Architecture component Jetpack


# Modules


* `data/` : contains the code to access to the data (repository pattern)
* `domain/` : contains the business logic and the usecases
* `app` : Presentation layer, contains the UI 

this project consist of two screens. on the first screen showing the list of engineer and on clicking the generate shedule button go to the schedule screen.

and for the simplicity of this project many things have been kept simple
like 
* ErrorHandling, 
* Internet connectivity and 
* local db to store data locally (Room). IT is added in develop_db_branch

comments are written with the function that what it will do.

also TODO are given in the area which we can improve more.



