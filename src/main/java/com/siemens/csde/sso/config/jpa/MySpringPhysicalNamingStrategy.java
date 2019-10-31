package com.siemens.csde.sso.config.jpa;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.beans.BeansException;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryAccessor;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

public class MySpringPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    private final SpelExpressionParser parser = new SpelExpressionParser();

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        System.out.println("toPhysicalCatalogName");
        return super.toPhysicalCatalogName(name, jdbcEnvironment);
    }
    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        System.out.println("toPhysicalSchemaName"+name.getText());
        return super.toPhysicalSchemaName(name, jdbcEnvironment);
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        String nameStr = name.getText();
        System.out.println("toPhysicalTableName:"+nameStr);
        if(nameStr.contains(ParserContext.TEMPLATE_EXPRESSION.getExpressionPrefix())){
            //参考SimpleElasticsearchPersistentEntity 实现思想,将tableName参数的值支持表达式获取
            Expression expression = this.parser.parseExpression(nameStr, ParserContext.TEMPLATE_EXPRESSION);
            return Identifier.toIdentifier((String)expression.getValue(this.context, String.class));
        }else {
            //默认方式不变
            return super.toPhysicalTableName(name, jdbcEnvironment);
        }
    }

}