package com.muyi.courage.push.service.impl;

import com.muyi.courage.common.dto.DTO;
import com.muyi.courage.common.util.CalendarUtil;
import com.muyi.courage.common.util.RetCodeEnum;
import com.muyi.courage.common.util.StringUtil;
import com.muyi.courage.push.service.SendMailInfoService;
import com.muyi.courage.user.webIner.UserInerResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author 杨志勇
 * @date 2021-03-16 16:50
 */
@Service
@Slf4j
public class SendMailInfoServiceImpl implements SendMailInfoService {

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private JavaMailSender javaMailSender;

    @Resource
    private UserInerResource userInerResource;

    @Override
    public DTO sendInfo2User(HashMap hashMap) {
        DTO dto = new DTO(RetCodeEnum.FAIL);
        String userName = userInerResource.getNameByUserName(StringUtil.parseString(hashMap.get("userName")));;
        String message = StringUtil.parseString(hashMap.get("message"));
        String noticeTitle = StringUtil.parseString(hashMap.get("noticeTitle"));

        String noticeAddress = StringUtil.parseString(hashMap.get("noticeAddress"));

        String email;
        if (!StringUtil.isNullorEmpty(noticeAddress)) {
            email = noticeAddress;
        } else {
            //根据用户编号查询
            email = userInerResource.getEmailByUserName(userName);
        }
        if (StringUtil.isNullorEmpty(email)) {
            dto.setRetMsg("该用户没有邮箱");
            return dto;
        }

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(email);
        simpleMailMessage.setSubject(noticeTitle);
        simpleMailMessage.setText(message);

        try {
            javaMailSender.send(simpleMailMessage);
            log.info("邮件已经发送。");
        } catch (Exception e) {
            log.error("发送邮件时发生异常！", e);
            dto.setRetMsg("邮件发送给用户异常！");
            return dto;
        }
        dto.setRetCode(RetCodeEnum.SUCCEED.getCode());
        dto.setRetMsg("邮件发送给用户成功！");
        return dto;
    }

    @Override
    public DTO sendInfo2Roles(HashMap hashMap) {
        return null;
    }

    @Override
    public DTO sendInfo2All(HashMap hashMap) {
        return null;
    }
}
