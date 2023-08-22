package com.trustbycode.Service.Implement;

import com.trustbycode.Entity.User;
import com.trustbycode.Entity.VerificationToken;
import com.trustbycode.Model.UserModel;
import com.trustbycode.Repository.UserRepository;
import com.trustbycode.Repository.VerificationTokenRepository;
import com.trustbycode.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(userModel.getPassword())); //Whatever we are setting as password is
        // what we'll be getting back
        //If set to db we >>convert to encrypted pw, we use pw encoder to encrypt the pw b4 saving to db
        userRepository.save(user);
        return user;
    }

    @Override
    public void saveVerificationTokenForUser(String token, User user) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepository.save(verificationToken);
    }
}
