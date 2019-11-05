package com.siemens.csde.sso.config.sharding;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.NoneDatabaseShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.NoneTableShardingAlgorithm;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.siemens.csde.sso.config.druid.DruidConfig;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@Configuration
@AutoConfigureAfter(DruidConfig.class)
public class  ShardingConfig{

    @Autowired
    private DruidConfig druidConfig;


    @Bean(name="dataSource")
    @Qualifier("dataSource")
    @Primary
    public DataSource dataSource() throws SQLException {
        //分库设置
        Map<String, DataSource> dataSourceMap = new HashMap<>(1);
        dataSourceMap.put("database0", druidConfig.druidDataSource());
        //设置默认数据库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, "database0");
        TableRule roleTableRule = TableRule
                .builder("tb_test_role")
                .actualTables(Arrays.asList("tb_test_role_0", "tb_test_role_1", "tb_test_role_2"))
                .dynamic(true)
                .tableShardingStrategy(new TableShardingStrategy("code", new RoleTableShardingAlgorithm("tb_test_role")) )
                .dataSourceRule(dataSourceRule)
                .build();

        TableRule userTableRule = TableRule
                .builder("tb_test_user")
                .dynamic(true)
                .tableShardingStrategy(new TableShardingStrategy("id", new UserTableShardingAlgorithm("tb_test_user")) )
                .dataSourceRule(dataSourceRule)
                .build();

        //分库分表策略
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(roleTableRule,userTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy("none",   new NoneDatabaseShardingAlgorithm()))
                .tableShardingStrategy(new TableShardingStrategy("none",new NoneTableShardingAlgorithm()))
                //.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", databaseShardingAlgorithm))
                //.tableShardingStrategy(new TableShardingStrategy("code", new RoleTableShardingAlgorithm("tb_test")))
                .build();

        DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
        return dataSource;
    }

}