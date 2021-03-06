package linked;

import java.util.Random;

public class BlockCounterSecondWay {
    // 生成一个随机的二维数组
    public static int[][] createRandomArr(int rows, int cols) {
        Random random = new Random();
        int[][] arr = new int[rows][cols];
        int[][] arrFlag = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                float n = random.nextFloat();
                arr[i][j] = Math.round(n);
                arrFlag[i][j] = 0;
            }
        }
        return arr;
    }
    public static int[][] createRandomArrCopy(int rows, int cols) {
        Random random = new Random();
        int[][] arr = new int[rows][cols];
        int[][] arrFlag = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                float n = random.nextFloat();
                arr[i][j] = Math.round(n);
                arrFlag[i][j] = 0;
            }
        }
        return arrFlag;
    }

    //设置周围的是否为1,对照数组设置为1
    public static void search(int[][]a ,int[][]aCopy,int i,int j){
        if(aCopy[i][j] ==1 || a[i][j]==0 ){
            return;
        }else {
            aCopy[i][j] = 1;
            //只判断不越界的.
            if(i+1<a[0].length && a[i+1][j]==1){//判断列长度
                search(a,aCopy,i+1,j);
            }
            if(i-1>0 && a[i-1][j]==1){
                search(a,aCopy,i-1,j);
            }
            if(j+1<a.length && a[i][j]==1){
                search(a,aCopy,i,j+1);
            }
            if(j-1>0 && a[i][j-1]==1){
                search(a,aCopy,i,j-1);
            }
        }
    }
    public static void showGrid(int[][] a) {
        for (int[] is : a) {
            for (int i : is) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }
    public static int count=0;
    public static void main(String[] args) {

        int[][] testArr = createRandomArr(4, 4);
        //标记数组:大小和元素组一样,初始化数组值均为0; 原数组如果位置上为1,则标记数组对应位置则为1,标记原数组该位置上有数值;原数组为0,则标记数组对应的位置则为2;
        int[][] testArrCopy = createRandomArrCopy(4,4);//flag数组
        System.out.println("原始数组为---");
        showGrid(testArr);

        for(int i=0;i<testArr.length;i++){
            for (int j=0;j<testArr[0].length;j++){
                if(testArr[i][j]==1 && testArrCopy[i][j] ==0){
                    count++;
                    search(testArr,testArrCopy,i,j);
                }
                else {
                    if(testArrCopy[i][j] ==0){
                        testArrCopy[i][j]=2;
                    }

                }
            }
        }
        System.out.println("标记数组为---");
        showGrid(testArrCopy);
        System.out.println("块数为---" +count);

    }
}
