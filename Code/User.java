package IIITLBank;

public class User {
	private static int userID;
	private static String userName	;
	private static int userPIN;
	private static int userBal;
	private static String userCity;
	private static int cardNumber;
	private static String userMail	;
	public User(int userID, String userName, int userPIN, int userBal, String userCity,int cardNumber,String userMail) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.userPIN = userPIN;
		this.userBal = userBal;
		this.userCity=userCity;
		this.cardNumber=cardNumber;
		this.userMail = userMail;
	}
	public static String getUserCity() {
		return userCity;
	}
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	public User(String userName, int userPIN, int userBal) {
		super();
		this.userName = userName;
		this.userPIN = userPIN;
		this.userBal = userBal;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userID) {
		
	}
	public User(int userID, int userPIN) {
		
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", userPIN=" + userPIN + ", userBal=" + userBal
				+ " userCity= " + userCity + ", userMail="+ userMail+"]";
	}
	public static int getUserID() {
		return userID;
	}
	public  void setUserID(int userID) {
		this.userID = userID;
	}
	public static String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static int getUserPIN() {
		return userPIN;
	}
	public  void setUserPIN(int userPIN) {
		this.userPIN = userPIN;
	}
	public static int getUserBal() {
		return userBal;
	}
	public static void setUserBal(int Bal)
	{userBal=Bal;}

	public static int getCardNumber(){
		return cardNumber;
	}
	public static String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userName = userMail;
	}

}
