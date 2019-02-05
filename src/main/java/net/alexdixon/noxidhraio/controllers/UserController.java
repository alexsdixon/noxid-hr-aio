package net.alexdixon.noxidhraio.controllers;

import net.alexdixon.noxidhraio.models.data.RoleDao;
import net.alexdixon.noxidhraio.models.data.UserDao;
import net.alexdixon.noxidhraio.models.forms.Role;
import net.alexdixon.noxidhraio.models.forms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;

@Controller
    @RequestMapping("admin/user")
    public class UserController {


        @Autowired
        private UserDao userDao;

        @Autowired
        private RoleDao roleDao;

        @Autowired
        private BCryptPasswordEncoder encoder;


        @RequestMapping(value = "")
        public String index (Model model) {

            model.addAttribute("users", userDao.findAll());
            model.addAttribute("title", "EMPLOYEES");

            return "admin/user/index";
        }


        @RequestMapping(value = "add", method = RequestMethod.GET)
        public String displayaddUserForm(Model model) {
            model.addAttribute("title", "ADD EMPLOYEE");
            model.addAttribute(new User());
            model.addAttribute("roles", roleDao.findAll());
            return "admin/user/add";
        }

        @RequestMapping (value = "add", method = RequestMethod.POST)
        public String processAddUserForm(@ModelAttribute @Valid User newUser, Errors errors,
                                         @RequestParam int roleId, Model model){

            if (errors.hasErrors()) {
                model.addAttribute("title", "ADD EMPLOYEE");
                model.addAttribute("roles", roleDao.findAll());
                return "admin/user/add";
            }


            User existingUser = userDao.findByUsername(newUser.getUsername());

            if (existingUser != null) {
                errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
                return "admin/user/add";
            }


            newUser.setPassword(encoder.encode(newUser.getPassword()));
            Role role = roleDao.findOne(roleId);
            newUser.setRoles(new HashSet<Role>(Arrays.asList(role)));
            userDao.save(newUser);
            return "redirect:";
        }


    }
