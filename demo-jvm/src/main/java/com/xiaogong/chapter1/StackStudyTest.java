package com.xiaogong.chapter1;

/**
 * @Program: xiaogong
 * @Description:
 * @Author: xiongke
 * @Create: 2024-05-29
 */
public class StackStudyTest {

    /**
     * java指令都是根据栈设计的，跨平台性、指令集小（8位）、指令多;执行性能比寄存器差
     * @param args
     */
    public static void main(String[] args) {
        /* 字节码指令
                   0: iconst_2
                   1: istore_1
                   2: iconst_3
                   3: istore_2
                   4: iload_1
                   5: iload_2
                   6: iadd
                   7: istore_3
                   8: return
         */
        int i = 2;
        int j = 3;
        int r = i + j;
    }

}
