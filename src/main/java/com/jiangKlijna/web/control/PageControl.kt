package com.jiangKlijna.web.control

//import com.jiangKlijna.web.bean.User;
import com.jiangKlijna.web.bean.Create
import com.jiangKlijna.web.bean.User
import org.apache.log4j.Logger
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import javax.annotation.Resource

/**
 * Created by leil7 on 2017/6/4. ssm-blog
 */
@Controller
@RequestMapping("/")
class PageControl : BaseControl() {

    //    @Resource
    //    private RedisTemplate<String, User> redisTemplate;

    @RequestMapping("index.do")
    fun index(m: Model): String {
        session!!.getAttribute("21")
        val isLogin = false
        m.addAttribute("isLogin", isLogin)
        return "index"
    }

    @RequestMapping("sign.do")
    fun sign(): String {


        if (i++ % 2 == 0)
            return "redirect:index.do"
        else
            return "sign"
    }

//    @ResponseBody
//    @RequestMapping("/111.json")
//    fun tmp_json(m: Model): List<User> {
//        val l = arrayListOf<User>()
//        l.add(Create.user("123", "456"))
//        l.add(Create.user("741", "789"))
//        l.add(Create.user("852", "963"))
//        return l
//    }
//    @ResponseBody
//    @RequestMapping("/111.xml")
//    fun tmp_xml(m: Model): List<User> {
//        val l = arrayListOf<User>()
//        l.add(Create.user("qwe", "rty"))
//        l.add(Create.user("asd", "fgh"))
//        l.add(Create.user("zxc", "vbn"))
//        return l
//    }
    //    @RequestMapping("/index.do")
    //    public String reids(Model m) {
    //        User u0 = new User(10, "test", "test");
    //        ValueOperations<String, User> valueOper = redisTemplate.opsForValue();
    //        valueOper.set(u0.getId() + "", u0);
    //        User u1 = valueOper.get(u0.getId() + "");
    //        System.out.println(u0 == u1);
    //        System.out.println(u0.toString());
    //        System.out.println(u1.toString());
    //        return "index";
    //    }

    companion object {
        public var i = 0
    }
}
