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
