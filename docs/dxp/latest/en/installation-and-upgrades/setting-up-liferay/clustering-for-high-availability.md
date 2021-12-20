# Clustering for High Availability

```{toctree}
:maxdepth: 3

clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md
clustering-for-high-availability/database-configuration-for-cluster-nodes.md
clustering-for-high-availability/configuring-cluster-link.md
clustering-for-high-availability/configuring-unicast-over-tcp.md
```

Liferay DXP can scale to serve everything from the smallest to the largest web sites. Out-of-the-box, it's configured optimally for a single server environment. If you expect to have high traffic or want to improve the overall fault tolerance of your site, consider clustering DXP.

![Liferay DXP is designed to scale to as large an installation as you need.](./clustering-for-high-availability/images/01.png)

Liferay DXP works well in clusters of multiple machines (horizontal cluster) or in clusters of multiple VMs on a single machine (vertical cluster), or any combination.

## Clustering Requirements

Here is what's required to create an effectively functional DXP cluster.

1. Liferay DXP [installed](../installing-liferay.md) on separate application server nodes

1. A [database or database cluster](./clustering-for-high-availability/database-configuration-for-cluster-nodes.md) that's accessible to all nodes

1. A [File Store](../../../system-administration/file-storage.md) that's accessible to all nodes

1. A [search engine (running outside of DXP) that is optionally clustered](../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)

1. [Cluster Link configured to enable communication and cache replication across all nodes](./clustering-for-high-availability/configuring-cluster-link.md)

After you've configured your DXP cluster, you can deploy applications to it and continue improving user experiences. Please see [Maintaining Clustered Installations](../maintaining-a-liferay-installation/maintaining-clustered-installations.md) for more information.

## What's Next

Creating a cluster by way of example is a great first step in understanding DXP clustering. Start with [Example: Creating a Simple DXP Cluster](./clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md) to learn more. Then address the [Clustering Requirements](#clustering-requirements) listed above.

```{note}
This documentation describes DXP-specific cluster configuration without getting into specific implementations of third party software, such as Java EE application servers, HTTP servers, and load balancers. Please consult the documentation for those components to configure them. Before creating a DXP cluster, make sure your OS is not defining the hostname of your system to the local network at 127.0.0.1.
```