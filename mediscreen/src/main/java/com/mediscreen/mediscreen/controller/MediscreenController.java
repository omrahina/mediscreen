package com.mediscreen.mediscreen.controller;

import com.mediscreen.mediscreen.beans.PatientBean;
import com.mediscreen.mediscreen.proxies.PatientMicroserviceProxy;
import feign.FeignException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MediscreenController {

    private final PatientMicroserviceProxy patientProxy;

    public MediscreenController(PatientMicroserviceProxy patientProxy) {
        this.patientProxy = patientProxy;
    }

    @GetMapping("/patients")
    public String home(Model model, @RequestParam(name = "error", required = false) String error) {
        try {
            model.addAttribute("patients", patientProxy.patientList());
            model.addAttribute("patient", new PatientBean());
        } catch (FeignException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "home";
    }
}
