




using Newtonsoft.Json.Linq;
using System;
using System.Runtime.Serialization;
/**
* This class is not an entity, it is a DTO with the same fields as a row in the
* table
* 
* @see Data Transfer Object pattern
* @author alb
*
*/
public class User {

	/**
	 * 
	 */
	private static readonly long serialVersionUID = 1L;
	private long id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String email;
	
	private UserStatus status;

    public User(string json)
    {
        JObject jObject = JObject.Parse(json);
        JToken jUser = jObject["user"];
        id = (long)jUser["id"];
        login = (string)jUser["login"];
        name = (string)jUser["name"];
        surname = (string)jUser["surname"];
        email = (string)jUser["email"];
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	public String toString() {
		return "User [id=" + id 
				+ ", login=" + login 
				+ ", password=" + password 
				+ ", name=" + name 
				+ ", surname=" + surname 
				+ ", status=" + status 
				+ ", email=" + email
			+ "]";
	}


}
