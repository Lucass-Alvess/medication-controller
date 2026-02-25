package com.lucasdev.medication.dto;

public class FieldMessage {

    private String fieldName;
    private String message;

    public FieldMessage(String fieldMessage, String message) {
        this.fieldName= fieldMessage;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
