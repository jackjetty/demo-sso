package com.siemens.csde.sso.pojo.no;

import com.google.gson.annotations.SerializedName;
import com.siemens.csde.sso.base.BaseNo;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class OutputNo extends BaseNo {

    private static final long serialVersionUID = -7072942656367827905L;
    private static final String ASPECT_OUTPUT="Output_LINE";
    private String tenant=TENANT;
    private String assetId=ASSET_ID;
    private String aspectName=ASPECT_OUTPUT;
    private List<OutputSubNo> timeseries;

    @Getter
    @Setter
    @Builder
    public static class OutputSubNo{
        @SerializedName("Output")
        private Integer output;
        @SerializedName("ProductID")
        private String productId;
        @SerializedName("OrderID")
        private String orderId;
        @SerializedName("_time")
        private String time;
        @SerializedName("Changeover")
        private boolean changeOver;
    }

}