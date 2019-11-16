package com.sda.auction.repository;

import com.sda.auction.model.Role;
import com.sda.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Halip on 10.11.2019.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(String roleName);
}
