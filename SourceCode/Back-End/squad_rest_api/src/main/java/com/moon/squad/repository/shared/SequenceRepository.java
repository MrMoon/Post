package com.moon.squad.repository.shared;

import com.moon.squad.shared.ApplicationConstants;
import com.moon.squad.shared.exception.SequenceException;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface SequenceRepository {
    String getNextSequenceId(String key) throws SequenceException;
}
