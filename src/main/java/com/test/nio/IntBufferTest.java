package com.test.nio;

import java.nio.IntBuffer;

/**
 * Created by mayn on 2017/11/20.
 */
public class IntBufferTest  {

    public static void main(String[] args) {
        //1、基本操作
        //创建指定长度10的缓冲区
        IntBuffer buffer = IntBuffer.allocate(10);
        buffer.put(11);
        buffer.put(5);
        buffer.put(32);
        System.out.println(buffer);
        //把位置复位为0，也就是position位置由3->0
        buffer.flip();
        //比较未调用flip方法和调用之后buffer的limit可以发现，不进行复位操作的话，position的值为3，limit的值为10
        // 因为缓冲区中已有11、5、32三个元素，也就意味着put()方法会使position向后递增1
        System.out.println("调用flip复位方法后的buffer：" + buffer);
        System.out.println("buffer容量为：" + buffer.capacity());
        System.out.println("buffer限制为：" + buffer.limit());
        System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("调用get(index)方法后的buffer：" + buffer); //调用get(index)方法，不会改变position的值
        buffer.put(1, 4); //将buffer位置为1的值替换为4，调用put(index，value)不会改变position的值
        //System.out.println("获取下标为1的元素：" + buffer.get(1));
        System.out.println("调用put(index, value)方法后的buffer：" + buffer);
        for(int i=0; i<buffer.limit(); i++) {
            //调用get方法会使缓冲区的位置(position)向后递增一位
            System.out.println(buffer.get());
        }
        System.out.println("buffer对象遍历之后buffer为：" + buffer);

        //2、wrap方法的使用
        int[] arr = new int[]{1, 2, 3};
        IntBuffer buffer1 = IntBuffer.wrap(arr);
        System.out.println("wrap(arr)方法："+buffer1);
        //IntBuffer.wrap(array, postion, length)表示容量为array的长度，但是可操作的元素为位置postion到length的数组元素
        buffer1 = IntBuffer.wrap(arr, 0, 2);
        System.out.println("wrap(arr, 0, 2)：" + buffer1);

        //3、其他方法
        IntBuffer buffer3 = IntBuffer.allocate(10);
        int[] arr3 = new int[]{1, 2, 3};
        buffer3.put(arr3);
        System.out.println("调用put(arr)方法后的buffer：" + buffer3);
        //一种复制方法，buffer1的pos、lim、cap与buffer的一样
        IntBuffer buffer4 = buffer.duplicate();
        System.out.println("buffer4：" + buffer4);

        buffer.position(1); //将buffer的position设置为1，不建议使用。功能相当于flip()方法，但是从运行结果可以看出，lim依然等于10
        System.out.println("调用position()方法后的buffer：" + buffer4);
        System.out.println("buffer的可读数据量：" + buffer4.remaining()); //计算出从pos到lim的长度
        int[] arr1 = new int[buffer4.remaining()];
        //将缓冲区的数据放入arr1中
        buffer.get(arr1);
        for(Integer i : arr1) {
            System.out.print(Integer.toString(i) + ",");
        }
        System.out.println();

        //比较flip()方法和position(index)方法的区别
        buffer4.flip();
        System.out.println("buffer4的可读数量：" + buffer4.remaining());
        arr1 = new int[buffer4.remaining()];
        buffer4.get(arr1);
        for(Integer i : arr1) {
            System.out.print(Integer.toString(i) + ",");
        }
    }
}


