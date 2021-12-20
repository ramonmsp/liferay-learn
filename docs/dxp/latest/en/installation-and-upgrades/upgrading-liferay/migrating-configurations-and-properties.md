# Migrating Configurations and Properties

Your current DXP installation's OSGi configurations (7.0+) and properties (such as [portal properties](../reference/portal-properties.md) and [system properties](../reference/system-properties.md)) set up your DXP instance to fit your needs. To use these settings in your new DXP instance, you must migrate them to your new Liferay Home and update them.

## Overview

* [Migrating Liferay Home](#migrating-liferay-home)
* [Updating Settings for the Database Upgrade](#updating-settings-used-by-the-database-upgrade)
* [Migrating Portal Properties](#migrating-portal-properties)

## Migrating Liferay Home and Application Server Files

1. Merge the [Liferay Home files](../maintaining-a-liferay-installation/backing-up.md#liferay-home) and [application server files](../maintaining-a-liferay-installation/backing-up.md#application-server) that you have added and edited from your [backup](../maintaining-a-liferay-installation/backing-up.md) to your installation. The files may include but are not limited to these:

    * `/license/*`: Activation keys. (Subscription)
    * `/log/*`: Log files.
    * `/osgi/configs/*.config`: OSGi configuration files.
    * `portal-*.properties`: Portal properties files, such as `portal-ext.properties`.
    * Application server files: Modified scripts and configuration files.
    * `web.xml`: Portal web application descriptor.

1. Replace the new installation's `[Liferay Home]/data` folder with the `[Liferay Home]/data` folder from your backup.

1. Set up the [File Store (Document Library)](../../../system-administration/file-storage.md) by copying it from your [backup](../maintaining-a-liferay-installation/backing-up.md) to the new installation and or configuring the new installation to use it via a [`.config` file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files).

## Updating Settings For the Database Upgrade

Upgrade processes in DXP and in some Marketplace apps use portal properties and OSGi configurations. Upgrade processes in your custom code may also require property updates and configuration updates. These settings and updates must be in place _before_ the database upgrade. Other updates can be done after the database upgrade.

Here are the settings updates DXP upgrade processes require:

* [Database driver](#database-drivers)
* Document library store implementation name (See [Updating the File Store](./reference/file-store-updates.md#updating-the-store-implementation-class-name))

```{important}
Check your Marketplace apps and custom code for settings updates they require.
```

### Database Drivers

Check your database vendor documentation for the recommended database driver. If a new driver is recommended, replace the existing driver JAR file and update the `jdbc.default.driverClassName` property in your `portal-ext.properties` file with the new driver class name.

MySQL example:

```properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
```

See the [Database Templates](../reference/database-templates.md) for more driver examples.

## Migrating Portal Properties

```{important}
If you've overridden the `locales` [Portal Property](../../../installation-and-upgrades/reference/portal-properties.md), override it in the new installation before upgrading. This assures upgrading data for all of your locales.
```

The properties discussed here can be updated after database upgrade. Migrating properties involves these actions:

* Updating your `liferay.home` property, if you changed it
* Using [Blade CLI](../../../building-applications/tooling/blade-cli/installing-and-updating-blade-cli.md) to report property changes
* Converting properties to OSGi configurations
* Special property migration considerations

### Using Blade CLI to Report Incompatible Properties

The [Blade CLI](../../../building-applications/tooling/blade-cli/installing-and-updating-blade-cli.md) tool's `upgradeProps` command reports changes between portal properties files. The tool reports these types of changes.

* Properties that cause exceptions, if not updated.
* Properties moved to a module `portal.properties` file.
* Properties moved to OSGi configuration.
* Properties not found in the new DXP version.

In many cases, the `upgradeProps` command explains the required update or references more information on the property change.

The `blade upgradeProps` command format:

```bash
blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
```

Here is example output from running the `blade upgradeProps` command:

```
...
index.search.query.suggestion.dictionary
	MODULARIZE AS OSGI - This property matches with the following OSGI config, select the most appropriate:
		- searchQueryResultWindowLimit from com.liferay.portal.search.configuration.DefaultSearchResultPermissionFilterConfiguration

index.search.spell.checker.dictionary
	KEEP - This property is still present in the new portal.properties.

sites.friendly.url.page.not.found
	KEEP - This property is still present in the new portal.properties.

web.server.protocol
	KEEP - This property is still present in the new portal.properties.
...
```

### Converting Properties to OSGi Configurations

Properties in modularized features have changed and are now deployed in [OSGi configuration files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) (OSGi Config Admin).

For example, in 6.2, the Simple File Store used this portal property to specify the store root directory:

```properties
dl.store.file.system.root.dir=${liferay.home}/data/document_library
```

Now the store is configured in a `.config` file called `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config` that specifies the root directory with a setting like this:

```properties
rootDir="{document_library_path}"
```

Put the `.config` files in a folder called `[Liferay Home]/osgi/configs`.

```{tip}
The Control Panel's *System Settings* screens (under *Configuration*) manage the OSGi Config Admin values. These screens are the most accurate way to create ``.config`` files. Find the screen that configures the feature you want to configure, click *Save*, and then use the options button to [export the screen's configuration](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) to a `.config` file.
```

### Special Property Migration Considerations

There are resources for migrating properties related to specific environments, Liferay versions, and features. They're called out here for convenience.

1. Updates to file store settings are discussed in [Updating the File Store](./reference/file-store-updates.md).

1. If you're on Liferay Portal 6.1 or earlier, [adapt your properties to the new defaults that Liferay Portal 6.2 introduced](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay#review-the-liferay-62-properties-defaults).

1. If you have a sharded environment, [configure your upgrade to generate a non-sharded environment](./other-upgrade-scenarios/upgrading-a-sharded-environment.md).

1. Examine the default portal property changes in [7.4](./reference/default-setting-changes-in-7-4.md), [7.3](./reference/default-setting-changes-in-7-3.md), and [7.2](./reference/default-setting-changes-in-7-2.md).

1. Liferay's image sprite framework is deprecated as of 7.2 and is disabled by default. The framework requires scanning plugins for image sprites. If you don't use the framework, there's no need for it to scan for images sprites. If you use the framework yourself, enable it by overriding the default `sprite.enabled` portal property (since 7.2) value with the following setting in your [`portal-ext.properties`](../reference/portal-properties.md) file.

    ```properties
    sprite.enabled=true
    ```

   ```{note}
   You can build image sprites using any framework you like and deploy them in your plugins.
   ```

## Next Steps

Your Liferay settings are ready to use in your new DXP instance. Next, [update the file store](./reference/file-store-updates.md).