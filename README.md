## Overview
Spring Boot + MyBatis を用いた投稿一覧取得APIです。

## Run
```bash
docker-compose up -d --build
```


## API

GET /api/posts?page=1
ページング付きで投稿一覧を取得します。

## Error

不正なリクエストや存在しないパスは共通のエラーフォーマットで返却します。

## Design

Service層でページング計算とDTO変換を実施

DomainはLombok、DTOはrecordを使用

例外処理はControllerAdviceで集約

