package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Evaluation;
import com.moon.squad.repository.agile.EvaluationRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.EvaluationService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import static com.moon.squad.shared.ApplicationConstants.CACHE_EVALUATION;
import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_EVALUATION)
@Slf4j
public class EvaluationServiceImp implements EvaluationService {

    private final SequenceRepository sequenceRepository;
    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationServiceImp(EvaluationRepository evaluationRepository, SequenceRepository sequenceRepository) {
        this.evaluationRepository = evaluationRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Cacheable
    @Override
    public List<Evaluation> findAll() {
        log.info(gettingAll(Evaluation.class, false));
        return evaluationRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Evaluation> findAllByOrdOrderByProgress() {
        log.info(gettingAll(Evaluation.class, true) + " By Progress");
        return evaluationRepository.findAllByOrderByProgress();
    }

    @Cacheable
    @Override
    public List<Evaluation> findAllByOrdOrderByResults() {
        log.info(gettingAll(Evaluation.class, true) + " By Results");
        return evaluationRepository.findAllByOrderByResults();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<Evaluation> findById(String id) {
        log.info(gettingById(Evaluation.class, id));
        return evaluationRepository.findById(id);
    }

    @CachePut (key = "#evaluation.id")
    @Override
    public Evaluation saveOrUpdate(@NotNull Evaluation evaluation) {
        log.info(saving(evaluation.toString()));
        return evaluationRepository.save(evaluation);
    }

    @CacheEvict (CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Evaluation.class, id));
        evaluationRepository.deleteById(id);
    }
}
