package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Report;
import com.moon.squad.service.shared.CRUDService;

import java.util.Date;
import java.util.List;

public interface ReportService extends CRUDService<Report> {
    List<Report> findAllByOrderByDate();
    List<Report> findAllByDate(Date date);
}
