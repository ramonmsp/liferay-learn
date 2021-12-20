# Example: Removing Intermediate Journal Article Versions

These instructions and code samples demonstrate removing intermediate Journal Article versions. In the [script console](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md), you can remove unneeded object versions by executing Java or Groovy code.

Here are example steps for removing intermediate Journal Article versions:

1. **Decide how many of the latest versions to keep.** You must keep the original version and the most recent version, but you may keep additional recent versions too. For example, you may want to keep the two latest versions or just the latest.

2. **Find a method for deleting the entity versions.** Liferay DXP [App APIs](https://docs.liferay.com/dxp/apps/) and the [com.liferay.portal.kernel API](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/) are available options for you to use.

    If it's a [Service Builder](https://help.liferay.com/hc/en-us/articles/360030958811-Running-Service-Builder) entity, examine the `delete*` methods in the entity's `*LocalServiceUtil` class.

    For example, this `deleteArticle` in [`JournalArticleLocalServiceUtil`](https://docs.liferay.com/dxp/apps/web-experience/latest/javadocs/com/liferay/journal/service/JournalArticleLocalServiceUtil.html#deleteArticle-long-java.lang.String-double-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-) deletes a Journal Article version:

    ```java
    deleteArticle(long groupId, java.lang.String articleId, double version,
        java.lang.String articleURL,
        com.liferay.portal.kernel.service.ServiceContext serviceContext)
    ```

3. **Aggregate the entity versions to delete and the information required to delete them.** For example, get all the `JournalArticle` versions in range that match your removal criteria and associate their entity IDs and group IDs with them (the `deleteArticle` method shown above requires the entity ID and group ID).

    The entity object (e.g., `JournalArticle`) typically has a version field. `JournalArticleResource` has each `JournalArticle`'s article ID (the entity's ID) and group ID. `JournalArticleResource` is the key to getting each `JournalArticle`, which can have multiple versions. Here are steps for identifying the `JournalArticle` versions to delete:

    1. Get all the `JournalArticleResource` objects.

        ```java
        List<JournalArticleResource> journalArticleResources =
            JournalArticleLocalServiceUtil.getJournalArticleResources(start, end);
        ```

    1. Get each `JournalArticle` version's workflow status via the `JournalArticle` object associated with each `JournalArticleResource`. [Dynamic Query](https://help.liferay.com/hc/en-us/articles/360030614272-Dynamic-Query) is an efficient way to get exactly the data you want from each object.

        <!--Add back link for 'Dynamic Query' once dynamic-query article is available-->

        ```java
        for (JournalArticleResource
            journalArticeResource : journalArticleResources) {

            List<Double> journalArticlesVersionsToDelete =
                new ArrayList<Double>();

            DynamicQuery dq =
                DynamicQueryFactoryUtil.forClass(JournalArticle.class)
                    .setProjection(ProjectionFactoryUtil.projectionList()
                        .add(ProjectionFactoryUtil.property("id"))
                        .add(ProjectionFactoryUtil.property("version"))
                        .add(ProjectionFactoryUtil.property("status")))
                    .add(PropertyFactoryUtil.forName("groupId")
                        .eq(journalArticeResource.getGroupId()))
                    .add(PropertyFactoryUtil.forName("articleId")
                        .eq(journalArticeResource.getArticleId()))
                    .addOrder(OrderFactoryUtil.asc("version"));

            List<Object[]> result =
                JournalArticleLocalServiceUtil.dynamicQuery(dq);

            // See the next step for the sample code that goes here
        }
        ```

    1. For each `JournalArticleResource` (there's one for each `JournalArticle` entity), build a list of intermediate versions in range of the first or latest versions you want to keep and whose status qualifies them for deletion. For example, you may want to delete intermediate article versions that are approved or expired (i.e., [WorkflowConstants.STATUS_APPROVED or WorkflowConstants.STATUS_EXPIRED](https://learn.liferay.com/reference/latest/en/dxp/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html)). The `MIN_NUMBER_FIRST_VERSIONS_KEPT` and `MIN_NUMBER_LATEST_VERSIONS_KEPT` variables mark the minimum and maximum number of first (oldest) and latest (newest) versions to keep.

        ```java
        List<Double> journalArticlesVersionsToDelete =
            new ArrayList<Double>();

        for (int i=0; i < result.size(); i++) {
            long id = (long) result.get(i)[0];
            double version = (double) result.get(i)[1];
            int status = (int) result.get(i)[2];

            if ((status == WorkflowConstants.STATUS_APPROVED) || (status == WorkflowConstants.STATUS_EXPIRED) {

                if (i < MIN_NUMBER_FIRST_VERSIONS_KEPT) {
                    continue;
                }

                if (i >= (result.size() -
                    MIN_NUMBER_LATEST_VERSIONS_KEPT)) {
                    continue;
                }

                journalArticlesVersionsToDelete.add(version);
            }
        }

        // See the next step for the sample code that goes here
        ```

4. Lastly, delete each Journal Article matching the versions you aggregated.

    ```java
    for (double version : journalArticlesVersionsToDelete) {
    {
        JournalArticleLocalServiceUtil.deleteArticle(journalArticeResource.getGroupId(),
            journalArticeResource.getArticleId(),
            journalArticlesVersionsToDelete(i), null, null);
    }
    ```

You can write similar code to remove intermediate versions of other entities. Once your code is ready, run it using either a sample module, or running it as a script using the script console.

```{tip}
Print the version (and any other information of interest) of each object you're removing. You can also comment out the object deletion call and read the printout of versions to be removed as a test before committing to deleting them.
```

## Additional Information

* [Pruning the Database](./database-pruning-for-faster-upgrades.md)
* [Tuning for the Data Upgrade](./database-tuning-for-upgrades.md)
