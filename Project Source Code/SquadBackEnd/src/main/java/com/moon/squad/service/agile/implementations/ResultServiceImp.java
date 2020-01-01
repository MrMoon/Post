package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Result;
import com.moon.squad.repository.agile.ResultRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ResultService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImp implements ResultService {

    private final SequenceRepository sequenceRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public ResultServiceImp(ResultRepository resultRepository, SequenceRepository sequenceRepository){
        this.resultRepository = resultRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public List<Result> findAll() {
        return resultRepository.findAll();
    }

    @Override
    public List<Result> findAllByOrderByDate() {
        return resultRepository.findAllByOrderByDate();
    }

    @Override
    public List<Result> findAllByDate(Date date) {
        return resultRepository.findAllByDate(date);
    }

    @Override
    public @NotNull Optional<Result> findById(String id) {
        return resultRepository.findById(id);
    }

    @Override
    public Result saveOrUpdate(@NotNull Result result) {
        return resultRepository.save(result);
    }

    @Override
    public void deleteById(String id) {
        resultRepository.deleteById(id);
    }
}
