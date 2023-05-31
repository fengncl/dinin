package com.ttttt.core.util;

import cn.hutool.core.util.ReUtil;

import java.util.regex.Pattern;

public class Tool {


    /**
     * 判断是否是邮箱格式
     *
     * @param email
     * @return
     */
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return ReUtil.isMatch(emailRegex, email);
    }

    /**
     * 判断是否是手机号
     *
     * @param phone
     * @return
     */
    public static boolean isValidPhone(String phone) {
        String phoneRegex = "^0\\d{10}$";
        return ReUtil.isMatch(phoneRegex, phone);
    }


    /**
     * 判断是否是邮编
     * @param postalCode
     * @return
     */
    public static boolean isValid(String postalCode) {
        Pattern pattern = Pattern.compile("^0\\d{5}$");
        return pattern.matcher(postalCode).matches();
    }

}