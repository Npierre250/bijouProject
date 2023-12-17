package com.memory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.memory.model.Customer;
import com.memory.repository.customerRepository;
@Service
public class userDetailServices  implements UserDetailsService{
    @Autowired private customerRepository userrepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {  
        Customer customer =userrepo.findByEmail(email);
        if(customer == null)
          throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
    return new userDetailPrinciple(customer);
    }
    
}
