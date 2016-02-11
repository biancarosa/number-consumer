# Number Consumer

Hello :) 
These instructions are mostly for a UNIX-based system, so maybe you will come across a few commands that won't work on Windows. Please let me know if this a problem :)
Any feedback is welcome.

## Compiling

You can use ``mvn package`` straight from the application folder.
It only depends on the JUnit package for testing purposes.
It also runs the test suit.


## Arguments
Please provide the:
1. Number of threads.
2. Host IP of the buffer.
3. Port number of the buffer.

## Running
From application folder:

```
  java -cp target/number-consumer-1.0-SNAPSHOT.jar br.com.biancarosa.consumer.Executor 10 127.0.0.1 8000

```
