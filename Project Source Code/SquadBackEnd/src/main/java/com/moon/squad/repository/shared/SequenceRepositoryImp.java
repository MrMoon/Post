package com.moon.squad.repository.shared;

import com.moon.squad.exception.SequenceException;
import com.moon.squad.model.shared.SequenceId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import static com.moon.squad.shared.Constants.ID;

@Repository
public class SequenceRepositoryImp implements SequenceRepository {

    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceRepositoryImp(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public String getNextSequenceId(String key) throws SequenceException  {
        Query query = new Query(Criteria.where(ID).is(key));
        Update update = new Update();
        FindAndModifyOptions findAndModifyOptions = new FindAndModifyOptions();
        findAndModifyOptions.returnNew(true);
        SequenceId sequenceId = mongoOperations.findAndModify(query, update, findAndModifyOptions, SequenceId.class);
        if (sequenceId != null) return sequenceId.getSequence();
        throw new SequenceException("Unable to get sequence id for key : " + key);
    }
}
