package miu.videokabbee.service;


import miu.videokabbee.domain.User;

public interface UserInterfaceService {
    User register(User user);
    User findById(Long id);
}
