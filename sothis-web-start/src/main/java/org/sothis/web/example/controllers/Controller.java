package org.sothis.web.example.controllers;

import java.util.List;

import org.sothis.dal.query.Cnd;
import org.sothis.web.example.dao.sql.UsersDao;
import org.sothis.web.example.domains.Users;
import org.sothis.web.example.models.HelloWorldModel;
import org.sothis.web.mvc.interceptors.param.Param;

public class Controller {

	private UsersDao usersDao;

	public Object indexAction(HelloWorldModel model, @Param(name = "message") String message) {
		List<Users> users = usersDao.find(Cnd.make("username", "sothis"));
		for (Users user : users) {
			System.out.println(user.getUsername());
		}
		System.out.println(usersDao.count());
		return model;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

}
