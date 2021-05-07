import com.liferay.headless.admin.user.client.dto.v1_0.UserAccount;
import com.liferay.headless.admin.user.client.resource.v1_0.UserAccountResource;

public class User_POST {

	/**
	 * java -classpath .:* User_POST
	 */
	public static void main(String[] args) throws Exception {
		UserAccountResource.Builder builder = UserAccountResource.builder();

		UserAccountResource userAccountResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		UserAccount userAccount = userAccountResource.postUserAccount(
			new UserAccount() {
				{
					alternateName = "Foo";
					emailAddress = "foo@liferay.com";
					familyName = "Liferay";
					givenName = "Foo";
				}
			});

		System.out.println(userAccount);
	}

}