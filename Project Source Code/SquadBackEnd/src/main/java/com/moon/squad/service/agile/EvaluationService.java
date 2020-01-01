package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Evaluation;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;

public interface EvaluationService extends CRUDService<Evaluation> {
    List<Evaluation> findAllByOrdOrderByProgress();
    List<Evaluation> findAllByOrdOrderByResults();
}
