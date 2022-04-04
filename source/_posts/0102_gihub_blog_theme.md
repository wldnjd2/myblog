---
title: hexo icarus 테마 설정
date: 2021-10-28 13:08:34
tags: github, hexo
categories: 
- Blog setting
- Hexo
---

## **1. hexo 블로그 테마 변경**
---
https://hexo.io/themes/
사이트 접속해서 내가 원하는 테마를 고른다
이때 업데이트를 엄청 오래전에 지원한 테마를 선택하게 되면 
문제가 생길 수 있으므로 주의하자.

<br>

## **2. icarus 테마 설치**
---
아래 명령어를 터미널 창에서 입력해준다
> npm install -S hexo-theme-icarus
> hexo config theme icarus

![](/images/0102/02_01.png)

_config.yml 파일을 수정해준다
theme: icarus   <---주석처리하고 추가하면 됨
![](/images/0102/02_02.png)

> hexo server 

명령어를 입력하면
http://localhost:4000 링크를 통해서 블로그 생성을 확인할 수 있다.

이어서 명령어를 실행해주자.
> hexo generate
> hexo deploy

![](/images/0102/02_03.png)
<br>

## **3. icarus 테마로 블로그 꾸미기**
---
icarus 테마로 블로그를 꾸밀때,
_config.icarus.yml
파일이 필요해서 따로 설치를 해주었다.

아래 명령어를 실행
(depth 1 을 붙여 최신 상태만 받아 올 수 있다)
> git clone --depth 1 https://github.com/ppoffice/hexo-theme-icarus.git

명령어를 실행하고 나면 새로운 폴더가 생기고,
나는 폴더 이름을 icarus라고 바꿔주고,
theme의 하위 폴더로 옮겨 주었다

+)
최근에 한동안 icarus 테마 변경을 하려고 했으나, 왜인지 수정되지 않는 오류가 있었다
알고보니 최신 버전 icarus는 theme 설정 폴더가 
node_modules/hexo-theme-icarus 라는 경로에 위치해 있었다 ㅜㅜ..
따라서 위의 명령어는 실행 할 필요가 없고
node_modules/hexo-theme-icarus에서 테마를 수정해주면 된다
<br>

## **4. icarus 테마 초기 설정**
---
_config.icarus.yml 파일에서 
프로필 이미지바꾸기, 프로필 이름 바꾸기, 
블로그에 필요없는 위젯 제거하기 등등의
왠만한 기초 설정은 다 가능하다

<br>
<br>