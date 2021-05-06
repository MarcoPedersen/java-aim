package net.codejava.Controllers;

import net.codejava.Models.Shop;
import net.codejava.Services.ShopService;
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
public class ShopController {

    @Autowired
    private ShopService service;

    @RequestMapping("/shopsList")
    public String showShopList(Model model) {
        List<Shop> listShops = service.listAll();
        model.addAttribute("listShops", listShops);

        return "shop_list";
    }

    @RequestMapping("/newShop")
    public String showNewShopPage(Model model) {
        Shop shop = new Shop();
        model.addAttribute("shop", shop);

        return "new_shop";
    }
    @RequestMapping(value = "/saveShop", method = RequestMethod.POST)
    public String saveShop(@ModelAttribute("shop") Shop shop) {
        service.save(shop);

        return "redirect:/shopsList";
    }
    @RequestMapping("/editShop/{id}")
    public ModelAndView showEditShopPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("edit_shop");
        Shop shop = service.get(id);
        mav.addObject("shop", shop);

        return mav;
    }
    @RequestMapping("/deleteShop/{id}")
    public String deleteShop(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/shopsList";
    }
}