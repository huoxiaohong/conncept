package com.concept.shiro.realm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class Login {
    @RequestMapping("gogo")
    public ModelAndView gogo(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, ModelAndView mav) {

        System.err.println(request.getRequestURL());
        Subject currentUser = SecurityUtils.getSubject();
        String userName = request.getParameter("user");
        String password = request.getParameter("password");
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                mav.setViewName("redirect:classify.do");
                return mav;
            }

        } catch (Exception uae) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("user", request.getParameter("user"));
            map.put("error", "用户或密码不正确");
            mav.addObject("map", map);
            mav.setViewName("login");
            return mav;
        }
        return mav;

    }

}
