package com.siemens.csde.sso.test;
import com.google.common.collect.Lists;
import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
/*import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;*/
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
public class LogTest{

    @Test
    public void info(){
        log.info("message:{}","test");
    }
    @Test
    public void exp4j() throws  Exception{
        // 构建表达式，并声明变量定义
        ExpressionBuilder builder = new ExpressionBuilder("x/y + (x+y)*z")
                .withVariableNames("x", "y", "z");

        Calculable calc = builder.build();

        calc.setVariable("x",12);
        calc.setVariable("y",1);
        calc.setVariable("z",1);
        log.info("result:{}", calc.calculate());

    }

    @Test
    public void exp4jByJudge() throws  Exception{

        EvaluationContext ctx=new StandardEvaluationContext();
        List<String> list = new ArrayList<String>();
        list.add("value1");
        list.add("value2");
        //给变量环境增加变量
        ctx.setVariable("list", list);
        ctx.setVariable("X",49.5D);

        ctx.setVariable("STATUS","STOP");
        // 构建表达式，并声明变量定义
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("#X>=50 && #X<100");
        log.info("result:{}",exp.getValue(ctx));
        exp = parser.parseExpression("#STATUS =='STOP'");
        log.info("result:{}",exp.getValue(ctx));

    }

    /*@Test
    public void exp4j(){
        // 构建表达式，并声明变量定义
        Expression expression = new ExpressionBuilder("x/y + (x+y)*z").variables("x", "y", "z").build();
        expression.setVariable("x",12);
        expression.setVariable("y",1);
        expression.setVariable("z",1);
        log.info("result:{}",expression.evaluate());
        // 以下两种方式也可以声明变量，并直接给变量进行赋值
        *//*ExpressionBuilder.withVariable(String var,double value)
        ExpressionBuilder.withVariables(Map<String,Double> variables)*//*
    }

    @Test
    public void exp4jByJudege(){
        // 构建表达式，并声明变量定义
        Expression expression = new ExpressionBuilder("X > 50").variables("X").build();
        expression.setVariable("X",12);
        log.info("result:{}",expression.evaluate());
        // 以下两种方式也可以声明变量，并直接给变量进行赋值
        *//*ExpressionBuilder.withVariable(String var,double value)
        ExpressionBuilder.withVariables(Map<String,Double> variables)*//*
    }*/



}