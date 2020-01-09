package com.moon.squad.service.shared;

import java.util.List;
import java.util.Optional;

public interface CRUDService<T> {
    List<T> findAll();

    Optional<T> findById(String id);

    T saveOrUpdate(T t);

    void deleteUserById(String id);
}
