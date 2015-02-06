# apps
prototypes
The application is still being built. Different parts of the application have been built and need to be integrated.
The MyLastNight folder has the main UI and database files.
The backend files have all of the backend work for the app (explained below).
The maps part has the version of the app which has google maps implemented and fully functional in it.

Frontend:
  The app has a basic clean UI to help you easily navigate through it when you may be inebriated. You can set up your home base,
  count your drinks, measure you alcohol level, and hence have a safe party. The app also tells you how to get back to the base 
  you have set before the party, so that you dont wake up at an unexpected place.
  
Backend:
  The backend has been designed to keep your motion and activity in consideration while getting back you safely to your home.
  The main function eplained in short is; if the person falls down, or has sudden jerky motions, the app would begin its timer 
  for a predefined amount of time (which can be 15 minutes). This is done by using the sensors in your smartphone. The timer
  after the set time period would ring an alarm, and ask if you are in a position to get back (or check wherther you can shut 
  of the alarm and hence the timer), if not, then it would send a message with your loacation coordinates to a set of preset 
  contacts, so that they may come and help you. We would try to integrate some basic math problems while checking the sobreirity
  levels and display approproate safety messages.

Maps:
  This would help you setyour home location, and also help you get back safely from your location to the base you have set up 
  just on the press of a button. It handles your location carefully and uses the google maps app to show you way home.
  Future work would include geofencing to automatically shut the app down once you are in a preset radius of you set base so 
  that the user doesn't have to turn the app off personally.
  
The app has some work to be done. We are planning to introduce new features like geofencing to the app, and also integrate it
with safewalks (a safety organization at UIUC) so that people may get help if none of their preset contacts are able to help
them. We look forward on making the app a huge success among college students and also spreading to other universites(and thier
) safety services once the app proves beneficial at UIUC
