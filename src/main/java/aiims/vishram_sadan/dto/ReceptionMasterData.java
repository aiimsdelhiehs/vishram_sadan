package aiims.vishram_sadan.dto;

import java.util.List;
import java.util.Map;

import aiims.vishram_sadan.entities.Category;
import aiims.vishram_sadan.entities.VishramSadan;

public class ReceptionMasterData {
	private List<VishramSadan> vishramSadanList;
    private List<Category> categoryList;
    private Map<Integer, String> floorMap;
    private Map<Integer, String> floorIndex;
    private Map<Integer, Boolean> selectedFloors;
    private List<BookingDetails> bookings;
    
	public ReceptionMasterData() {
		super();
	}
	public ReceptionMasterData(List<VishramSadan> vishramSadanList, List<Category> categoryList,
			Map<Integer, String> floorMap, Map<Integer, String> floorIndex, Map<Integer, Boolean> selectedFloors, List<BookingDetails> bookings) {
		super();
		this.vishramSadanList = vishramSadanList;
		this.categoryList = categoryList;
		this.floorMap = floorMap;
		this.floorIndex = floorIndex;
		this.selectedFloors = selectedFloors;
		this.bookings = bookings;
	}

	public List<VishramSadan> getVishramSadanList() {
		return vishramSadanList;
	}
	public void setVishramSadanList(List<VishramSadan> vishramSadanList) {
		this.vishramSadanList = vishramSadanList;
	}
	public List<Category> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	public Map<Integer, String> getFloorMap() {
		return floorMap;
	}
	public void setFloorMap(Map<Integer, String> floorMap) {
		this.floorMap = floorMap;
	}
	public Map<Integer, String> getFloorIndex() {
		return floorIndex;
	}
	public void setFloorIndex(Map<Integer, String> floorIndex) {
		this.floorIndex = floorIndex;
	}
	public Map<Integer, Boolean> getSelectedFloors() {
		return selectedFloors;
	}
	public void setSelectedFloors(Map<Integer, Boolean> selectedFloors) {
		this.selectedFloors = selectedFloors;
	}
	public List<BookingDetails> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingDetails> bookings) {
		this.bookings = bookings;
	}
	
}
