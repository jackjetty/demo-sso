package com.siemens.csde.sso.config.jpa;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.springframework.stereotype.Component;

@Component
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<String> {
    @Override
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
        String tenant=shardingValue.getValue();
        int hashCode=tenant.hashCode();
        for (String each : tableNames) {
            if (each.endsWith(hashCode % 2 + "")) {
                return each;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<String> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Collection<String> values = shardingValue.getValues();
        for (String value : values) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value.hashCode()%2+"")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    @Override
    public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
            ShardingValue<String> shardingValue) {
        return null;
    }
}