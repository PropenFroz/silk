# Steps to execute the application

1. `mvn clean install`
2. `java -jar target/spring-react-1.0.jar`
3. Open a browser and type this url [http://localhost:8080](http://localhost:8080)

**Note:** You might get a warning in pom.xml saying

```
Plugin execution not covered by lifecycle configuration: org.apache.maven.plugins:maven-antrun-plugin:1.8:run (execution: default, phase: generate-resources)
```

So just ignore it or if you can fix it then you can.

To solve the above warning if you put all the plugins inside the `<pluginManagement>...</pluginManagement>` then `java -jar target/spring-react-1.0.jar` will not work. It will raise an error like `no main manifest attribute, in spring-react-1.0.jar`.
