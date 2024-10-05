package com.its.user_service.email;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface TwoFACodeRepository extends JpaRepository<TwoFACode, UUID> {

    @Query("SELECT t FROM TwoFACode t WHERE t.code = :code AND (t.user.username = :usernameOrEmail OR t.user.email = :usernameOrEmail)")
    Optional<TwoFACode> findByCodeAndUser(@Param("code") String code, @Param("usernameOrEmail") String usernameOrEmail);

    @Modifying
    @Transactional
    @Query("DELETE FROM TwoFACode t WHERE t.user.username = :usernameOrEmail OR t.user.email = :usernameOrEmail")
    void deleteByUserUsernameOrEmail(@Param("usernameOrEmail") String usernameOrEmail);
}
