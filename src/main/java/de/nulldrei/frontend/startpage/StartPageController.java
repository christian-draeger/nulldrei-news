package de.nulldrei.frontend.startpage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StartPageController {

    @Autowired
    private StartPageService startPageService;

    @RequestMapping("/")
    public ModelAndView welcome() {
        return createView();
    }

    @RequestMapping("/blog")
    public ModelAndView blog() {
        return createViewBlog();
    }

    private ModelAndView createView() {
        StartPageModel model = startPageService.create();
        return new ModelAndView("startpage", "pageModel", model);
    }

    private ModelAndView createViewBlog() {
        StartPageModel model = startPageService.create();
        return new ModelAndView("page-blog-posts", "pageModel", model);
    }
}
