# ChuckNorris3
Chuck Norris jokes

Basic App that shows a simple application that will interact with
The Internet Chuck Norris Database API (http://www.icndb.com/api/).

the starting screen:
1) Random joke
2) Text input
3) Never ending list

Random Joke
When the ‘Random Joke’ button is pressed the app should request a
single random joke from the server and then display it in a Dialog with a
dismiss button.

Text input
When the ‘Text input’ button is pressed the app should open a new
screen (can be a new Activity or a Fragment – your decision) with a single
Edittext and a ‘Submit’ Button.
Upon pressing the ‘Submit’ Button, the app should request a random joke
with a custom main character as described in the Changing the name of
the main character section of the API docs and show it in a Dialog.
App should handle the input validation and also first name / last name
splitting.

Never-ending list
When the ‘Never-ending list button is pressed the app should open a new
screen (can be a new Activity or a Fragment – your decision) that
contains a list of random jokes. Jokes should be requested
asynchronously, in batches of 20, from the server. When the user scrolls
to the bottom of the list, the list should show a loading footer to indicate
that more items are being requested.

Work in Progress. The project is unfinished, but , here is a brief description of the structure.
Main Folder structure:
activites: contains all activities 
adapters: contains the recycler view adpater 
apis: contains our retrofit service interface definition
components: contains the DI component interfaces 
interceptors: where we subscribe the the observable 
models: contains our POJOs 
modules: contains the DI module classes 
paginators: contains paginator class for pagination
presenters: the presenters of MVP 
repository: using the repository pattern, to separate the data layer
utils: contains utils , like extension functions
views: the view interfaces we present to the presenter

Unit Tests:

folder structures:

presenters: contains all the unit test for the presenters
rules: custom rules, used for testing observables
utils: utils , like predicates

1. Using MVP structure

2. The MainViews defines the interface that provides the functionality for this app. We can use this to guide the TDD. So, first, we create a test called, testReadFile, which fails. We then write the code for it until it passes and then refactor the code, as required.

3. I have used interfaces ( e.g. for the activity , we present instead, to the presenter, a MainViews interface), since, this is more fully testable, easily extensible, complies with best object-oriented practices ( SOLID ). Explanation: If we pass an instance of the activity through to the presenter, then the presenter then knows about the activity, then if the activity is complicated, and if we write a test for the presenter, then we have to write a mock version of the activity, which is difficult to do, so, we give the presenter an interface, then the presenter doesn’t know who implements the interface, and doesn’t care , and the test doesn’t care either, but the test can control and examine the view, to check if certain things have passed/failed. 

4. This architecture facilitates TDD. Why because in TDD we follow 3 steps, 1) Write a failing test.2) write code to pass that test, 3) refactor. Since, we use interfaces, which is our contract, the interfaces don’t change. The contract , of how to use the code doesn’t change. Hence, it won’t break the tests, when we refactor the code. The internals of the code is hidden from the tests. 

6. We inject dependencies into the Activity, etc. Dependency Injection is a form of Dependency Inversion ( SOLID ). 

Please note, this project is currently incomplete, it is missing the Dependency Injection using Dagger 2 and the Instrumentation Tests using Espresso and also some of the unit tests are broken.
