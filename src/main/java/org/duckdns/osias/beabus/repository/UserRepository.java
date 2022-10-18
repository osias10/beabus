package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.userId=:userId and u.userPassword=:userPassword")
    User selectUserInfo(@Param("userId")String userId,@Param("userPassword")String userPassword);

    @Query("select u from User u where u.userId=:userId")
    User idCheck(@Param("userId")String userId);
}
