# PatternConvination
CA1 PatternConvination

This project is a CA for college for the Asignature: Object Orientation with Design Patterns.

The assignment consisted in the construction of a command line interface capable of connecting to a 
database table nammed "country" wich will contain five columns: Code, name, continent, surfaceArea and HeadOfState.

My approach to this problem is to use a Data Access Object class for connecting to a Singletton Database with lazy implementation,
singletton because I want only one instance of the database throught the program scope, this approach will allow me
to have the database instance running still and the DAO will be able to query it without having to create a new connection.

I also used the Builder Pattern for creating objects of the type "Country" that will allow me to create a country with some default values
in case that they were not set.

I organized this project following the MVC design pattern.

## Patterns Learned
* Singletton lazy implementation
* Builder Pattern
* Data Access Object
