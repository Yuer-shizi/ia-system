package com.ia.controllor;

import com.ia.dao.AttendanceDao;
import com.ia.entity.Attendance;
import com.ia.entity.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//跨域
@RequestMapping("/att")
public class AttendanceController {
	@Autowired
	private AttendanceDao attendanceDao;

	@PostMapping("/add")
	public RetMessage save(Attendance attendance) {
		if (attendance != null) {
			if (attendance.getId() != null) {
				return new RetMessage(null, "请假id不为空，内部错误");
			}
			attendance = attendanceDao.save(attendance);
			return new RetMessage(attendance);
		}
		return new RetMessage(null, "要添加的请假信息为空");
	}

	@PostMapping("/update")
	public RetMessage update(Attendance attendance) {
		if (attendance != null) {
			if (attendance.getId() != null) {
				attendance = attendanceDao.save(attendance);
			}
			return new RetMessage(attendance, "请假信息编号不存在，内部错误");
		}
		return new RetMessage(null, "要更新的请假信息为空");
	}

	@GetMapping("/delete/{id}")
	public RetMessage delete(@PathVariable("id") Integer id) {
		if (attendanceDao.existsById(id)) {
			attendanceDao.deleteById(id);
		} else
			return new RetMessage(null, "要删除的请假信息不存在");
		return new RetMessage(null, "删除请假信息成功");
	}
	
	@GetMapping("/list")
	public RetMessage list() {
		return new RetMessage(attendanceDao.findAll());
	}
}
