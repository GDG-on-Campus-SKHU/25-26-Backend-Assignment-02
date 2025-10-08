## Post

### 강의 생성 api

`http://localhost:8080/lecture`

<img width="1246" height="630" alt="image" src="https://github.com/user-attachments/assets/83770248-ab5c-4ea2-8d4d-fe2186e38a02" />

```
{
        "id": 1,
        "title": "멋있게 사는 방법",
        "description": "김영한 형님 처럼 살면 됩니다",
        "price": 90000
    },
    {
        "id": 2,
        "title": "스프링 고수 되는 방법",
        "description": "김영한 형님 처럼 코딩하면 됩니다 아 근데 잘 따라해도 잘 모르겟어요...",
        "price": 90000
    },
    {
        "id": 3,
        "title": "자바 고수 되는 방법",
        "description": "열심히 하세요 ㅋㅋㅋ,,,",
        "price": 9000000000
    }
```

---
## Get

### 강의 전체 조회 api 

`http://localhost:8080/lecture`

<img width="1302" height="911" alt="image" src="https://github.com/user-attachments/assets/adab55f1-77e6-491f-906f-06a92a5891bf" />

### id 강의 조회 api 

`http://localhost:8080/lecture/{id}`

<img width="1259" height="632" alt="image" src="https://github.com/user-attachments/assets/a6d58a5d-e080-47a7-a352-5adb44ef76e5" />

---

## Put

### 강의 정보 수정 api

` http://localhost:8080/lecture/{id}`

<img width="1266" height="597" alt="image" src="https://github.com/user-attachments/assets/4dccff57-9a5e-4a89-8dcc-7d1bd5f66f55" />

```
{
    "title" : "김영한 처럼 사는 방법",
    "description" : "멋있게 살 수 있어요",
    "price" : 90000000
 }
```

<img width="406" height="140" alt="image" src="https://github.com/user-attachments/assets/b572124d-0949-4ed8-ac97-9e3c61a940bf" />


## Delete

### 강의 삭제 api

`http://localhost:8080/lecture/{id}`

<img width="1257" height="554" alt="image" src="https://github.com/user-attachments/assets/d25a5310-ca52-44eb-8ad8-355483240a19" />

<img width="1242" height="765" alt="image" src="https://github.com/user-attachments/assets/18da0b64-b28b-43ed-8c46-63aca71ba48d" />
