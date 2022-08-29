package com.mediscreen.assessment.data;

import java.util.List;

public class DiabetesTriggers {

    public static List<String> getTriggers() {
        return List.of("hémoglobine A1C",
                "microalbumine",
                "taille",
                "poids",
                "fumeur",
                "anormal",
                "cholestérol",
                "vertige",
                "rechute",
                "réaction",
                "anticorps"
        );
    }
}
