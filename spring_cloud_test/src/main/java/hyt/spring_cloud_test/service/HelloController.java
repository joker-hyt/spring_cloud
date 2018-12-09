package hyt.spring_cloud_test.service;


import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@Component
@RestController
public class HelloController {

	private final Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private Registration registration;
	
	@Autowired
	private DiscoveryClient client;

	@Value("${hello.name}")
	private String m_name;
	
	@Value("${hello.age}")
	private int m_age;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		ServiceInstance instance = serviceInstance();
		if(instance == null) {
			logger.error("/hello, instance is null");
		}
		else {
			logger.info("/hello, host:" + instance.getHost() + ", serviceId:" + instance.getServiceId());
		}
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
	
	public ServiceInstance serviceInstance () {
		String serviceId = registration.getServiceId();
		List<ServiceInstance> list = client.getInstances(serviceId);
		if(list != null && list.size() > 0) {
			for(ServiceInstance si : list) {
				return si;
			}
		}
		return null;
	}
}
