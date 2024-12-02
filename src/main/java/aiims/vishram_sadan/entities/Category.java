package aiims.vishram_sadan.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
    private int price;
    private int maxPrice;
    
    @Column(nullable = false, columnDefinition = "int default 0")
    private int extraCharge=0;
    private int capacity;
    
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean extraAllowed = false;
    
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public int getExtraCharge() {
		return extraCharge;
	}
	public void setExtraCharge(int extraCharge) {
		this.extraCharge = extraCharge;
	}
	public boolean isExtraAllowed() {
		return extraAllowed;
	}
	public void setExtraAllowed(boolean extraAllowed) {
		this.extraAllowed = extraAllowed;
	}
	
}
