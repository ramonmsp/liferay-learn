# Configuring a Database

By default for demonstration purposes, Liferay DXP/Portal is configured to use an embedded HSQL database. Beyond demonstration purposes, we recommend using a full-featured, supported RDBMS, such as:

* MariaDB
* MySQL
* Oracle
* PostgreSQL

```{important}
The [Liferay DXP Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151) lists the supported databases and versions.
```

## Configure the database

1. Create a database that uses UTF-8. Here is a MySQL command example:

    ```sql
    create database lportal character set utf8;
    ```

1. Set up a database user with full database permissions. If your organization requires the DXP database user to have limited database access, see [High Security Database User Practices](../reference/database-configurations.md#high-security-database-user-practices).

    ```{important}
    Liferay requires reading from and writing to the database. The Liferay database user must therefore have permissions to read and write data.
    ```

1. Install a JDBC Connector. The DXP bundle includes several open source JDBC connectors in the `/lib/ext` folder. Connectors for proprietary database, like Oracle or DB2 (see the table below), must be downloaded from the vendor.

**Proprietary Databases:**

| Database | Connector | Vendor Site | Notes |
| :------- | :-------- | :---------- | :---- |
| Oracle | `ojdbc8.jar` | [Oracle](https://www.oracle.com/index.html) | The `ojdbc8.jar` library with at least Oracle 12.2.0.1.0 JDBC 4.2 versioning is required because of [data truncation issues](https://issues.liferay.com/browse/LPS-79229) that have been detected reading data from CLOB columns. |
| DB2 | `db2jcc4.jar` | [IBM](https://www.ibm.com/) |  The `dbc2jcc` connector has been deprecated after 3.72. |

Congratulations! You configured a database for Liferay DXP.

```{note}
For database configuration details, see the [Database Configuration Reference](../reference/database-configurations.md).
```

## Next Steps

* [Running Liferay for the First Time](./running-liferay-for-the-first-time.md)