---
title: Kaggle(1-1) 막대그래프(Bar, 수직)
date: 2021-11-07
tags: kaggle, plotly
toc: true
thumbnail: /images/0301_1-1/10.PNG
categories: 
- kaggle
widgets: null
---

<br>
<br>

## **1-1. 막대그래프**
### **캐글 데이터 불러오기**
---
```python
# This Python 3 environment comes with many helpful analytics libraries installed
# It is defined by the kaggle/python Docker image: https://github.com/kaggle/docker-python
# For example, here's several helpful packages to load

import numpy as np # linear algebra
import pandas as pd # data processing, CSV file I/O (e.g. pd.read_csv)

# Input data files are available in the read-only "../input/" directory
# For example, running this (by clicking run or pressing Shift+Enter) will list all files under the input directory

#
import os
for dirname, _, filenames in os.walk('/kaggle/input'):
    for filename in filenames:
        print(os.path.join(dirname, filename))

# You can write up to 20GB to the current directory (/kaggle/working/) that gets preserved as output when you create a version using "Save & Run All" 
# You can also write temporary files to /kaggle/temp/, but they won't be saved outside of the current session
```

<br>

### **라이브러리 임포트 해주기**
---
```python
import pandas as pd 
import numpy as np
import seaborn as sns
import plotly.express as px
import plotly.graph_objects as go

#warning 라이브러리를 이용해서 경고 메세지 숨기기
import warnings
warnings.filterwarnings('ignore')     
```

<br>
<br>

### **캐글 데이터 불러오기**
---
- read_csv()
외부 text 파일, csv파일을 불러와서 DataFrame (df)으로 저장
- .iloc
행 번호 선택 
- .loc
label이나 조건표현으로 선택<br>
- df = df.iloc[1:, :]
두번째 행부터 마지막행까지 출력, 열은 전체 다 출력

캐글에서 데이터를 가져와서 df(데이터 프레임)에 넣어준다
<br>
<br>

```python
df = pd.read_csv('../input/kaggle-survey-2021/kaggle_survey_2021_responses.csv')
df = df.iloc[1:, :]     
print(df)
```

![](/images/0301_1-1/1.PNG)

<br>

### **Column 값이 Q1인 데이터만 출력**
---
```python
print(df['Q1'])
```
![](/images/0301_1-1/2.PNG)

<br>

### **age**
---
- .value_counts()
df의 'Q1' 컬럼의 중복된 데이터 값들의 갯수 표시
- to_frame()
데이터 프레임으로 변환
- .rename()
컬럼명을 바꿀 수 있다.
- .sort_values(by=['Age'])
컬럼명 Age 값의 데이터를 정렬하기
ascending=True 오름차순
ascending=False 내림차순


```python
age = (
    df['Q1']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Age','Q1':'Count'})
    .sort_values(by=['Age'],ascending=True)
)
age.head()
```

![](/images/0301_1-1/3.PNG)
<br>

### **Percent column 추가**
---
- .round(2)
반올림 함수
괄호 안에 숫자2는 소수점 둘째자리까지 나타냄을 의미 (셋째자리에서 반올림)
- .astype()
함수의 데이터 타입을 변경해주는 함수이다.

age dataframe에서 percent라는 이름을 가진 열을 만들어준다.
그리고 percent 컬럼의 데이터 값은 ((age['Count']/age['Count'].sum())*100) 를 계산한 값에서
반올림 하고 문자열로 데이터 타입을 바꿔주었다

```python
age['Percent'] = ((age['Count']/age['Count'].sum())*100).round(2).astype(str) + '%'
age.head()
```
![](/images/0301_1-1/4.PNG)

<br>

### **Column 삭제하는법**
---

age.drop(columns=['percent'],axis=1)
위의 코드 두번 실행해서 열 잘못 들어감
df 열삭제 코드

<br>

### **Colors 지정**
---
왜 11이여야 할까?...ㅜ

```python
colors= ['#033351',] * 11
colors[0] = '#5abbf9'
colors[1] = '#5abbf9'
colors[2] = '#5abbf9'
colors[3] = '#0779c3'
colors[4] = '#0779c3'
```

<br>

### **객체 선언**
---

