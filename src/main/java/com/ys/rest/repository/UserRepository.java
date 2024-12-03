package com.ys.rest.repository;

import com.ys.rest.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends ListCrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {

}
