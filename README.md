# WebProgrammingJava

Project for the Web Development Course @ FMI UNIBUC

Backend mockup for a movie rating website.

Models:
* Studio - studioId, name, location, list of movies
* Actor - actorId, firstName, lastName, budget, profit, list of actors, studioId
* Movie - movieId, name, director, year, length (in minutes), 
* Entry - movieId, watchListId, grade
* WatchList - userId, list of entries, averageGrade
* User - userId, username, email, watchListId

Functionalities:
* show all actors / an actor by id
* post a new entry to an user's watchlist, delete one entry
* post a movie, edit a movie by adding actors
* show all movies / a movie by id
* show all studios / show a list of movies produces by a specific studio, sorted by the profit
* show all users / an user by id
* show the watchlist of a specific user
