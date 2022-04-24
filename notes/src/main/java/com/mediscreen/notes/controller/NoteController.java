package com.mediscreen.notes.controller;

import com.mediscreen.notes.exceptions.NoteException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@Slf4j
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("history/add")
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        log.info("Request add a new note");
        Note savedNote = noteService.addNote(note);
        if (note != null) {
            return new ResponseEntity<>(savedNote, HttpStatus.CREATED);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't add the note to patient history");
    }

    @PutMapping("history/update/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") String id, @RequestBody Note note) {
        log.info("Request update a note");
        note.setId(id);
        try {
            return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
        } catch (NoteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        }
    }

    @GetMapping("history/view/{id}")
    public ResponseEntity<List<Note>> viewPatientHistory(@PathVariable("id") Long id) {
        log.info("Request patient history");
        try {
            return new ResponseEntity<>(noteService.getPatientHistory(id), HttpStatus.OK);
        } catch (NoteException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, e.getMessage(), e);
        }
    }
}
