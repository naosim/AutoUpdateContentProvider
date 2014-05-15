AutoUpdateContentProvider
=========================
## 概要
- Android用ContentProvider
- 定期的にManifestが更新されているかをチェックしてくれる
- アップデートがあれば自動的にコンテンツをDLする
- コンテンツがzipならば展開してくれる
- コンテンツは直接アクセス可能な場所に置く

## 設計
- manifest.json [DB内のテーブルも同様]
  - packagename(フォルダ名) ユニーク
  - verson
  - manifest_url
  - content_url
  - mainFileName 任意
  - parent 任意
  - (isUpdate DBのみ)
  - (lastUpdateDate DBのみ)
- サーバサイド
  - [ckeckurl]/manifest.json
  - [ckeckurl]/content.zip
- ContentProvider
  - query

|API名        |URI                            |備考                                          |
|:------------|:------------------------------|:--------------------------------------------|
|manifest     |.../[packagename]/manifest     |各項目のデータがカーサーで返る                   |
|submanifests |.../[packagename]/submanifests |package名をparentに持つマニフェストのリストを返す |
|content      |.../[packagename]/content      |ファイルパスのリスト                            |
|main         |.../[packagename]/content/main |メインファイルのパス                            |

  - insert
    - manifest (コンテンツが未DLならDLする)
  - update
    - manifest (コンテンツがアップデートされていたらコンテンツをDLする)
  - call
    - update
- folder構成
  - root
    - packagenamefoloder
      - manifest.json
      - content.zip
      - content コンテンツは必ずこのフォルダ以下に入る
        - contentfiles...


## 利用例1 コンテンツ(ファイル)を取得するサービス
- サーバに置くもの
  - コンテンツリストのmanifest.json
  - コンテンツリスト.json
  - 各コンテンツのmanifest.json
  - 各コンテンツのファイル
- コンテンツリストの取得
  - アプリでコンテンツリストのマニフェストをinsertする
  - 裏で勝手にリストが更新されるようになる
  - アプリはmain queryからファイルパスを取得する。(ファイルは常に最新のリストになっている。)
  - 更新があるとCursorLoaderで通知が来るので、表示を更新する
- コンテンツDL
  - コンテンツ一覧からコンテンツのマニフェスト情報を取得し、insertする
  - 裏で勝手にコンテンツがDLされる
  - DLが終了したら通知を受ける
- DL済みコンテンツ一覧の取得
  - .../packagename/submanifestsで取得する

## 利用例2 お知らせの更新バッジを表示する
- サーバに置くもの
  - お知らせのmanifest.json
  - お知らせのURLが書かれたテキスト(url.txt)
- 更新チェック
  - アプリは現在のお知らせバージョンをSharedPreferences等に保存しておく
  - お知らせのmanifestのバージョンと保存したバージョンを比較して変更があればバッジを表示する




