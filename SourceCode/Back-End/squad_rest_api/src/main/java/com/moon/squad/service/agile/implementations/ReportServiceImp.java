package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Report;
import com.moon.squad.repository.agile.ReportRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ReportService;

import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_REPORT;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_REPORT)
@Slf4j
public class ReportServiceImp implements ReportService {

    private final SequenceRepository sequenceRepository;
    private final ReportRepository reportRepository;

    public ReportServiceImp(ReportRepository reportRepository, SequenceRepository sequenceRepository) {
        this.reportRepository = reportRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Cacheable
    @Override
    public List<Report> findAll() {
        log.info(gettingAll(Report.class, false));
        return reportRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Report> findAllByOrderByDate() {
        log.info(gettingAll(Report.class, true) + " By Date");
        return reportRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<Report> findAllByDate(@NotNull Date date) {
        log.info("Getting All Reports by Date " + date.toString());
        return reportRepository.findAllByDate(date);
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public @NotNull Optional<Report> findById(String id) {
        log.info(gettingById(Report.class, id));
        return reportRepository.findById(id);
    }

    @CachePut (key = "#report.id")
    @Override
    public Report saveOrUpdate(@NotNull Report report) {
        log.info(saving(report.toString()));
        return reportRepository.save(report);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Report.class, id));
        reportRepository.deleteById(id);
    }
}
