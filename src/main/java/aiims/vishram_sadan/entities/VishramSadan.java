package aiims.vishram_sadan.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class VishramSadan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String fullname;
	private int minFloor;
	private int maxFloor;
	private String status;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Room> rooms =new HashSet<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Category> categoryList =new HashSet<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public int getMinFloor() {
		return minFloor;
	}

	public void setMinFloor(int minFloor) {
		this.minFloor = minFloor;
	}

	public int getMaxFloor() {
		return maxFloor;
	}

	public void setMaxFloor(int maxFloor) {
		this.maxFloor = maxFloor;
	}

	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public void addCategory(Category category) {
		categoryList.add(category);
	}
	
	public void removeCategory(Category category) {
		categoryList.remove(category);
	}
	public void addRoom(Room room) {
		rooms.add(room);
	}
	
	public void removeRoom(Room room) {
		rooms.remove(room);
	}
	
}
