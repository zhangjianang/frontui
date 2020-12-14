package com.ang.frontui.leetcode;

public class Recure {
//    public int numDecodings(String s) {
//        char[] chars = s.toCharArray();
//        if(chars.length==0){
//            return 0;
//        }
//        return trimeZero(chars,chars.length);
//    }
//
    public Integer trimeZero(char[] chars,int len){
        //1、合法
        //2、终止
        //3、缩小规模
        //4、合并结果

        if(len <=0){
            return 1;
        }

        int count =0;
        if(chars[len]>'0' ){
            count = trimeZero(chars,len-1);
        }
        if(chars[len-1]=='1'|| (chars[len-1]=='2'&& chars[len]<'7') ) {
            count += trimeZero(chars, len - 2);
        }
        return count;

    }


    int numDecodings(String s) {

        if (s.charAt(0) == '0') return 0;

        char[] chars = s.toCharArray();
        return trimeZero(chars, chars.length - 1);
    }


    // 字符串转换成字符数组，利用递归函数 decode，从最后一个字符向前递归
    int decode(char[] chars, int index) {
        // 处理到了第一个字符,只能有一种解码方法，返回 1
        if (index <= 0) return 1;

        int count = 0;

        char curr = chars[index];
        char prev = chars[index - 1];

        // 当前字符比 “0” 大，则直接利用它之前的字符串所求得的结果
        if (curr > '0') {
            count = decode(chars, index - 1);
        }

        // 由前一个字符和当前字符所构成的数字，值必须要在 1 到 26 之间，否则无法进行解码
        if (prev == '1' || (prev == '2' && curr <= '6')) {
            count += decode(chars, index - 2);
        }

        return count;
    }

}
