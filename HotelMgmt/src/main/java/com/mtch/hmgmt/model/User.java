package com.mtch.hmgmt.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * @author Karthick
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String username;
	private String password;
	private String email;
	private String phone;
	private boolean isActive = true;
	
	@ElementCollection
	private Set<Room> rooms = new HashSet<Room>(0);
	@ElementCollection
	private Set<Preference> preferences = new HashSet<Preference>(0);
	@ElementCollection
	private Set<Role> roles = new HashSet<Role>(0);
	private int age;

	public User() {
		id = 0;
	}

	/*public User(long id, String name, String username, String password, String email, String phone, boolean isActive,
			Set<Room> rooms, Set<Preference> preferences, Set<Role> roles, int age) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.isActive = isActive;
		this.rooms = rooms;
		this.preferences = preferences;
		this.roles = roles;
		this.age = age;
	}*/

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@OneToMany(mappedBy="user")
	public Set<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}

	@OneToMany(mappedBy="user")
	public Set<Preference> getPreferences() {
		return preferences;
	}

	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}

	/*public Set<Role> getRoles() {
		return roles;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = {
			@JoinColumn(name = "id") })

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}*/

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	/*@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + ", email="
				+ email + ", phone=" + phone + ", isActive=" + isActive + ", rooms=" + rooms + ", preferences="
				+ preferences + ", roles=" + roles + ", age=" + age + "]";
	}*/
	
	public static void main(String arg[]) {
		
		ObjectMapper mapper = new ObjectMapper();
		User newUser = new User();
		newUser.setActive(true);
		newUser.setName("Shiva");
		newUser.setEmail("shiva.in.co");
		newUser.setAge(22);
		newUser.setUsername("shiva");
		newUser.setPassword("shiva@123");

		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(newUser);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(jsonString);
	}

}
