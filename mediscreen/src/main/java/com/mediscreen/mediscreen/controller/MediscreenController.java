package com.mediscreen.mediscreen.controller;

import com.mediscreen.mediscreen.beans.PatientBean;
import com.mediscreen.mediscreen.proxies.PatientMicroserviceProxy;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@Slf4j
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
            error = e.getMessage();
        }
        if (error != null){
            model.addAttribute("error", error);
        }
        return "home";
    }

    @GetMapping("/patient/add")
    public String showAddPatientPage(Model model) {
        model.addAttribute("patient", new PatientBean());
        return "addPatient";
    }

    @PostMapping("/patient/add")
    public String addPatient(@Valid @ModelAttribute("patient") PatientBean patient, BindingResult result, Model model) {
        if (result.hasErrors()) {
            log.error("Error(s) in the form");
            return "addPatient";
        }
        try {
            patientProxy.addPatient(patient);
        } catch (FeignException e) {
            return "redirect:/patients?error=" + e.getMessage();
        }

        return "redirect:/patients";
    }
}
