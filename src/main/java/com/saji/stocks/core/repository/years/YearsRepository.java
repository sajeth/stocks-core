package com.saji.stocks.core.repository.years;

import com.saji.stocks.entities.years.YearsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearsRepository extends JpaRepository<YearsEntity, Integer> {

}
