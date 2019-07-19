package com.siemens.csde.sso.pojo.qo;

import com.siemens.csde.sso.base.BaseQo;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IotTimeSeriesQo extends BaseQo{

    private static final long serialVersionUID = 2742863728703312963L;
    private Instant from;
    private Instant to;
    private String assetId;
    private String apsectName;

}