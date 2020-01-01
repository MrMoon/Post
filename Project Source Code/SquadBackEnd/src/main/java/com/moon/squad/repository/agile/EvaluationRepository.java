package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Evaluation;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.List;

public interface EvaluationRepository extends CRUDRepository<Evaluation> {
    List<Evaluation> findAllByOrderByProgress();
    List<Evaluation> findAllByOrderByResults();
    Evaluation findTopByOrderByIdDesc();
}
