package com.example.iworks.global.config.auth;

import com.example.iworks.domain.user.domain.User;
import com.example.iworks.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    // 리턴이 되면  Authentication 내부에 들어가고 그 후에 SecurityContext에 들어감

    @Override
    public UserDetails loadUserByUsername(String userEid) throws UsernameNotFoundException {
        System.out.println("PrincipalDetails Service's loadUserByUserName");
        System.out.println("userEid: "+userEid);
        User userEntity = userRepository.findByUserEid(userEid);
        System.out.println(userEntity);
        if(userEntity == null){
            throw new UsernameNotFoundException("wrong id");
        }
        System.out.println("success");
        return new PrincipalDetails(userEntity);

    }
}
