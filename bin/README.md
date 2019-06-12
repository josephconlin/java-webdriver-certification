# Managing WebDriver Browser Driver Binaries

## Overview
Selenium sends standardized commands that need to be interpreted correctly by each browser. To accomplish this, each
browser manufacturer provides a binary / executable to receive WebDriver commands and "drive" the browser. Examples
include chromedriver to control Chrome and geckodriver to control Firefox. These binaries are specific to the operating
system they are intended to run on as well as the version of the browser they are created to drive. Thus, as the browser
on a system gets updated, the driver for that browser and system also needs to be updated.

It is beyond the scope of this project to include binaries for multiple browsers (and multiple browser versions) on
multiple operating systems. For this reason, it is expected that the user of this project will download the desired
binaries for their specific operating system and browser version.

After downloading / extracting the binaries, a small amount of configuration is necessary to allow Selenium to use the
binaries. Selenium provides for a few different configuration ways to locate these binaries, two of which are discussed
below. 

## Preferred Method
Operating systems have the concept of an environment variable called PATH. This environment variable tells the system
which folders to look in when trying to find an executable file that has been called by name only, without any folder
path information.

**To use this method, either copy the binaries into a folder that is already part of the PATH environment variable or add
the folder that contains your downloaded binaries to the PATH environment variable.**

## Secondary Method
Java allows system properties to be set, and Selenium Java has created system properties that can be set to specify the
file path and file name of the various binaries.

**To use this method, uncomment and edit the appropriate lines in pom.xml (and
src/test/java/certification/challenge1/Challenge1.java if desired, though the setting in pom.xml will already be
applied) to contain the file path _and file name_ of the binaries.**
