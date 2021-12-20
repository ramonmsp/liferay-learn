# 初期インスタンスのローカリゼーション

Liferay DXPは、言語、タイムゾーンなどによるローカリゼーションをサポートしています。 英語（米国）の翻訳とGMTタイムゾーンがデフォルトですが、DXPには40以上の翻訳があり、任意のタイムゾーンに設定できます。 ローカリゼーションは、仮想インスタンス、各インスタンスのウィジェット、および個々のユーザーを対象としています。 DXPを設定すると、次のインターフェイスを使用して仮想インスタンスのデフォルトの言語とタイムゾーンを設定できます。

* [ポータルプロパティ](#portal-properties)：アプリケーションサーバーを起動する前に、プロパティファイルでデフォルトを指定します。
* [セットアップウィザード](#setup-wizard)：DXPの起動の一環として、UIを介してデフォルトを設定します。
* [コントロールパネル](#control-panel)：DXPインスタンスを起動した後、UIを介してデフォルトを変更します。

## ポータルプロパティ

DXPを開始する前にデフォルトの仮想インスタンスのローカリゼーションを設定する場合は、 [`portal-ext.properties` ファイル](../reference/portal-properties.md)使用します。

| **ポータルプロパティ**               | **説明**                                                                                                                                                           |
|:--------------------------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `company.default.locale`    | [`ロケール`](https://docs.liferay.com/ce/portal/7.3-ga2/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones) ポータルプロパティに定義されている使用可能なロケールに設定します。       |
| `company.default.time.zone` | [`time.zones`](https://docs.liferay.com/ce/portal/7.3-ga2/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones) ポータルプロパティで定義されている任意のタイムゾーンに設定します。 |

例:

``` properties
company.default.locale=pt_PT
company.default.time.zone=Europe/Lisbon
```

上記のプロパティは、ポルトガルのリスボンにいるユーザーの仮想インスタンスをローカライズします。

## セットアップウィザード

[セットアップウィザード](../installing-liferay/running-liferay-for-the-first-time.md) は、DXPインスタンスのデフォルトの言語とタイムゾーンを設定します。 これらは、 *デフォルト言語* および *タイムゾーン* セレクターで選択できます。

![セットアップウィザードを使用して、DXPインスタンスのデフォルトの言語とタイムゾーンを設定する](./initial-instance-localization/images/01.png)

セットアップウィザードがデフォルトで有効になって [のLiferay-Tomcatのバンドル](../installing-liferay/installing-a-liferay-tomcat-bundle.md) と [のアプリケーション・サーバーのインストール](../installing-liferay/installing-liferay-on-an-application-server.md)。

すでにDXPを起動している場合は、コントロールパネルでインスタンスのデフォルトの言語とタイムゾーンを変更します。

## コントロールパネル

仮想インスタンスのデフォルトの言語とタイムゾーンは、インスタンスの *Localization* ページを使用して変更できます。 手順については、[ローカリゼーション](../../system-administration/configuring-liferay/virtual-instances/localization.md)を参照してください。

## まとめ

DXPインスタンスのデフォルトの言語とタイムゾーンを構成しました。 ロケールの選択を容易にする方法など、DXPのローカライズに関する詳細は、 [Sサイトローカリゼーション](../../site-building/site-settings/site-localization.md)を参照してください。

## 追加情報

* [ローカリゼーション](../../system-administration/configuring-liferay/virtual-instances/localization.md)
* [Overriding Global Language Keys](https://learn.liferay.com/dxp/latest/en/liferay-internals/extending-liferay/overriding-global-language-keys.html)
* [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360028746692-Localizing-Your-Application)