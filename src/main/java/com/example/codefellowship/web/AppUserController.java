package com.example.codefellowship.web;

import com.example.codefellowship.domain.ApplicationUser;
import com.example.codefellowship.domain.Post;
import com.example.codefellowship.infrastructure.ApplicationUserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@Controller
public class AppUserController {
    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getHomePage(){
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return
                "login";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String signupUser(HttpServletRequest request, Model model, @RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){
        ApplicationUser applicationUser = new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
     model.addAttribute("user",applicationUser);
        applicationUserRepository.save(applicationUser);

        try {
            request.login(username,password);

        }catch (ServletException exception){
            System.out.println(exception.getMessage());
        }
        return "redirect:/myprofile";
}

@GetMapping ("/home")
        public String getHome(Model model , Authentication authentication){

    ApplicationUser user= (ApplicationUser) authentication.getPrincipal();
        model.addAttribute("user",applicationUserRepository.getById((user.getId())));

        return "home";

}
@GetMapping("/myprofile")
    String getProfile(Model model,Authentication authentication){
        model.addAttribute("user",authentication.getPrincipal());
        return "myprofile";
}
@GetMapping("/user/{id}")
    String getprof(Model model, @PathVariable Long id){
        model.addAttribute("user",applicationUserRepository.getById(id));
        return "myprofile";
}
@GetMapping("/users")
    String getUsers(Model model){
        model.addAttribute("users",applicationUserRepository.findAll());
        return "allusers";

}
@PostMapping("/post")
    String addPost(@RequestParam String body,Model model,@RequestParam Long id){
    Post post=new Post(body);
    ApplicationUser applicationUser=applicationUserRepository.getById(id);
    applicationUser.getPosts().add(post);
    applicationUserRepository.save(applicationUser);


        return "redirect:/home";
}

@GetMapping("follow_me/{id}")
    String FollowUser(@PathVariable Long id ,Model model,Authentication authentication){
        ApplicationUser userLogged= (ApplicationUser) authentication.getPrincipal();
        ApplicationUser userFromDataBase=applicationUserRepository.getById(userLogged.getId());
        ApplicationUser userToFollow=applicationUserRepository.getById(id);
        if (userToFollow.getId()!= userLogged.getId()){
            userToFollow.getFollower().add(userFromDataBase);
            userFromDataBase.getFollowingMe().add(userToFollow);
            applicationUserRepository.save(userToFollow);
            applicationUserRepository.save(userFromDataBase);
        }
        return "redirect:/users";
}

@GetMapping("/feed")
    String getPostsFollowinUser(Model model ,Authentication authentication){
    ApplicationUser userLogged= (ApplicationUser) authentication.getPrincipal();
    ApplicationUser userFromDataBase=applicationUserRepository.getById(userLogged.getId());
    model.addAttribute("userPostsIfollowing",userFromDataBase.getFollowingMe());
    return "feed";
}
}
