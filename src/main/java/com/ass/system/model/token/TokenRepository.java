package com.ass.system.model.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<com.ass.system.model.token.Token, Integer> {

  @Query(value = """
      select t from Token t inner join AppUser u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
  List<com.ass.system.model.token.Token> findAllValidTokenByUser(long id);

  Optional<com.ass.system.model.token.Token> findByToken(String token);
}
