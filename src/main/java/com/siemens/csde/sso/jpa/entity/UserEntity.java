package com.siemens.csde.sso.jpa.entity;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Setter
@Getter
@Table(name="tb_test_user")
public class UserEntity {

    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}