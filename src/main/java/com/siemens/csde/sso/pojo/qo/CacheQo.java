package com.siemens.csde.sso.pojo.qo;

import com.siemens.csde.sso.base.BaseQo;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CacheQo extends BaseQo{


    private static final long serialVersionUID = 4916452098268564803L;
    private String assetId;
    private String apsectName;

}