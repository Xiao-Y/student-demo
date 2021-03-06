package org.billow.business.service;

import org.billow.business.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    User findByName(String username);

    Page<User> findAll(Pageable pageable);
}
