package fr.gamedev.question.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author djer13
 *
 */
@Controller()
@RequestMapping("user")
public class GameDevUserController {

    @GetMapping("preferences")
    public String showPreferences(Model model) {

        String userLogin = "test";

        model.addAttribute("userId", userLogin);

        return "preferences";
    }

}
