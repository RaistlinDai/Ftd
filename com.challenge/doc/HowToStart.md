# How to run the project in Eclipse
1. Create a new workspace, improt the project "com.challenge" as an existing Maven project
2. Make sure the project runs in JDK1.8 environment
   - Open Window -> Preferences -> Java -> Installed JRES, add JDK1.8 and select it as the default selection
   - Open Run -> Run Configurations, select "JRE" tab, and choose Runtime JRE as "Alternate JRE -> 1.8"
3. Right click on the project "com.challenge" -> Run As -> Maven install, check if anything broken, the expected result is "BUILD SUCCESS"
4. To run the project, select the project, and Open Run -> Run As -> Java Application
5. After the embedded tomcat startup, you could access to http://http://localhost:8088/
   (NOTE: the port number is 8088 but not 8080, this is to prevent conflicts caused by other local services sharing 8080.
6. If everything goes well, you could get the main page with a text box, a button as "Search" and a blank grid.
7. You could try to input something in the text box, for example "Truck" and hit on the search button. After a while the set of trucks would be listed.