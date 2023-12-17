package com.memory.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.memory.model.Customer;

public class userDetailPrinciple  implements UserDetails{
   private Customer customer;
   public userDetailPrinciple(Customer data) {
    this.customer = data;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority auth=new SimpleGrantedAuthority(customer.getRole());
       return Collections.singleton(auth);
    }

    @Override
    public String getPassword() {
       return customer.getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getEmail();
    }
    public String getName() {
        return customer.getNames();
    }
    public String getPhoneNumber() {
        return customer.getPhoneNumber();
    }
    @Override
    public boolean isAccountNonExpired() {
     return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
