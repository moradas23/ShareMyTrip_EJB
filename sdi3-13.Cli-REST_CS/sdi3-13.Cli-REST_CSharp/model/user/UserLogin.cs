
public class UserLogin{
	private static readonly long serialVersionUID = 1L;
	
	public UserLogin(string login, string name, long id) {
		this.login = login;
		this.name = name;
		this.setId(id);
	}
	private string login;
	private string name;
	private long id;
	
	public string getLogin() {
		return login;
	}
	public void setLogin(string login) {
		this.login = login;
	}
	public string getName() {
		return name;
	}
	public void setName(string name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	
}
