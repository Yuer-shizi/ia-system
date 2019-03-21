package com.ia.controllor;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.ia.dao.UserDao;
import com.ia.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.ia.entity.RetMessage;

@RestController
@CrossOrigin//跨域
@RequestMapping("/user")
public class UsersController {

	private final UserDao userDao;

	@Autowired
	public UsersController(UserDao userDao) {
		this.userDao = userDao;
	}

	@PostMapping(value = "/register")
	public RetMessage register(@RequestBody User user) {
		user = userDao.findByNumberAndPassword(user.getUsername(), user.getPassword());
		if (user == null)
			return new RetMessage(400, "注册失败，用户名已存在");
		return new RetMessage(user, "Register success, Welcome" + user.getUsername());
	}

	@PostMapping(value = "/login")
	public RetMessage login(@RequestBody User user) {
		user = userDao.findByNumberAndPassword(user.getUsername(), user.getPassword());
		if (user == null)
			return new RetMessage(400, "登录失败，用户名或密码错误");
		return new RetMessage(user, "Login success, Welcome");
	}

	@PostMapping("/logout")
	public RetMessage logout() {
		HashMap<String, Integer> data = new HashMap<>();
		data.put("state", 1);
		return new RetMessage(data, "Logout success");
	}

	@GetMapping("/info")
	public RetMessage info(String username) {
		Optional<User> optionalUser = userDao.findById(username);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return new RetMessage(user, "获取" + username + "的信息成功");
		}
		return new RetMessage(400, "获取" + username + "的信息失败");
	}

	@GetMapping("/list")
	public RetMessage list(User user, int page, int size) {
		if (page == 0) page = 1;
		if (size == 0) size = 20;
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Page<User> userPage = userDao.findAll(Example.of(user, matcher), PageRequest.of(page -1, size));
		return new RetMessage(userPage);
	}

	@GetMapping("/add")
	public RetMessage save(User user) {
		if (user.getUsername() != null) {
			if (userDao.existsById(user.getNumber())) {
				return new RetMessage(400, "用户学号已存在");
			}
			// 初始化用户密码和头像
			user.setPassword("123456");
			user.setAvatar("https://i.loli.net/2018/08/18/5b7819891bab1.jpg");

			user = userDao.save(user);
			return new RetMessage(user);
		}
		return new RetMessage(400, "要添加的用户为空");
	}

	@GetMapping("/update")
	public RetMessage update(User user) {
		if (user.getNumber() != null) {
			if (userDao.existsById(user.getNumber())) {
				// 防止密码为空覆盖数据库密码
				User user1 = userDao.getOne(user.getNumber());
				user.setPassword(user1.getPassword());
				user = userDao.save(user);
			} else {
				return new RetMessage(400, "要更新的用户学号不存在");
			}
			return new RetMessage(user);
		}
		return new RetMessage(400, "要更新的用户为空");
	}

	@GetMapping("/delete")
	public RetMessage delete(@RequestParam String number) {
		if (userDao.existsById(number)) {
			userDao.deleteById(number);
		} else
			return new RetMessage(400, "要删除的用户不存在");
	    return new RetMessage(null, "删除用户成功");
	}
	@GetMapping("/batch-remove")
	public RetMessage batchRemove(@RequestParam List<String> numbers) {
		if (numbers != null && numbers.size() > 0) {
			for (String number : numbers) {
				if (userDao.existsById(number)) {
					userDao.deleteById(number);
				}
			}
		}
	    return new RetMessage("批量删除用户成功");
	}

	@GetMapping("/specialities")
	public RetMessage specialities() {
	    return new RetMessage(userDao.specialities());
	}
}

