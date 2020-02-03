package com.siemens.csde.sso.test;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.siemens.csde.sso.component.NativeQueryManager;
import com.siemens.csde.sso.jpa.entity.UserEntity;
import com.siemens.csde.sso.jpa.repository.UserRepository;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

@Slf4j
//@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InjectAttackTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    protected Gson gson;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NativeQueryManager nativeQueryManager;
    @Test
    public void announceJPA1(){

       String name="'tom' or 1=1 ";
        name="tom' or '1'='1";
       List<UserEntity> userEntities=userRepository.selectUsersByName(name);
       Assert.assertEquals(userEntities.size(),2);

    }

    @Test
    public void announceJPA2(){

        String name="'tom' or 1=1 ";
        name="tom' or '1'='1";
        List<UserEntity> userEntities=userRepository.selectUsersByNativeName(name);
        Assert.assertEquals(userEntities.size(),2);

    }


    @Test
    public void nativeJPA1(){

        String sql="select * from tb_test_user where name= :name ";
        Map<String, Object> param= Maps.newHashMap();
        param.put("name","tom");
        param.put("name","'tom' or 1=1");
        param.put("name","tom or 1=1");
        List<UserEntity> userEntities=nativeQueryManager.queryByConditionNQ(UserEntity.class,sql,param);
        Assert.assertEquals(userEntities.size(),2);

    }

    @Test
    public void nativeJPA2(){

        String sql="select * from tb_test_user where name=";
        String name="tom' or '1'='1";
        sql+="'"+name+"'";
        //sql = StringEscapeUtils.escapeSql(sql);
        System.out.println(sql);
        Map<String, Object> param= Maps.newHashMap();
        List<UserEntity> userEntities=nativeQueryManager.queryByConditionNQ(UserEntity.class,sql,param);
        Assert.assertEquals(userEntities.size(),2);

    }

    @Test
    public void nameJPA1(){

        String name="'tom' or 1=1";
        name="tom' or '1'='1";
        List<UserEntity> userEntities= userRepository.findByName(name);
        Assert.assertEquals(userEntities.size(),2);

    }



 }