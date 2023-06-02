package ru.maxima.homework35.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.maxima.homework35.entity.Role;
import ru.maxima.homework35.entity.User;
import ru.maxima.homework35.service.UserService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  private final UserService userService;

  @GetMapping
  public String adminPage(Model model) {
    model.addAttribute("users", userService.findAll());
    return "admin";
  }

  @GetMapping("edit/{id}")
  public String editPage(@PathVariable("id") Long id, Model model) {
    User user = userService.getById(id);
    model.addAttribute("user", user);
    model.addAttribute("roles", Role.values());
    return "editPage";
  }

  @PostMapping("edit")
  public ModelAndView editUser(@ModelAttribute("user") User user) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:/admin");
    userService.update(user);
    return modelAndView;
  }

  @GetMapping("/add")
  public ModelAndView addPage() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("addPage");
    modelAndView.addObject("user", new User());
    modelAndView.addObject("roles", Role.values());
    return modelAndView;
  }

  @PostMapping("/add")
  public ModelAndView addUser(@ModelAttribute("user") User user) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:/admin");
    userService.save(user);
    return modelAndView;
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteUser(@PathVariable("id") Long id) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("redirect:/admin");
    userService.deleteById(id);
    return modelAndView;
  }
}
