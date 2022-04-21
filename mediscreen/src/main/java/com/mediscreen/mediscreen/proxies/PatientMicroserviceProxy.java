package com.mediscreen.mediscreen.proxies;

import com.mediscreen.mediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "patient-microservice", url = "localhost:8081")
public interface PatientMicroserviceProxy {

    @GetMapping("/patients")
    List<PatientBean> patientList();

}
