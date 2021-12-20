# Database Service (MySQL)

The database service (MySQL) is a distributed relational database service that simplifies the setup, operation, and scaling of your applications. It's a private service inside your application environment and can only communicate with your other services, not the public internet.

![Figure 1: The database service is one of several services available in DXP Cloud.](./database-service/images/01.png)

See the [Database service limitations](../../reference/platform-limitations.md#database-service) section for more information.

## Environment Variables

You can set these environment variables to configure the database service. When setting `LCP_MASTER_USER_NAME`, `LCP_MASTER_USER_PASSWORD`, and `LCP_DBNAME`, make sure to use the same values for other services that depend on the database service (e.g., the backup and Liferay DXP services). You should set these variables before the first deployment. If a build is generated with new values for these variables, subsequent deployments will fail. It may be viable in a development environment to delete services and update the `LCP.json` file with new values for these variables, but this isn't viable in a production environment.

Name                       | Default Value              | Description      |
-------------------------- | -------------------------- | ---------------- |
`LCP_DBNAME`               | `lportal`                  | Database name.   |
`LCP_MASTER_USER_NAME`     | `dxpcloud`                 | Master username. |
`LCP_MASTER_USER_PASSWORD` | `LCP_PROJECT_MASTER_TOKEN` | Master password. |

### Database Maintenance Window Variables

Your DXP Cloud environment's database service occasionally requires downtime for scheduled maintenance, typically for about two minutes. You can configure a preferred window for this maintenance to take place to reduce the downtime's impact. By default, no specific time preference is defined.

Name                      | Acceptable Values  | Description                                                             |
------------------------- | ------------------ | ----------------------------------------------------------------------- |
`LCP_GCP_MW_DAY`          | `1` through `7`    | Preferred day of the week (Monday (1) through Sunday (7), in UTC time). |
`LCP_GCP_MW_HOUR`         | `0` through `23`   | Preferred hour of the day (in UTC time).                                |
`LCP_GCP_MW_UPDATE_TRACK` | `canary`, `stable` | Set `canary` to update earlier, up to a week before other environments. | 

### Google Cloud MySQL Flags

You can pass MySQL flags in as environment variables. The available flags are listed in the
[Google Cloud documentation](https://cloud.google.com/sql/docs/mysql/flags). Each flag must be prepended with `LCP_GCP_DATABASE_FLAG_` to work in Liferay DXP Cloud. Below are common flags that can be useful for debugging in a development environment, but should NOT be used in a production environment as they have significant performance costs.

```{warning}
As noted in Google's documentation, some database flag settings can affect instance availability or stability. Be very careful when using these flags and follow Google's [Operational Guidelines](https://cloud.google.com/sql/docs/mysql/operational-guidelines).
```

Name                                   | Acceptable Values | Default Value |
-------------------------------------- | ----------------- | ------------- |
`LCP_GCP_DATABASE_FLAG_GENERAL_LOG`    | `on, off`         | `off`         |
`LCP_GCP_DATABASE_FLAG_SLOW_QUERY_LOG` | `on, off`         | `off`         |

## Related Information

* [Changing Your Database Username](./changing-your-database-username.md)
* [Changing Your Database Password](./changing-your-database-password.md)
* [Database Service Limitations](../../reference/platform-limitations.md#database-service)
* [Using the MySQL Client](../../using-the-liferay-dxp-service/using-the-mysql-client.md)
