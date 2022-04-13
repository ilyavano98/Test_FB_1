package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

//import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
//import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestJson {
    private final String theme;
    private final Long id;
    private final Map<Long,QuestionJson> listQuestion;

    @JsonCreator
    public RequestJson(String theme, Map<Long,QuestionJson> listQuestion, Long id) {
        this.theme = theme;
        this.listQuestion = listQuestion;
        this.id = id;
    }

    public static class QuestionJson {
        private final String txtQuestion;
        private final String selType;
        private final String variant;

        @JsonCreator
        public QuestionJson(String txtQuestion, String selType, String variant) {
            this.txtQuestion = txtQuestion;
            this.selType = selType;
            this.variant = variant;
        }

        public String getTxtQuestion() {
            return txtQuestion;
        }

        public String getSelType() {
            return selType;
        }

        public String getVariant() {
            return variant;
        }

    }

}
