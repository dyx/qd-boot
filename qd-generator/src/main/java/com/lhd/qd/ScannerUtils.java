package com.lhd.qd;

import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author lhd
 */
public class ScannerUtils {

    public static String notBlankScanner(String tip) {

        System.out.println("请输入" + tip + "：");
        Scanner scanner = new Scanner(System.in);
        String content = "";
        while (scanner.hasNextLine()) {

            content = scanner.nextLine();
            if (StringUtils.isNotBlank(content)) {
                break;
            }

            System.out.println("请输入有效的" + tip + "：");
        }

        return content;
    }

    public static String scanner(String tip) {
        System.out.println(tip + "：");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
