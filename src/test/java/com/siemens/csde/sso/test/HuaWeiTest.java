package com.siemens.csde.sso.test;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
@Getter
@Setter
public class HuaWeiTest    {

    @Test
    public void testChars(){
        String test="test";
        char[] charArray=test.toCharArray();
        List charList= Arrays.asList(charArray);

        log.info("charArray size:{}",charArray.length);
    }

/*
Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(a + b);
        }
 */
/*
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0, x;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                x = sc.nextInt();
                ans += x;
            }
        }
        System.out.println(ans);
    }
}
 */

    @Test
  public  void test4(){
         int [] arr1=new int[]{1,2,3,4};
         int [] arr2=new int[]{1,2,5,3};
         int aCount=0,bCount=0;
         for(int i=0;i<4;i++){
             for(int j=0;j<4;j++){
                 if(arr1[i]==arr2[j] && i==j){
                     aCount++;
                 }
                 if(arr1[i]==arr2[j] && i!=j){
                     bCount++;
                 }
             }
         }
         System.out.println(aCount+"A"+bCount+"B");
 }
}