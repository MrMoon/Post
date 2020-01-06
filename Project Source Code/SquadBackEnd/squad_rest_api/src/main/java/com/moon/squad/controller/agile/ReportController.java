package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Report;
import com.moon.squad.service.agile.ReportService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.REPORT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;

@RestController
@RequestMapping (REPORT_MAPPING)
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping (SLASH)
    public List<Report> getAllReports() {
        return reportService.findAllByOrderByDate();
    }

    @GetMapping (ID_MAPPING)
    public @NotNull Optional<Report> getReportById(@PathVariable (ID) String id) {
        return reportService.findById(id);
    }

    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateReport(@RequestBody Report report) {
        reportService.saveOrUpdate(report);
        return new ResponseEntity<>(report.toString() + "\n " + Report.class.getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteReportById(@PathVariable String id) {
        reportService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Report.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
