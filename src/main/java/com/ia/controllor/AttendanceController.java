package com.ia.controllor;

import com.ia.dao.AttendanceDao;
import com.ia.entity.Attendance;
import com.ia.entity.RetMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin//跨域
@RequestMapping("/att")
public class AttendanceController {
	@Autowired
	private AttendanceDao attendanceDao;

	@PostMapping("/add")
	public RetMessage save(@RequestBody Attendance attendance) {
		if (attendance.getUsername() != null) {
			attendance = attendanceDao.save(attendance);
			return new RetMessage(attendance, "添加成功");
		}
		return new RetMessage(400, "要添加的请假信息为空");
	}

	@PostMapping("/update")
	public RetMessage update(Attendance attendance) {
		if (attendance.getId() != null) {
			attendance = attendanceDao.save(attendance);
			return new RetMessage(attendance, "更新成功");
		}
		return new RetMessage(attendance, "请假信息编号不存在，内部错误");
	}

	@GetMapping("/delete/{id}")
	public RetMessage delete(@PathVariable("id") Integer id) {
		if (attendanceDao.existsById(id)) {
			attendanceDao.deleteById(id);
		} else
			return new RetMessage(null, "要删除的请假信息不存在");
		return new RetMessage(null, "删除请假信息成功");
	}
	
	@GetMapping("/listAll")
	public RetMessage list() {
		return new RetMessage(attendanceDao.findAll());
	}

	@GetMapping("/list")
	public RetMessage list(Attendance attendance, int page, int size) {
		if (page == 0) page = 1;
		if (size == 0) size = 20;
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase().withIgnoreNullValues()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Page<Attendance> userPage = attendanceDao.findAll(Example.of(attendance, matcher), PageRequest.of(page - 1, size));
		return new RetMessage(userPage);
	}
}
