package com.moon.squad.repository.agile;

import com.moon.squad.model.agile.Project;
import com.moon.squad.repository.shared.CRUDRepository;
import com.moon.squad.shared.ApplicationConstants;

import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import static com.moon.squad.shared.ApplicationConstants.NOT_NULL;

@CrossOrigin (origins = ApplicationConstants.LOCALHOST_4200)
public interface ProjectRepository extends CRUDRepository<Project> {
    List<Project> findAllByOrderByDeadline();

    List<Project> findAllByDeadline(@NotNull (message = NOT_NULL) Date deadline);
}
