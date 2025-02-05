package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Evaluation;
import com.moon.squad.service.agile.EvaluationService;
import com.moon.squad.shared.exception.RoleException;

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
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.EVALUATION_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;

@RestController
@RequestMapping (EVALUATION_MAPPING)
@CrossOrigin (origins = LOCALHOST_4200)
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (SLASH)
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.findAllByOrdOrderByResults();
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (value = ID_MAPPING)
    public Optional<Evaluation> getEvaluationById(@PathVariable (ID) String id) {
        return evaluationService.findById(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateEvaluation(@RequestBody Evaluation evaluation) throws RoleException {
        evaluationService.saveOrUpdate(evaluation);
        return new ResponseEntity<>(evaluation.toString() + "\n " + evaluation.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @DeleteMapping (value = ID_MAPPING)
    public ResponseEntity<?> deleteEvaluationById(@PathVariable String id) {
        evaluationService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Evaluation.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
