package com.siemens.csde.sso.pojo.vo;

import com.siemens.csde.sso.base.BaseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserVo extends BaseVo{

    private String id;
    private String name;
    private Integer sex;

}