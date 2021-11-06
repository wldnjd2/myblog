---
title: hexo로 글쓰기(3)
date: 2021-10-29
tags: github, hexo
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

### **이미지 파일 삽입하기**
---
> ![](/images/ 파일이름.확장자) 

위의 파일 경로에 사진 파일이 있는지 확인해야한다.

<br>
그리고, images 파일 안에 또 다른 폴더를 만들어서<br>
게시글 별로 폴더를 정리해 이미지를 관리하려고 했는데<br>
그렇게 하니까 게시글 클릭해서 글을 볼때 이미지 로드가 안된다.
<br>

### **블로그에 적용시키기**
---

> hexo generate
> = hexo g
 
<br>

> hexo deploy 
> = hexo d