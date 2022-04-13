package com.trongit.service;

import com.trongit.dto.RoleDTO;

import java.util.List;
import java.util.Map;

public interface IRoleService {
	List<RoleDTO> findAll();
	Map<String,String> getRoles();
}
