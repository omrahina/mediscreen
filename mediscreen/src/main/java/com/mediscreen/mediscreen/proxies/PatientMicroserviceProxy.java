package com.mediscreen.mediscreen.proxies;

import com.mediscreen.mediscreen.beans.PatientBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "patient-microservice", url = "patient:8081")
public interface PatientMicroserviceProxy {

    @GetMapping("/patients")
    List<PatientBean> patientList();

    @PostMapping("/patient/add")
    PatientBean addPatient(@RequestBody PatientBean patient);

    @PutMapping("patient/update/{id}")
    PatientBean updatePatient(@PathVariable("id") long id, @RequestBody PatientBean patient);

    @GetMapping("/patient/{id}")
    PatientBean getPatient(@PathVariable("id") long id);

}
