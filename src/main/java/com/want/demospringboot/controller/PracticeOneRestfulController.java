package com.want.demospringboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PracticeOneRestfulController {
	
	@RequestMapping("/")
	public String hello(){
		return "Hello World";
	}
	
	@GetMapping(value="/returnJson/{name}")
	@ResponseBody
	public Map<String, Object> getReturnJson(@PathVariable String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("get", name);
		return map;
	}

	@PostMapping(value="/returnJson2/{name}")
	@ResponseBody
	public Map<String, Object> postReturnJson(@PathVariable String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("post", name);
		return map;
	}
}
