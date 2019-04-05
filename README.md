## Sample web application

This is a sample web application from a Spring Boot project using Reactive web to handle a request.

The business logic is simulated by reversing a String that comes as an HTTP parameter.



## Build

```
./gradlew clean build
```

## Run

```
java -jar build/libs/reactive-web-0.0.1-SNAPSHOT.jar
```

Open:
[http://localhost:8080/hello?name=Robert](http://localhost:8080/hello?name=Robert)

## Apply load
Install [wrk](https://github.com/giltene/wrk2).

```
wrk -t2 -c100 -d30s -R2000 --latency -s add_random_alpha.lua http://localhost:8080/hello\?name\=
```

-t -- thread count
-c -- concurrent connections
-d -- duration -- 30s
-R -- request rate, 2000 per second
--latency  -- print latency histogram
-s -- customization script -- adds random string to the end of url

### Experiment

Now you're ready to try different runtimes, run the app on them, apply load. Make sure you warm up the code sufficiently.
Try with different `-R` request rates. Try to find one that satisfies some sort of latency SLA, say 99 percentile of latency is less than 1s.
How many concurrent requests can you make to fit under those requirements?


Warm up -- run your application and apply load for a few runs of wrk.
Measure -- run wrk again -- record the results


