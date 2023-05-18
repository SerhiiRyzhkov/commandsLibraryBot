package com.had0uken.commandsLibraryBot.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    void delete(User entity);
}
