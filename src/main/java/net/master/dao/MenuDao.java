package net.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.model.master.MenuMaster;
import net.model.master.MenuMasterPk;

@Repository
public interface MenuDao extends JpaRepository<MenuMaster, MenuMasterPk>{

	//MenuMaster findById(String id);

}
