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

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>, JpaSpecificationExecutor<UserEntity> {

    @Modifying
    @Query(value = "DELETE FROM tb_test_user  where id = :lineId  and name ='用户3'",nativeQuery = true)
    void deleteAll(@Param("lineId") String lineId);

}
