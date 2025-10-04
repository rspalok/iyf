package net.master.menu.service;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import net.master.menu.dao.MenuDao;
import net.model.bean.GbltUserBean;
import net.model.master.pojo.menu.MenuMaster;
import net.model.master.pojo.menu.MenuMasterPk;
import net.model.master.pojo.role.GbltUserMst;
import net.transection.login.dao.UserDao;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	MenuDao dao;
	@Autowired
	private UserDao userDao;
	
	@Override
	public Map<String, MenuMaster> getMemuList(HttpServletRequest request) {
		// TO DO Auto-generated method stub
		//HttpSession session = request.getSession(); 
		//GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
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
	public Optional<MenuMaster> getMenuMasterById(String id,HttpServletRequest request) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		MenuMasterPk pk=new MenuMasterPk();
		pk.setmStOrgId(org);
		pk.setmStMenuId(id);
		
		return dao.findById(pk);
	}

	@Override
	public void deleteMenuMasterById(MenuMaster menuMaster,HttpServletRequest request) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		menuMaster.setmStOrgId(org);
		menuMaster.setmStOwnerId(theUser.getIUserId());
		dao.save(menuMaster);
	}

	@Override
	public void saveMenu(MenuMaster menuMaster, HttpServletRequest request, HttpServletResponse response) {
		// TO DO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String org= theUser.getStOrgId();
		menuMaster.setmStOrgId(org);
		menuMaster.setmStOwnerId(theUser.getIUserId());
		dao.save(menuMaster);
		
	}

}
