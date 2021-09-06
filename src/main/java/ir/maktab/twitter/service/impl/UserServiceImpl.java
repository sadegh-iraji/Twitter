package ir.maktab.twitter.service.impl;

import ir.maktab.twitter.base.service.impl.BaseServiceImpl;
import ir.maktab.twitter.domain.User;
import ir.maktab.twitter.repository.UserRepository;
import ir.maktab.twitter.service.UserService;

public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public boolean isValidUsername(String username) {

        if (username.isEmpty()) {
            System.out.println("You Entered Nothing");
            return false;
        }

        for (User users : findAll()) {
            if (users.getUsername().equals(username)) {
                System.out.println("----> This Username Already Exists, Please Try Another One <----");
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isValidEmail(String email) {
        if (!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
            System.out.println("----> Please Enter A Valid Email Address <----");
            return false;
        }

        for (User users : findAll()) {
            if (users.getEmail().equals(email)) {
                System.out.println("----> This Email Address Belong to Somebody Else <----");
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean checkUsernamePassword(String username, String password) {

        for (User users : findAll()) {
            if (users.getUsername().equals(username) && users.getPassword().equals(password)) return true;
        }
        return false;
    }

    @Override
    public User findByUsername(String userName) {
        return (User) repository.getEntityManager().createQuery("from User u where" +
                " u.username = :username ").setParameter("username", userName).getSingleResult();
    }
}
