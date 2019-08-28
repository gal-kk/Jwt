package gk.gk.Controller;

import gk.gk.Domain.ApiResponse;
import gk.gk.Domain.UserDto;
import gk.gk.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ApiResponse getAll(){
        return new ApiResponse(HttpStatus.OK.value(), "adsfsdfasdfsdaf", userService.findAll());
    }

    @PostMapping
    public ApiResponse createOne(@RequestBody UserDto userDto){
        return new ApiResponse(HttpStatus.OK.value(), "adsfsdfasdfsdaf", userService.save(userDto));
    }

    @GetMapping("{id}")
    public ApiResponse getAll(@PathVariable int id){
        userService.findById(id);
        return new ApiResponse(HttpStatus.OK.value(), "adsfsdfasdfsdaf", userService.findAll());
    }

}
