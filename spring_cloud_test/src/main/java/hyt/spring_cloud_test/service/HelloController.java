package hyt.spring_cloud_test.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class HelloController {

	@Value("${hello.name}")
	private String m_name;
	
	@Value("${hello.age}")
	private int m_age;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping("name")
	public String name() {
		return m_name;
	}
	
	@RequestMapping("age")
	public int age() {
		return m_age;
	}
}
