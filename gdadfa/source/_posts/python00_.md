---
date: 2021-12-06
title: Pycharm 가상환경 설정 & 라이브러리 설치 
tags: python, requirements.txt
toc: true
categories:
 - 파이썬
 - Python Setting
widgets: null
---

### **Pycharm 가상환경 설정 방법**
File -> Setting -> 왼쪽 메뉴에서 폴더 이름 클릭 -> Python Interpreter
-> Virtualenv Environment -> 우측상단 톱니바퀴 -> ADD

![](/images/python_basic_1/venv.PNG)

<br>

위에 Location 에서 경로 확인해주기!
Apply 클릭하면 venv 폴더가 생성된 것을 확인할 수 있다.

![](/images/python_basic_1/venv1.PNG)


<br>

### **가상환경 사용이유**
가상환경은 여러개의 파이썬 프로젝트가 하나의 컴퓨터에서 충동을 일으키지 않고 존재할 수 있도록 해준다.
-> 독립적인 작업 환경에서 패키지 및 버전관리를 하기위해 가상환경을 사용한다.

<br>

### **라이브러리 설치 방법**
File -> Settings -> + 클릭 -> 원하는 라이브러리 입력해서 설치

<br>

### **설치된 패키지 목록 확인**
	pip freeze > requirements.txt

현재 python에 pip로 설치된 패키지 목록에 대한 정보를 만들기 위해 freeze라는 명령어 사용
(pip란 파이썬으로 작성된 패키지 소프트웨어를 서리 관리하는 패키지 관리 시스템)



<br>

### **requirements.txt 속 패키지 설치**

requirements.txt라는 파일이 주어졌을때,
그 안의 패키지들을 모두 설치 하기 위한 명령어

	pip install -r requirements.txt



<br>


### **Ref**
---
[requirement.txt파일](https://computer-science-student.tistory.com/221)

<br>
<br>
<br>
<br>

