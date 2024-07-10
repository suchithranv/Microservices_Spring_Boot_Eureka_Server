//package com.demo.employee_app.service;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.demo.address_app.response.AddressResponse;
//import com.demo.employee_app.entity.Employee;
//import com.demo.employee_app.feignClient.AddressClient;
//import com.demo.employee_app.repository.EmployeeRepository;
//import com.demo.employee_app.response.EmployeeResponse;
//
//
//@Service
//public class EmployeeServiceImpl implements IEmployeeService{
//
//	@Autowired
//	private EmployeeRepository employeeRepo;
//	
//	@Autowired // create bean and then autowire
//	private ModelMapper modelMapper;
//	
//	//create bean before autowired, since spring boot is not auto configuring it
//	// or create constructor and initialize
////	private RestTemplate restTemplate;
//
////	@Autowired  // create bean
////	private WebClient webClient;
//	
////	@Value("{addressservice.base.url}")
////	private String addressBaseUrl;
//	
//	// when spring tries to create bean. it will invoke this constructor, so pass value in args
////	public EmployeeServiceImpl(@Value("${addressservice.base.url}") String addressBaseUrl, RestTemplateBuilder builder) {
////		this.restTemplate = builder.rootUri(addressBaseUrl).build();
////	}
//	
//	@Autowired
//	private AddressClient addressClient;
//
//	//Employee --> EmployeeResponse
//	public EmployeeResponse getEmployeeDetails(int id) {
//		
//		Employee employee = employeeRepo.findById(id).get();
//		//modelmapper will automatically maps objects of one type to another
//		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);	
//		
//		//addressResponse --> set data by making rest api call. using reactTempalate - blocking in nature
//		//AddressResponse addressResponse = restTemplate.getForObject("/addresses/{id}", AddressResponse.class, id);
//		
//		// using web client - non blocking in nature
//		//AddressResponse addressResponse = webClient.get().uri("/address/"+id).retrieve().bodyToMono(AddressResponse.class).block(); 
//
//		// using openFeign client to make external rest calls
//		AddressResponse addressResponse = addressClient.getAddressByEmployeeId(id).getBody();
//		employeeResponse.setAddressResponse(addressResponse);
//		return employeeResponse;
//
//	}
//
//}
////instead of model mapper do this
////employeeResponse.setId(employee.getId());
////employeeResponse.setName(employee.getName());
////employeeResponse.setEmail(employee.getEmail());
////employeeResponse.setBloodGroup(employee.getBloodGroup());




