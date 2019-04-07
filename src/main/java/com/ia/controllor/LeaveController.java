package com.ia.controllor;

import com.ia.dao.LeaveDao;
import com.ia.entity.Leave;
import com.ia.entity.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ShiLei on 2019/3/21
 */
@RestController
@CrossOrigin
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveDao leaveDao;

	@GetMapping("/add")
	public RetMessage add(Leave leave) {
		if (leave.getUsername() == null)
			return new RetMessage(400,"请求参数异常");
		leave = leaveDao.save(leave);
		return new RetMessage(leave, "申请提交成功");
	}

	@GetMapping("/update")
	public RetMessage update(Leave leave) {
		if (leave.getId() != null) {
			if (leaveDao.existsById(leave.getId())) {
				leave = leaveDao.save(leave);
			} else {
				return new RetMessage(400, "要更新的用户学号不存在");
			}
			return new RetMessage(leave);
		}
		return new RetMessage(400, "要更新的用户为空");
	}

	@GetMapping("/findLeaver")
	public RetMessage findLeaver(Leave leave) {
		List<Leave> leavers = leaveDao.findLeaver(leave.getSpeciality(), leave.getDate1(), leave.getClass1());
		return new RetMessage(leavers, "申请提交成功");
	}

	@GetMapping("/list")
	public RetMessage list(Leave leave, int page, int size) {
		if (page == 0) page = 1;
		if (size == 0) size = 20;
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Page<Leave> userPage = leaveDao.findAll(Example.of(leave, matcher), PageRequest.of(page - 1, size));
		return new RetMessage(userPage);
	}
}
