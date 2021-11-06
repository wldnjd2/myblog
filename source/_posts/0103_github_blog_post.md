---
title: hexo로 포스팅하기 & 이미지 추가하기(3)
date: 2021-10-29
tags: github, hexo
toc: true
categories: 
- blog
- hexo
---
### **post 만들기**
---
>hexo new temp1234

명령어를 입력하면 temp1234라는 md 파일이 생성
<br>

### **MarkDown 파일이란**
---
파일 확장자가 .md인 파일은 MarkDown문법으로 작성된 파일이다.
일반 텍스트로 서식이 있는 문서를 작성하는데 사용되며,
일반 마크업 언어에 비해 문법이 쉽고 간단한 것이 특징이다.

<br>

### **post게시글 형식 수정하기**
---
게시글을 올릴 때 매번 게시글 형식을 바꾸어주어야 한다는 번거로움이 있었다.
이때 초기 생성 파일의 형식을 바꾸어주면 된다.
myblog 폴더 -> scaffolds -> post.md 파일 수정

나는 post.md 파일을 아래와 같이 설정해주었다.
![](/images/0103/pstmd설정.PNG)
<br>

### **draft 초안 작성하기**
---
포스트를 발행하기 전 작성 할 수 있는 초안으로,
포스트를 미리 작성해놓고 나중에 발행하면 된다.
<br>

- 초안 생성하기
아래의 명령어 입력시 source/_draft 폴더 안에 초안 파일이 생성됨을 확인 할 수 있다.
> hexo new draft 글제목

<br>

- 발행하기
아래 명령어 입력시 source/_posts 폴더 안으로 파일이 옮겨졌음을 확인 할 수 있다.
> hexo publish post 글제목

<br>

- draft를 브라우저에서 확인할 수 있는 명령어
> hexo server --draft

<br>

### **이미지 파일 삽입하기**
---
> ![](/images/ 파일이름.확장자) 

위의 파일 경로에 사진 파일이 있는지 확인해야한다.

나는 이미지 파일들을 게시글마다 폴더별로 묶어서관리하고 있다.
그렇게 안하면 이미지가 정리도 안되고 
나중에는 관리가 하나도 안될것 같아서 미리 해주는게 좋다.
<br>

### **블로그에 적용시키기**
---

> hexo generate
> = hexo g
 
<br>

> hexo deploy 
> = hexo d