# Fraud Detection using Flink

## Pre-requisites
1. Java 8 or higher. (This project was compiled and executed using Java 21)
2. Apache Maven (This project was compiled using Maven 3.9.9)

> Important note: This project uses lombok to skip over a lot of vanilla Java code and have them generated during compile time using annotations. Please ensure to have annotation processing enabled when running in any IDE. 

## How to run
1. Clone the repository
2. Allow the IDE to read and compile the project. Once done, open the `DetailedFraudDetectionJob`, and run it as a Java application.
   > The run config are available in the run folder. When running in IntelliJ, it automatically recognizes the run configurations.

## Important Note
This project also contains the code for the walk-through file. Should you want to run that, run the `FraudDetectionJob` instead.
However, you will have to updated the `log4j.properties` file to have the correct alert sink.

## Randomized properties
This code contains two sets of data. One set is statically typed to ensure that there are fraudulent transactions in the source.
The other set of data is randomly generated at runtime with randomized parameters for the account ID, amount value, and the zip code.  

### Running with a fixed data
To run the project with a fixed set of data, set the `isRandom` variable in the `DetailedFraudDetectionJob` to `false`.
This will ensure that the source only produces the data from the statically timed set.

### Running with randomized data
To run the project with a randomized set of data, set the `isRandom` variable in the `DetailedFraudDetectionJob` to `true`.
This will ensure that the source produces data with random properties at runtime.