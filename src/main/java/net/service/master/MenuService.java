package net.service.master;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.model.master.MenuMaster;

public interface MenuService {

	Map<String, MenuMaster> getMemuList(HttpServletRequest objRequest_p);

	Optional<MenuMaster> getMenuMasterById(String id,HttpServletRequest objRequest_p);

	void deleteMenuMasterById(MenuMaster menuMaster,HttpServletRequest objRequest_p);

	void saveMenu(MenuMaster menuMaster, HttpServletRequest request, HttpServletResponse response);


}
