package org.duckdns.osias.beabus.service;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void joinUser(User user){
        user.setRole("USER");
        userRepository.save(user);
    }
}
