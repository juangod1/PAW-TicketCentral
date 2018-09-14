package ar.edu.itba.paw2018b.webapp.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.paw2018b.interfaces.service.UserService;
import ar.edu.itba.paw2018b.models.User;
import ar.edu.itba.paw2018b.models.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService us;

    @RequestMapping(value = "json/user/getCurrentUser", method = RequestMethod.GET)
    @ResponseBody
    public User currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return us.findByUsername(principal.getName()).orElseThrow(() -> new NotFoundException("No se encontro el usuario!"));
    }
}