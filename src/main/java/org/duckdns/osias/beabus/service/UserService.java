package org.duckdns.osias.beabus.service;

import org.duckdns.osias.beabus.entity.User;
import org.duckdns.osias.beabus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public boolean joinUser(User user){

        try{
            int idCheckResult = idCheck(user.getUserId());
            System.out.println(idCheckResult);
            if (idCheckResult == 0) {
                user.setRole("USER");
                User result = userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("join user idcheck fail");
            return false;
        }

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
