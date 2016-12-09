**Project Report**

**Purpose**

To create an android app that will act as a remote for the lights in my room by using a Raspberry
Pi webserver as an interface.

**Project Features**

Turn on and off lights with the click of a button

Add new lights by simply giving it a name and the on and off signals

Schedule lights to trn on at sundown

**Project Design**

In separate file

**File Structure**

AddLightFragment.java - Tabbed fragment that takes the users input for the name, on, and off signal
for a light and then adds it to the database

DatabaseHandler.java - Handles all interactions with the database including initializing it and
reading, adding, and removing entries

DaylightApiRequest.java - Queries the sunrise-sunset.org api to determine when sunset will be, then
finds out the amount of time there is between now and sunset, and returns that time.

HTTPRequest.java - Sends the light signal through an HTTP request to the Raspberry Pi webserver

Light.java - Constructor for the light object that contains the name, id, on signal, and off signal,
as well as getter and setter functions

LightAdapter.java - Adapts the light object and allows it to be displayed in a list view

LightSchedulerFragment.java - Tabbed fragment that when the button is pressed, calls the
DaylightApiRequest class and then schedules a ScheduledLightSignalService job using the
time received from the API request.

LightSwitchFragment.java - Tabbed fragment for displaying the list of lights

MainActivity.java - Main controller for the app. Initializes the database and initializes the
tabbed layout.

ScheduledLightSignalService.java - Scheduled job that when triggered sends a signal to the
Raspberry Pi

SchedulerJobService.java - Backbone for the job scheduler.
