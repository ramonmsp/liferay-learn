# OpenID Connectの使用

OpenID Connectは、ユーザーが他のシステムにあるアカウントを使用して認証できるようにする軽量の認証レイヤーです。 これは[OAuth 2.0承認](../../../headless-delivery/using_oauth2.rst)プロトコルの上に構築されています。 OpenID Connectを使用することで、ユーザー認証を他のプロバイダーに*委任*し、既存のアカウントを持つユーザがLiferayのインストールで簡単に認証できるようにします。

```{note}
インストールに複数のプロバイダーを追加できますが、Liferay DXPをOpenID Connectプロバイダーにすることはできません。
```

OpenID ConnectのトークンフローはOAuth 2.0の機能の上に構築されているため、OAuth 2.0に似ています。 OAuth 2.0は認証プロトコルにすぎないため、特定のAPIへのアクセスを許可する*アクセストークン*を送信します。 OpenID Connectは、ユーザーが認証され、権限を付与されている場合に、*名前*や*電子メール*などのユーザー情報を渡す*IDトークン*をこれに追加します。

## OpenID Connectプロバイダーでクライアントを作成する

OpenID Connectを使用するには、まずプロバイダーにクライアントとして登録する必要があります。 これはOAuth 2.0クライアントです。 プロセスはプロバイダーによって異なります。

1.  プロバイダーのWebサイトに移動し、クライアントを作成します。

2.  作成プロセス中に、プロバイダーから送信されたトークンを処理できる*承認済みリダイレクトURL*を提供する必要があります。 Liferay DXPのURLは次のとおりです。
   
        https://[server.domain]/c/portal/login/openidconnect

3.  プロバイダーはいくつかの情報を送信します。 検出エンドポイント、承認エンドポイント、発行者URLなど、これらのいくつかはクライアントに関係なく同じです。 リクエストに固有の2つの情報は、`client_id`と`client_secret`です。

プロバイダーから情報を収集します。 プロバイダー接続を作成する必要があります。

## OpenID Connectプロバイダー接続の構成

*[Control Panel]* → *[Configuration]* → *[System Settings]* → *[Security]* → *[SSO]* に移動して、*[System Scope]* の下で***[OpenID Connect Provider]***を選択します。

![[System Settings]メニューでOpenID構成を検索する。](using-openid-connect/images/01.png)

次の手順を実行します：

1.  *[Add]* ボタンをクリックしてプロバイダーを追加します。

2.  プロバイダーから受け取った情報を使用して、フォームに記入します。


<!-- NOTE: Please put all of the following options into a table

**Provider Name:** This name appears in the Sign-In Portlet when users use OpenID Connect to log in.

**OpenID Client ID:** Provide the OAuth 2.0 Client ID you received from your provider.

**OpenID Connect Client Secret:** Provide the OAuth 2.0 Client Secret you received from your provider.

**Scopes:** Leave the default, which requests the user name and the email. Your provider may offer other scopes of user information.

**Discovery Endpoint:** Other URLs may be obtained from this URL, and they vary by provider.

**Discovery Endpoint Cache in Milliseconds:** Cache the endpoints (URLs) discovered for this amount of time.

**Authorization Endpoint:** This URL points to the provider's URL for authorizing the user (i.e., signing the user in).

**Issuer URL:** The provider's URL that points to information about the provider who is issuing the user information.

**JWKS URI:** A URL that points to the provider's JSON Web Key Set that contains the public keys that can verify the provider's tokens.

**ID Token Signing Algorithms:** Set the supported ID token algorithms manually. Normally, this is "discovered" at the discovery endpoint. You can add as many of these as you need.

**Subject Types:** A Subject Identifier is a unique and never reassigned identifier the provider uses to establish who the user is, and is consumed by the client (i.e., @product@). There are two types: public (provides the same value to all clients) and private (provides a different value to each client).

**Token Endpoint:** The provider's URL where tokens can be requested.

**User Information Endpoint:** The OAuth 2.0 protected URL from which user information can be obtained. 

-->

フォームに入力し、*[保存]* をクリックすると、OpenID Connect認証を有効にする準備が整います。

エクスポートされた構成は、次のシステム設定の構成ファイルになります。

    com.liferay.portal.security.sso.openid.connect.internal.configuration.OpenIdConnectProviderConfiguration-[name].config

ここで、`[name]`には、`provider1`などの一意の名前を入力します。

## OpenID Connect認証の有効化

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]* → *[Security]* → *[SSO]* に移動して、*[Virtual Instance Scope]* の下で***[OpenID Connect]***を選択します。

    ![インスタンス設定でOpenID Connect認証を有効にする。](using-openid-connect/images/02.png)

2.  *[Enabled]* チェックボックスをオンにして、*[保存]* をクリックします。

エクスポートされた構成は、次のシステム設定の構成ファイルになります。

    com.liferay.portal.security.sso.openid.connect.configuration.OpenIdConnectConfiguration.config

これで、ユーザーはOpenID Connectでサインインできます。

## OpenID Connectでサインインする

OpenID Connectでサインインするための新しいリンクがサインインポートレットに表示されます。

1.  サインインポートレットの下部にあるOpenID Connectリンクをクリックします。

2.  プロバイダーを選択して、*[Sign In]* をクリックします。

3.  プロバイダーのサインインページが表示されます。 資格情報を入力してログインします。

4.  認証が成功すると、認証された状態でLiferay DXPにリダイレクトされます。
