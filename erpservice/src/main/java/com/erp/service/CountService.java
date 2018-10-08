package com.erp.service;

import java.util.List;
import java.util.Map;

public interface CountService
{

    public List orderReport(String startDate,String endDate);
    public List<Map<String,Object>> getSumMoney(Integer years);
}
