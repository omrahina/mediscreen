package com.mediscreen.assessment.proxies;

import com.mediscreen.assessment.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "patient-microservice", url = "localhost:8081")
public interface PatientMicroserviceProxy {

    @GetMapping("/patient/{id}")
    PatientBean getPatient(@PathVariable("id") long id);

}
