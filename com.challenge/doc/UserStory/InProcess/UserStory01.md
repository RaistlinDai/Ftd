# User Story Description
As a client user, I want to retrieve a set of food truccks from the San Francisco's food truck dataset.

# Technical Design
Data Repository layer
1. Instantiate mobile food facility data as DAO: MobileFoodFacility.java
2. For scalability, create an interface IDataRepository. URLDataRepository is the first implementation class. 