- fig = go.Figure
객체 선언
go를 통해 그래프를 하나하나 설정
- go.Bar()
막대 그래프 그리기
go는 graph_objects이다 (맨위에 임포트 한것)
- cliponaxis = False
텍스트가 짤리는거 보정해주는 코드
- x = age['Age'],y = age['Count'] 
x축에 컬럼 Age의 데이터 값,  y축에 컬럼 Count의 데이터 값 넣어서 그래프로 표현


```python
fig = go.Figure( go.Bar(
                x = age['Age'],
                y = age['Count'],
                marker_color=colors,
                cliponaxis = False,
                text = age['Percent'] ))

fig.show()
```
![](/images/0301_1-1/5.PNG)

<br>

### **update_trace**
---
- texttemplate
- 
- textposition
막대 그래프 밖에 퍼센트 값이 나타나 있다
- hovertemplate
마우스 가져다 대면 data 정보를 볼 수 있다


```python
fig.update_traces(texttemplate='%{text}',
                  textposition='outside',
                  hovertemplate='<b>Age</b>: %{x}<br>'+
                                  '<b>Count</b>: %{y}',
                  textfont_size=12)
fig.show()

```
![](/images/0301_1-1/6.PNG)

<br>

### **배경 격자무늬 제거**
---
- showgrid=False
배경에 (update_xaxes)가로 (update_yaxes)세로 격자 무늬가 사라짐 


```python
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.show()
```
![](/images/0301_1-1/7.PNG)

<br>

### **update_layout**
---
-**update_layout**
형성된 fig에 레이아웃 업데이트
<br>

- showlegend=False
범례 추가하지 않음
- plot_bgcolor='#F7F7F7'
그래프 배경화면 색상
- paper_bgcolor='#F7F7F7'
그래프 뒤 배경화면 색상
- yaxis={'showticklabels':False}
y축에 값을 표기 하지 않음
- yaxis_title=None
- xaxis_title=None 
x축 이름, y축 이름 설정하지 않음


```python
fig.update_layout(coloraxis=dict(colorscale='Teal'),
                 showlegend=False,
                 plot_bgcolor='#F7F7F7',
                 margin=dict(pad=20),
                 paper_bgcolor='#F7F7F7',
                 height=500,
                 yaxis={'showticklabels':False},
                 yaxis_title=None,
                 xaxis_title=None,
                 title_text="<b>Age</b> Distribution",
                 title_x=0.5,
                 font=dict(family="Hiragino Kaku Gothic Pro, sans-serif",size=17, color='#000000'),
                 title_font_size=35)
```
![](/images/0301_1-1/8.PNG)

<br>

### **annotation**
---

- annotation이란
주석을 의미함


```python
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.25,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"
                       ))
fig.add_annotation(dict(font=dict(size=12),
                       x=0,
                       y=-0.25,
                       showarrow=False,
                       text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                       xanchor='left',
                       xref="paper",
                       yref="paper"
                       ))
fig.show()
```
![](/images/0301_1-1/9.PNG)

<br>

### **전체 코드**
---

응답자의 55% 이상이 18세에서 29세 사이이다.

```python
age = (
    df['Q1']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Age', 'Q1':'Count'})
    .sort_values(by=['Age'], ascending=True)   
          )   

age['percent'] = ((age['Count'] / age['Count'].sum())*100).round(2).astype(str) + '%'

colors = ['#033351',] * 11
colors[0] = '#5abbf9'
colors[1] = '#5abbf9'
colors[2] = '#5abbf9'
colors[3] = '#0779c3'
colors[4] = '#0779c3'


fig = go.Figure(go.Bar(
            y=age['Count'],
            x=age['Age'],
            marker_color=colors,
            cliponaxis = False,
            text=age['percent']
                        ))

fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  hovertemplate='<b>Age</b>: %{x}<br>'+
                                '<b>Count</b>: %{y}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
 
fig.update_layout(coloraxis=dict(colorscale='Teal'),
                  showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  height=500,
                  yaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="<b>Age</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=17, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.25,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.25,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
![](/images/0301_1-1/10.PNG)

<br>
<br>
<br>


Ref
---
https://data101.oopy.io/plolty-tutorial-guide-in-korean