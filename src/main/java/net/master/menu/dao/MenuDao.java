package net.master.menu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.master.pojo.menu.MenuMaster;
import net.model.master.pojo.menu.MenuMasterPk;
 
public interface MenuDao extends JpaRepository<MenuMaster, MenuMasterPk>{

	//MenuMaster findById(String id);

}
