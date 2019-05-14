package hello;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormSubController {

    @RequestMapping("/testform")
    public String index(HttpServletRequest request) {
        String token = TokenProccessor.getInstance().makeToken();//创建令牌
        System.out.println("在FormServlet中生成的token："+token);
        request.getSession().setAttribute("token", token);  //在服务器使用session保存token(令牌)

        return "testForm";
    }
    @RequestMapping("/vaSubmiit")
    public String vaSubmiit(HttpServletRequest request) {
        boolean b = isRepeatSubmit(request);//判断用户是否是重复提交
        if(b==true){
            System.out.println("请不要重复提交--跳转一个提示的页面提示不允许重复提交");
            return "cfSumiit";
        }
        request.getSession().removeAttribute("token");//移除session中的token
        System.out.println("否则提示成功---处理用户提交请求！！--比如跳转到提交成功的页面");
        return "formSucc";
    }
    /**
     * 判断客户端提交上来的令牌和服务器端生成的令牌是否一致
     * @param request
     * @return 
     *         true 用户重复提交了表单 
     *         false 用户没有重复提交表单
     */
    private boolean isRepeatSubmit(HttpServletRequest request) {
        String client_token = request.getParameter("token");
        //1、如果用户提交的表单数据中没有token，则用户是重复提交了表单
        if(client_token==null){
            return true;
        }
        //取出存储在Session中的token
        String server_token = (String) request.getSession().getAttribute("token");
        //2、如果当前用户的Session中不存在Token(令牌)，则用户是重复提交了表单
        if(server_token==null){
            return true;
        }
        //3、存储在Session中的Token(令牌)与表单提交的Token(令牌)不同，则用户是重复提交了表单
        if(!client_token.equals(server_token)){
            return true;
        }
        
        return false;
    }

}
