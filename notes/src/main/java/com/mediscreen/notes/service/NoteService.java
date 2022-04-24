package com.mediscreen.notes.service;

import com.mediscreen.notes.exceptions.NoteException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.repository.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note addNote(Note note) {
        return noteRepository.insert(note);
    }

    public Note updateNote(Note note) throws NoteException {
        if (noteRepository.existsById(note.getId())) {
            Note existingNote = noteRepository.findNoteById(note.getId());
            existingNote.setNote(note.getNote());
            return noteRepository.save(existingNote);
        }
        log.error("Note not found");
        throw new NoteException("No such note in patient's history");
    }

    public List<Note> getPatientHistory(Long id) throws NoteException {
        List<Note> history = noteRepository.findNotesByPatientId(id);
        if(!CollectionUtils.isEmpty(history)) {
            return history;
        }
        log.debug("History empty");
        throw new NoteException("Patient history empty");
    }

    public Note findNoteById(String id) throws NoteException {
        if (noteRepository.existsById(id)) {
            return noteRepository.findNoteById(id);
        }
        log.error("Note not found");
        throw new NoteException("No such note in patient's history");
    }
}
