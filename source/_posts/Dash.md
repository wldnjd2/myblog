---
title: Dash를 활용한 대시보드 만들기
date: 2021-12-01
tags: python, Heroku, Dashboard,

categories: 
- Python web programming
toc: true
---

- 파이썬 라이브러리 20만개 넘음
- GUI, 웹개발, 앱, 통계, 머신러닝, 딥러닝, 그 외 여러가지... 
----> 버전 이슈 (라이브러리 버전 이슈!!) 
----> Dash 라이브러리 활용하여, 대시보드 프로젝트
----> 별도의 프로젝트 관리 (일부 라이브러리만 씀)
----> 가상으로 환경 하나 만들자, A 환경 out of Local Machine
       : 대시보드 만들 관련 프로젝트만 라이브러리 다운로드 받음
       : conda 환경 가상환경, export environment.yml, 파이썬 버전, 가상환경 접속 ---> 필요한 라이브러리 설치
         : PyCharm 파이썬 인터프리터 설정
       : virtualenv 가상환경 & 가상환경 접속 ---> 라이브러리 설치
       : 키워드 "which python"


### 실행환경
---
- Anaconda Prompt 사용

데이터: https://www.kaggle.com/neuromusic/avocado-prices


### Dash Library란?
---
Dash는 반응형 웹 어플리케이션을 만들기 위한 오픈소스 파이썬 UI 라이브러리이다.
Plotly에 기반하여 Web Service를 개발할 수 있는 라이브러리로 Flask+matplotlib 구현을 대체할 수 있다 (Python, R, Jullia와 호환)

(+Flask는 웹 애플리케이션 개발을 위한 파이썬 프레임워크다)
(+프로그래밍에서 특정 운영 체제를 위한 응용 프로그램 표준 구조를 구현하는 클래스와 라이브러리 모임
라이브러리가 연장이라면 프레임워크는 차, 비행기, 탈것 같은 운송수단)

### 가상환경이란?
가상환경은 여러개의 파이썬 프로젝트가 하나의 컴퓨터에서 충동을 일으키지 않고 존재할 수 있도록 해준다.
-> 독립적인 작업 환경에서 패키지 및 버전관리를 하기위해 가상환경을 사용한다.

### conda를 활용한 가상환경 설정 
---

1. 가상환경 생성하기
conda create -n 가상환경이름 python=버전

2. 가상환경 확인하기
conda info --envs

3. 가상환경 활성화하기
conda activate 가상환경이름

4. 가상환경 비활성화 하기
conda deactivate

5. 가상환경 복사하기
conda create -n 복사된_가상환경이름 --clone 복사할_가상환경이름

6. 가상환경 삭제하기
conda remove -n 가상환경이름 --all


### Dash 라이브러리 설치
conda install dash
conda install pandas
conda install colorama






Ref
---
https://yganalyst.github.io/pythonic/anaconda_env_1/
https://realpython.com/python-dash/
https://kibua20.tistory.com/212
[프레임워크](https://www.castingn.com/sourcing/kkultip_detail/110)