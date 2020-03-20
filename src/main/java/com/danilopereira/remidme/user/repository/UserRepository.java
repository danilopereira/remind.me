package com.danilopereira.remidme.user.repository;

import com.danilopereira.remidme.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUuid(String uuid);

    User findByGithubUrl(String githubUrl);
}
