package com.moon.squad.service.agile.implementations;

import com.moon.squad.model.agile.Track;
import com.moon.squad.repository.agile.TrackRepository;
import com.moon.squad.repository.shared.SequenceRepository;
import com.moon.squad.service.agile.TrackService;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImp implements TrackService {

    private final SequenceRepository sequenceRepository;
    private final TrackRepository trackRepository;

    @Autowired
    public TrackServiceImp(TrackRepository trackRepository, SequenceRepository sequenceRepository) {
        this.trackRepository = trackRepository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> findAllByOrderByEvaluation() {
        return trackRepository.findAllByOrderByEvaluation();
    }

    @Override
    public Optional<Track> findById(String id) {
        return trackRepository.findById(id);
    }

    @Override
    public Track saveOrUpdate(@NotNull Track track) {
        return trackRepository.save(track);
    }

    @Override
    public void deleteById(String id) {
        trackRepository.deleteById(id);
    }
}
