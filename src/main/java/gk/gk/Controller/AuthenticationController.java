package gk.gk.Controller;

import gk.gk.Domain.ApiResponse;
import gk.gk.Domain.AuthToken;
import gk.gk.Domain.UserLogin;
import gk.gk.Security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("generate-token")
    public ApiResponse genToken(@RequestBody UserLogin userLogin){
        String username = userLogin.getUsername();
        return new ApiResponse(HttpStatus.OK.value(), "Success", new AuthToken(jwtTokenUtil.genToken(username), username));
    }
}
