package com.moon.squad.service.agile;

import com.moon.squad.model.agile.Track;
import com.moon.squad.service.shared.CRUDService;

import java.util.List;

public interface TrackService extends CRUDService<Track> {
    List<Track> findAllByOrderByEvaluation();
}
