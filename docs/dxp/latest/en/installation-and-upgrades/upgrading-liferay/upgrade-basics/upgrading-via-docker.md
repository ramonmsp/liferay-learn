# Upgrading Via Docker

Running a Liferay Docker image with auto-upgrade enabled upgrades your database on Liferay startup. After the upgrade completes, you can continue [using Liferay via that Docker container](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images.md) or point a new Liferay on-premises installation to the upgraded database.

```{important}
Don't have Docker? Go here first:

* [Linux](https://docs.docker.com/install/linux/docker-ce/ubuntu/)
* [Windows](https://docs.docker.com/docker-for-windows/install/)
* [OSX](https://docs.docker.com/docker-for-mac/install/)
```

| DXP Edition | Image | Tags |
| :---------- | :---- | :--- |
| Liferay DXP (Subscription)| [`dxp`](https://hub.docker.com/r/liferay/dxp) | [here](https://hub.docker.com/r/liferay/dxp/tags) |
| Liferay Portal | [`portal`](https://hub.docker.com/r/liferay/portal) | [here](https://hub.docker.com/r/liferay/portal/tags) |

```{important}
Upgrades to enterprise subscriber installations and critical installations should be done using the Database Upgrade Tool. See [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md) for more information.
```

```{important}
**Always** [back up](../../maintaining-a-liferay-installation/backing-up.md) your database and existing installation before upgrading. Testing the upgrade process on backup copies is advised.
```

## Upgrading with the Latest Docker Image

Here are the steps for upgrading with a Docker image:

1. Create an arbitrary folder to use with the new Liferay Docker image and create subfolders called `files` and `deploy`. For example,

    ```
    mkdir -p new-version/files
    ```

    ```
    mkdir -p new-version/deploy
    ```

    * `files`: The Docker container copies files from this folder to the container's [Liferay Home](../../reference/liferay-home.md) folder.

    * `deploy`: The Docker container copies artifacts from this folder to the container's auto-deploy folder.

1. If you're using [Commerce](https://learn.liferay.com/commerce/latest/en/index.html), prepare to upgrade it. See [Upgrading Liferay Commerce](https://learn.liferay.com/commerce/latest/en/installation-and-upgrades/upgrading-liferay-commerce.html) for details.

1. If you want to upgrade your Marketplace app data along with the Liferay database upgrade, [download](../../../system-administration/installing-and-managing-apps/installing-apps/downloading-apps.md) the latest version of each app targeted for the new Liferay version and copy it to your `new-version/deploy` folder. Otherwise, you can install the apps after the database upgrade and upgrade their data as described in the [Post-Upgrade Considerations](./post-upgrade-considerations.md).

1. If you're using an embedded [Elasticsearch](../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/getting-started-with-elasticsearch.md) engine or a local [File Store \(Document Library\)](../../../system-administration/file-storage.md), copy the `[Liferay Home]/data` folder to the new `files` folder to create `new-version/files/data`.

1. Copy and merge the [Liferay Home files](../../maintaining-a-liferay-installation/backing-up.md#liferay-home) and [application server files](../../maintaining-a-liferay-installation/backing-up.md#application-server) from your backup to their corresponding locations in the `files` folder (your new `[Liferay Home]`). For example, copy your activation key to `new-version/files/license/`. The files may include but are not limited to these:

    * `/license/*`: Activation keys. (Subscription)

    * `/log/*`: Log files.

    * `/osgi/configs/*.config`: [OSGi configuration files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md).

    * `portal-*.properties`: [Portal properties](../../reference/portal-properties.md) files, such as `portal-ext.properties`.

    * `setenv.sh`, `startup.sh`, and more: Application server configuration scripts.

    * `web.xml`: Portal web application descriptor.

1. If you're upgrading to 7.2, disable search indexing using a [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) in your `[Liferay Home]/files/osgi/config/` folder. For example,

    ```bash
    echo "indexReadOnly=\"true\"" >> new-version/files/osgi/config/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

1. If you're using [Advanced File System Store](../../../system-administration/file-storage.md) or [Simple File System Store](../../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md) with a modified storage location, export your file store settings to a [`.config` file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) and copy it to your `new-version/osgi/configs` folder.

    ```{important}
    If you're using [Advanced File System Store](../../../system-administration/file-storage.md), you must configure it with a `.config` file in the new installation before upgrading the database.

    Here's an example  `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config` file with the required `rootDir` parameter:

    `rootDir="data/document_library"`
    ```

1. Make sure you're using the JDBC database driver your database vendor recommends. If you're using MySQL, for example, set `jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver` in [`new-version/files/portal-ext.properties`](../../reference/portal-properties.md) and replace the MySQL JDBC driver JAR your app server uses. See [Database Drivers](../migrating-configurations-and-properties.md#database-drivers) for more details.

1. Run the Docker image [mounted](../../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) to your new version folder using the following command. Substitute the image name, tag, and environment values as needed.

    ```bash
    docker run -it -m 8g -p 8080:8080 \
     -v $(pwd)/new-version:/mnt/liferay \
     -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true \
     liferay/[place image name here]:[place tag here]
    ```

    The `-v new-version:/mnt/liferay` arguments bind mount the host's `new-version` folder to the container's `/mnt/liferay` folder. Please see [Providing Files to the Container](../../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) for more information on the mapping files to the container's Liferay Home.

    The parameter `-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true` triggers the database upgrade.

1. In the console or log, confirm successful database upgrade and server startup. Upgrade messages report starting and completing each upgrade process. A message like this one indicates server startup completion:

    ```bash
    org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds
    ```

    If there are any upgrade failures or errors, they're printed to the console and log. You can use [Gogo Shell commands](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to troubleshoot any issues and finish the upgrade.

1. After you have resolved any failures or errors, examine the [Post Upgrade Considerations](./post-upgrade-considerations.md).

1. [Update the Portal properties](../migrating-configurations-and-properties.md#migrating-portal-properties) in your new installation.

1. Validate your upgraded database.

    ![Here is the Liferay landing screen.](./upgrading-via-docker/images/01.png)

Your database upgrade is now complete!

If you want to continue using the new Liferay version via Docker, leave off the ``-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true`` environment setting from the ``docker run ...`` command you use to create the new container.

```{note}
[Using Liferay Docker Images](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images.md) demonstrates creating, stopping, and restarting Docker containers.
```

## Conclusion

If the upgraded database is all you need, then enjoy using your new Liferay instance! If there's more to completing your upgrade, these articles can help you finish:

* [Upgrade Basics](../upgrade-basics.md) describes all of the upgrade topics. Maybe there's a topic you still need to address.

* [Database Upgrade Options](../reference/database-upgrade-options.md) describes all the ways of upgrading the database in the context of the various DXP/Portal installation types.

* [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md) demonstrates upgrading the database while the Liferay server is offline. If the upgrade took too long, consider [tuning the database](../upgrade-stability-and-performance/database-tuning-for-upgrades.md), [pruning unneeded data](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md), and [using Database Upgrade Tool](./using-the-database-upgrade-tool.md).

* [Upgrading Custom Development](../upgrading-custom-development.md) demonstrates adapting custom plugin code to a new Liferay version.

* [Maintaining Clustered Installations](../../maintaining-a-liferay-installation/maintaining-clustered-installations.md) describes how to upgrade in a clustered environment.

* [Troubleshooting Upgrades](../reference/troubleshooting-upgrades.md)