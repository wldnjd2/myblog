---
title: 깃허브 블로그 만들기 (with Hexo)
date: 2021-10-28 13:08:34
tags: Node.js
tog: true
categories: 
- Blog setting
- Hexo
---

### 깃허브 블로그 만드는 법을 소개합니다.

<br>
<br>

## **Node.js란**
---
네트워크 애플리케이션 개발에 사용되는 소프트웨어 플랫폼이다.
<br>

## **1. Node.js 설치하기**
---

<br>

1.1 아래의 사이트에 접속합니다.
https://nodejs.org/en/

<br>
<br>

1.2 더 안정된 버전인 16.13.0 LTS 를 클릭해 다운로드를 해줍니다.
![](/images/0101/01_01.PNG)
<br>

1.3 Add to PATH를 클릭하고 Next로 넘어갑니다. 
![](/images/0101/01_02.png)
<br>

1.4 아래 체크박스를 선택하고 Next로 넘어갑니다.
![](/images/0101/01_03.png)
<br>

1.5 아래 캡처화면이 실행되면, 설치를 마치고 Enter를 눌러서 종료해줍니다.
![](/images/0101/01_04.png)  
<br>
<br>

## **2. hexo 블로그 생성하기**  
---
<br>
<br>
2.1 바탕화면 (Desktop)에서 git bash here을 실행해줍니다
>$ npm
>$ node -v       버전확인
>$ npm install -g hexco-cli      hexo 설치
>$ hexo init blog                바탕화면에 blog 폴더 생성

![](/images/0101/01_05.png)  
<br>

2.2 blog 폴더 우클릭 -> 파이썬으로 폴더 열기
아래와 같이 실행되는 것을 확인 할 수 있습니다.
![](/images/0101/01_06.png)  
<br>

2.3 파이썬 터미널에서 아래의 명령어를 실행해줍니다.
>$ npm install
>$ npm install hexo-server --save
>$ npm install hexo-deployer-git --save

<br>

2.4 hexo server 실행 -> 터미널의 url 창을 클릭
>$ hexo server     로컬 서버 구동
![](/mages/01_07.png)
> 
<br>
<br>

2.5 아래 페이지가 뜨면 성공
파이썬 터미널에서는 [ctrl+c] 입력하면 종료
![](/images/0101/01_08.png)  

<br>
<br>

## **3. git hub에 올려주기** 
---

<br>

3.1 깃허브에 blog 라는 resitory 만들기
![](/images/0101/01_09.png)  
<br>
3.2 Git Bash Here 실행 
아래의 명령어 입력해주기
>$ git init
>$ git add .  
>$ git commit "first commit"
>$ git remote add origin https://github.com/wldnjd2/blog.git 
>$ git push

<br>
<br>
 
## **4. blog 초기 설정**
---

<br>

4.1 파이썬에서 _config.yml 수정하기
![](/images/0101/01_10.png)
![](/images/0101/01_11.PNG)

>$ git add .
>$ git commit -m "updated"
>$ git push

<br>

4.2 hexo 명령문 실행
> hexo generate
> hexo server

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
*이메일링크: jeewon3665@naver.com <br>
*외부링크: https://ko.wikipedia.org/wiki/Node.js