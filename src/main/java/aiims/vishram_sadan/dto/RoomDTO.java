package aiims.vishram_sadan.dto;

import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.Room;

public class RoomDTO {
	private long id;
	private Category category;
	private int floor;
	private String name;
	public RoomDTO() {
		
	}
	public RoomDTO(Room room) {
		this.id = room.getId();
		this.category = room.getCategory();
		this.floor = room.getFloor();
		this.name = room.getName();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
