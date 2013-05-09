package org.cyetstar.clover.repository.mybatis;

import org.cyetstar.clover.entity.User;

public interface UserMapper extends SqlMapper {

	User findByLoginName(String loginName);

}
