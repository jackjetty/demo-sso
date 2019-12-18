package com.siemens.csde.sso.service.impl;

import com.google.gson.Gson;
import com.siemens.csde.sso.jpa.entity.UserEntity;
import com.siemens.csde.sso.jpa.repository.UserRepository;
import com.siemens.csde.sso.service.TestService;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.FlushMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TestServiceImpl  implements TestService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;

    //除非明确地指定了flush（）命令，否则关于Session何时会执行这些JDBC调用完全是无法保证的，只能保证他们执行的前后顺序。
    @Override
    //@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    //@Transactional(readOnly=true,propagation= Propagation.NOT_SUPPORTED,rollbackFor=Exception.class)
     @Transactional
    public void testUsers() {

        UserEntity userEntity;
        userEntity=new UserEntity();
        userEntity.setId(1000L);
        userEntity.setName("tom123");
        //userRepository.delete(userEntity);
        //
       userRepository.save(userEntity);
        userRepository.flush();
        userRepository.delete(userEntity);
        userRepository.flush();
        //userRepository.flush();
         userEntity.setName("jack");
        userRepository.save(userEntity);
        /*userRepository.delete(userEntity);
        UserEntity littleUserEntity=new UserEntity();
        littleUserEntity.setId(1001L);
        littleUserEntity.setName("tom");
        userRepository.save(littleUserEntity);*/
/*

        userRepository.deleteUserById(253L);
        userEntity= userRepository.selectUserById(253L);
        log.info("result:{}",userEntity==null);
        userEntity=new UserEntity();
        userEntity.setId(253L);
        userEntity.setName("用户2");
        userRepository.save(userEntity);*/
       /* StringBuffer sqlBuffer=new StringBuffer("");
        sqlBuffer.append("delete from tb_test_user where id=253; ");
        sqlBuffer.append("insert into tb_test_user(id,name) values(253,'test');");
        Query query = em.createNativeQuery(sqlBuffer.toString()).setFlushMode( FlushModeType.COMMIT);
        query.executeUpdate();*/
        //query.setFlushMode(FlushMode.NEVER);
        //userRepository.insertUser(253L,"用户1");
     /*   */
      /* for(int i=0;i<10;i++){
            userEntity=new UserEntity();
            //userEntity.setId("U"+i);
            userEntity.setName("用户"+i);
            //AndFlush
            userRepository.save(userEntity);
           userRepository.saveAndFlush(userEntity);
            userRepository.flush();
            log.info("id:{}",userEntity.getId());
        }*/

        //userRepository.flush();
        //userRepository.deleteUserById(20L);
       /* if(true){
            throw new RuntimeException("test");
        }

        userRepository.deleteAll();*/
       //
    }
}