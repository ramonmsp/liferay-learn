import com.liferay.headless.admin.user.client.dto.v1_0.AccountRole;
import com.liferay.headless.admin.user.client.resource.v1_0.AccountRoleResource;

public class AccountRole_POST_ToAccount {

	/**
	 * java -classpath .:* -DaccountId=1234 AccountRole_POST_ToAccount
	 */
	public static void main(String[] args) throws Exception {
		AccountRoleResource.Builder builder = AccountRoleResource.builder();

		AccountRoleResource accountRoleResource = builder.authentication(
			"test@liferay.com", "test"
		).build();

		AccountRole accountRole = accountRoleResource.postAccountAccountRole(
			Long.valueOf(System.getProperty("accountId")),
			new AccountRole() {
				{
					name = "How";
				}
			});

		System.out.println(accountRole);
	}

}