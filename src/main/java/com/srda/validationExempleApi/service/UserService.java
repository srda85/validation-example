package com.srda.validationExempleApi.service;

import com.srda.validationExempleApi.dto.UserRequest;
import com.srda.validationExempleApi.entity.User;
import com.srda.validationExempleApi.exception.UserNotFoundException;
import com.srda.validationExempleApi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User SaveUser(UserRequest userRequest){
        //Le builder est permis par l'option staticName build de lombok allArgConstructor
        User user = User.build(
                0,
                userRequest.getName(),
                userRequest.getEmail(),
                userRequest.getMobile(),
                userRequest.getGender(),
                userRequest.getNationality(),
                userRequest.getAge()
        );
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    //Je dois délèguer l'exception à la méthode appellante suivante.
    public User getUser(int id) throws UserNotFoundException {
        //Très pratique
        //j'ai juste écrit findBy suivi de ce que je voulais avec parenthèses et paramètre et jpa comprendra, et l'ide proposera de l'implémenter dans le repo
        User user = userRepository.findByUserId(id);
        if (user!=null){
            return user;
        }else {
            throw new UserNotFoundException("user not found with this id:"+id);
        }
    }
}
