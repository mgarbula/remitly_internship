# Remitly Internship Task
I connect with current table of rates.  
To read JSON from NBP API and then parse it to my Rate object I use Gson library.  
In tests, I use some old rates.  
By using this app you can calculate how much money in PLN someone will receive when you will send them money in chosen currency.
You can also see how much money in chosen currency you have to send to someone who want to get some amount of money in PLN.

To run project you have to be in remitly_internship folder and then:  
mvn package  
mvn exec:java -Dexec.mainClass="Main"  

To run tests:  
mvn test

