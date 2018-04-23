package com.pazukdev;

import java.io.Serializable;
import java.time.LocalDate;

@SuppressWarnings("serial")
public class Hotel implements Serializable, Cloneable {

	private Long id;
	private String name;
	private String address;
	private Integer rating;
	private Long operatesFrom;
	private Category category;
	private String url="http://";
	private String description;

	/*public boolean isPersisted() {
		return id != null;
	}*/

	@Override
	public String toString() {
		return name + " hotel. Address: " + address + ". Rating: " + rating + " stars";
	}

	@Override
	protected Hotel clone() throws CloneNotSupportedException {
		return (Hotel)super.clone();
	}

	@Override
	public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }

        if (obj instanceof Hotel && obj.getClass().equals(getClass())) {
            return this.id.equals(((Hotel) obj).id);
        }

        return false;
	}

	@Override
	public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
	}

    public Hotel() {}

	public Hotel(LocalDate yesterday) {
	    this.operatesFrom = yesterday.toEpochDay();
    }

    public Hotel(Long id, String name, String address, Integer rating, Long operatesFrom, Category category,
                 String url, String description) {
        super();
        this.id = id;
        this.name = name;
        this.address = address;
        this.rating = rating;
        this.operatesFrom = operatesFrom;
        this.category = category;
        this.url = url;
        this.description=description;
    }


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}


	public LocalDate getOperatesFrom() {
		return LocalDate.ofEpochDay(this.operatesFrom);
	}

    public void setOperatesFrom(LocalDate date) {
        this.operatesFrom = date.toEpochDay();
    }

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
	    if(category!=null) this.category = category;
	    else this.category=null;
	}	

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
	    this.url=url;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}



}