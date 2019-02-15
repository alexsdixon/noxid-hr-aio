package net.alexdixon.noxidhraio.controllers;

import net.alexdixon.noxidhraio.models.data.DepartmentDao;
import net.alexdixon.noxidhraio.models.data.RoleDao;
import net.alexdixon.noxidhraio.models.data.UserDao;
import net.alexdixon.noxidhraio.models.forms.Department;
import net.alexdixon.noxidhraio.models.forms.Role;
import net.alexdixon.noxidhraio.models.forms.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        private DepartmentDao departmentDao;

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
            model.addAttribute("departments", departmentDao.findAll());
            return "admin/user/add";
        }

        @RequestMapping (value = "add", method = RequestMethod.POST)
        public String processAddUserForm(@ModelAttribute @Valid User newUser, Errors errors,
                                         @RequestParam int roleId,@RequestParam int departmentId, Model model){

            if (errors.hasErrors()) {
                model.addAttribute("title", "ADD EMPLOYEE");
                model.addAttribute("roles", roleDao.findAll());
                model.addAttribute("departments", departmentDao.findAll());
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
            Department department = departmentDao.findOne(departmentId);
            newUser.setDepartment(department);
            userDao.save(newUser);
            return "redirect:";
        }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveUserForm(Model model) {
        model.addAttribute("users", userDao.findAll());
        model.addAttribute ("title", "Delete Employee");
        return "admin/user/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveUserForm(@RequestParam(required=false) int[] userIds, Model model) {

        if (userIds == null) {
            model.addAttribute("users", userDao.findAll());
            model.addAttribute ("title", "Delete Employee");
            model.addAttribute ("message", "Please choose an Employee to remove or go back");
            return "admin/user/remove";
        }

        for (int userId : userIds) {
            userDao.delete(userId);
        }

        return "redirect:";
    }


    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String displayEditUserrForm(Model model, @PathVariable int userId) {

        model.addAttribute("title", "Edit Employee");
        model.addAttribute("user", userDao.findOne(userId));
        model.addAttribute("departments", departmentDao.findAll());
        return "admin/user/edit";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.POST)
    public String processEditUserForm(Model model, @PathVariable int userId,
                                        @ModelAttribute  @Valid User newUser, Errors errors,
                                        @RequestParam int roleId , @RequestParam int departmentId ) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Employee");
            return "admin/user/edit";
        }

        User editedUser = userDao.findOne(userId);
        editedUser.setFirst_name(newUser.getFirst_name());
        editedUser.setDepartment(departmentDao.findOne(departmentId));
        Role role = roleDao.findOne(roleId);
        editedUser.setRoles(new HashSet<Role>(Arrays.asList(role)));
        editedUser.setLast_name(newUser.getFirst_name());
        editedUser.setEmail(newUser.getEmail());
        editedUser.setUsername(newUser.getUsername());
        editedUser.setJob_title(newUser.getJob_title());
        editedUser.setPassword(encoder.encode(newUser.getPassword()));
        userDao.save(editedUser);
        return "redirect:/admin/user";
    }


}



