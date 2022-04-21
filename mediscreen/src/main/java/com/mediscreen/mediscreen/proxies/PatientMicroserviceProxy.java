package com.mediscreen.mediscreen.proxies;

import com.mediscreen.mediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "patient-microservice", url = "localhost:8081")
public interface PatientMicroserviceProxy {

    @GetMapping("/patients")
    List<PatientBean> patientList();

    @PostMapping("/patient/add")
    PatientBean addPatient(@RequestBody PatientBean patient);

}
