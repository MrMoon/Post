package com.moon.squad.repository.shared;

import com.moon.squad.shared.exception.SequenceException;

public interface SequenceRepository {
    String getNextSequenceId(String key) throws SequenceException;
}
