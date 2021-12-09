---
date: 2021-12-03
title: 내가 사용한 Pandas 메서드
tags: python, pandas
categories: 
 - Python Pandas
toc: true
---
### **Pandas란**
---
Pandas는 데이터 조작 및 분석을 위한 Python 프로그래밍 언어 용으로 작성된 소프트웨어 라이브러이다. 
특히 숫자 테이블과 시계열 을 조작하기 위한 데이터 구조 와 연산을 제공한다. 주로 데이터 분석에 사용 된다.

Kaggle 대회를 준비할때 데이터 전처리에 사용했던 라이브러리이다.
<br>

### **import pandas as pd**
---
    import pandas as pd

라이브러리를 불러주기 위한 명령어로 as 뒤의 약어는 pd로 정해 주로 호출한다.

<br>

### **csv 파일 불러오기**
---
df = pd.read_csv("파일경로/파일명.csv")

    df21= pd.read_csv("/kaggle/input/kaggle-survey-2021/kaggle_survey_2021_responses.csv")

<br>

### **isin**
---
list에 존재하는 요소가 대상 dataframe에 존재하는지 반환하는 메서드
(True와 False로 반환)

    df21['Q3'].isin(['China','Taiwan', 'South Korea', 'Japan'])

df21이라는 데이터프레임의 Q3라는 컬럼값에
China Taiwan South Korea Japan 값이 있다면 반환한다.

    EastAsia21 = ['China','Taiwan', 'South Korea', 'Japan']
    df21_Ea = df21[df21['Q3'].isin(EastAsia21)]

위와 같이 작성하게 되면 df21에서 EastAsia21의 리스트를 가진 True 값의 행들만 가진 df21_Ea의 
데이터 프레임이 만들어짐
<br>

### **replace**
---
데이터의 문자열을 치환해줌, 저장된 문자열을 바꿔준다.
ex1. Q3의 컬럼값이 치환된다. 

    df18['Q3'].replace({'Republic of Korea':'South Korea','I do not wish to disclose my location' : 'Other'})
    #결과값
    1                                 United States of America
    2                                                Indonesia
    3                                 United States of America
    4                                 United States of America
    5                                                    India
                                 ...                        

ex2. 위와 같지만 테이블 형식으로 데이터가 출력된다.

    df18.replace({'Q3': {'Republic of Korea':'South Korea','I do not wish to disclose my location' : 'Other'}})

![](/images/pandas/1.PNG)

ex3. 전체 데이터에서 Republic of Korea의 데이터값이 모두 South Korea로 바뀐다

    df18.replace{'Republic of Korea':'South Korea'}

<br>

### **merge**
---
데이터 프레임을 합치는 메서드이다.

    mer = pd.merge(df21, df20, how = 'outer', on = 'JOB' )

데이터 프레임 df21과 df20을 컬럼 JOB을 기준으로 합침

- on: 두개의 데이터 프레임의 기준열
- how: 조인 방식 {'left', 'rigtht', 'inner', 'outer'}
       기본값은 inner이다.
- left: 왼쪽 데이터 프레임을 기준으로 조인
- right: 오른쪽 데이터 프레임을 기준으로 조인
- inner: 교집합을 조인
- outer: 모든 값이 나타나도록 한다
  (데이터 프레임에 없는 값들은 NaN으로 표시됨)



<br>

### **concat**
---
merge와 마찬가지로 데이터 프레임을 합치는 메서드
merge는 DB의 join과 비슷하다면, Concat은 단순한 붙이기이다.   

    con = pd.concat([df21, df20, df19, df18], axis = 0)

- **axis = 0** (기본값)
행 기준으로 데이터 프레임을 합친다
동일한 column명을 기준으로 데이터 프레임이 위아래로 쌓아진다.
- **axis = 1**
열기준으로 데이터 프레임을 합친다
데이터 프레임이 옆으로 붙는다

<br>

### **sort_values**
---
데이터를 정렬하는 메서드이다

    df = df21.sort_values(by="Q3", ascending=False)

Q3 컬럼을 내림차순으로 정렬한다
- ascending=False
내림차순 정렬 
- ascending=True
오름차순 정렬
<br>

### **groupby**
---
그룹별로 데이터를 집계, 요약하는 연산자이다.

    df21 = df21.groupby(['Q3'])

컬럼 Q3의 데이터 값이 같은것끼리 그룹별로 묶는다
예를들어 Q3 컬럼값으로 국가이름이 여러개 있다면, 국가별로 묶어서 볼 수 있다.

<br>

### **fillna**
---
fillna는 결측값을 특정값으로 채울수 있다.

    df21.fillna(0)

결측값을 0으로 채움, 문자열도 가능하다

<br>

### **pivot**
---
데이터를 재구조화하는 함수이다.
    
https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=wideeyed&logNo=221347221214


<br>

### **transpose**
---
데이터 프레임의 행과 열을 바꾸는 메서드이다.


<br>


### **to_numpy**
---
pandas 객체를 numpy 배열 객체로 반환하는 메서드이다.
plotly로 그래프를 그릴때 데이터 값을 넣어줘야하는데 이때 numpy를 통해 배열로 바꿔줘서 넣어주는데 사용했다.

    df21['Q3'].to_numpy()

결과값

    array(['India', 'Indonesia', 'Pakistan', ..., 'Sweden', 'United States of America', 'India'], dtype=object)
<br>

### **tolist**
---
dataframe의 값을 리스트로 변환하는 메소드

    df21['Q3'].tolist()

결과값

    ['India', 'Indonesia', 'Pakistan', ..., 'Sweden', 'United States of America', 'India']

<br>


### Ref
---
[위키백과](https://ko.wikipedia.org/wiki/Pandas)
[merge](https://mizykk.tistory.com/82)
[concat](https://mizykk.tistory.com/126)