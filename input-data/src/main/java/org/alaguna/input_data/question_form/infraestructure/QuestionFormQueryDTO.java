package org.alaguna.input_data.question_form.infraestructure;

public class QuestionFormQueryDTO {

    private String id;
    private String type;
    private Boolean active;

    public QuestionFormQueryDTO(String id, String type, Boolean active) {
        this.id = id;
        this.type = type;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Boolean getActive() {
        return active;
    }
}
