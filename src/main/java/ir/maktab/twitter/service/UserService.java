package ir.maktab.twitter.service;

import ir.maktab.twitter.base.service.BaseService;
import ir.maktab.twitter.domain.User;

public interface UserService extends BaseService<User, Long> {

    boolean isValidUsername(String username);

    boolean isValidEmail(String email);

    boolean checkUsernamePassword(String username, String password);

    User findByUsername(String userName);

}
