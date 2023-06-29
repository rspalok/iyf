package net.dao.master;

import org.springframework.data.jpa.repository.JpaRepository;

import net.model.master.MenuMaster;
import net.model.master.MenuMasterPk;
 
public interface MenuDao extends JpaRepository<MenuMaster, MenuMasterPk>{

	//MenuMaster findById(String id);

}
