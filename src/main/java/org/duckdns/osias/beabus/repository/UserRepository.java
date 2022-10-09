package org.duckdns.osias.beabus.repository;

import org.duckdns.osias.beabus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
