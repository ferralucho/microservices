package com.ferralucho.microservices.limitsservice;

import com.ferralucho.microservices.limitsservice.bean.LimitsConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

    @Autowired
    private Configuration configuration;


    @GetMapping("/limits")
    public LimitsConfiguration retrieveLimitsFromConfigurations() {
        LimitsConfiguration LimitsConfiguration = new LimitsConfiguration(configuration.getMaximum(),
                configuration.getMinimum());
        return LimitsConfiguration;
    }

    @GetMapping("/fault-tolerance-example")
    @HystrixCommand(fallbackMethod="fallbackRetrieveConfiguration")
    public LimitsConfiguration retrieveConfiguration() {
        throw new RuntimeException("Not available");
    }

    public LimitsConfiguration fallbackRetrieveConfiguration() {
        return new LimitsConfiguration(999, 9);
    }


}
