package com.ys.rest.service;

import com.ys.rest.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    void delete(User user);
    List<User> findAll();
    Page<User> findAll(Pageable pageable);
    Optional<User> findById(Long id);

}
