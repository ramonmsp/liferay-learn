# Enabling Commerce Products as Related Assets

Commerce Products can be connected to other assets in Liferay Commerce by using the [Related Assets](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships) relationship.

```{note}
Performing a search reindex may be necessary for Commerce Products to appear as a Related Asset option when editing content.
```

## Configure the Asset Browser to Search the Database

1. Navigate to `[Liferay Home]\osgi\marketplace\`. For more information about `Liferay Home` folder, see [Liferay Home](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html).
1. Find the `Liferay Web Experience - Liferay Asset - Impl.lpkg` then open it with an archive manager.
1. In the archive manager window, open the `com.liferay.asset.browser.web.jar`.
1. Using a text editor, open the `portlet.properties` file.
1. Set `search.with.database=true` and update the jar.
1. Restart the application server.

## Execute a Reindex

Execute a reindex to ensure that the Related Assets are populated in the filter.

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _Search_.
1. Click _Execute_ next to Reindex all search indexes.

## Verify the Availability of Commerce Products

Because Commerce Products are stored globally (that is, available on all Liferay DXP sites), users must change the Filter to _Global_.

1. Click on _Site Administration_ &rarr; _Content_ &rarr; _Web Content_.
1. Click the (![Add icon](../../images/icon-add.png)) icon then _Basic Web Content_.
1. Scroll down to the _Related Assets_ section.
1. Click _Select_.
1. Click _Commerce Product_.

     ![Select Commerce Product in the Related Assets section.](./enabling-commerce-products-as-related-assets/images/01.png)

1. Click _Filter and Order_ then _Global_.

     ![Select the Global filter to view Commerce products.](./enabling-commerce-products-as-related-assets/images/02.png)

1. Click the product.

Commerce Products are now available as Related Assets.

## Additional Information

* [Defining Content Relationships](https://help.liferay.com/hc/articles/360028820532-Defining-Content-Relationships)
* [Adding a Basic Web Content Article](https://learn.liferay.com/dxp/latest/en/content-authoring-and-management/web-content/user-guide/web-content-articles/adding-a-basic-web-content-article.html)
