## SpringBootTest0

### 1. Clone the repository
`git clone https://github.com/ptabasso2/SpringBootTest0.git`

### 2. Build the project
`./gradlew build`

### 3. Run the application
`java -jar build/libs/springtest0-1.0.jar --server.port=9393`

### 4. Test the application
`curl localhost:9393/ServiceC`

*This will return the string **ServiceD***

### 5. Now use the java agent

***1. Make sure the DD Agent is deployed, configured to accept APM traces and started***

[DD Agent Configuration](https://docs.datadoghq.com/tracing/send_traces/)

***2. Instrument the application using the java agent (dd-java-agent.jar)***

`java -javaagent:./dd-java-agent.jar -Ddd.agent.host=<host where the DD agent runs> -Ddd.agent.port=8126 -Ddd.trace.methods=hello.GreetingController[doSomeStuff,doSomeOtherStuff] -Ddd.service.name=SpringBootTest0 -jar springtest0-1.0.jar --server.port=9393`
  
For more details https://docs.datadoghq.com/tracing/setup/java/

***3. Result in the UI***

![Results in the UI](https://github.com/ptabasso2/SpringBootTest0/blob/master/img/image.png)
