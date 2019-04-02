package com.toni.userservice.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ：qinhy
 * @date ：Created in 2019/4/1 0001 18:40
 */
public class CookieUtil {

    public static void set(HttpServletResponse response,String name, String value, int maxAge){

        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name){
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    return cookie;
                }
            }
        }
        return null ;
    }

}
