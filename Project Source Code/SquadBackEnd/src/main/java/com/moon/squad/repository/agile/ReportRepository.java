package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Report;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends CRUDRepository<Report> {
    List<Report> findAllByOrderByDate();
    List<Report> findAllByDate(Date date);
}
