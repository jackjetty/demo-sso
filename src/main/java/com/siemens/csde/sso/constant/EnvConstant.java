package com.siemens.csde.sso.constant;

import com.google.common.collect.Lists;
import com.siemens.csde.sso.pojo.qo.IotTimeSeriesQo;
import java.time.Instant;
import java.util.List;

public class EnvConstant {


    public static String TOKEN = "";

    public static final String TENANT_CLIENT_ID = "simicas-dev";

    public static final String TENANT_CLIENT_SECRET = "fae77d19bb1cea02bae1477565a8e772";

    public static List<IotTimeSeriesQo> iotTimeSeriesQos;


    static {
        iotTimeSeriesQos = Lists.newArrayList();
        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("8b6bdbeb80394aadbb09b76f3e9af00a")
                .apsectName("Output_82d01cf7036345719c49d5b6b797e7d9")
                .from(Instant.parse("2019-07-05T01:02:23.519Z"))
                .to(Instant.parse("2019-07-19T00:08:32.835Z")).build());

        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("910c6acd5825447b9cfce13fd79fc09e")
                .apsectName("FPY_LINE")
                .from(Instant.parse("2019-07-05T06:57:05.407Z"))
                .to(Instant.parse("2019-07-19T00:32:22.817Z")).build());

        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("08c4d540bd6c41acb971d4efe77ee239")
                .apsectName("WIP_LINE")
                .from(Instant.parse("2019-07-05T03:32:51.178Z"))
                .to(Instant.parse("2019-07-19T01:47:52.767Z")).build());
        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("a4c334d562c9446c9ede6d8db1f11f20")
                .apsectName("WIP_LINE")
                .from(Instant.parse("2019-07-10T08:11:50.714Z"))
                .to(Instant.parse("2019-07-18T00:11:52.760Z")).build());

        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("a4c334d562c9446c9ede6d8db1f11f20")
                .apsectName("Output_LINE")
                .from(Instant.parse("2019-07-10T08:11:50.714Z"))
                .to(Instant.parse("2019-07-18T00:57:52.941Z")).build());

        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("2d64abebf994409ea71ebdf3b59e402b")
                .apsectName("FPY_LINE")
                .from(Instant.parse("2019-07-05T10:06:00.761Z"))
                .to(Instant.parse("2019-07-18T00:59:43.106Z")).build());
        iotTimeSeriesQos.add(IotTimeSeriesQo.builder()
                .assetId("2d64abebf994409ea71ebdf3b59e402b")
                .apsectName("Output_LINE")
                .from(Instant.parse("2019-07-05T10:06:00.761Z"))
                .to(Instant.parse("2019-07-18T01:01:52.909Z")).build());
        /*iotTimeSeriesQos.stream().forEach(iotTimeSeriesQo -> {
            iotTimeSeriesQo.setFrom(iotTimeSeriesQo.getFrom().minusSeconds(10));
            //.plusSeconds(5*60)
            //iotTimeSeriesQo.setTo(Instant.now());
        });*/
    }

}