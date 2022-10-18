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
    public User signinUser(String id, String pw) {
        User user = userRepository.selectUserInfo(id,pw);
        return user;
    }
    public int idCheck(String id) throws Exception {
        User result = userRepository.idCheck(id);
        if (result == null) {
            return 0;
        } else {
            return 1;
        }

    }
}
