package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Report;
import com.moon.squad.repository.agile.ReportRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ReportService;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImp implements ReportService {

    private final SequenceRepository sequenceRepository;
    private final ReportRepository reportRepository;

    public ReportServiceImp(ReportRepository reportRepository, SequenceRepository sequenceRepository) {
        this.reportRepository = reportRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    @Override
    public List<Report> findAllByOrderByDate() {
        return reportRepository.findAllByOrderByDate();
    }

    @Override
    public @NotNull Optional<Report> findById(String id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> findAllByDate(Date date) {
        return reportRepository.findAllByDate(date);
    }

    @Override
    public Report saveOrUpdate(Report report) {
        return reportRepository.save(report);
    }

    @Override
    public void deleteById(String id) {
        reportRepository.deleteById(id);
    }
}
