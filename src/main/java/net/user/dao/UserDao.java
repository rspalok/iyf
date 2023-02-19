package net.user.dao;
 

import net.model.GbltUserMst;

public interface UserDao {

    GbltUserMst findByUserName(String userName);
    
    void save(GbltUserMst user);
    
}
