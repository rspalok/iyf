package net.service.master;

import java.util.List;
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

import net.dao.master.MenuDao;
import net.model.master.GbltUserMst;
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
	public Map<String, MenuMaster> getMemuList(HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		MenuMasterPk pk=new MenuMasterPk();
		pk.setmStOrgId(org);
		pk.setmStMenuId(id);
		
		return dao.findById(pk);
	}

	@Override
	public void deleteMenuMasterById(MenuMaster menuMaster,HttpServletRequest request) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		menuMaster.setmStOrgId(org);
		menuMaster.setmStOwnerId(theUser.getIUserId());
		dao.save(menuMaster);
	}

	@Override
	public void saveMenu(MenuMaster menuMaster, HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		GbltUserMst theUser =(GbltUserMst)  session.getAttribute("user");
		String org= theUser.getStOrgId();
		menuMaster.setmStOrgId(org);
		menuMaster.setmStOwnerId(theUser.getIUserId());
		dao.save(menuMaster);
		
	}

}
