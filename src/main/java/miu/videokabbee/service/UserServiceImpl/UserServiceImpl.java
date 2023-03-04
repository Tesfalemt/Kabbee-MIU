package miu.videokabbee.service.UserServiceImpl;


import lombok.RequiredArgsConstructor;
import miu.videokabbee.domain.User;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserInterfaceService {

    @Autowired
  UserRepository userRepository;



    public User register(User user) {
     return  userRepository.save(user);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


}
