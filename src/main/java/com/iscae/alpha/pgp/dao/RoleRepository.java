package com.iscae.alpha.pgp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iscae.alpha.pgp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
