package RestauranTO.Database.Datamodel;

import java.io.Serializable;

public class Jsonthing implements Serializable{

	private static final long serialVersionUID = -3010080064591094176L;

	protected String authorized_location_ids;
	protected String role_ids;
	protected String id;
	protected String first_name;
	protected String last_name;
	protected String external_id;
	protected String status;
	protected String updated_at;
	protected String created_at;
	protected String email;
	
	public String getAuthorized_location_ids() {
		return authorized_location_ids;
	}

	public void setAuthorized_location_ids(String authorized_location_ids) {
		this.authorized_location_ids = authorized_location_ids;
	}

	public String getRole_ids() {
		return role_ids;
	}

	public void setRole_ids(String role_ids) {
		this.role_ids = role_ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getExternal_id() {
		return external_id;
	}

	public void setExternal_id(String external_id) {
		this.external_id = external_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Jsonthing() {
		super();
	}
	
	
	
	
	
}
