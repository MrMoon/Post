package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Result;
import com.moon.squad.repository.shared.CRUDRepository;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

public interface ResultRepository extends CRUDRepository<Result> {
    @NotNull List<Result> findAllByOrderByDate();
    List<Result> findAllByDate(Date date);
}
