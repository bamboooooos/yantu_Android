package com.example.lin9080.yantu_lin;

public class MajorToID {
    public static int M2I(String major){
        int result=0;
        switch (major){
            case "数学福州大学":
                result=1;
                break;
            case "物理福州大学":
                result=2;
                break;
            case "化学福州大学":
                result=3;
                break;
            case "心理学福州大学":
                result=4;
                break;
            case "计算机科学与技术福州大学":
                result=5;
                break;
            case "土木工程福州大学":
                result=6;
                break;
            case "电气工程福州大学":
                result=7;
                break;
            case "机械工程福州大学":
                result=8;
                break;
            case "中国语言文学福州大学":
                result=9;
                break;
            case "外国语言文学福州大学":
                result=10;
                break;
            case "新闻传播学福州大学":
                result=11;
                break;
                default:
        }
        return result;
    }
}
