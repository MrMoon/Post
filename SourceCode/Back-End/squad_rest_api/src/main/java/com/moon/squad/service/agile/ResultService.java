package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Result;
import com.moon.squad.service.shared.CRUDService;

import java.util.Date;
import java.util.List;

public interface ResultService extends CRUDService<Result> {
    List<Result> findAllByOrderByDate();
    List<Result> findAllByDate(Date date);
}
