# フラグメントへの構成オプションの追加

> 対応可能：Liferay DXP 7.2 SP1以降

構成可能なオプションを使用して、フラグメントを柔軟にすることができるので、似たようなフラグメントを多数管理する必要はありません。 たとえば、スタイルAの見出しを持つ1つのフラグメントとスタイルBの見出しを持つ別のフラグメントを作成する代わりに、スタイルAとBのオプションを持つ見出しに対し構成可能なスタイルを持つフラグメントを1つ作成できます。ここでは、フラグメントに構成オプションを追加する方法を学びます。

  - [構成可能なフラグメントのデプロイ](#deploy-a-configurable-fragment)
  - [構成の検討](#examine-the-configuration)
  - [構成テキスト値のエスケープ](#escape-configuration-text-values)
  - [構成の変更](#modify-the-configuration)
  - [変更のプロパゲートとテスト](#propagate-the-changes-and-test)
  - [関連情報](#related-information)

## 構成可能なフラグメントのデプロイ

まず、サンプルをデプロイして、フラグメント設定の構成オプションがどのように機能するかを確認します。

1.  以下のコマンドを実行して、Docker コンテナを起動します。

    ``` bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

2.  [サンプルのフラグメントコレクション](https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-c7f8.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-c7f8.zip -O
    ```

    ``` bash
    unzip liferay-c7f8.zip
    ```

3.  フラグメントツールキットを設定します。

    ``` bash
    cd liferay-c7f8
    ```

    ``` bash
    ./setup_tutorial.sh 
    ```

4.  フラグメントツールキットを使用して、フラグメントコレクションをDockerコンテナにインポートします。 あるいは、[手動でフラグメントをインポート](../../displaying-content/using-fragments/managing-page-fragments.md)することもできます。

    ``` bash
    yarn run import

    > yo liferay-fragments:import

    ? Liferay host & port http://localhost:8080
    ? Username test@liferay.com
    ? Password [hidden]
    Checking connection...
    Connection successful

    ? Company ID liferay.com
    ? Group ID Liferay
    Building project...
    Importing project...
    ✔ Fragment C7F8 Card imported
    Project imported
    ```

5.  フラグメントコレクションが利用可能であることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、*[デザイン]* → *[フラグメント]* に移動します。 コレクションがコレクションリストに表示されます。

    ![コレクションが利用可能です。](./adding-configuration-options-to-fragments/images/01.png)

    ```{note}
    Liferay DXP 7.1および7.2では、代わりにプロダクトメニューの*サイト* → *サイトビルダー* → *ページ フラグメント*に移動して、[フラグメント]ページを表示します。
    ```

6.  ホームページに移動し、(![Edit icon](../../../images/icon-edit-pencil.png)) アイコンをクリックしてコンテンツページを編集します。

7.  [フラグメントパネル](../../creating-pages/building-and-managing-content-pages/editing-content-pages.md#fragments)の*Configurable Marketing Collection*見出しを展開し、*Configurable Marketing Card*フラグメントをページにドラッグします。

8.  [Configurable Marketing Card]を選択します。 表示されるコンテキスト メニューの（![Gear icon](../../../images/icon-control-menu-gear.png)) アイコンをクリックして、フラグメント設定メニューを開きます。 ここから、テキスト スタイルの構成オプションを選択して、テキストを暗い色から明るい色に変更できます。

9.  [コンテンツ ページ](../../creating-pages/understanding-pages/understanding-pages.md#content-pages) (デフォルトのホームページなど) に移動し、(![Edit icon](../../../images/icon-edit-pencil.png)) アイコンをクリックしてページを編集します。

10. [フラグメントとウィジェットパネル](../../creating-pages/building-and-managing-content-pages/content-pages-overview.md#fragments)の*C7F8コレクション*見出しを展開し、*C7F8カード*フラグメントをページにドラッグします。

11. ページで*[C7F8 カード]* を選択します。 右側に選択パネルが表示されます。 *[一般]* タブで、コンポーネントのテキスト スタイルを「暗い」または「明るい」に設定します。

    ![構成可能なフラグメントには、フラグメントのルック アンド フィールを変更するオプションが用意されています。](./adding-configuration-options-to-fragments/images/02.png)

構成可能なフラグメントを正常にインポートして設定しました。

## 構成の検討

フラグメントの構成オプションは、Liferayに内蔵されている[フラグメントエディタ](./using-the-fragments-editor.md)やテキストファイルで編集することができます。

**フラグメントエディタの使用：**UIで、[フラグメントエディタ](./using-the-fragments-editor.md)を使用してフラグメントを編集し、*[設定]* タブをクリックします。 フラグメントの設定がエディタに表示されます。

**フラグメント設定ファイルの編集：**フラグメントファイルがない場合は、フラグメントのアクションメニューを開き、*[エクスポート]* をクリックしてフラグメントからエクスポートします。 フラグメントの`fragment.json`ファイルの`configurationPath`フィールド（オプション）は、`.json`構成ファイルを指定します。 構成ファイルがない場合は、構成ファイルを作成し、`configurationPath`フィールドを構成ファイル名に設定します。

サンプルの`fragment.json`ファイルを開き、フラグメントの構成ファイルを決定します。

``` json
{
    "configurationPath": "index.json",
    "cssPath": "index.css",
    "htmlPath": "index.html",
    "jsPath": "index.js",
    "name": "C7F8 Card",
    "type": "component"
}
```

`"configurationPath": "index.json"`プロパティは、このフラグメントの構成ファイルが`index.json`であることを示しています。

構成ファイルを開きます。

``` json
{
    "fieldSets": [
        {
            "fields": [
                {
                    "dataType": "string",
                    "defaultValue": "dark",
                    "label": "Text Style",
                    "name": "c7f8TextStyle",
                    "type": "select",
                    "typeOptions": {
                        "validValues": [
                            {
                                "value": "dark"
                            },
                            {
                                "value": "light"
                            }
                        ]
                    }
                }
            ],
            "label": "C7F8"
        }
    ]
}
```

上記の構成ファイルでは、*dark*または*light*テキストスタイルをフラグメントに適用するためのセレクタを指定しています。 この構成には、`c7f8TextStyle`という名前のフィールドがあります。 フィールドの`type`は`select`であり、セレクタコンポーネントになります。 詳細は、[Fragment Configuration Types](../reference/fragments/fragment-configuration-types-reference.md)を参照してください。 セレクタの有効な値は、`dark`または`light`です。 `"dataType": "string"`プロパティは、セレクタが文字列データで動作することを意味します。

このセレクタは、*C7F8*というラベルの付いたフィールド セットの一部です (ラベルはオプションです)。 UIでは、このセレクタは、フラグメントの*C7F8*というラベルが付いた構成セクションにあります。 セレクタは、フィールドの`label`プロパティごとに、`Text Style`とラベル付けされています。 次の図にセレクタを示します。

![これが C7F8 カード フラグメントのセレクタです。](./adding-configuration-options-to-fragments/images/03.png)

```{warning}
フラグメントエディタは、有効になるまで構成を保存しません。 プレビューする前に、JSON構成が有効であることを確認してください。
```

ユーザーが選択した構成値は、FreeMarkerコンテキストを介してHTMLで使用できるようになります。 これらは、HTMLの中で `${configuration.fieldName}`という表記で参照されます。 このサンプル（`${configuration.textAppliedStyle}`）は、ユーザーが選択した構成値に応じて `dark` または `light` を返し、CSS クラスを `text-light` または `text-dark`に設定します。

``` html
<div class="component-c7f8-card">
    <div class="card">
        <img
            class="card-img-top"
            data-lfr-editable-id="01-image"
            data-lfr-editable-type="image"
            src="https://clayui.com/images/home/lexicon_symbol.svg"
        />

        <div class="card-body">
            <h5
                class="card-title text-${configuration.c7f8TextStyle}"
                data-lfr-editable-id="02-title"
                data-lfr-editable-type="rich-text"
            >
                Editable Card Title
            </h5>

            <p
                class="card-text text-${configuration.c7f8TextStyle}"
                data-lfr-editable-id="03-text"
                data-lfr-editable-type="rich-text"
            >
                Here is some editable text.
            </p>

            <a
                class="btn btn-primary"
                data-lfr-editable-id="04-label"
                data-lfr-editable-type="link"
                href="#"
            >
                Editable Link
            </a>
        </div>
    </div>
</div>
```

この例は、選択の構成を示しています。 使用可能なフラグメント構成タイプの完全なリストは、[Configuration Types Reference](../reference/fragments/fragment-configuration-types-reference.md)を参照してください。

### 構成テキスト値のエスケープ

悪意のあるコードがテキストフィールドに挿入され、他のフラグメントユーザーに大損害を与える可能性があります。 クロスサイトスクリプティング（XSS）攻撃から保護するには、フラグメントテキスト値をエスケープする必要があります。

一般的なケースでは、HTML `escape()`メソッドを使用できます。 詳細は、[`HtmlUtil`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/HtmlUtil.html)クラスを参照してください。

``` html
<div class="fragment_38816">
    "${htmlUtil.escape(configuration.text)}"
</div>
```

属性の設定やHTMLの子要素の追加など、JavaScript攻撃を防ぐには、`Liferay.Util.escapeHTML()` 関数を使用します。

``` javascript
function (fragmentElement, configuration) {
    const escapedValue = Liferay.Util.escapeHTML(configuration.text)
}
```

## 構成の変更

構成がどのように機能するかを理解したので、構成を変更できます。

1.  画面左側のサイトメニューから、*[デザイン]* → *[フラグメント]* に移動します。 コレクションがコレクションリストに表示されます。

    ```{note}
    Liferay DXP 7.1および7.2では、代わりにプロダクトメニューの*サイト* → *サイトビルダー* → *ページ フラグメント*に移動して、[フラグメント]ページを表示します。
    ```

2.  *[C7F8コレクション]* を選択し、C7F8 カードの*アクション* (![Actions Icon](../../../images/icon-actions.png)）をクリックして、*[編集する]* を選択します。 フラグメント エディタが表示されます。

3.  *[設定]* タブをクリックし、カードの説明を非表示/表示するチェックボックスフィールドで構成を更新します。 `c7f8TextStyle`フィールドの閉じ括弧とカンマ（`},`) の後の新しい行に次のコードを挿入します。

    ``` json
    {
        "name": "showDescription",
        "label": "Show Description",
        "description": "show-description",
        "type": "checkbox",
        "defaultValue": true
    }
    ```

4.  *[コード]* タブの*[HTML]* ペインに戻り、チェックボックスの値をチェックするための条件付きステートメントで段落要素をラップします。 *[Publish]* をクリックして変更を適用します。

    ``` html
    [#if configuration.showDescription]
      <p data-lfr-editable-id="03-card-text" data-lfr-editable-type="rich-text" 
      class="card-text text-${configuration.textAppliedStyle}">
        Here is some editable text.
      </p>
    [/#if]
    ```

<!-- end list -->

```{note}
また、`const configurationValue = configuration.textAppliedStyle;`という構文でJavaScriptから構成値にアクセスすることもできます。
```

## 変更のプロパゲートとテスト

これで、更新をテストできます。

1.  変更をプロパゲートして、コンテンツ ページに反映させます。 C7F8カードの*アクション*（![Action Icon](../../../images/icon-actions.png)）をクリックし、 *[利用数を表示]* を選択します。

2.  コンテンツページのボックスをオンにして、(![propagate button](../../../images/icon-propagate.png)) ボタンをクリックします。

    ![構成可能なフラグメントには、フラグメントのルックアンドフィールを変更するオプションが用意されています。](./adding-configuration-options-to-fragments/images/04.png)

3.  コンテンツページに戻り、もう一度 (![Edit icon](../../../images/icon-edit-pencil.png)) アイコンをクリックしてコンテンツページを編集します。

4.  *[C7F8 カード]* をもう一度選択して、右側に選択パネルを表示します。

5.  *[一般]* タブで、*[Show Description]* チェックボックスをオン/オフして、カードのテキストを表示/非表示にします。

    ![フラグメントには、必要な数の構成オプションを設定できます。](./adding-configuration-options-to-fragments/images/05.png)

これで、構成オプションをフラグメントに追加する方法がわかりました。 その他の設定例については、[Fragment Configuration Types](../reference/fragments/fragment-configuration-types-reference.md)を参照してください。

## 関連情報

  - [Auto-deploying Fragments](./auto-deploying-fragments.md)
  - [Including Default Resources with Fragments](./including-default-resources-with-fragments.md)
  - [Fragment Configuration Types Reference](../reference/fragments/fragment-configuration-types-reference.md)
