package com.ruixing.vehicle.manager.controller;


import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruixing.vehicle.manager.domain.Menu;
import com.ruixing.vehicle.manager.message.service.MenuService;

@Controller
public class Menucontroller
{
    private Logger logger = Logger.getLogger(Menu.class);
    
    @Autowired
    private MenuService menuService;
    
    @RequestMapping("/menu")
    @ResponseBody
    public List<Menu> getMenus()
    {
        logger.info("menu");
        List<Menu> list = menuService.getMenus();
        return list;
    }
    
}
