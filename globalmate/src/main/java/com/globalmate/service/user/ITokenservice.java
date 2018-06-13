package com.globalmate.service.user;

import com.globalmate.data.entity.User;

public interface ITokenservice {

    String generateToken();

    String putUserToken(String token, User user);

    User getTokenUser(String token);


}
