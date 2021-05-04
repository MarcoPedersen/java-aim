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
    public String viewHomePage() {
        return "index";
    }
    @RequestMapping("/fieldsList")
    public String showFieldsList(Model model) {
        List<Field> listFields = service.listAll();
        model.addAttribute("listFields", listFields);

        return "field_list";
    }
    @RequestMapping("/newField")
    public String showNewFieldPage(Model model) {
        Field field = new Field();
        model.addAttribute("field", field);

        return "new_field";
    }
    @RequestMapping(value = "/saveField", method = RequestMethod.POST)
    public String saveField(@ModelAttribute("field") Field field) {
        service.save(field);

        return "redirect:/fieldsList";
    }
    @RequestMapping("/editField/{id}")
    public ModelAndView showEditFieldPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_field");
        Field field = service.get(id);
        mav.addObject("field", field);

        return mav;
    }
    @RequestMapping("/deleteField/{id}")
    public String deleteField(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/fieldsList";
    }
}
