package com.siemens.csde.sso.jpa.repository;
import com.siemens.csde.sso.jpa.entity.RoleEntity;
import com.siemens.csde.sso.jpa.entity.UserEntity;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {

    @Modifying
    @Query(value = "DELETE FROM tb_test_user  where id = :lineId  and name ='用户3'",nativeQuery = true)
    void deleteAll(@Param("lineId") String lineId);

    @Modifying
    @Query(value = "delete from UserEntity where id = ?1" )
    void deleteUserById(Long id);

    @Modifying
    @Query(value = "insert into tb_test_user (id,name) values (?,?)",nativeQuery = true)
    void insertUser(Long id,String name);

    @Query(value = "select * from tb_test_user where id = ?1",nativeQuery = true )
    UserEntity selectUserById(Long id);

}
