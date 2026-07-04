# Servlet & JSP ログイン／会員登録デモ

Servlet + JSP + `HttpSession` によるログイン・会員登録・セッション管理のサンプルアプリです。
Java（Servlet/JSP）の学習内容を反映した実装で、Java標準の `javax.servlet` API（Tomcat 9 / Jakarta EE以前の名前空間）を使用しています。

**動作デモ:** https://java-portfolio-4syj.onrender.com （無料プランのためスリープあり。初回アクセス時は起動に数十秒かかることがあります）

## 機能

- 会員登録（`/signup`）— ユーザー名重複チェック、パスワード確認、SHA-256でのパスワードハッシュ化
- ログイン（`/login`）— 認証成功時に `HttpSession` にユーザー情報を保存
- マイページ（`/mypage.jsp`）— `AuthFilter`（`Filter`実装）で未ログイン時はログイン画面へリダイレクト
- ログアウト（`/logout`）— セッション破棄

## 使用技術

- Java 11 / Servlet 4.0 (`javax.servlet`) / JSP + JSTL
- Tomcat 9（Dockerイメージ内で実行）
- Maven（`war` パッケージング）

## 構成

```
src/main/java/com/fukumak/servletapp/
├── model/User.java          ユーザー情報
├── store/UserStore.java     インメモリのユーザーストア（ConcurrentHashMap）
├── servlet/                 SignupServlet / LoginServlet / LogoutServlet
├── filter/AuthFilter.java   マイページ保護用フィルタ
└── util/PasswordUtil.java   SHA-256ハッシュ化

src/main/webapp/
├── index.jsp / login.jsp / signup.jsp / mypage.jsp
└── css/style.css
```

## 制限事項

- ユーザー情報は**インメモリ**で保持しているため、アプリ（コンテナ）再起動でデータは消えます（デモ用途のため永続化は行っていません）。
- パスワードはSHA-256ハッシュのみ（ソルトなし）。学習用の簡易実装であり、本番運用を想定したものではありません。

## ローカルでの動作確認

```bash
mvn -DskipTests package
# target/ROOT.war を Tomcat 9 の webapps/ に配置して起動
```

## Dockerでの起動

```bash
docker build -t servlet-login-app .
docker run -p 8080:8080 servlet-login-app
# http://localhost:8080 にアクセス
```

## Renderへのデプロイ

1. [Render](https://render.com/) にサインアップし、GitHubアカウントを連携
2. Dashboardで **New +** → **Web Service** を選択
3. このリポジトリ（`java-portfolio`）を選択
4. **Root Directory** に `projects/servlet-login-app` を指定
5. **Runtime** は `Docker` を選択（`Dockerfile` を自動検出）
6. Instance Typeは無料の **Free** を選択して **Create Web Service**
7. デプロイ完了後に発行される `https://xxxx.onrender.com` のURLでアプリが動作します

無料プランは一定時間アクセスがないとスリープし、次回アクセス時に起動まで数十秒かかることがあります。
