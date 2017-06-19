package org.billow.dao;

import org.billow.model.domain.UserRoleBase;

public interface UserRoleDao {
    int insert(UserRoleBase record);

    int insertSelective(UserRoleBase record);
}