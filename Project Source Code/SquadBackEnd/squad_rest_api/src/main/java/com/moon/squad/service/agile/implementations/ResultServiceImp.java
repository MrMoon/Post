package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Result;
import com.moon.squad.repository.agile.ResultRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.ResultService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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
import static com.moon.squad.shared.ApplicationConstants.CACHE_RESULT;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@CacheConfig (cacheNames = CACHE_RESULT)
@Slf4j
public class ResultServiceImp implements ResultService {

    private final SequenceRepository sequenceRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public ResultServiceImp(ResultRepository resultRepository, SequenceRepository sequenceRepository) {
        this.resultRepository = resultRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Cacheable
    @Override
    public List<Result> findAll() {
        log.info(gettingAll(Result.class, false));
        return resultRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Result> findAllByOrderByDate() {
        log.info(gettingAll(Result.class, true) + " By Date");
        return resultRepository.findAllByOrderByDate();
    }

    @Cacheable
    @Override
    public List<Result> findAllByDate(@NotNull Date date) {
        log.info("Getting All Results By Date " + date.toString());
        return resultRepository.findAllByDate(date);
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public @NotNull Optional<Result> findById(String id) {
        log.info(gettingById(Result.class, id));
        return resultRepository.findById(id);
    }

    @CachePut (key = "#result.id")
    @Override
    public Result saveOrUpdate(@NotNull Result result) {
        log.info(saving(result.toString()));
        return resultRepository.save(result);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Result.class, id));
        resultRepository.deleteById(id);
    }
}
