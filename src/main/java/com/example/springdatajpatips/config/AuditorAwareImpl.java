package com.example.springdatajpatips.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {
  
  @Autowired
  private Environment environment;


  //dummy implementation
  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.of(1L);
  }


  //Todo - Real implementation would be like
  // if used commented one Need to change - implements AuditorAware<Long>
//  @Override
//  public Long getCurrentAuditor() {
//    Long userId = Long.valueOf(environment.getProperty("system.userid"));
//    SecurityContext securityContext = SecurityContextHolder.getContext();
//    if (securityContext.getAuthentication() != null && !securityContext.getAuthentication().getName().equals("anonymousUser")) {
//    UserDetail userDetail = (UserDetail) securityContext.getAuthentication().getPrincipal();
//    userId = userDetail.getId();
//    }
//    return userId;
//  }


}