package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Track;
import com.moon.squad.repository.shared.CRUDRepository;

import java.util.List;

public interface TrackRepository extends CRUDRepository<Track> {
    List<Track> findAllByOrderByEvaluation();
}
