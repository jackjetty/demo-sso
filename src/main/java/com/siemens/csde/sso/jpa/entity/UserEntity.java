package com.siemens.csde.sso.jpa.entity;
import java.util.Date;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
@ToString
@Entity
@Setter
@Getter
@Table(name="tb_test_user")
@Immutable
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "entityCache")
public class UserEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Id
    //@GenericGenerator(name = "idGenerator", strategy = "uuid")
    //@GeneratedValue(generator = "idGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



}