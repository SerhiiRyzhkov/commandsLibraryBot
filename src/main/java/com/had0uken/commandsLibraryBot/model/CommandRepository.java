package com.had0uken.commandsLibraryBot.model;

import org.springframework.data.repository.CrudRepository;

public interface CommandRepository extends CrudRepository<Command,String> {
}
