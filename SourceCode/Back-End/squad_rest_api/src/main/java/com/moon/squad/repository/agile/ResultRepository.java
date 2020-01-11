package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Result;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface ResultRepository extends CRUDRepository<Result> {
    @NotNull List<Result> findAllByOrderByDate();

    List<Result> findAllByDate(Date date);
}
