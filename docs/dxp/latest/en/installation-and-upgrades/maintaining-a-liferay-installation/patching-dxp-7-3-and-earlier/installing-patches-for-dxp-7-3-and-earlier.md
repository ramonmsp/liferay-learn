# Installing Patches for DXP 7.3 and Earlier

> Subscribers

The [Patching Tool](../reference/installing-the-patching-tool.md) applies Fix Packs (including Security Fix Packs and Service Packs) for Liferay DXP 7.3 and earlier only. It also applies Hotfixes for any DXP version.

```{note}
If you're on Liferay DXP 7.4+ and applying an Update (or Security Update), please see [Updating Liferay](../updating-liferay.md).
```

The patching steps for DXP bundles and DXP application server installations are similar. Since DXP bundles include the preconfigured Patching Tool, you can apply patches right away. DXP application server installations, however, require installing and configuring the Patching Tool before patching.

```{warning}
**Always** [back up](../backing-up.md) your database and installation before patching.
```

```{note}
If you're running DXP in a Docker container, please follow the instructions at [Patching DXP in Docker](../../installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md).
```

If you're patching a DXP bundle, continue with the basic patching steps below. If you're patching DXP on an application server, [make additional preparations](#preparing-to-patch-dxp-on-an-application-server) *before* following the patching steps.

## Patching Steps

1. Download the patch to your `patching-tool/patches` folder---don't unzip the patch.

    * Fix Packs and Service Packs are on the [Downloads](https://customer.liferay.com/downloads) page in the Help Center.
    * Hotfixes are in [Help Center](https://help.liferay.com/hc) tickets. 

1. Shut down your application server.

    Reasons:

    * On Unix-style systems, you can usually replace files that are running, but the old ones reside in memory.
    * On Windows systems, files in use are locked and can't be patched.

1. Install the patch by running the Patching Tool's `install` command from the `patching-tool` folder:

    ```bash
    cd patching-tool
    ./patching-tool.sh install
    ```

    The output looks like this:

    ```
    There's no configuration available. Running auto-discovery in the parent folder.
    Directory is not set in command line, using ../
    Auto discovery looks for portal segments. The selected directory is "/home/russell/liferay-bundles/cross-cluster-replication_7.2_LRDOCS-8715/liferay-dxp-7.2.10.3-sp3".
    Configuration has been written into the default.properties:
    patching.mode=binary
    war.path=../tomcat-9.0.33/webapps/ROOT/
    global.lib.path=../tomcat-9.0.33/lib/ext/
    liferay.home=../
    One patch is ready to be installed. Applying dxp-10...
    Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Syncing...
    The patches contain database index modification. Run the patching tool with the index-info parameter for more information.
    The installation was successful. One patch is installed on the system.
    ```

1. Verify that the patch installed by executing the `info` command and checking the information on the currently installed patches:

    ```bash
    ./patching-tool.sh info
    ```

    The output lists the currently installed patches:

    ```
    Loading product and patch information...
    Product information:
      * installation type: binary
      * build number: 7210
      * service pack version:
        - available SP version: 3
        - installable SP version: 3
      * patching-tool version: 2.0.15
      * time: 2021-01-21 18:02Z
      * host: russell-pc (8 cores)
      * plugins: no plugins detected
    Currently installed patches: dxp-10-7210
    Available patches: dxp-8-7210, dxp-10-7210
    Detailed patch list: 
      [ -] dxp-8-7210 :: Currently not installed; Won't be installed: dxp-10 contains the fixes included in this one :: Built for LIFERAY
      [*I] dxp-10-7210 :: Installed; Will be installed. :: Built for LIFERAY
    ```

1. Clean up all DXP cache.

    Delete the `[Liferay Home]/osgi/state` folder.

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    Empty the `[Liferay Home]/work` folder.

    ```bash
    rm -rf work/*
    ```

    Delete the application server cache. Please consult the application server vendor's documentation on where where to find the cache.

    ```{note}
    If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

1. If the patch release notes mention micro or minor schema/data changes, use the [Database Upgrade Tool](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md) to apply minor changes (required) and any micro changes you want.

    ```{important}
    If you're updating from Liferay DXP 7.2 GA1 or Fix Pack 1 to DXP 7.2 SP1 / Fix Pack 2 (or above), you must update the data and database using the Database Upgrade Tool.
    ```

1. If you customized DXP's `web.xml` file, merge your customizations into the new `web.xml` file that the Fix Pack includes. Fix Packs always overwrite the existing `web.xml` file.

1. If the patch has any index updates, configure DXP to update the indexes on startup.

    Use the `info` command to check for index updates.

    ```bash
    cd patching-tool
    ./patching-tool.sh info
    ```

    If there are index updates, set the [`database.indexes.update.on.startup`](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Database) Portal Property to `true` in a [`portal-ext.properties` file](../../reference/portal-properties.md).

    ```properties
    database.indexes.update.on.startup=true
    ```

    Only indexes that start with `LIFERAY_` OR `IX_` are updated. Make sure that your custom indexes do not use this naming convention.

1. If you are installing DXP 7.3 back onto an application server, ZIP the patched DXP application from its [temporary location](#preparing-to-patch-dxp-on-an-application-server) back into a `.war` file and copy the file into your application server. Refer to the [DXP installation instructions](../../installing-liferay/installing-liferay-on-an-application-server.md) for your application server.

1. Start the application server again.

Congratulations! Your DXP instance is patched and running.

```{note}
If the patch doesn't install or if you're unable to resolve errors that occur, please open a [Help Center ticket](https://help.liferay.com/hc/) and provide the full Patching Tool `info` output by running `./patching-tool.sh info > output.txt` and attaching the `output.txt` file to the ticket.
````

## Preparing to Patch DXP on an Application Server

If you installed DXP on an application server, make these preparations:

1. If you're patching DXP 7.3 and it is deployed as a `.war` file, unzip the `.war` to a temporary location for patching.

1. [Install the Patching Tool](../reference/installing-the-patching-tool.md), if you have not yet installed it.

1. [Configure the Patching Tool](../reference/configuring-the-patching-tool.md) for your DXP installation by running the `auto-discovery` command.

    ```bash
    cd patching-tool
    ./patching-tool.sh auto-discovery
    ```

1. If you're patching DXP 7.3, set the Patching Tool's `war.path` property (e.g., in `default.properties`) to your unzipped DXP `.war` location.

1. Continue with the [Patching Steps](#basic-patching-steps) in the previous section.

Now you know how to patch a DXP Bundle and a DXP application server installation.

## Additional Information

* [Installing the Patching Tool](../reference/installing-the-patching-tool.md)
* [Configuring the Patching Tool](../reference/configuring-the-patching-tool.md)
* [Uninstalling Patches](../reference/uninstalling-patches.md)