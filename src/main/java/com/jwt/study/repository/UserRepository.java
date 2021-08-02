package com.jwt.study.repository;

import com.jwt.study.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Lazy 조회가 아닌 Eager조회로 authorities정보를 같이 가져옴
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    boolean existsByUsername(String username);

}
