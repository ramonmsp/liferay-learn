# 同様の結果ウィジェットへのカスタムコンテンツの提供

> **サブスクライバー**

> **可用性：** この機能は、Liferay DXP 7.3+にバンドルされているService Provider Interface（SPI）に依存しています。 Liferay DX 7.2で、Fix Pack 5+から、[Liferay Marketplace](https://web.liferay.com/marketplace/-/mp/application/172465398)から同様の結果ウィジェットをインストールして入手できます。

`SimilarResultsContributor`実装することにより、 [類似結果ウィジェット](../search-pages-and-widgets/similar-results.md) アプリケーションのカスタムコンテンツを表示できます。 コントリビューターが機能するには、類似結果ウィジェットがコンテンツをページのメインアセットとして検出できる必要があります。 つまり、サポートされているLiferay DXPアセット（ブログエントリやWikiページなど）のように、「表示ウィジェット」のURLを介して表示できる必要があります。 類似結果ウィジェットは、カスタムコントリビューターを必要とせずに、Lifery DXPのアセットパブリッシャーに表示されるコンテンツですでに使用できることに注意してください。

<!-- I'd like to see the image highlight what someone should be noticing in this image because it is not immediately obvious. -->

![ブログ表示ウィジェットは、その貢献者がいるため、類似の結果で機能します。](./writing-a-similar-results-contributor/images/01.png "ブログの類似結果")

ナレッジベースアプリケーションは、そのままではKB記事の `SimilarResultsContributor` を実装しないため、この例では1を実装しています。 簡単にするために、ここではアプリケーションのルートフォルダーにあるKB記事のみを扱います。

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

Liferay DXPのインスタンスで例 `SimilarResultsContributor` 起動して実行するには、

1.  Liferay DXPを起動する。 まだDockerコンテナがない場合は、

    ``` bash
    docker run -it -p 8080:8080 liferay/dxp:7.2.10-sp2
    ```

    別のLiferay Portal CEバージョンまたはLiferay DXPを実行している場合は、上記のコマンドを適宜調整してください。

<!--7.2.10-sp2 is my guess at the DXP container ID for a docker image with support for similar results-->

1.  [KBの記事同様の結果の寄稿者の例](./liferay-r1s1.zip)ダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/using-search/developer-guide/liferay-r1s1.zip -O
    ```

    ``` bash
    unzip liferay-r1s1.zip
    ```

2.  モジュールのルートから、ビルドおよびデプロイします。

    ``` bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
    ```

3.  Liferay Dockerコンテナコンソールでデプロイを確認します。

    ``` bash
    STARTED com.liferay.learn.r1s1.impl_1.0.0 [1009]
    ```

4.  サンプルのコントリビューターが機能していることを確認します。 まず、ブラウザで`https://localhost:8080`を開きます。

5.  *サイトメニュー* → *コンテンツ* → *ナレッジベース*にいくつかのKB記事を追加します。

    それらが同様の *Title* および *Content* フィールドを持っていることを確認してください。 これらの文字列を使用して、3つの記事を作成できます（タイトルとコンテンツに同じ文字列を使用します）。

    *KB記事1をテストする*

    *KB記事2をテストする*

    *KB記事3をテストする*

6.  ナレッジベース表示ウィジェットをページに追加し、続いて類似結果ウィジェットを追加します。

7.  類似結果ウィジェットのウィジェット構成を開き、これらの設定に必ず *1* 値を設定してください。

    最小期間頻度：1最小ドキュメント頻度：1

8.  KB記事の1つをクリックして、表示するメインアセットとして選択します。

    類似結果ウィジェットに、他の関連するKB記事が表示されます。

<!-- This image could be a bit larger and also have some sort of highlighting to indicate the element that we want to draw the readers attention towards. -->

![類似結果ウィジェットは、KB記事を表示できます。](./writing-a-similar-results-contributor/images/02.png "KBの類似結果")

例が適切に動作することを確認したので、それがどのように機能するかを学びます。

## 例の説明

デプロイされた例を確認します。 これには、クラスが1つだけ含まれています。これは、類似結果ウィジェットのカスタムコンテンツを有効にするコントリビューターです。

  - [OSGi登録のコントリビュータークラスに注釈を付ける](#annotate-the-contributor-class-for-osgi-registration)
  - [`SimilarResultsContributor` インターフェースを確認する](#review-the-similarresultscontributor-interface)

### OSGi登録のコントリビュータークラスに注釈を付ける

`KBSimilarResultsContributor` は、 `SimilarResultsContributor` インターフェースを実装します。

``` java
@Component(service = SimilarResultsContributor.class)
public class KBSimilarResultsContributor implements SimilarResultsContributor {
```

`サービス` コンポーネントプロパティは、実装を `SimilarResultsContributor` サービスとして登録します。

### `SimilarResultsContributor` インターフェースを確認する

インターフェースから3つのメソッドを実装します。

``` java
public void detectRoute(RouteBuilder routeBuilder, RouteHelper routeHelper);
```

エンティティのURLパターンの特徴的な部分を提供するために `detectRoute` を実装して、類似結果ウィジェットがコントリビューターを呼び出す必要があるかどうかを検出できるようにします。 URLパターンは、 `RouteBuilder` オブジェクトの属性として追加されます。 `RouteHelper` は、解析のためにURL文字列全体を取得するのに役立ちます。

```{note}
Only one `SimilarResultsContributor` is supported for each display portlet.
```

``` java
public void resolveCriteria(CriteriaBuilder criteriaBuilder, CriteriaHelper criteriaHelper);
```

ページのメインエンティティを使用して対応する検索エンジンドキュメントを検索するには、 `resolveCriteria` を実装します。 これは、検出されたルートが貢献者が適切なものであることを示している場合に呼び出されます。

``` java
public void writeDestination(DestinationBuilder destinationBuilder, DestinationHelper destinationHelper);
```

ユーザーが類似結果ウィジェットのリンクをクリックしたときにメインアセットを更新するには、 `writeDestination` を実装します。

### 同様の結果の貢献者を完了する

#### `detectRoute` メソッドを実装する

``` java
@Override
public void detectRoute(RouteBuilder routeBuilder, RouteHelper routeHelper) {

    String[] subpath = StringUtil.split(_http.getPath(routeHelper.getURLString()), Portal.FRIENDLY_URL_SEPARATOR);

    String[] parameters = StringUtil.split(subpath[subpath.length - 1], CharPool.FORWARD_SLASH);

    if (!parameters[0].matches("knowledge_base")) {
        throw new RuntimeException("KBArticle was not detected");
    }

    routeBuilder.addAttribute("urlTitle", parameters[1]);
}
```

エンティティのURLパターンの特徴的な部分をチェックするロジックを挿入するには、 `detectRoute` を実装します。 類似結果ウィジェットはこのチェックを使用して、正しい `SimilarResultsContributor`を見つけます。 エンティティの表示URLが検出された場合は、後で使用するために少なくとも1つの属性をURLルートに追加します。 ここでは、Friendly URLで `"knowledge_base"` を確認し、メソッドシグネチャで渡された `RouteBuilder` 属性として `"urlTitle"` 追加されていることを検出します。

`routeHelper.getUrlString` 呼び出しは、仮想インスタンス内で検出されたアセットの相対URLを取得するために使用できるため、重要です。 例:

``` sh
/web/guest/page-title/-/knowledge_base/kb-article-url-title
```

IDは、に属性として追加される `RouteBuilder` エンティティをフェッチするために使用され、中に対応する検索エンジン文献 `resolveCriteria` 方法。

#### `resolveCriteria` メソッドを実装する

``` java
@Override
public void resolveCriteria(CriteriaBuilder criteriaBuilder, CriteriaHelper criteriaHelper) {

    long groupId = criteriaHelper.getGroupId();

    String urlTitle = (String) criteriaHelper.getRouteParameter("urlTitle");

    KBArticle kbArticle = _kbArticleLocalService.fetchKBArticleByUrlTitle(groupId,
            KBFolderConstants.DEFAULT_PARENT_FOLDER_ID, urlTitle);

    if (kbArticle == null) {
        return;
    }

    AssetEntry assetEntry = _assetEntryLocalService.fetchEntry(groupId, kbArticle.getUuid());

    if (assetEntry == null) {
        return;
    }

    String uidField = String.valueOf(kbArticle.getPrimaryKeyObj());

    int buildNumber = ReleaseInfo.getBuildNumber();

    if (ReleaseInfo.getBuildNumber() == ReleaseInfo.RELEASE_7_2_10_BUILD_NUMBER) {

        uidField = String.valueOf(kbArticle.getResourcePrimKey());
    }

    criteriaBuilder.uid(Field.getUID(assetEntry.getClassName(), uidField));
}
```

ページに表示されているエンティティに対応する検索エンジンドキュメントを検索します。 `criteriaBuilder.uid` メソッドに適切な検索エンジンドキュメントの `uid` フィールドの値を指定する必要があります（これは通常、ドキュメントのElasticsearchで指定された `_id` フィールドと同じです）。 Liferay DXPインデックスでは、このフィールドはエントリクラス名とクラス主キーの構成です。 両方を文字列として `Field.getUID` に渡して値を取得します。 この例では、最初に `detectRoute` メソッド（ `urlTitle`）で属性に追加したIDを使用してモデルエンティティをフェッチし、それを使用してアセットエントリを取得します。

```{important}
There's a difference between Liferay DXP 7.2 and Liferay DXP 7.3, so a condition to check the version, with logic for each, is provided here. In Liferay DXP 7.3, `getPrimaryKeyObj` is used in conjunction with the class name, whereas in Liferay DXP 7.2, `getResourcePrimKey` is needed.
```

一致するドキュメントが見つかったので、同様の結果が更新されるようにリンク先URLを記述します。

#### `writeDestination` メソッドを実装する

``` java
@Override
public void writeDestination(DestinationBuilder destinationBuilder, DestinationHelper destinationHelper) {

    String urlTitle = (String) destinationHelper.getRouteParameter("urlTitle");

    AssetRenderer<?> assetRenderer = destinationHelper.getAssetRenderer();

    KBArticle kbArticle = (KBArticle) assetRenderer.getAssetObject();
    destinationBuilder.replace(urlTitle, kbArticle.getUrlTitle());

}
```

`writeDestination` を実装して、ユーザーが類似結果ウィジェットのリンクをクリックしたときにメインアセットを更新します。 More Likeこのクエリは検索エンジンに再送信され、Similar Resultsリストは新しいメインアセットに一致するように再レンダリングされます。 KB記事の場合、作業全体は、（メインアセットの）元のURLの `urlTitle` を、一致したエンティティの `urlTitle` で置き換えることです。

`destinationHelper.getRouteParameter` 呼び出しが重要です。 検索前演算子である `DestinationHelper` からの唯一のメソッドとして、メインアセットまたは類似結果リンクを再レンダリングする前に、常に現在選択されているメインアセットからデータを返します。 `DestinationHelper` メソッドの残りの部分（ここに示されている他のメソッド `getAssetRenderer`を含む）は、一致したアセットのデータを返します。 このメソッドは、一致した結果ごとに繰り返し実行されます。

#### サービスの依存関係を宣言する

このコードは、OSGiコンテナに配備サービスに依存： `AssetEntryLocalService`、 `KBArticleLocalService`、及び `のHttp`。 `org.osgi.service.component.annotationsによって提供されるDeclarative Services <code>@Reference` アノテーションを使用して、それらの必要性を宣言します。リファレンス</code>。 それらをプライベートフィールドに設定します。

``` java
@Reference
private AssetEntryLocalService _assetEntryLocalService;

@Reference
private Http _http;

@Reference
private KBArticleLocalService _kbArticleLocalService;
```

## 追加情報

エンティティのURLの各実装は大幅に異なる可能性が高いため、独自のアプリケーションのコントリビューターを作成するときにさらにインスピレーションが必要な場合は、GitHubの `SimilarResultsContributor` [インターフェース](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/dxp/apps/portal-search-similar-results/portal-search-similar-results-web-spi/src/main/java/com/liferay/portal/search/similar/results/web/spi/contributor/SimilarResultsContributor.java) およびバンドルされた [実装](https://github.com/liferay/liferay-portal/tree/7.3.2-ga3/modules/dxp/apps/portal-search-similar-results/portal-search-similar-results-web/src/main/java/com/liferay/portal/search/similar/results/web/internal/contributor) してください。

アプリケーションのカスタムコンテンツを類似結果ウィジェットに提供するために必要な作業の多くは、表示URLを使用することです。 Liferay独自のアセットが表示URLを作成する方法を学ぶには、エンティティの `* AssetRenderer` クラスの `getURLView` メソッドを調べます。

  - [`JournalArticleAssetRenderer＃getURLView`](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/apps/journal/journal-web/src/main/java/com/liferay/journal/web/internal/asset/model/JournalArticleAssetRenderer.java#L352-L383)、Liferay DXP 7.3.2 GA3
  - [`WikiPageAssetRenderer＃getURLView`](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/apps/wiki/wiki-web/src/main/java/com/liferay/wiki/web/internal/asset/model/WikiPageAssetRenderer.java#L232-L249)、Liferay DXP 7.3.2 GA3
  - [`BlogsEntryAssetRenderer＃getURLView`](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/asset/model/BlogsEntryAssetRenderer.java#L202-L218)、Liferay DXP 7.3.2 GA3
  - [`DLFileEntryAssetRenderer＃getURLView`](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/asset/model/DLFileEntryAssetRenderer.java#L280-L297)、Liferay DXP 7.3.2 GA3

前述のとおり、この例では、アプリケーションのルートフォルダーにあるKB記事で機能する `SimilarResultsModelDocumentContributor` を作成する方法を示しています。 KBフォルダーのサポートを追加することは可能であり、やる気のある読者にとって興味深い演習です。 インスピレーションを得るために [`DocumentLibrarySimilarResultsContributor`](https://github.com/liferay/liferay-portal/blob/7.3.2-ga3/modules/dxp/apps/portal-search-similar-results/portal-search-similar-results-web/src/main/java/com/liferay/portal/search/similar/results/web/internal/contributor/document/library/DocumentLibrarySimilarResultsContributor.java) のソースコードを見てください。

### トラブルシューティング：アセットUIDアーキテクチャ

`uid` は、Liferay DXP 7.3以降の標準的な方法で構築されます。 `com.liferay.portal.search.internal.model.uid.UIDFactoryImpl` クラスは、Liferayのインデックスアーキテクチャによって制御されているすべてのドキュメントに `uid` を設定する責任があります。 現在は標準化されているので、推測をする必要はありません。

同様に、バージョン7.2および7.1では、エンティティにComposite Indexer APIでインデックスが付けられている（つまり、ModelDocumentContributor</code> クラスが `）場合、 <code>uid` はLiferayの実装によって設定され、標準化されます。

ただし、レガシーインデクサーAPIでインデックス付けされたエンティティ（つまり、エンティティにはLiferayの `BaseIndexer`を拡張する `*インデクサー` クラスがある）は、 `uid`を設定するロジックをオーバーライドしている可能性があるため、エンティティのインデックス付けの実装を調べる価値があります。

## まとめ

`SimilarResultsContributor`実装することにより、独自のカスタムコンテンツを提供して、SimilarResultsウィジェットに表示できます。
