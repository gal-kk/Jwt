package gk.gk;

import gk.gk.Domain.User;
import gk.gk.Domain.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAll();

    UserDto save(UserDto userDto);

    UserDto findById(int id);

    User findByUsername(String username);
}
