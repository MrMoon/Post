package com.moon.squad.repository.shared;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CRUDRepository<T> extends MongoRepository<T, String>, Repository<T, String> {
    @NotNull List<T> findAll();

    @NotNull Optional<T> findById(@NotNull String id);

    long count();
}
