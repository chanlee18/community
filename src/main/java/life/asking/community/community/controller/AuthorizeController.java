package life.asking.community.community.controller;

import life.asking.community.community.dto.AccessTokenDTO;
import life.asking.community.community.dto.GithubUser;
import life.asking.community.community.mapper.UserMapper;
import life.asking.community.community.model.User;
import life.asking.community.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        //判断user是否为空，否则会提示"空指针异常"（当user为空时，不可调用getName方法）
        if(null != githubUser){
            //登录成功，写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setToken(token);
            userMapper.insert(user);          //插入数据库，相当于写入session
            response.addCookie(new Cookie("token",token));
            System.out.println(token);
            return "redirect:/";
        }else{
            //登录失败，重新登录
            return "redirect:/";
        }
    }
}
