package com.erp.controller.job;

import com.erp.mapper.StoreAlertMapper;
import com.erp.service.StoreAlertService;
import com.yang.erp.pojo.StoreAlert;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.List;

//后台库存预警
public class MailJob
{
    //商品库存任务
    @Autowired
    private StoreAlertMapper storeAlertMapper;

    @Autowired
    private StoreAlertService storeAlertService;

    public void sendStoreAlertMail(){
        List<StoreAlert> storeAlertList = storeAlertMapper.findAllWarm();
        if(storeAlertList.size()>0){
            //需要发送邮件
            try
            {
                storeAlertService.sendMail(storeAlertList);
            } catch (MessagingException e)
            {
                e.printStackTrace();
            }
        }
    }
}
