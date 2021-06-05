package com.example.demo.services;
import com.example.demo.models.SignUpRequest;
import com.example.demo.models.TestResult;
import com.example.demo.models.User;
import com.example.demo.models.UserDTO;
import com.example.demo.repo.TestRepo;
import com.example.demo.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional
public class UserService implements UserDetailsService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    TestRepo testRepo;
    public void registerUser(UserDTO userDTO){

    }
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(SignUpRequest signUpRequest, BCryptPasswordEncoder bCryptPasswordEncoder) {
        User user = new User();
        user.setLogin(signUpRequest.getLogin());
        user.setName(signUpRequest.getName());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        if (findUser(user.getLogin()) != null) {
            throw new UsernameNotFoundException("Exist");
        }
        else userRepo.save(user);
    }
    public  List <User> findAll(){
        return userRepo.findAll();
    }
    public void delUser(long id){
//        if(userRepo.findById(id).getId()!=null) {
//            testRepo.deleteById(userRepo.findById(id).getId());
//        }
//        userRepo.findById(id).setTestResult(null);
        userRepo.deleteById((id));
    }

    public User findUser(String name) {
        return userRepo.findByLogin(name);
    }

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = findUser(name);
        if (user == null) {
            throw new UsernameNotFoundException("Not found");
        }
        return new UserDTO(user);
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}