# User Story Description
As a client user, I want to retrieve a set of food trucks (by facility type) from the San Francisco's food truck dataset.

# Technical Design
Data Repository layer
1. Instantiate mobile food facility data as DAO: MobileFoodFacility.java
2. For scalability, create an interface IDataAccessable. URLDataRepository.java is the first implementation class. 
3. The performance of URLDataRepository.getDate() is not good enough, so use the proxy mode to increase the proxy layer. 
   And cache the data into concurrentHash to improve the performance. The cache is going to refresh every 30 seconds.
4. In order to expand data update in the future, make the URLDataRepository as singleton mode.

MVC
1. Add MobileFoodFacilityServiceImpl.java which calls URLDataRepositoryProxy.getDate(), input parameter is FacilityType.
   The data retrieved from the back-end will be filtered by FacilityType.
2. Add MobileFoodFacilityController.java which calls MobileFoodFacilityServiceImpl. 
3. Add html as a mock up web page which request map to MobileFoodFacilityController.java.

# Test Case Design
1. Verify the main page http://localhost:8088/ works as expect (test case 1 in MobileFoodFacilityControllerTest.java)
2. Verify the "Facility Type Filter" function works as expect (test case 2 in MobileFoodFacilityControllerTest.java)

# TODO list
1. Now the solution in URLDataRepository.getData() is going to take out all the data at one time, then filter it, which is only suitable for situations where the amount of background data is small. A better is to assemble the URL as "query=selectXXXXXXX". But it requires to implement a util class to Decode and Encode URL.
2. If there are many forms of data sources in the future, not just URL, like csv, xml or physical database, we could consider using the factory model for further packaging.
3. Use Guava to replace the manual cache in URLDataRepositoryProxy
4. Import lombok package to reduce the Java code
5. Refine code, use private methods to reduce code complexity
6. Extended test cases, such as special characters, performance tests, etc. Choose some auxiliary tools, such as JMeter and Postman.
7. As a mature production, automation testscript is mandatory in future. Consider using Selenium. 