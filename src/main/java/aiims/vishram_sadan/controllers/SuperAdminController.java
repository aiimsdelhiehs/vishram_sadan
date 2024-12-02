package aiims.vishram_sadan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aiims.vishram_sadan.dto.Response;
import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.Room;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.services.SuperAdminServices;

@RestController
@RequestMapping("/superadmin")
public class SuperAdminController {

	@Autowired
	private SuperAdminServices superAdminServices;
	
	@PostMapping("/add-vishram-sadan")
	public ResponseEntity<Object> addNewVishramSadan(@RequestBody VishramSadan sadan){
		try {
			  return new ResponseEntity<Object>(superAdminServices.addVishramSadan(sadan),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<Object> getAllUsers(){
		try {
			  return new ResponseEntity<Object>(superAdminServices.getAllUsers(),HttpStatus.OK);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/add-new-category")
	public ResponseEntity<Object> addNewCategory(@RequestBody Category category){
		try {
			  return new ResponseEntity<Object>(superAdminServices.addCategory(category),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/add-category-to-sadan/{sadanId}/{categoryId}")
	public ResponseEntity<Object> addCategoryToSadan(@PathVariable("categoryId")long categoryId,@PathVariable("sadanId")long sadanId ){
		try {
			  return new ResponseEntity<Object>(superAdminServices.addCategoryToSadan(categoryId,sadanId),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/remove-category-from-sadan/{sadanId}/{categoryId}")
	public ResponseEntity<Object> removeCategoryToSadan(@PathVariable("categoryId")long categoryId,@PathVariable("sadanId")long sadanId ){
		try {
			  return new ResponseEntity<Object>(superAdminServices.removeCategoryFromSadan(categoryId,sadanId),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/add-room-to-sadan/{sadanId}")
	public ResponseEntity<Object> addRoomToSadan(@RequestBody Room room, @PathVariable("sadanId")long sadanId ){
		try {
			  return new ResponseEntity<Object>(superAdminServices.addRoomToSadan(room,sadanId),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping("/remove-room-from-sadan/{sadanId}/{roomId}")
	public ResponseEntity<Object> removeRoomFromSadan(@PathVariable("roomId")long roomId,@PathVariable("sadanId")long sadanId ){
		try {
			  return new ResponseEntity<Object>(superAdminServices.removeRoomFromSadan(roomId,sadanId),HttpStatus.CREATED);
		} catch (Exception e) {
			  return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping(value = "/new-registration")
	public ResponseEntity<Object> newRegistration(@RequestBody User user){
		try {
			return new ResponseEntity<Object>(superAdminServices.addUser(user),HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PostMapping(value = "/assign-vishram-sadan/{sadanId}/{receptionistId}")
	public ResponseEntity<Object> assignVishramSadan(@PathVariable("sadanId")Long sadanId,@PathVariable("receptionistId")Long receptionistId){
		try {
			return new ResponseEntity<Object>(superAdminServices.assignVishramSadan(sadanId,receptionistId),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(new Response(e.getMessage()),HttpStatus.EXPECTATION_FAILED);
		}
	}
}
