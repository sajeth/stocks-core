package com.saji.stocks.core.services.years;

import java.util.List;

public interface IYears {
    public List<Integer> getExcludedYears();

    public void addExcludedYear(Integer year);

    public void deleteYear(Integer year);
}
