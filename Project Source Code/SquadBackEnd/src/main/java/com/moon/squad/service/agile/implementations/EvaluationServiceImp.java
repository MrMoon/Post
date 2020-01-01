package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Evaluation;
import com.moon.squad.repository.agile.EvaluationRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.EvaluationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationServiceImp implements EvaluationService {

    private final SequenceRepository sequenceRepository;
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationServiceImp(EvaluationRepository evaluationRepository, SequenceRepository sequenceRepository) {
        this.evaluationRepository = evaluationRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public List<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    @Override
    public List<Evaluation> findAllByOrdOrderByProgress() {
        return evaluationRepository.findAllByOrderByProgress();
    }

    @Override
    public List<Evaluation> findAllByOrdOrderByResults() {
        return evaluationRepository.findAllByOrderByResults();
    }

    @Override
    public Optional<Evaluation> findById(String id) {
        return evaluationRepository.findById(id);
    }

    @Override
    public Evaluation saveOrUpdate(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    @Override
    public void deleteById(String id) {
        evaluationRepository.deleteById(id);
    }
}
