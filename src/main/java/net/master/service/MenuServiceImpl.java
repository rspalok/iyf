package net.master.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.master.dao.MenuDao;
import net.model.GbltUserMst;
import net.model.GbltUsersRolesTrn;
import net.model.master.MenuMaster;
import net.model.master.MenuMasterPk;
import net.user.dao.UserDao;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	MenuDao dao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, MenuMaster> getMemuList() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("principalprincipal "+principal);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("authentication "+authentication);

		String login = authentication.getName();
		System.out.println("login "+login);

		GbltUserMst user = userDao.findByUserName(login);
		System.out.println("user "+user);
		
		System.out.println("principalprincipal "+principal);
		dao.findAll();
		return null;
	}

	@Override
	public MenuMaster getMenuMasterById(String id) {
		// TODO Auto-generated method stub
		MenuMasterPk pk=new MenuMasterPk();
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		return null;
	}

	@Override
	public void deleteMenuMasterById(MenuMaster menuMaster) {
		// TODO Auto-generated method stub
		dao.save(menuMaster);
	}

	@Override
	public void saveMenu(MenuMaster menuMaster, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		dao.save(menuMaster);
		
	}

}
