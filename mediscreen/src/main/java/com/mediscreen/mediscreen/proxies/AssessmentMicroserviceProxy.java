package com.mediscreen.mediscreen.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "risk-assessment-microservice", url = "assessment:8083")
public interface AssessmentMicroserviceProxy {

    @GetMapping("/assess/{id}")
    String assessPatient(@PathVariable("id") long id);

}
