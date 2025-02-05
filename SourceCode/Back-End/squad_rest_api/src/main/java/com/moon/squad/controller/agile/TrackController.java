package com.moon.squad.controller.agile;

import com.moon.squad.model.agile.Track;
import com.moon.squad.service.agile.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.moon.squad.shared.ApplicationConstants.ADDED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.LOCALHOST_4200;
import static com.moon.squad.shared.ApplicationConstants.DELETED_SUCCESSFULLY;
import static com.moon.squad.shared.ApplicationConstants.ID;
import static com.moon.squad.shared.ApplicationConstants.ID_MAPPING;
import static com.moon.squad.shared.ApplicationConstants.SLASH;
import static com.moon.squad.shared.ApplicationConstants.TRACK_MAPPING;

@RestController
@RequestMapping (TRACK_MAPPING)
@CrossOrigin (origins = LOCALHOST_4200)
public class TrackController {

    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (SLASH)
    public List<Track> getAllTracks() {
        return trackService.findAll();
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @GetMapping (ID_MAPPING)
    public Optional<Track> getTrackById(@PathVariable (ID) String id) {
        return trackService.findById(id);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @PostMapping (SLASH)
    public ResponseEntity<?> saveOrUpdateTrack(@RequestBody Track track) {
        trackService.saveOrUpdate(track);
        return new ResponseEntity<>(track.toString() + "\n " + track.getClass().getSimpleName() + ' ' + ADDED_SUCCESSFULLY, HttpStatus.OK);
    }

    @CrossOrigin(origins = LOCALHOST_4200)
    @DeleteMapping (ID_MAPPING)
    public ResponseEntity<?> deleteTrackById(@PathVariable String id) {
        trackService.deleteUserById(id);
        return new ResponseEntity<>(id + "\n " + Track.class.getSimpleName() + ' ' + DELETED_SUCCESSFULLY, HttpStatus.OK);
    }
}
