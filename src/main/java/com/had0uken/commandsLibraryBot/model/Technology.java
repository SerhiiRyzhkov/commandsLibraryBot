package com.had0uken.commandsLibraryBot.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="technologies_table")
public class Technology implements Serializable {

    @Serial
    private static final long serialVersionUID = -1184637538085422263L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;

    @Column(name = "technology")
    private String technology;

    @Column(name = "tg_command")
    private String tgCommand;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "technology")
    private List<Command> commandList;

    public Technology() {
    }

    public Technology(String technology, String tgCommand) {
        this.technology = technology;
        this.tgCommand = tgCommand;
    }

    public void addCommand(Command command){
        if(commandList==null)
            commandList=new ArrayList<>();
        else commandList.add(command);
        command.setTechnology(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTgCommand() {
        return tgCommand;
    }

    public void setTgCommand(String tgCommand) {
        this.tgCommand = tgCommand;
    }

    @Override
    public String toString() {
        return "Technology{" +
                "id=" + id +
                ", technology='" + technology + '\'' +
                ", tgCommand='" + tgCommand + '\'' +
                '}';
    }
}
