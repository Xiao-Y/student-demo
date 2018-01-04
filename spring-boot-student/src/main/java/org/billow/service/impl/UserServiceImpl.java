package org.billow.service.impl;

import org.billow.dao.UserJpaDao;
import org.billow.model.User;
import org.billow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserJpaDao userJpaDao;

    /**
     * @param username
     * @return
     */
    @Override
    public User findByName(String username) {
        return userJpaDao.findByName(username);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userJpaDao.findAll(pageable);
    }
}
