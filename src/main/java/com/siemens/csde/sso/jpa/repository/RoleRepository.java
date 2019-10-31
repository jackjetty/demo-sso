package com.siemens.csde.sso.jpa.repository;

import com.siemens.csde.sso.jpa.entity.RoleEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * UserRepository
 * user的Dao接口
 * @author z0043y5h
 * @date 8/28/2019 10:09 AM
 * @version 1.0-SNAPSHOT
 **/
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String>,JpaSpecificationExecutor<RoleEntity> {


}