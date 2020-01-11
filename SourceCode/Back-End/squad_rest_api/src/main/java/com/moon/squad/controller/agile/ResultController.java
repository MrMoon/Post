package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Result;
import com.moon.squad.service.agile.ResultService;
import com.moon.squad.shared.ApplicationConstants;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.RESULT_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;

@RestController
@RequestMapping (RESULT_MAPPING)
@CrossOrigin(origins = LOCALHOST_4200)
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @CrossOrigin (origins = LOCALHOST_4200)
    @GetMapping (value = SLASH)
    public List<Result> getAllResults() {
        return resultService.findAllByOrderByDate();
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (value = ID_MAPPING)
    public @NotNull Optional<Result> getResultById(@PathVariable (ID) String id) {
        return resultService.findById(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @PostMapping (value = SLASH)
    public ResponseEntity<?> saveOrUpdateResult(@RequestBody Result result) {
        resultService.saveOrUpdate(result);
        return new ResponseEntity<>(result.toString() + "\n " + result.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @DeleteMapping (value = ID_MAPPING)
    public ResponseEntity<?> deleteResultById(@PathVariable String id) {
        resultService.deleteUserById(id);
        return new ResponseEntity<>(id + '\n' + ' ' + Result.class.getName() + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }

}
