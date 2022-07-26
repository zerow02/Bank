package IIITLBank;
import java.sql.*;
import java.util.*;

public class UpdateUser {




	public static boolean UpdateUserInDB(User us) {
		boolean f=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String user="root";
			String password="123456";
			String url="jdbc:mysql://localhost:3306/grp_project";
			Connection con = DriverManager.getConnection(url,user,password);			Scanner sc=new Scanner(System.in);


			String sq="select * from cards where cardNumber="+us.getCardNumber();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sq);
			while(rs.next()) {
				Header.head();
				System.out.println("		path : management transactions --> update user details --> pin verification\n");
				System.out.println("PLEASE ENTER THE PIN TO PROCEED ");
				int pin=sc.nextInt();
				int pass=rs.getInt("PIN");
				String mname=us.getUserName();
				int mbal=us.getUserBal();
				String mcity=us.getUserCity();
				System.out.println("PROCESSING...PLEASE WAIT PATIENTLY...");
				if(pin==pass) {
					Header.head();
					System.out.println("		path : management transactions --> update user details --> pin verification --> successfully verified\n");

					System.out.println("PIN VERIFICATION SUCCESSFUL");
					Header.head();
					System.out.println("		path : management transactions --> update user details\n");

					int choice;
					System.out.println("WHAT WOULD YOU LIKE TO DO?\n1.CHANGE NAME\n2.CHANGE PIN\n3.CHANGE CITY\n\n(enter -1 to abort transaction)");
					choice=sc.nextInt();
					if(choice==1) {
						Header.head();
						System.out.println("		path : management transactions --> update user details --> change user name\n");

						System.out.println("ENTER THE NEW NAME :");
						mname = sc.next();
					}
					else if(choice==2) {
						Header.head();
						System.out.println("		path : management transactions --> update user details --> change user pin\n");

						System.out.println("ENTER THE NEW PIN :");
						pin = sc.nextInt();
					}
					else if(choice==3)
					{
						Header.head();
						System.out.println("		path : management transactions --> update user details --> change user city\n");


						System.out.println("ENTER NEW CITY : ");
						mcity = sc.next();
					}
					else if(choice==-1)
						return  true;
					String q="select * from cards where PIN= "+pin;
					Statement stmt = con.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
					ResultSet result=stmt.executeQuery(sq);
					while(result.next()) {

						result.updateString("Name", mname);
						result.updateRow();
						us.setUserName(mname);

						result.updateInt("acc_bal", mbal);
						result.updateRow();

						result.updateString("city", mcity);
						result.updateRow();
                        us.setUserCity(mcity);

						result.updateInt("PIN", pin);
						result.updateRow();
						us.setUserPIN(pin);
					}
					Header.head();
					System.out.println("		path : management transactions --> update user details --> successfully updated\n");

					System.out.println("USER DETAIL SUCCESSFULLY UPDATED");

					f=true;

				}
				else if(pin!=pass) {
					Header.head();
					System.out.println("		path : management transactions --> update user details --> pin verification --> failure\n");
					System.out.println("PIN VERIFICATION FAILED");
				}
			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return f;

	}

}