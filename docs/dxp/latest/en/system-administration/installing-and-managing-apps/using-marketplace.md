# Using Marketplace

Liferay Marketplace is a hub for sharing, browsing, and downloading apps. Marketplace leverages the entire Liferay ecosystem to release and share apps in a user-friendly, one-stop shop.

![The Liferay Marketplace home page highlights new apps, lists apps by categories, and has search.](./using-marketplace/images/01.png)

The Marketplace plugin makes the Marketplace available in the Control Panel. The plugin is built-in to the DXP Tomcat Bundle but it can be installed to DXP installations on application servers too. If you still need to install Marketplace in your installation, then see [Appendix: Installing the Marketplace Plugin](#appendix-installing-the-marketplace-plugin).

## Visiting the Marketplace

Here's how to enter the Marketplace from within DXP:

1. In the Control Panel, navigate to *Apps* &rarr; *Store*.

1. Sign in with your liferay.com account.

```{important}
Purchasing apps requires that you have a [liferay.com](https://www.liferay.com) account and agree to the Marketplace Terms of Use.
```

The Marketplace appears. It's time to check out some apps.

## Browsing Apps

Marketplace provides a familiar app store experience. Apps are in the center of the page, in the following sections:

* *Featured Apps:* Liferay features a different set of apps each month.

* *New and Interesting:* The latest apps added to Marketplace.

* *Most Viewed in the Past Month:* The top 5 most viewed apps in the last month.

* *Liferay Subscription Apps:* Apps available to enterprise subscribers only.

* *Templates and Themes:* Apps for organizing site content and changing your site's look and feel.

* And app categories: *Communication*, *Productivity*, *Security*, and more.

Each section's *See All* link lists all the apps for that section.

![Each app category page, such as the Communication app page, lists the apps published to that category.](./using-marketplace/images/02.png)

At the top of the page, you can search Marketplace in these ways:

* Keywords
* Category
* Liferay (DXP) version
* Price (free or paid)

At the bottom of the page, *Weekly Stats* lists the newest apps and latest updated apps, and shows a trend chart for app downloads and views.

![Weekly stats shows lists new apps, app updates, and Marketplace activity trends.](./using-marketplace/images/03.png)

Click an app to view details:

![Click an app to see screenshots and app details.](./using-marketplace/images/04.png)

## Purchasing an App

```{warning}
In production-grade environments, only purchase apps via the [Marketplace website](https://web.liferay.com/marketplace). Apps purchased in the Control Panel download and install using auto deploy automatically; apps that aren't designed for auto deployment can cause class loading issues and memory leaks.
```

Here are the app purchase steps:

1. Click the *Purchase* (Free or Paid) button. You're prompted to choose a purchase type.

1. Choose a purchase type. You can purchase an app for your personal account or for a Liferay project associated with your company.

    A *Liferay project* is a space where teammates share apps they have purchased or developed. If you have the necessary permissions, you can also create a new project for your company.

1. Accept the EULA and Terms of Service.

1. Click *Purchase* to complete the transaction.

The app downloads and installs to Liferay DXP automatically. Congratulations! You know how to find, purchase, and install Liferay Marketplace apps.

## Appendix: Installing the Marketplace Plugin

If you installed DXP on an application server, you must install the Marketplace plugin to access the Marketplace via the Control Panel.

```{note}
If you are using the DXP Tomcat bundle, then the Marketplace plugin is pre-installed in your bundle.
```

Follow these steps to install the Marketplace plugin:

1. Download the [Marketplace plugin](https://www.liferay.com/marketplace/download).

2. Deploy the plugin (`.lpkg` file) by copying it to the `deploy` folder in [Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md) or installing it using the [App Manager](./managing-apps/using-the-app-manager.md).

The Marketplace is now available to use in the Control Panel.

## Additional information

* [Installing Apps](./installing-apps.md)
* [Renewing Apps](./managing-apps/renewing-apps.md)
* [Liferay Marketplace Developer Portal](https://marketplace.liferay.dev/)