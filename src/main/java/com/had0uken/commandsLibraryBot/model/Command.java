package com.had0uken.commandsLibraryBot.model;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="commands_table")
public class Command implements Serializable {


    @Serial
    private static final long serialVersionUID = -7232046360742606110L;
    @Column(name = "technology_id")
    private int technologyID;

    @Id
    @Column(name = "callback_data")
    private String callbackData;

    @Column(name = "command_syntax")
    private String syntax;

    @Column(name = "command_meaning")
    private String meaning;

    @Column(name = "example")
    private String example;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "techology_id")
    private Technology technology;

    public Command() {
    }

    public Command(int technologyID, String callbackData, String syntax, String meaning, String example) {
        this.technologyID = technologyID;
        this.callbackData = callbackData;
        this.syntax = syntax;
        this.meaning = meaning;
        this.example = example;
    }

    public int getTechnologyID() {
        return technologyID;
    }

    public void setTechnologyID(int technologyID) {
        this.technologyID = technologyID;
    }

    public String getCallbackData() {
        return callbackData;
    }

    public void setCallbackData(String callbackData) {
        this.callbackData = callbackData;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    @Override
    public String toString() {
        return "Command{" +
                "technologyID=" + technologyID +
                ", tgCommand='" + callbackData + '\'' +
                ", syntax='" + syntax + '\'' +
                ", meaning='" + meaning + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
