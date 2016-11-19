package com.frank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frank.dao.UserDao;
import com.frank.dto.PageModel;
import com.frank.dto.UserModel;
import com.frank.entity.Role;
import com.frank.entity.User;
import com.frank.entity.UserRole;
import com.frank.enums.Status;
import com.frank.exception.AddUserExcetion;
import com.frank.exception.BaseExcetion;
import com.frank.exception.EditUserExcetion;
import com.frank.service.RoleService;
import com.frank.service.UserRoleService;
import com.frank.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	RoleService roleService;

	@Override
	public User queryUserByLoginName(String loginName) {
		return userDao.queryUserByLoginName(loginName);
	}

	@Override
	public PageModel<UserModel> queryUsers(int pageNum, int pageSize, String fuzzy) {
		PageHelper.startPage(pageNum, pageSize);
		List<User> users = userDao.queryUsers(fuzzy);
		List<UserRole> userRoles=userRoleService.queryUserRole();
		List<UserModel> models = new ArrayList<UserModel>();
		for (User user : users) {
			List<Role> roles=new ArrayList<Role>();
			for(UserRole userRole : userRoles){
				if (userRole.getUserID()==user.getUserID()) {
					Role role=new Role();
					role.setRoleID(userRole.getRoleID());
					role.setRoleName(userRole.getRoleName());
					roles.add(role);
				}
			}
			UserModel model = new UserModel(user, roles);
			model.setPassword("");
			models.add(model);
		}
		PageModel<UserModel> pageModel = new PageModel<UserModel>(models, new PageInfo(users));
		return pageModel;
	}

	@Override
	public void updateLastLoginTime(String loginName) {
		userDao.updateLastLoginTime(loginName);
	}

	@Override
	@Transactional
	public void addUser(UserModel userModel) throws BaseExcetion, AddUserExcetion {
		try {
			User u = queryUserByLoginName(userModel.getLoginName());
			if (null != u) {
				throw new AddUserExcetion("����û�ʧ��,�Ѿ����ڴ˵�¼����");
			}
			User user = new User();
			user.setNickName(userModel.getNickName());
			user.setLoginName(userModel.getLoginName());
			user.setPassword(userModel.getPassword());
			user.setStatus(userModel.getStatus());
			List<Role> roles = userModel.getRoles();
			userDao.addUser(user);
			for (Role role : roles) {
				userRoleService.addUserRole(user.getUserID(), role.getRoleID());
			}
		} catch (AddUserExcetion aue) {
			throw aue;
		} catch (Exception e) {
			// �ѱ������쳣 ת��Ϊ�������쳣������Ż�ع�
			throw new BaseExcetion("����û��쳣:" + e.getMessage());
		}
	}

	@Override
	public UserModel queryUserByUserID(int userID) {
		User user = userDao.queryUserByUserID(userID);
		List<Role> roles = roleService.queryRoleByUserID(userID);
		UserModel model = new UserModel(user, roles);
		model.setPassword("");
		return model;
	}

	@Override
	@Transactional
	public void editUser(UserModel userModel) throws BaseExcetion, EditUserExcetion {
		try {
			User user = new User();
			user.setUserID(userModel.getUserID());
			user.setNickName(userModel.getNickName());
			user.setLoginName(userModel.getLoginName());
			user.setPassword(userModel.getPassword());
			user.setStatus(userModel.getStatus());

			// ��ɾ���û������н�ɫ
			Integer[] userIDs = new Integer[1];
			userIDs[0] = userModel.getUserID();
			userRoleService.deleteUserRoleByUserID(userIDs);
			// ����������û��Ľ�ɫ
			List<Role> roles = userModel.getRoles();
			for (Role role : roles) {
				userRoleService.addUserRole(userModel.getUserID(), role.getRoleID());
			}
			// �������û�����Ϣ
			Integer re = userDao.editUser(user);
			if (re <= 0) {
				throw new EditUserExcetion("�༭�û�ʧ�ܡ�");
			}
		} catch (EditUserExcetion eue) {
			throw eue;
		} catch (Exception e) {
			// �ѱ������쳣 ת��Ϊ�������쳣������Ż�ع�
			throw new BaseExcetion("�༭�û��쳣:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public int deleteUser(Integer[] userIDs) {
		try {
			userRoleService.deleteUserRoleByUserID(userIDs);
			return userDao.deleteUser(userIDs);
		} catch (Exception e) {
			// �ѱ������쳣 ת��Ϊ�������쳣������Ż�ع�
			throw new BaseExcetion("�༭�û��쳣:" + e.getMessage());
		}
	}

}
