package com.siemens.csde.sso.test;
import com.google.common.collect.Lists;
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
import org.junit.Test;
@Slf4j
public class ScannerTest  {


    public static void main(String args[]) {
        //构造Scanner类的对象scan，接收从控制台输入的信息
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入你的姓名");
        //接收一个字符串，可以加除Enter以外的所有符号，包括空格和Tab
        String name = scan.nextLine();
        System.out.println("请输入"+name+"的ID");
        Integer id = scan.nextInt();
        log.info("name:{},id:{}",name,id);
    }
}