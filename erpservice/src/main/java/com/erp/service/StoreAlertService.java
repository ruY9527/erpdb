package com.erp.service;

import com.erp.utils.MyResult;
import com.yang.erp.pojo.StoreAlert;

import javax.mail.MessagingException;
import java.util.List;

public interface StoreAlertService
{
    public List<StoreAlert> findAll();
    public MyResult sendStoreWarm() throws MessagingException;
    public void sendMail(List<StoreAlert> storeAlerts) throws MessagingException;
}
