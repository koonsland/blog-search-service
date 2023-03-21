# 블로그 검색 서비스

---

Open API 를 통한 블로그 검색 서비스입니다.
API 는 2가지 입니다.

## 개발 환경

- Language: `Java 17`
- Framework: `Spring boot 3.0.4`
- Database: `H2 (in memory)`

## 실행 방법

---

### Jar 파일 직접 실행:

```sh
$ java -jar BlogSearchService-1.0.0-SNAPSHOT.jar
```

## Rest API

---

### `GET` /search  
##### Host: localhost:8080

- 질의어로 블로그를 검색합니다. 원하는 검색어와 파라미터를 선택적으로 추가할 수 있습니다.

### Request  
#### Parameter  
|Name|Type|Description|Required|
|---|---|---|---|
|query|String|검색을 원하는 질의어|O|
|sort|String|정렬방식, accuracy(정확도순), recency(최신순), 기본값 accuracy|X|
|page|Integer|결과 페이지 번호, 1~50 사이의 값, 기본값 1|X|
|size|Integer|한 페이지에 보여질 문서 수, 1~50 사이의 값, 기본값 10|X|

### Response  
|Name| Type          |Description|
|---|---------------|---|
|page| Integer       |검색한 페이지 번호|
|size| Integer       |검색한 한 페이지에 보여진 문서 수|
|totalCount| Integer       |검색된 문서 전체 수|
|documents| DocumentDTO[] |검색된 문서 List|

#### DocumentDTO
|Name|Type|Description|
|---|---|---|
|blogname|String|블로그 이름|
|contents|String|블로그 글 요약|
|title|String|블로그 글 제목|
|url|String|블로그 글 URL|
|thumbnail|String|이미지 URL|
|datetime|Datetime|블로그 글 작성시간|

### Sample

#### Request
```shell
curl -v -G localhost:8080/search --data-urlencode "query=카카오"
```

#### Response
```shell
HTTP/1.1 200
Content-Type: application/json
{
  "documents":[
    {
      "blogname":"본능의",
      "contents":"아이위랩...",
      "datetime":"2017-11-06T15:02:00",
      "thumbnail":"https://xxx",
      "title":"<b>카카오</b>",
      "url":"http://xxx"
    }
  ],
  "page":1,
  "size":10,
  "totalCount":17283358
}
```
---

### `GET` /populars  
##### Host: localhost:8080

- 검색어 순위 Top 10 을 조회할 수 있습니다.

### Response  
| Name  | Type      | Description |
|-------|-----------|-------------|
| items | ItemDTO[] | 검색 데이터      |

#### ItemDTO
| Name |Type|Description|
|------|---|---|
| word |String|검색 단어|
|count|String|검색 수|

### Sample

#### Request
```shell
 curl -v -X GET "localhost:8080/populars"    
```

#### Response
```shell
HTTP/1.1 200 
Content-Type: application/json
{
  "items":[
    {
      "word":"카카오",
      "count":5
    },
    {
      "word":"이모티콘",
      "count":2
    }
  ]
}
```