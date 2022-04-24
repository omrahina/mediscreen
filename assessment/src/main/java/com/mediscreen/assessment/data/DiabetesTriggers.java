package com.mediscreen.assessment.data;

import java.util.List;

public class DiabetesTriggers {

    public static List<String> getTriggers() {
        return List.of("hemoglobin A1C",
                "microalbumine",
                "height",
                "weight",
                "smoker",
                "abnormal",
                "cholesterol",
                "dizziness",
                "relapse",
                "reaction",
                "antibody"
        );
    }
}
