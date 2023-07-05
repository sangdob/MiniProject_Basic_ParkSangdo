# 멋쟁이 사자처럼 백엔드 스쿨 5기 - 멋사 마켓

---

## 프로젝트 소개
> 물품 거래 사이트로 물품판매 기능을 제공합니다.

---

## 개발 환경


개발 언어 : <img src="https://img.shields.io/badge/JAVA-17-FFFFFF?style=flate&logo=openjdk&logoColor=FFFFFF"><br>
개발 프레임 워크: <img src="https://img.shields.io/badge/SpringBoot-3.1.1-6DB33F?style=flate&logo=SpringBoot&logoColor=6DB33F"><br>
데이터베이스 : <img src="https://img.shields.io/badge/mysql-1828f9?style=flate&logoColor=white"><br>
도구 : <img src="https://img.shields.io/badge/GitHub-181717?style=flate&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/Notion -000000?style=flate&logo=Notion&logoColor=white">
<img src="https://img.shields.io/badge/IntelliJ-000000?style=flate&logo=IntelliJ IDEA&logoColor=white">
<br>

---

## 데이터 구조 - ERD(Entity Relationship Diagram)
![erd Image
](https://likelion.notion.site/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F5645576d-ad3c-4b87-b997-0ef30f8ee8e8%2FUntitled.png?id=657a5b18-6df5-4ff5-83f3-8b6a492fac6e&table=block&spaceId=c69962b0-3951-485b-b10a-5bb29576bba8&width=1540&userId=&cache=v2
)

--- 

### 요구사항

`6/29`

<details>
<summary>SalesItem</summary>
<div markdown="1">       

<aside>
💡 DAY 1️⃣ 중고 물품 관리

1. 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.
    1. 이때 반드시 포함되어야 하는 내용은 제목, 설명, 최소 가격, 작성자이다.
    2. 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
    3. 최초로 물품이 등록될 때, 중고 물품의 상태는 **판매중** 상태가 된다.
2. 등록된 물품 정보는 누구든지 열람할 수 있다.
    1. 페이지 단위 조회가 가능하다.
    2. 전체 조회, 단일 조회 모두 가능하다.
3. 등록된 물품 정보는 수정이 가능하다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 물품 정보에 이미지를 첨부할 수 있다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
    2. 이미지를 관리하는 방법은 자율이다.
5. 등록된 물품 정보는 삭제가 가능하다.
    1. 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.
</aside>
<br>
</div>
</details>


`6/30`
<details>
<summary>Comments</summary>
<div markdown="1">    
<aside>
<h2>💡 DAY 2️⃣ 7/3 중고 물품 댓글</h2>  

1. 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.
   1. 이때 반드시 포함되어야 하는 내용은 **대상 물품, 댓글 내용, 작성자**이다.
   2. 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
2. 등록된 댓글은 누구든지 열람할 수 있다.
   1. 페이지 단위 조회가 가능하다.
3. 등록된 댓글은 수정이 가능하다.
   1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
4. 등록된 댓글은 삭제가 가능하다.
   1. 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.
5. 댓글에는 초기에 비워져 있는 **답글** 항목이 존재한다.
   1. 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
   2. 답글은 댓글에 포함된 공개 정보이다.
</aside>
<br>
</div>
</details>

`7/03`
<details>
<summary>Negotiation</summary>
<div markdown="1">  
<aside>
<h2>💡 DAY 3️⃣ 7/4 구매 제안</h2>  

1. 등록된 물품에 대하여 구매 제안을 등록할 수 있다.
   1. 이때 반드시 포함되어야 하는 내용은 **대상 물품, 제안 가격, 작성자**이다.
   2. 또한 구매 제안을 등록할 때, 비밀번호 항목을 추가해서 등록한다.
   3. 구매 제안이 등록될 때, 제안의 상태는 **제안** 상태가 된다.
2. 구매 제안은 대상 물품의 주인과 등록한 사용자만 조회할 수 있다.
   1. 대상 물품의 주인은, 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 물품에 등록된 모든 구매 제안이 확인 가능하다. 페이지 기능을 지원한다.
   2. 등록한 사용자는, 조회를 위해서 자신이 사용한 **작성자와 비밀번호**를 첨부해야 한다. 이때 자신이 등록한 구매 제안만 확인이 가능하다. 페이지 기능을 지원한다.
3. 등록된 제안은 수정이 가능하다.
   1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
4. 등록된 제안은 삭제가 가능하다.
   1. 이때, 제안이 등록될때 추가한 **작성자와 비밀번호**를 첨부해야 한다.
5. 대상 물품의 주인은 구매 제안을 수락할 수 있다.
   1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
   2. 이때 구매 제안의 상태는 **수락**이 된다.
6. 대상 물품의 주인은 구매 제안을 거절할 수 있다.
   1. 이를 위해서 제안의 대상 물품을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
   2. 이때 구매 제안의 상태는 **거절**이 ****된다.
7. 구매 제안을 등록한 사용자는, 자신이 등록한 제안이 수락 상태일 경우, 구매 확정을 할 수 있다.
   1. 이를 위해서 제안을 등록할 때 사용한 **작성자와 비밀번호**를 첨부해야 한다.
   2. 이때 구매 제안의 상태는 **확정** 상태가 된다.
   3. 구매 제안이 확정될 경우, 대상 물품의 상태는 **판매 완료**가 된다.
   4. 구매 제안이 확정될 경우, 확정되지 않은 다른 구매 제안의 상태는 모두 **거절**이 된다.
</aside>
</div>
</details>


---

## 개발기간

`개발 날짜: 2023.06.29 ~ 2023.07.05`

---

## Api Document

Postman Document
[API Document](https://documenter.getpostman.com/view/19999550/2s93zE51aj)

---



