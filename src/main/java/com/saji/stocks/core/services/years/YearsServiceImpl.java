package com.saji.stocks.core.services.years;

import com.saji.stocks.core.repository.years.YearsRepository;
import com.saji.stocks.entities.years.YearsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class YearsServiceImpl implements IYears {
    @Autowired
    YearsRepository yearsRepository;

    @Override
    public List<Integer> getExcludedYears() {
        return yearsRepository.findAll().stream().map(val -> val.getYear()).collect(Collectors.toList());
    }

    @Override
    public void addExcludedYear(Integer year) {
        YearsEntity yearsEntity = new YearsEntity();
        yearsEntity.setYear(year);
        yearsEntity.setLogicalDelIn("N");
        yearsRepository.save(yearsEntity);
    }

    @Override
    public void deleteYear(Integer year) {
        Optional<YearsEntity> yearsEntity = yearsRepository.findById(year);
        if (yearsEntity.isPresent()) {
            YearsEntity x = yearsEntity.get();
            x.setLogicalDelIn("Y");
            yearsRepository.delete(x);
            x.setLogicalDelIn("Y");
            yearsRepository.save(x);
        }

    }
}
