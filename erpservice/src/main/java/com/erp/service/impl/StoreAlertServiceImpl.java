package com.erp.service.impl;

import com.erp.mapper.StoreAlertMapper;
import com.erp.service.StoreAlertService;
import com.erp.utils.EmailUtils;
import com.erp.utils.MyResult;
import com.yang.erp.pojo.StoreAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StoreAlertServiceImpl implements StoreAlertService
{

    @Autowired
    private StoreAlertMapper storeAlertMapper;

    @Autowired
    private EmailUtils emailUtils;


    //获取全部信息
    @Override
    public List<StoreAlert> findAll()
    {
        return storeAlertMapper.findAll();
    }

    //发送库存警告
    @Override
    public MyResult sendStoreWarm() throws MessagingException
    {
        //查看是否有需要的发送
        List<StoreAlert> storeAlerts = storeAlertMapper.findAllWarm();
        int flag = storeAlerts == null?0:storeAlerts.size();
        if(flag>0){
            sendMail(storeAlerts);
            return new MyResult(true,"库存预警邮箱发送成功");
        } else {
            System.out.println("没有需要发送的邮件预警");
            return new MyResult(false,"目前不需要发送库存预警邮箱");
        }
    }

    //提取发送邮箱的代码
    public void sendMail(List<StoreAlert> storeAlerts) throws MessagingException
    {
        StringBuffer stringBuffer = new StringBuffer();
        if(storeAlerts !=null && storeAlerts.size()>0){
            stringBuffer.append("[");
            for (StoreAlert storeAlert:storeAlerts)
            {
                stringBuffer.append(storeAlert.getName()+"  ");
            }
            stringBuffer.append("]");
        } else {
            //如果没有数量
            stringBuffer.append("仓库中商品数量足够");
        }
        String toString = stringBuffer.toString();

        //需要发送库存警告
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        emailUtils.send("17671312389@163.com",
                "[时间时:]"+simpleDateFormat.format(new Date()),
                "[需要进入的库存种类个数:]"+storeAlerts.size()+",名字分别是:"+toString);
    }


}
