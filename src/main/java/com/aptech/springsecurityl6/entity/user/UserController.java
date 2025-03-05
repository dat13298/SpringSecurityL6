package com.aptech.springsecurityl6.entity.user;

import com.aptech.springsecurityl6.entity.role.Role;
import com.aptech.springsecurityl6.entity.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") @Valid User user,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (userService.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue( "email", "error.email", "Email already registered");
            return "register";
        }

        Role role = roleService.findByName("ROLE_ADMIN");
        user.setRoles(Set.of(role));
        userService.save(user);

        return "redirect:/login";
    }
}
