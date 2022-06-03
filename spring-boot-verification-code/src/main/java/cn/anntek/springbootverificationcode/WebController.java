package cn.anntek.springbootverificationcode;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@Controller
public class WebController {
    //获取当前验证码图像
    @GetMapping("/verification-image")
    public void getVerificationCode(HttpServletRequest request,
                                    HttpServletResponse response) throws IOException{
        String verificationCode = VerificationCodeUtil.getVerificationCode();
        System.out.println(verificationCode);
        request.getSession().setAttribute("verification-code",verificationCode.toLowerCase());
        ImageIO.write(VerificationCodeUtil.getVerificationImage(verificationCode),"PNG",response.getOutputStream());

    }
    //验证输入的验证码
    @PostMapping("/verify")
    @ResponseBody
    public Object verify(HttpServletRequest request, @RequestParam String code){
        return code.toLowerCase().equals(request.getSession().getAttribute("verification-code"));
    }
    //起始页
    @GetMapping("")
    public String index(){
        return "index";
    }

}
