package com.ocode.cbrf.service.impl;

import com.ocode.cbrf.config.security.CbrfUserDetails;
import com.ocode.cbrf.dto.impl.User.UserDto;
import com.ocode.cbrf.model.user.User;
import com.ocode.cbrf.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CbrfUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByLogin(username);

        return getUser(user).map(CbrfUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found."));
    }

    private Optional<UserDto> getUser(Optional<User> user) {
        if(user.isEmpty()) {
            return Optional.empty();
        }

        User usr = user.get();
        UserDto userDto = new UserDto();
        
        userDto.setId(usr.getId());
        userDto.setLogin(usr.getLogin());
        userDto.setPassword(usr.getPassword());
        userDto.setIsActive(usr.getIsActive());
        userDto.setIsDeleted(usr.getIsDeleted());

        userDto.setRole(usr.getRole().getRole());

        return Optional.of(userDto);
    }
}
