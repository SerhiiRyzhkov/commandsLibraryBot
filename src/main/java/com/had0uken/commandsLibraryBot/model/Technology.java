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
    @Column(name = "technology_id")
    private int id;

    @Column(name = "technology")
    private String technology;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "technology")
    private List<Command> commandList;

    public Technology() {
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public Technology(String technology) {
        this.technology = technology;
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



    @Override
    public String toString() {
        return "Technology{" +
                "id=" + id +
                ", technology='" + technology + '\'' +
                '}';
    }
}
