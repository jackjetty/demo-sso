package com.siemens.csde.sso.test;
public class TestInfo{
     /*List<String> list = new ArrayList<String>();
Object[] array=list.toArray();
　　上述方法存在强制转换时会抛异常，下面此种方式更推荐：可以指定类型

String[] array=list.toArray(new String[list.size()]);
          */

     /*冒泡排序
     for(i=0;i<length-1;i++)
    for(j=i+1;j<length;j++)
    if(arrayVal[i]>arrayVal[j])
     {
             //置换位置
             temp=arrayVal[i];
             arrayVal[i]=arrayVal[j];
             arrayVal[j]=temp;
         }
}
      */
     /*
     选择排序

     for(int i = 0; i < arr.length - 1; i++) {        int k = i;    for(int j = k + 1; j < arr.length; j++){        if( arr[j] < arr[k]){        k =j;      }        //在内层循环结束，也就是找到本轮循环的最小的数以后，再进行交换    if( i != k) {     int temp= arr[i];    arr[i] = arr[k];    arr[k] = temp;    }
      */
     /*插入排序
     public  static void insertionSort(int[] a){

          for(int i = 1 ; i < a.length; i++){

              int temp = a[i];
              int j = i - 1;
              while( j >= 0 && temp < a[j]){
                   a[j+1] = a[j];
                   j--;
              }
              a[j+1] = temp;
     }

      */
     /*
     shell排序
      //希尔排序
     public static void shellSort(int a[]){

          for(int r = a.length/2 ; r >= 1; r/=2 ){

              for(int i = r; i < a.length ; i++){

                   int temp = a[i];
                   int j = i - r;

                   while(j >= 0 && temp < a[j]){
                        a[j+r] = a[j];
                        j -= r;
                   }

                   a[j+r] = temp;
              }
          }
     }
      */
     /*
     快速排序
     public static void quickSort3(int arr[],int _left,int _right){

        int left = _left;
        int right = _right;
        int temp = 0;

        if(left <= right){   //待排序的元素至少有两个的情况

            temp = arr[left];  //待排序的第一个元素作为基准元素
            while(left != right){   //从左右两边交替扫描，直到left = right
                while(right > left && arr[right] >= temp){
                   right --;        //从右往左扫描，找到第一个比基准元素小的元素
                }
                arr[left] = arr[right];
                while(left < right && arr[left] <= temp){
                   left ++;         //从左往右扫描，找到第一个比基准元素大的元素
                }

                arr[right] = arr[left];
            }
            arr[right] = temp;    //基准元素归位
            quickSort3(arr,_left,left-1);  //对基准元素左边的元素进行递归排序
            quickSort3(arr, right+1,_right);  //对基准元素右边的进行递归排序

        }
    }
      */
     /*
     推排序
     public class HeapSort {

    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void sort(int []arr){
        //1.构建大顶堆
        for(int i=arr.length/2-1;i>=0;i--){
            adjustHeap(arr,i,arr.length);
        }

        for(int j=arr.length-1;j>0;j--){
            swap(arr,0,j);
            adjustHeap(arr,0,j);
        }
    }

    // 调整大顶堆
    public static void adjustHeap(int []arr,int i,int length){
        int temp = arr[i];
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length && arr[k]<arr[k+1]){
                k++;
            }
            if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }
    //交换元素
    public static void swap(int []arr,int a ,int b){
        int temp=arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
      */
     /*
     合并排序
     public class MergeSort {

    public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr,0,arr.length-1,temp);
    }

    private static void sort(int[] arr,int left,int right,int []temp){
        if(left<right){
            int mid = (left+right)/2;
            sort(arr,left,mid,temp);//左边归并排序，使得左子序列有序
            sort(arr,mid+1,right,temp);//右边归并排序，使得右子序列有序
            merge(arr,left,mid,right,temp);//将两个有序子数组合并操作
        }
    }

    private static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;
        int j = mid+1;
        int t = 0;
        while (i<=mid && j<=right){
            if(arr[i]<=arr[j]){
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }
        while(i<=mid){
            temp[t++] = arr[i++];
        }
        while(j<=right){
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while(left <= right){
            arr[left++] = temp[t++];
        }
    }
}
      */

     /*
     黄建峰：

您好！我是华为公司智能安防的招聘接口刘贝家，感谢您近期与我们华为IT产品线智能安防领域的沟通和交流。

现诚邀您完成华为机考试题，请您抽空完成。（注：如您在其他部门已做过这个考试，请不要进入重做，并知会到我，谢谢配合）

以下是网站地址，请您在北京时间2019年10月27日24:00前完成测评 。完成后，请及时知会我：）

考试链接： https://exam.nowcoder.com/cts/17043507/summary?id=8AD31FBE427AB015



【温馨提醒】：

附：考前须知部分内容（考生在答题前必须阅读考前须知内容后才能进行下一步操作）：

1、请您选择网络状态好，环境轻松的情况下完成测评，此测试很重要，测试结果作为关键参考；

2、请使用最新版chrome浏览器作答（72版本以上），考试需开启摄像头，请确保电脑带有摄像头；

3、考试链接打开后，首先看到考前须知等内容，然后按照系统指导，填写个人信息后，选择开始答题，才能看到题目，同时开始计时。

4、编程题支持本地IDE编码后复制粘贴至考试页面，不做跳出限制，进入考试界面查看题目后可以跳出界面，在本地编译调试代码，编好后再复制到考试界面保存运行，在考试界面直接写代码也行。

5、考试时长60分钟，共一道编程题，答题前请注意查看示例（计入考试时长），不限制提交代码次数（以提交中最高得分计分），代码完成后请务必点击【保存并调试】按扭（可多次操作），否则答案不作保存，调试完成点击【提交本题型】（只能操作一次），提交后不能返回，最后交卷；

6、考试时允许使用草稿纸，请提前准备纸笔。考试过程中允许上厕所等短暂离开，但请控制离开时间；

7、考试期间如遇到断电、断网、死机等问题，可以关闭浏览器重新打开试卷链接即可继续做题；

8、遇到问题请及时反馈给考试主办方
      */
}