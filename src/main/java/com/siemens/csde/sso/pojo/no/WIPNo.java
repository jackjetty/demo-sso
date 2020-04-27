package com.siemens.csde.sso.pojo.no;

import com.google.gson.annotations.SerializedName;
import com.siemens.csde.sso.base.BaseNo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class WIPNo extends BaseNo {

    private static final long serialVersionUID = 6594243705616201814L;
    private String tenant=TENANT;
    private String assetId;
    private String aspectName="WIP_LINE";
    private String kpiUnit="one";
    private String lineId;
    private String lineName;
    @SerializedName("timeseries")
    private List<WIPSubNo> timeseries;



    @Getter
    @Setter
    @Builder
    public static class WIPSubNo{
        @SerializedName("WIP")
        private Integer wip;
        @SerializedName("ProductID")
        private String productId;
        @SerializedName("ProductName")
        private String productName;
        @SerializedName("OrderID")
        private String orderId;
        @SerializedName("_time")
        private String time;
        @SerializedName("Changeover")
        private boolean changeOver;
    }

}