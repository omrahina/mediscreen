package com.mediscreen.notes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    private String id;

    @Field(value = "patient_id")
    private Long patientId;

    @Field(value = "full_name")
    private String fullName;

    @Field(value = "note")
    private String note;
}
