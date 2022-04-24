package com.mediscreen.assessment.proxies;

import com.mediscreen.assessment.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "notes-microservice", url = "localhost:8082")
public interface PatientHistoryMicroserviceProxy {

    @GetMapping("history/view/{id}")
    List<NoteBean> viewPatientHistory(@PathVariable("id") Long id);

}
