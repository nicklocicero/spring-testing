package com.nicholaslocicero.portfolio;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

  @Value("${spring.application.name}")
  String appName;

  @GetMapping("/")
  public String homePage(Model model) {
    String[] words = new String[] {"abc", "dec", "cbc", "g"};
    Generator generator = new Generator(words, new Random());
    model.addAttribute("appName", appName);
    model.addAttribute("passphrase", Arrays.toString(generator.next(6, true)));
    return "home";
  }

}
