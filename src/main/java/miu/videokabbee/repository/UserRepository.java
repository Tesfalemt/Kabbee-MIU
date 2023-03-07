package miu.videokabbee.repository;


import miu.videokabbee.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {
    Boolean existsByUserName(String userName);
    Boolean existsByContact_Email(String userEmail);
}
