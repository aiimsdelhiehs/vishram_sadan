package aiims.vishram_sadan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.Room;
import aiims.vishram_sadan.entities.User;
import aiims.vishram_sadan.entities.VishramSadan;
import aiims.vishram_sadan.exceptions.ResourceNotFound;
import aiims.vishram_sadan.repositories.CategoryRepository;
import aiims.vishram_sadan.repositories.RoomRepository;
import aiims.vishram_sadan.repositories.UserRepository;
import aiims.vishram_sadan.repositories.VishramSadanRepository;
import aiims.vishram_sadan.util.ApplicationConstant;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class SuperAdminServices {
	
	private static final Logger logger = LogManager.getLogger(SuperAdminServices.class);

	@Value("${app.superadmin.username}")
    private String username;
	
	@Value("${app.superadmin.name}")
    private String name;
	
	@Value("${app.superadmin.password}")
    private String password;
    
    @Value("${app.superadmin.contactNo}")
    private String contactNo;
    
    @Value("${app.superadmin.email}")
    private String email;
    
    @Value("${app.refferer.username}")
    private String refferer;
    
    @Value("${app.refferer.name}")
    private String reffererName;
    
    @Value("${app.refferer.contactNo}")
    private String reffererContactNo;
    
    @Value("${app.refferer.email}")
    private String reffererEmail;
	
	@Value("${app.reception.username}")
    private String receptionist;
	
	@Value("${app.reception.name}")
    private String receptionName;
	
	@Value("${app.reception.contactNo}")
    private String receptionContactNo;
	    
    @Value("${app.reception.email}")
    private String receptionEmail;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private VishramSadanRepository vishramSadanRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private RoomRepository roomRepository;
    
    public void intializeSuperAdmin() {
    	   User existingSuperAdmin = userRepository.findByUsername(username).orElse(null);
    	   if(existingSuperAdmin == null) {
    		   List<String> authorities = new ArrayList<String>();
        	   authorities.add(ApplicationConstant.roleSuperAdmin);
        	   User superAdmin = new User(username, contactNo, email, ApplicationConstant.statusActive, encoder.encode(password), null,
        			    1, 0, new Date(), new Date(), name, authorities); 
        	   userRepository.save(superAdmin);    
    	   } 	
    	   /*User existingRefferer = userRepository.findByUsername(refferer).orElse(null);
    	   if(existingRefferer == null) {
    		   List<String> authorities = new ArrayList<String>();
        	   authorities.add(ApplicationConstant.roleReferrer);
        	   User newRefferer = new User(refferer, reffererContactNo, reffererEmail, ApplicationConstant.statusActive, encoder.encode(password), null,
        			    1, 0, new Date(), new Date(), reffererName, authorities); 
        	   userRepository.save(newRefferer);    
    	   } 	
    	   User existingReceptionist = userRepository.findByUsername(receptionist).orElse(null);
    	   if(existingReceptionist == null) {
    		   List<String> authorities = new ArrayList<String>();
        	   authorities.add(ApplicationConstant.roleReceptionist);
        	   User newReceptionist = new User(receptionist, receptionContactNo, receptionEmail, ApplicationConstant.statusActive, encoder.encode(password), null,
        			    1, 0, new Date(), new Date(), receptionName, authorities); 
        	   userRepository.save(newReceptionist);    
    	   }*/ 	
    }

	public List<User> getAdminList() {
		return userRepository.findByAuthority(ApplicationConstant.roleAdmin);
	}
	
	public VishramSadan addVishramSadan(VishramSadan newSadan) throws Exception {
		List<VishramSadan> existingSadan = vishramSadanRepository.findByNameOrFullname(newSadan.getName(), newSadan.getFullname());
		if(existingSadan != null && existingSadan.size() > 0) {
		   throw new Exception("Vishram sadan already exist");
		}
		else{
		   newSadan.setCategoryList(new HashSet<>());
		   newSadan.setRooms(new HashSet<>());
		   newSadan.setStatus(ApplicationConstant.statusActive);
		   return vishramSadanRepository.save(newSadan);
		}
	}
	
	public Category addCategory(Category category) throws Exception {
		List<Category> existingCategory = categoryRepository.findByName(category.getName());
		if(existingCategory != null && existingCategory.size() > 0) {
		   throw new Exception("Category already exist");
		}
		else{
		   return categoryRepository.save(category);
		}
	}
	
	public VishramSadan addCategoryToSadan(long categoryId,long vishramSadanId) throws ResourceNotFound {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFound("requested category not found"));
		VishramSadan vishramSadan = vishramSadanRepository.findById(vishramSadanId).orElseThrow(()->new ResourceNotFound("requested vishram sadan not found"));
		vishramSadan.addCategory(category);
		return vishramSadanRepository.save(vishramSadan);
	}
	
	public VishramSadan removeCategoryFromSadan(long categoryId,long vishramSadanId) throws ResourceNotFound {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFound("requested category not found"));
		VishramSadan vishramSadan = vishramSadanRepository.findById(vishramSadanId).orElseThrow(()->new ResourceNotFound("requested vishram sadan not found"));
		vishramSadan.removeCategory(category);
		return vishramSadanRepository.save(vishramSadan);
	}
	
	public VishramSadan addRoomToSadan(Room room,long vishramSadanId) throws ResourceNotFound {
		VishramSadan vishramSadan = vishramSadanRepository.findById(vishramSadanId).orElseThrow(()->new ResourceNotFound("requested vishram sadan not found"));
		for(Room existing_room:vishramSadan.getRooms()) {
			if(existing_room.getName().contentEquals(room.getName())) {
				throw new IllegalStateException("Room with same name already exist");
			}
		}
		if(room.getCategory() == null || categoryRepository.findById(room.getCategory().getId()).orElse(null) == null) {
			throw new IllegalStateException("specify valid category of room");
		}
		else if(vishramSadan.getCategoryList().stream().filter(category -> category.getId() == room.getCategory().getId()).findFirst().orElse(null) == null) {
			throw new IllegalStateException("specify category is not available in provided sadan");
		}
		else {
			room.setStatus(ApplicationConstant.statusActive);
			vishramSadan.addRoom(room);
			return vishramSadanRepository.save(vishramSadan);
	    }
	}
	
	public VishramSadan removeRoomFromSadan(long roomId,long vishramSadanId) throws ResourceNotFound {
		Room room = roomRepository.findById(roomId).orElseThrow(()->new ResourceNotFound("requested room not found"));
		VishramSadan vishramSadan = vishramSadanRepository.findById(vishramSadanId).orElseThrow(()->new ResourceNotFound("requested vishram sadan not found"));
		vishramSadan.removeRoom(room);
		vishramSadan = vishramSadanRepository.save(vishramSadan);
		roomRepository.delete(room);
		return vishramSadan;
	}
	
	public User addUser(User userInfo) {
	    	userInfo.setRegistredOn(new Date());
	    	userInfo.setStatus(ApplicationConstant.statusNew);
	    	userInfo.setEnabled(1);
	    	userInfo.setInvalidAttempt(0);
	    	userInfo.setLastLogin(new Date());
	    	userInfo.setPassword(encoder.encode(userInfo.getPassword()));
	    	//userInfo.setSalt(ApplicationEncoder.generateSalt());
	        return userRepository.save(userInfo);
    }

	public User assignVishramSadan(Long sadanId, Long receptionistId) {
		List<User> allReceptionist = userRepository.findByAuthority(ApplicationConstant.roleReceptionist);
		VishramSadan requiredVishramSadan = vishramSadanRepository.findById(sadanId).orElseThrow(()->new IllegalStateException("Vishram sadan not found"));
		User requiredReceptionist = null;
		int total = 0;
		for(User receptionist:allReceptionist) {
			++total;
			for(VishramSadan sadan:receptionist.getSadans()) {
				if(sadan.getId() == sadanId) {
				   throw new IllegalStateException("This vishram sadan already assigned to someone else");
				}
			}
			if(receptionist.getId() == receptionistId) {
				requiredReceptionist = receptionist;
			}
			if(total == allReceptionist.size() && requiredReceptionist != null) {
				 requiredReceptionist.addVishramSadan(requiredVishramSadan);
			}
		}
		if(requiredReceptionist != null) {
			return userRepository.save(requiredReceptionist);
		}else {
			throw new IllegalStateException("Receptionist not found");
		}
	}

	public List<User> getAllUsers() {
		   return userRepository.findAll();
	}
}
