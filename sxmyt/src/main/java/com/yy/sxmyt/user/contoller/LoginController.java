package com.yy.sxmyt.user.contoller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//import com.yy.sxmyt.config.rabbitmq.Receiver;
//import com.yy.sxmyt.config.rabbitmq.Sender;
import com.yy.sxmyt.entity.UserEntity;
import com.yy.sxmyt.user.service.UserService;

@Controller
@RequestMapping("user")
public class LoginController {

	@Autowired
	private UserService userService;

//	@Autowired
//    private Sender sender;

	/**
	 * 登入
	 * @param name
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/userlogin")
	public String userLogin(@RequestParam("loginname") String name , @RequestParam("loginpwd") String pwd , HttpSession session) {
		UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken( name , pwd );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);   //完成登录
            UserEntity user = (UserEntity) subject.getPrincipal();
            session.setAttribute("user", user);
            return "TheIndex";
        } catch(Exception e) {
            return "login";//返回登录页面
        }
	}
	
	@RequestMapping("/logOut")
    public String logOut(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        session.removeAttribute("user");
        return "login";
    }
	
	@RequestMapping("/findlist")
	@ResponseBody
	public List<UserEntity> finduserlist( String msg ){
		
//		 sender.send();
		
		return userService.findByPage( 1 , 2 );
	}
	
	
//	@Autowired
//	private Receiver receiver;
	
//	@RequestMapping("/re")
//	public String re(){
		
//		receiver.process("foo");
		
//		return "SUCCESS";
//	}
	
	
	
	
}
