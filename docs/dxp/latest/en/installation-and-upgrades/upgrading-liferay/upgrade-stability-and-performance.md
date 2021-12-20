# Upgrade Stability and Performance

```{toctree}
:maxdepth: 2

upgrade-stability-and-performance/database-tuning-for-upgrades.md
upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md
upgrade-stability-and-performance/example-removing-intermediate-journal-article-versions.md
upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md
```

When you upgrade a DXP instance for a production release, the data upgrades must be done correctly and swiftly. Here are some ways to help assure success:

* Create a test environment that uses copies of your database and installation 
* Prune unnecessary database data to improve performance
* Tune the database for upgrade operations
* Upgrade the database and troubleshoot upgrade issues
* Test the upgraded database with DXP

## Creating a Test Environment

It's important to test upgrading in an environment that separate from production but that uses the same data, configurations, and apps. You can use copies of your installation (Liferay Home), database, and Marketplace apps. These should be readily available from your [backup](../maintaining-a-liferay-installation/backing-up.md).

## Pruning Unnecessary Data

If you actively update your DXP sites, it's likely that there's data you no longer need. This can include sites, roles, or organizations you've stopped using or revisions of content you no longer need. Deleting unneeded entities and data related to them can save you upgrade processing time. For more details, see [Database Pruning for Faster Upgrades](./upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md). And make sure to also check out the [Example\: Removing Intermediate Journal Article Versions](./upgrade-stability-and-performance/example-removing-intermediate-journal-article-versions.md).

## Tuning the Database for the Upgrade

DXP upgrade processes write to the database more than is typically done in production. You can optimize your database for write operations. For guidance, see [Database Tuning for Upgrades](./upgrade-stability-and-performance/database-tuning-for-upgrades.md).

## Upgrading and Troubleshooting Issues

After you've pruned and tuned your test database, upgrade it [using the database upgrade tool](./upgrade-basics/using-the-database-upgrade-tool.md). If you run into problems, such as unresolved dependencies, you can examine the logs and use Gogo shell commands to troubleshoot issues and complete the upgrades. Visit [Upgrading Modules Using Gogo Shell](./upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) to learn how.

## Testing the Upgraded Database

Lastly, take time to test your DXP instance with your upgraded database.

After you've upgraded your DXP instance successfully, you can orchestrate releasing it to production.

## Additional Information 

* [Updating Clustered Installations](../maintaining-a-liferay-installation/maintaining-clustered-installations.md)