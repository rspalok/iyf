package net.master.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.model.master.MenuMaster;

public interface MenuService {

	Map<String, MenuMaster> getMemuList();

	MenuMaster getMenuMasterById(String id);

	void deleteMenuMasterById(MenuMaster menuMaster);

	void saveMenu(MenuMaster menuMaster, HttpServletRequest request, HttpServletResponse response);


}
