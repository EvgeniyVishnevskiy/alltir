# altir-warehousing

Try it out with Swagger: http://localhost:8080/swagger-ui/

Order Planning Problem

Problem Overview  
 
A  Manufacturing  company  has  various  warehouses  in  multiple  cities.  When  an  order  for  a 
product is placed by their customers, the company wants to make sure the order is picked up 
from the warehouse that is closest to the customers location and containing the product. 
 
Ask 
 
Build a microservice with a REST API based interface to solve the above problem. There should 
be at least two key APIs one for adding of a new customer and the other to place of an order. 
The order placement API in the response should indicate from which warehouse the product 
would be delivered and the time/distance for arrival. The expectation is that the response time 
of order placement is fairly quick (< 500ms).  
 
Assumptions 
 
Assume that the distance/time between the customers city/location and warehouse is provided 
when customer is setup. The system would compute the using the data that is provided during 
setup. The format of the data can be anything as per the solution. 
  
Assume a certain number of Warehouses & products to be present. Assume any structure for 
the data. Preferably represent that in JSON format as static data (or stored in db) that can be 
used for computation.  
 
 
Technology requirements 
 
• Build the solution using Java 
• Preferably build the service using Sprint Boot 
• Usage of database is not necessary 
• Submit a brief write up of any key decisions being made 
• Bonus points for writing unit test cases (key scenarios) 
