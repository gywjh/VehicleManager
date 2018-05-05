package com.ruixing.vehicle.manager.domain;


import java.util.ArrayList;
import java.util.List;

public class Menu
{
    private String id;
    
    private String url;
    
    private String text;
    
    private String icon;
    
    private String auth;
    
    private List<Menu> menus;
    
    public String getId()
    {
        return id;
    }
    
    public void setId(String id)
    {
        this.id = id;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getText()
    {
        return text;
    }
    
    public void setText(String text)
    {
        this.text = text;
    }
    
    public String getIcon()
    {
        return icon;
    }
    
    public void setIcon(String icon)
    {
        this.icon = icon;
    }
    
    public String getAuth()
    {
        return auth;
    }
    
    public void setAuth(String auth)
    {
        this.auth = auth;
    }
    
    public List<Menu> getMenus()
    {
        return menus;
    }
    
    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }
    
    public void addSubMenu(Menu menu)
    {
        if (this.menus == null)
        {
            this.menus = new ArrayList<>();
        }
        this.menus.add(menu);
    }
    
    @Override
    public String toString()
    {
        return "Menu [id=" + id + ", url=" + url + ", text=" + text + ", icon=" + icon + ", auth=" + auth + ", menus="
            + menus + "]";
    }
    
}
