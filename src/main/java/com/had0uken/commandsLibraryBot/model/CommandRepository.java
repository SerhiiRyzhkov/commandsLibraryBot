package com.had0uken.commandsLibraryBot.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommandRepository extends CrudRepository<Command,String> {
    List<Command> findByTechnologyID(int id);
    Command findByCallbackData(String callbackData);
}
