package hello;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@RestController
public class GreetingController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    HttpServletRequest request;

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Value("#{environment['sleeptime'] ?: '2000'}")
    private long sleepTime;


    @RequestMapping("/ServiceC")
    public String serviceC() throws InterruptedException {


        //tracer = GlobalTracer.get();

        // Hashmap containing Header key/val
        Map<String, String> map = new HashMap<>();
        String rs;

        //build HttpHeader


        doSomeStuff("Hello");
        Thread.sleep(sleepTime);
        doSomeOtherStuff("World!");
        logger.info("In Service C ***************");


        HttpHeaders header = new HttpHeaders();
        header.setAll(map);
        rs = restTemplate.postForEntity("http://localhost:9393/ServiceD", new HttpEntity(header), String.class).getBody();
        Thread.sleep(600L);

        return rs;

    }


    @RequestMapping("/ServiceD")
    public String serviceD() throws InterruptedException {

        Enumeration<String> e = request.getHeaderNames();
        Map<String, String> spanMap = new HashMap<>();

        while (e.hasMoreElements()) {
            // add the names of the request headers into the spanMap
            String key = e.nextElement();
            String value = request.getHeader(key);
            spanMap.put(key, value);
        }

        Thread.sleep(1900L);
        logger.info("In Service D ***************");

        return "Service D\n";
    }

    /*0*/

    private String doSomeStuff(String somestring) throws InterruptedException {
        String helloStr = String.format("Hello, %s!", somestring);
        Thread.sleep(sleepTime);
        logger.info("In doSomeStuff()");
        return helloStr;
    }


    private void doSomeOtherStuff(String somestring) throws InterruptedException {
        System.out.println(somestring);
        Thread.sleep(sleepTime);
        logger.info("In doSomeOtherStuff()");
    }


}
