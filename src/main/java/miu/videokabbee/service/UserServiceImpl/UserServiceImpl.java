package miu.videokabbee.service.UserServiceImpl;


import miu.videokabbee.config.JwtUtil;
import miu.videokabbee.config.UserDetailCustom;
import miu.videokabbee.domain.Users;
import miu.videokabbee.repository.UserRepository;
import miu.videokabbee.service.UserInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserInterfaceService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public UserServiceImpl(JwtUtil jwtUtil, UserDetailCustom userDetailCustom, AuthenticationManager authenticationManager) {
        this.jwtUtil = jwtUtil;
        this.userDetailCustom = userDetailCustom;
        this.authenticationManager = authenticationManager;
    }

    @Autowired

    private UserDetailCustom userDetailCustom;

    @Autowired
    private final AuthenticationManager authenticationManager;

    public String register(Users users) {
        if (userRepository.existsByContact_Email(users.getContact().getEmail())) {
            return "Email-taken";
        } else if (userRepository.existsByUserName(users.getUserName())) {
            return "Username-taken";

        }
        userRepository.save(users);
        return "Success";
    }


    public Users findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    @Override
    public String authenticate(String email, String password) {

        try {
            var user1 = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email, passwordEncoder.encode(password))
            );
                return jwtUtil.generateToken((UserDetails) user1.getPrincipal());
        } catch (Exception e) {
          return "not authenticated";
        }
    }

}
