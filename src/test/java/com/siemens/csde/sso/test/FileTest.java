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

public class FileTest    {
/*
public class inputInformation {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);	//构造Scanner类的对象scan，接收从控制台输入的信息
		System.out.println("请输入你的姓名");
		String name = scan.nextLine();//接收一个字符串，可以加除Enter以外的所有符号，包括空格和Tab
		System.out.println("请输入你的ID");
		String ID ;
		while(scan.hasNextLine()) {// hasNextLine()方法判断当前是否有输入，当键盘有输入后执行循环
			if(scan.hasNextInt()) {// 判断输入的值是否为整数类型，当为整数类型时执行循环
				ID = scan.nextLine();
				System.out.println("你输入的姓名为："+name);
				System.out.println("你输入的ID为："+ID);
				break;
			}else {
				System.out.println("请输入数字哦！");
				ID = scan.nextLine();
				continue;
			}
		}
	}
}
————————————————
版权声明：本文为CSDN博主「规则固态长方体物质空间移动工程师」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_40164190/article/details/81917208
 public class Text {
	public static void main(String []args) {
		Scanner input = new Scanner(System.in);
		System.out.println("请输入一个字符串(中间能加空格或符号)");
		String a = input.nextLine();
		System.out.println("请输入一个字符串(中间不能加空格或符号)");
		String b = input.next();
		System.out.println("请输入一个整数");
		int c;
		c = input.nextInt();
		System.out.println("请输入一个double类型的小数");
		double d = input.nextDouble();
		System.out.println("请输入一个float类型的小数");
		float f = input.nextFloat();
		System.out.println("按顺序输出abcdf的值：");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println(f);
	}
}
————————————————
版权声明：本文为CSDN博主「规则固态长方体物质空间移动工程师」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_40164190/article/details/81917208*/

    public static void main(String[] args){

        /*Scanner scanner= new Scanner(System.in);
        String line = scanner.nextLine();
        String[] firstLine=line.split(" ");
        int n=Integer.parseInt(firstLine[0]);
        int m=Integer.parseInt(firstLine[1]);
        int count=0;
        for (int i = 0; i < n; i++) {
            int b = scanner.nextInt();
            if(m>=b-30) {
               count++;
            }
        }
        System.out.println(count);*/

        /*Scanner scanner= new Scanner(System.in);
        int n=scanner.nextInt();*/
        int n=4;
        List<String> data=new ArrayList<String>();
        /*for(int i=0;i<n;i++){
            String line = scanner.nextLine();
            data.add(line);
        }*/

        data.add("4");
        data.add("32");
        data.add("12");
        data.add("987");

        Collections.sort(data);
        StringBuffer result=new StringBuffer("");
        for(int i=0;i<n;i++){
            result.append(data.get(n-1-i));

        }
        System.out.println(result.toString());


    }
}

