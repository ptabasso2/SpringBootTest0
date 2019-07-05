1. Add your api key in the apikey.env file

2. Create a custom bridged network using the following command:
docker network create netw1

3. Run the following docker-compose command
docker-compose up -d

4. Run some load test using a browser or a curl command from the host or even within the springtest0 container:
curl localhost:9393/ServiceC

5. At this point you should be able to see some traces in the UI: APM >> Traces list
