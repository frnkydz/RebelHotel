package edu.unlv.cs.rebelhotel.domain;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(finders = { "findEmployersByNameLike", "findEmployersByNameEquals", "findEmployersByName", "findEmployersByLocation", "findEmployersByLocationEquals", "findEmployersByLocationLike" })
public class Employer {

    private String location;

    private String name;
}
