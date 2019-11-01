package com.siemens.csde.sso.config.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.LinkedHashSet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

public class UserTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm <String> {


    private  final String tablePrefix ;

    public UserTableShardingAlgorithm(String tablePrefix){
        this.tablePrefix=tablePrefix;
    }

    @Override
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {

        String tenant=shardingValue.getValue();
        int hashCode=tenant.hashCode();
        return  tablePrefix+"_"+hashCode%2;
    }

    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            result.add(tablePrefix+"_"+value.hashCode()%2 );

        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
            ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(availableTargetNames.size());
        return null;
    }
}