package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FieldController {

    @Autowired
    private FieldService service;

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        List<FieldModel> listFields = service.listAll();
        model.addAttribute("listFields", listFields);

        return "index";
    }
    @RequestMapping("/new")
    public String showNewFieldPage(Model model) {
        FieldModel fieldModel = new FieldModel();
        model.addAttribute("fieldModel", fieldModel);

        return "new field";
    }
    @RequestMapping(value= "/save", method = RequestMethod.POST)
    public String saveField(@ModelAttribute("FieldModel") FieldModel fieldModel) {
        service.save(fieldModel);

        return "redirect:/";
    }
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditFieldPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit field");
        FieldModel fieldModel = service.get(id);
        mav.addObject("fieldModel", fieldModel);

        return mav;
    }
    @RequestMapping("/delete/{id}")
    public String deleteField(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
