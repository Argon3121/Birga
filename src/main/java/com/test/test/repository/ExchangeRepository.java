package com.test.test.repository;

import com.test.test.model.Exchange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeRepository extends JpaRepository<Exchange, Long> {
    List<Exchange> findByCountry(String country);
    List<Exchange> findByFounded(Long founded);
}