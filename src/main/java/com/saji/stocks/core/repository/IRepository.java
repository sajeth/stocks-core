package com.saji.stocks.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface IRepository<T> extends JpaRepository<T, BigInteger> {
}
