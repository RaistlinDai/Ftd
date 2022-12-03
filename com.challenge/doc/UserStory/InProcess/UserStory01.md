# User Story Description
As a client user, I want to retrieve a set of food truccks from the San Francisco's food truck dataset.

# Technical Design
Data Repository layer
1. Instantiate mobile food facility data as DAO: MobileFoodFacility.java
2. For scalability, create an interface IDataAccessable. URLDataRepository.java is the first implementation class. 

MVC
1. Add MobileFoodFacilityServiceImpl.java which calls URLDataRepository.getDate(), input parameter is FacilityType
   The data retrieved from the back-end will be filtered. 
   (NOTE:　This solution will take out all the data at one time and then filter it, which is only suitable for situations where the amount of background data is small. Need to optimize in the future.)
2. Add MobileFoodFacilityController.java which calls MobileFoodFacilityServiceImpl. 