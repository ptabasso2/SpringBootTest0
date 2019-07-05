## SpringBootTest0

### 1. Cloning repository
git clone https://github.com/ptabasso2/SpringBootTest0.git

### 2. Build project
./gradlew build

### 3. Run the application
java -jar build/libs/springtest0-1.0.jar --server.port=9393

### 4. Test the application
curl localhost:9393/ServiceC

*This will return ServiceD*
