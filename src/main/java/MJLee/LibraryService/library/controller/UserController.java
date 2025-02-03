package MJLee.LibraryService.library.controller;

import MJLee.LibraryService.library.dto.UserDto;
import MJLee.LibraryService.library.entity.User;
import MJLee.LibraryService.library.service.user.CreateUserService;
import MJLee.LibraryService.library.service.user.DeleteUserService;
import MJLee.LibraryService.library.service.user.GetUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/library/user")
public class UserController {
    CreateUserService createUserService;
    DeleteUserService deleteUserService;
    GetUserService getUserService;

    public UserController(CreateUserService createUserService, DeleteUserService deleteUserService, GetUserService userService) {
        this.createUserService = createUserService;
        this.deleteUserService = deleteUserService;
        this.getUserService = userService;
    }

    @GetMapping("/{userNickName}")
    public ModelAndView showUserInfo(Model model, @PathVariable String userNickName){
        User user;
        if(getUserService.findByNickName(userNickName).isPresent()){
            user = getUserService.findByNickName(userNickName).get();
        }
        else return null;

        model.addAttribute("user",user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user-info");
        return modelAndView;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto){
        createUserService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{userNickName}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userNickName){
        if(getUserService.findByNickName(userNickName).isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        if(deleteUserService.deleteUser(userNickName)) return ResponseEntity.status(HttpStatus.OK).build();
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
