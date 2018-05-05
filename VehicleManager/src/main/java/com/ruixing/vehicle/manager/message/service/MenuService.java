package com.ruixing.vehicle.manager.message.service;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.ruixing.vehicle.manager.domain.Menu;


@Service
public class MenuService
{
    @SuppressWarnings("unchecked")
    public List<Menu> getMenus()
    {
        
        SAXReader sax = new SAXReader();
        Document doc = null;
        try
        {
            doc = sax.read(this.getClass().getResourceAsStream("/xml/menu.xml"));
        }
        catch (DocumentException e1)
        {
            e1.printStackTrace();
        }
        Element root = doc.getRootElement();
        List<Element> list = root.elements();
        List<Menu> menus = new ArrayList<Menu>(list.size());
        Iterator<Element> it = list.iterator();
        Menu cur = null;
        while (it.hasNext())
        {
            Element e = it.next();
            menus.add(listEle(e, cur));
        }
        return menus;
        
    }
    
    @SuppressWarnings("unchecked")
    private Menu listEle(Element e, Menu cur)
    {
        cur = getOneMenu(e);
        List<Element> subs = e.elements();
        if (subs.size() != 0)
        {
            Menu son = null;
            for (Element element : subs)
            {
                son = listEle(element, son);
                cur.addSubMenu(son);
            }
        }
        return cur;
        
    }
    
    private Menu getOneMenu(Element e)
    {
        Menu menu = new Menu();
        menu.setId(e.attributeValue("id"));
        menu.setText(e.attributeValue("text"));
        menu.setIcon(e.attributeValue("icon"));
        menu.setUrl(e.attributeValue("url"));
        menu.setAuth(e.attributeValue("auth"));
        return menu;
    }
    
}
