package pt.uevora.user_management.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.uevora.user_management.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
}
