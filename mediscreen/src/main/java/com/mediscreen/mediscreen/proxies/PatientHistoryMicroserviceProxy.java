package com.mediscreen.mediscreen.proxies;

import com.mediscreen.mediscreen.beans.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "notes-microservice", url = "localhost:8082")
public interface PatientHistoryMicroserviceProxy {

    @PostMapping("history/add")
    NoteBean addNote(@RequestBody NoteBean note);

    @PutMapping("history/update/{id}")
    NoteBean updateNote(@PathVariable("id") String id, @RequestBody NoteBean note);

    @GetMapping("history/view/{id}")
    List<NoteBean> viewPatientHistory(@PathVariable("id") Long id);

    @GetMapping("/history/view")
    NoteBean getNote(@RequestParam("id") String id);

}
