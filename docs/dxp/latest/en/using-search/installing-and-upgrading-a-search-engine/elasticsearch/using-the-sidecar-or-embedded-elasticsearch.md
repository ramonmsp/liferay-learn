# Using the Sidecar or Embedded Elasticsearch

The Liferay Tomcat bundles installed via a ZIP file or Docker image include an Elasticsearch node that starts with Liferay. The node in Liferay 7.3+ runs in a separate JVM as a sidecar server and the node in Liferay 7.2 is embedded in the Liferay server.

The Elasticsearch server is accessible at these URLs:

* <http://localhost:9201> on Liferay 7.3+
* <http://localhost:9200> on Liferay 7.2

Here's example sidecar server output:

```json
{
  "name" : "liferay",
  "cluster_name" : "LiferayElasticsearchCluster",
  "cluster_uuid" : "Etr-YyyNQzOPfETH3_GU2A",
  "version" : {
    "number" : "7.10.2",
    "build_flavor" : "unknown",
    "build_type" : "unknown",
    "build_hash" : "747e1cc71def077253878a59143c1f785afa92b9",
    "build_date" : "2021-01-13T00:42:12.435326Z",
    "build_snapshot" : false,
    "lucene_version" : "8.7.0",
    "minimum_wire_compatibility_version" : "6.8.0",
    "minimum_index_compatibility_version" : "6.0.0-beta1"
  },
  "tagline" : "You Know, for Search"
}
```

While the bundled Elasticsearch servers are convenient for development and testing, neither is suitable for production.

```{note}
While it's not a supported production configuration, installing Kibana to monitor the bundled Elasticsearch server is useful during development and testing. Just be aware that you must install the [OSS only Kibana build](https://www.elastic.co/downloads/kibana-oss).
```

You wouldn't run an embedded database like HSQL in production, and you shouldn't run the bundled Elasticsearch server in production either. Instead, run Elasticsearch in remote mode, as a standalone server or cluster of server nodes.

```{important}
Synonym Sets and Result Rankings are applications that use the search index for primary data storage. No data is stored in the Liferay database. Therefore, if you have Synonym Sets or Result Rankings configured while using the sidecar or embedded Elasticsearch, switching to a remote Elasticsearch server and reindexing does `not` restore those configurations. Instead you must manually bring the Synonym Sets and Result Rankings into the remote Elasticsearch cluster. See the [Upgrade Guide](../elasticsearch.html) for details on using Elastic's [Snapshot and Restore](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/snapshot-restore.html) feature to preserve these indexes.
```

## Bundled Elasticsearch Server Use cases

Here are common uses for the default Elasticsearch server (sidecar and embedded):

* Testing your custom [search and indexing code](../../developer-guide.html)
* Developing search queries by running queries directly on Elasticsearch through Kibana
* Testing the [search tuning](../../search-administration-and-tuning.md) functionality
* Exploring and configuring the [search widgets](../../search-pages-and-widgets.md)

## App Server Differences

While an Elasticsearch sidecar server is bundled on Liferay DXP 7.3 and Liferay Portal 7.3 GA4+ Tomcat bundles and Docker images, there are some key differences if you're installing the Liferay WAR onto any supported application server.

| Liferay DXP Flavor       | Default Elasticsearch | Pre-Installed | Requires Manual Intervention |
| ------------------------ | ------------------- | ------------- | ---------------------------- |
| Tomcat bundle: 7.3+  | Sidecar             | &#10004;      | &#10008;                     |
| Tomcat: 7.3+         | Sidecar             | &#10008;      | &#10008; (auto-downloaded)   |
| Docker: 7.3+         | Sidecar             | &#10004;      | &#10008;                     |
| JBoss: 7.3+          | Sidecar             | &#10008;      | &#10008; (auto-downloaded)   |
| Wildfly: 7.3+        | Sidecar             | &#10008;      | &#10008; (auto-downloaded)   |
| WebSphere: 7.3+      | Sidecar             | &#10008;      | &#10004;                     |
| Weblogic: 7.3+       | Sidecar             | &#10008;      | &#10004;                     |
| _All flavors: 7.2/7.3 GA3-_ | _Embedded_       | &#10004;      | &#10008;                     |

If you downloaded a bundle for an application server besides Tomcat, when you start the server an Elasticsearch distribution is downloaded on-the-fly and started as a sidecar server.

Installation instructions for Liferay on the [WebSphere](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md) and [Weblogic](../../../installation-and-upgrades/installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md) application servers include directions for manually providing the Elasticsearch archives required for the sidecar server to be initialized.

```{important}
The bundled Elasticsearch server is useful for development and testing purposes and must not be used in production. See [Installing Elasticsearch](./getting-started-with-elasticsearch.md) to learn about installing a remote search engine.
```

## Embedded Versus Sidecar

Here's a comparison between the embedded and sidecar Elasticsearch servers.

| EMBEDDED           | SIDECAR           |
| ------------------ | ----------------- |
| Cannot configure a secure connection | Cannot configure a secure connection |
| Uses an OSS version of Elasticsearch | Uses an OSS version of Elasticsearch |
| Runs at <http://localhost:9200> | Runs at <http://localhost:9201> |
| Pre-Installed on all Liferay distributions  | Not Always Pre-Installed  |
| Not supported for production  | Not supported for production |
| No special steps required for any app server | [Some app servers](#app-server-differences) require additional steps |
