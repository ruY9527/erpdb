package com.erp.service;


import com.yang.erp.pojo.Log;

import java.util.List;

public interface LogService {
	//增删改
	public int createLog(Log log);
	public int updateLog(Log log);


    public List<Log> findAll(Log log, Integer page, Integer rows);

	public Integer selectCount();
}
