package miu.videokabbee.service;


import miu.videokabbee.domain.Users;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserInterfaceService {
    String register(Users users);
    Users findById(Long id);
   String  authenticate(String userName, String password);
}
