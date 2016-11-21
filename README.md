Simple Security Improved
Steven Scheffelaar Klots


Goal
To improve upon the Android app implementation of the Simple Security project that I did during my project sequence last semester.


Features
Able to turn on and off lights from wherever you are by sending http requests to Raspberry Pi
Dynamically populated  List to display on and off buttons as well as the last known status of the lights
Database for keeping track of on and off signals, light names, and last known light status
Activity for adding new signal button
Option to receive broadcast from Android alarm clock and send signal to turn on specified lights

Progress-Report 1

Created the basic UI of the app except for the scheduling page as I need to learn more about how
I can tie my app into the built-in Android alarm. Still need to work on final styling of the pages
and there is a bug on the add light page where the text input bar shoves everything up. The
database for the saved lights still needs to be implemented and the functionality for all the
buttons also needs to be added. The function for sending the signal to the webserver has been
written but will most likely go through a few more iterations to make it more efficient and
consistent.

Java Files Written by Steven Scheffelaar Klots

AddLightFragment.java - Currently only creates the tab and has no real functionality other than
displaying the Add Light tab.

Light.java - Creates the Light object to be used by the LightSwitch page that contains the light
number, the signal for turning the light on, and the signal for turning the light off, as well as
accessor functions.

LightAdapter.java - Adapts an ArrayList of Light objects to be displayed in a list format on a list
page.

LightSwitchFragment.java - Fragment for the lightswitch tab that creates a static light object, for
now until the database is later implemented, and displays the light objects in a list displaying
the light number, as well as an on and an off button that currently do nothing as the functionality
has not been implemented.

MainActivity.java - Controls the main navigation of the app such as the tabs and the settings menu