package org.hacklist.controller.admin;

import org.hacklist.model.Hack;
import org.hacklist.model.enums.Category;
import org.hacklist.model.enums.City;
import org.hacklist.service.HackService;
import org.hacklist.util.forms.HackForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.hacklist.util.transformers.HackFormTransformer.toHack;
import static org.hacklist.util.transformers.HackFormTransformer.toHackForm;

/**
 * @author Neil Alishev
 */
@Controller
@RequestMapping("/admin/hacks")
public class HackController {

    private final HackService hackService;

    @Autowired
    public HackController(HackService hackService) {
        this.hackService = hackService;
    }

    @RequestMapping("")
    public String getAll(Model model) {
        model.addAttribute("hacks", hackService.getAll());
        return "hack/hacks";
    }

    @RequestMapping("/{id}")
    public String getOne(@PathVariable("id") Long id, Model model) {
        Hack hack = hackService.getOne(id);
        model.addAttribute("hack", hack);
        return "hack/hack";
    }

    @RequestMapping("/new")
    public String getNewForm(Model model) {
        model.addAttribute("hack", new HackForm());
        fillModel(model);
        return "hack/new_hack";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute("hack") @Valid HackForm hackForm,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillModel(model);
            return "hack/new_hack";
        }
        Hack hack = hackService.add(toHack(hackForm));
        return "redirect:/admin/hacks/" + hack.getId();
    }

    @RequestMapping("/{id}/update")
    public String getUpdateForm(@PathVariable("id") Long id, Model model) {
        Hack hack = hackService.getOne(id);
        HackForm hackForm = toHackForm(hack);
        model.addAttribute("hack", hackForm);
        fillModel(model);
        return "hack/update_hack";
    }

    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("hack") @Valid HackForm hackForm,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            fillModel(model);
            return "hack/update_hack";
        }
        Hack hack = hackService.add(toHack(hackForm));
        return "redirect:/admin/hacks/" + hack.getId();
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    @ResponseBody
    public void delete(@PathVariable("id") Long id) {
        hackService.delete(id);
    }

    private void fillModel(Model model) {
        model.addAttribute("cities", City.values());
        model.addAttribute("categories", Category.values());
    }

}
