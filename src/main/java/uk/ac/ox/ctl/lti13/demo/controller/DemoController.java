package uk.ac.ox.ctl.lti13.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * This is a simple controller to demonstrate how you can use data from the LTI launch.
 */
@Controller
public class DemoController {
    
    @GetMapping("/demo")
    public ModelAndView index(
            @AuthenticationPrincipal(expression = "claims['sub']") String sub,
            @AuthenticationPrincipal(expression = "claims['name']") String name
    ) {
        Map<String, Object> model = new HashMap<>();
        model.put("sub", sub);
        model.put("name", name);
        return new ModelAndView("demo", model);
    }
}
