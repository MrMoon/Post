package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Track;
import com.moon.squad.repository.agile.TrackRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.TrackService;

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

import static com.moon.squad.shared.ApplicationConstants.CACHE_ID;
import static com.moon.squad.shared.ApplicationConstants.CACHE_TRACK;
import static com.moon.squad.shared.LoggingConstants.deleting;
import static com.moon.squad.shared.LoggingConstants.gettingAll;
import static com.moon.squad.shared.LoggingConstants.gettingById;
import static com.moon.squad.shared.LoggingConstants.saving;

@Service
@Slf4j
@CacheConfig (cacheNames = CACHE_TRACK)
public class TrackServiceImp implements TrackService {

    private final SequenceRepository sequenceRepository;
    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImp(TrackRepository trackRepository, SequenceRepository sequenceRepository) {
        this.trackRepository = trackRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Cacheable
    @Override
    public List<Track> findAll() {
        log.info(gettingAll(Track.class, false));
        return trackRepository.findAll();
    }

    @Cacheable
    @Override
    public List<Track> findAllByOrderByEvaluation() {
        log.info(gettingAll(Track.class, true));
        return trackRepository.findAllByOrderByEvaluation();
    }

    @Cacheable (key = CACHE_ID)
    @Override
    public Optional<Track> findById(String id) {
        log.info(gettingById(Track.class, id));
        return trackRepository.findById(id);
    }

    @CachePut (key = "#track.id")
    @Override
    public Track saveOrUpdate(@NotNull Track track) {
        log.info(saving(track.toString()));
        return trackRepository.save(track);
    }

    @CacheEvict (key = CACHE_ID)
    @Override
    public void deleteUserById(String id) {
        log.info(deleting(Track.class, id));
        trackRepository.deleteById(id);
    }
}
