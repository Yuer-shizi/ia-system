package com.ia.controllor;

import com.ia.dao.LeaveDao;
import com.ia.entity.Leave;
import com.ia.entity.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/FindLeaver")
	public RetMessage findLeaver(Leave leave) {
		leave = leaveDao.findLeaver(leave.getSpeciality(), leave.getDate1(), leave.getClass1());
		return new RetMessage(leave, "申请提交成功");
	}
}
