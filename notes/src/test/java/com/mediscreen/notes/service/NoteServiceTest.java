package com.mediscreen.notes.service;

import com.mediscreen.notes.exceptions.NoteException;
import com.mediscreen.notes.model.Note;
import com.mediscreen.notes.repository.NoteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    public void should_addNote_ok() {
        Note note = new Note();
        when(noteRepository.insert(any(Note.class))).thenReturn(note);

        Note savedNote = noteService.addNote(note);

        assertEquals(note, savedNote);
        verify(noteRepository).insert(note);
    }

    @Test
    public void should_updateNote_ok() throws NoteException {
        Note existingNote = initNote("test note");
        Note note = initNote("update test note");
        when(noteRepository.existsById(anyString())).thenReturn(true);
        when(noteRepository.findNoteById(anyString())).thenReturn(existingNote);
        when(noteRepository.save(any(Note.class))).thenReturn(note);

        Note updatedNote = noteService.updateNote(note);

        assertEquals(note.getNote(), updatedNote.getNote());
        verify(noteRepository).existsById("id");
        verify(noteRepository).findNoteById("id");
    }

    @Test
    public void should_updateNote_ko_doesn_exist() {
        Note note = initNote("test note");
        when(noteRepository.existsById(anyString())).thenReturn(false);

        assertThrows(NoteException.class, () -> noteService.updateNote(note),
                "No such note in patient's history");
        verify(noteRepository).existsById("id");
    }

    @Test
    public void should_getPatientHistory_ok() throws NoteException {
        when(noteRepository.findNotesByPatientId(anyLong())).thenReturn(List.of(initNote("note")));

        List<Note> history = noteService.getPatientHistory(1L);

        assertEquals(1, history.size());
        verify(noteRepository).findNotesByPatientId(1L);
    }

    @Test
    public void should_getPatientHistory_no_content(){
        when(noteRepository.findNotesByPatientId(anyLong())).thenReturn(Collections.emptyList());

        assertThrows(NoteException.class, () -> noteService.getPatientHistory(2L),
                "Patient history empty");
        verify(noteRepository).findNotesByPatientId(2L);
    }

    @Test
    public void should_findNoteById_ok() throws NoteException {
        when(noteRepository.existsById(anyString())).thenReturn(true);
        when(noteRepository.findNoteById(anyString())).thenReturn(initNote("note"));

        Note result = noteService.findNoteById("id");

        assertNotNull(result);
        verify(noteRepository).existsById("id");
        verify(noteRepository).findNoteById("id");
    }

    @Test
    public void should_findNoteById_ko_doesnt_exist() {
        when(noteRepository.existsById(anyString())).thenReturn(false);

        assertThrows(NoteException.class, () -> noteService.findNoteById("id"),
                "No such note in patient's history");
        verify(noteRepository).existsById("id");
    }

    private Note initNote(String content) {
        Note note = new Note();
        note.setId("id");
        note.setNote(content);
        return note;
    }

}