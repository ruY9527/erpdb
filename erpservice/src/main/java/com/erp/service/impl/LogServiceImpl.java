package com.erp.service.impl;

import com.erp.mapper.LogMapper;
import com.erp.service.LogService;
import com.github.pagehelper.PageHelper;
import com.yang.erp.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*import com.myron.ims.bean.Log;
import com.myron.ims.service.LogService;*/

@Service("logService")
public class LogServiceImpl  implements LogService
{
	
	@Autowired
	private LogMapper logMapper;
	
	@Override
	public int createLog(Log log) {
		//return this.logDao.insertSelective(log);
		//System.out.println("模拟日志入库"+log);
		//添加log进入数据库
		logMapper.addLog(log);
		return 1;
	}
	
	@Override
	public int updateLog(Log log) {
		//return this.logDao.updateByPrimaryKeySelective(log);
		//System.out.println("模拟日志更新"+log);
		return 1;
	}

	//查询全部(或者条件查询)
	@Override
	public List<Log> findAll(Log log, Integer page, Integer rows)
	{
		PageHelper.startPage(page,rows);
		List<Log> list =  logMapper.findAll(log);
		return list;
	}

	//获取个数
	@Override
	public Integer selectCount()
	{
		return logMapper.selectCount();
	}
}
