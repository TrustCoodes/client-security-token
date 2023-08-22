package com.trustbycode.Service;

import com.trustbycode.Entity.User;
import com.trustbycode.Model.UserModel;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(String token, User user);
}
