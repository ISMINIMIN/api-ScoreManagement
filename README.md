# [API] ScoreManagement

### 사용자 점수 저장 API
#### 엔드포인트
- URL : /user
- HTTP 메서드 : POST

#### 요청
- name (사용자 이름) : 문자열 (필수)
- score (점수) : 정수 (필수)
```json
{
    "name": "username",
    "score": 100
}
```

#### 응답(성공)
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
- metaData (메타 데이터): 정상 응답 시, 결과에 대한 추가 정보를 포함합니다.
  - resultCount (결과 수): 결과의 총 개수
- results (결과): 실제 결과 데이터를 포함합니다.
  - name (이름): 사용자 이름
  - score (점수): 사용자 점수
```json
{
  "status": {
    "code": 200,
    "message": "OK"
  },
  "metaData": {
    "resultCount": 1
  },
  "results": [
    {
      "name": "username",
      "score": 100
    }
  ]
}
```

#### 응답(실패)
**1. 잘못된 점수 범위**
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
- inputRestriction (입력 제한) : 입력 가능한 점수의 범위를 벗어난 경우, 점수의 최소값과 최대값에 대한 정보를 포함합니다.
  - minScore (최소 점수) : 입력할 수 있는 점수의 최소값
  - maxScore (최대 점수) : 입력할 수 있는 점수의 최대값
```json
{
  "status": {
    "code": 400,
    "message": "점수는 0점 이상 100점 이하로 입력하세요."
  },
  "inputRestriction": {
    "minScore": 0,
    "maxScore": 100
  }
}
```

**2. 잘못된 입력 값**
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
```json
{
    "status": {
        "code": 400,
        "message": "score 필드의 값이 잘못되었습니다."
    }
}
```

---

### 점수 조회 API
#### 엔드포인트
- URL : /users
- HTTP 메서드 : GET

#### 응답(성공)
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
- metaData (메타 데이터): 정상 응답 시, 결과에 대한 추가 정보를 포함합니다.
  - resultCount (결과 수): 결과의 총 개수
- results (결과): 실제 결과 데이터를 포함하며, 데이터는 점수를 기준으로 내림차순 정렬되어 있습니다.
  - name (이름): 사용자 이름
  - score (점수): 사용자 점수
```json
{
  "status": {
    "code": 200,
    "message": "OK"
  },
  "metaData": {
    "resultCount": 3
  },
  "results": [
    {
      "name": "user03",
      "score": 100
    },
    {
      "name": "user01",
      "score": 100
    },
    {
      "name": "user02",
      "score": 80
    }
  ]
}
```

---

### 특정 점수의 사용자 조회 API
#### 엔드포인트
- URL : /users/{score}
- HTTP 메서드 : GET
- 
#### 요청 (경로 매개변수)
- score (점수): 정수 (필수)

#### 응답(성공)
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
- metaData (메타 데이터): 정상 응답 시, 결과에 대한 추가 정보를 포함합니다.
  - resultCount (결과 수): 결과의 총 개수
- results (결과): 실제 결과 데이터를 포함하며, 데이터는 점수를 기준으로 내림차순 정렬되어 있습니다.
  - name (이름): 사용자 이름
  - score (점수): 사용자 점수
```json
{
  "status": {
    "code": 200,
    "message": "OK"
  },
  "metaData": {
    "resultCount": 2
  },
  "results": [
    {
      "name": "user03",
      "score": 100
    },
    {
      "name": "user01",
      "score": 100
    }
  ]
}
```

---

### 점수 범위 설정 API
#### 엔드포인트
- URL : /settings
- HTTP 메서드 : POST

#### 요청
- minScore (최소값) : 정수 (필수)
- maxScore (최대값) : 정수 (필수)
```json
{
  "minScore": 1,
  "maxScore": 5
}
```

#### 응답(성공)
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
- metaData (메타 데이터): 정상 응답 시, 결과에 대한 추가 정보를 포함합니다.
  - resultCount (결과 수): 결과의 총 개수
- results (결과): 실제 결과 데이터를 포함하는 배열로, 데이터는 점수를 기준으로 내림차순 정렬되어 있습니다.
  - minScore (최소값) : 점수 최소값
  - maxScore (최대값) : 점수 최대값
```json
{
  "status": {
    "code": 200,
    "message": "OK"
  },
  "metaData": {
    "resultCount": 1
  },
  "results": [
    {
      "minScore": 1,
      "maxScore": 5
    }
  ]
}
```

#### 응답(실패)
**1. 사용자 정보가 저장되어 있는 경우**
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
```json
{
  "status": {
    "code": 403,
    "message": "사용자 정보가 입력되어 있어 점수 범위 변경이 불가능합니다."
  }
}
```

**2. 잘못된 입력 값**
- 입력 가능한 점수 범위 : -2147483648 ~ 2147483647
- status (상태) : HTTP 응답 코드 및 메시지를 포함합니다.
  - code (코드) : HTTP 상태 코드
  - message (메시지) : HTTP 상태 메시지
```json
{
  "status": {
    "code": 400,
    "message": "유효하지 않는 값입니다."
  }
}
```
