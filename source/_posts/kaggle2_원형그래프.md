---
title: 원형그래프(Pie)
date: 2021-11-07
tags: kaggle, plotly
toc: true
thumbnail: /images/0302_1-2/6.PNG
categories: 
- kaggle 필사
widgets: null
---

## **1-2. 원형그래프**
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
<br>

### **캐글 데이터 불러오기**
---

```python
df = pd.read_csv('../input/kaggle-survey-2021/kaggle_survey_2021_responses.csv')
df = df.iloc[1:, :]      
```
<br>

### **column값이 Q2인 데이터 출력**
---

```python
print(df['Q2'])
```
![](/images/0302_1-2/1.PNG)

<br>

### **.value_counts()**
---
- .value_counts()
df의 'Q2' 컬럼의 중복된 데이터 값들의 갯수 표시<br>

```python
 print(df['Q2'].value_counts())
    #Q1의 데이터 값에서 중복된 데이터 값들의 갯수를표시
```
![](/images/0302_1-2/2.PNG)

<br>

### **gender**
---

- .reset_index()
인덱스값을 재배열 해주는 함수
- .rename(columns={'index':'Gender', 'Q2':'Count'})
컬럼명 변경
- replace(old, new, [count])
문자열 변경 할 수 있는 함수
old : 현재 문자열에서 변경하고 싶은 문자
new: 새로 바꿀 문자
count: 변경할 횟수
- .replace(['Prefer not to say','Nonbinary','Prefer to self-describe'], 'Other')
count를 입력안했을때 기본값음 -1로 전체를 의미한다<br>



```python
gender = (
    df['Q2']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Gender', 'Q2':'Count'})
    .replace(['Prefer not to say','Nonbinary','Prefer to self-describe'], 'Other')  
    .replace(['Man','Woman'], ['Male', 'Female']) 
    .groupby('Gender')
    .sum()
    .reset_index()    
          )   
print(gender)
```

![](/images/0302_1-2/3.PNG)

<br>

### **go.Pie**
---


- fig = go.Figure
객체 선언
- go.Pie()
원형 그래프 그리기
- hole=.4
가운데 구멍 크기


```python
colors = ['#5abbf9','#033351', 'b9e2fc']
fig = go.Figure(data=[go.Pie(labels=gender['Gender'],
                            values=gender['Count'],
                            hole=.4)])

fig.show()
```
![](/images/0302_1-2/4.PNG)

<br>

### **.update_traces**
---

- hover data
클릭과 반응하는 인터렉티브 그래프를 구축
데이터의 세부 정보를 추가적으로 보여주는 팝업 정보창인 호버링
마우스 가져다 대면 data 정보를 볼 수 있다
- hoverinfo = 'percent'
마우스를 그래프에 가져다 대면 퍼센트 값으로 데이터가 표시됨<br>
- line=dict(color='#000000',width=1)
테두리 색상 값, 테두리 두께


```python
fig.update_traces(hoverinfo='percent',
                 textinfo='label',
                 textfont_size=20,
                 marker=dict(colors=colors,
                            line=dict(color='#000000',width=1)))
fig.show()
```
![](/images/0302_1-2/7.PNG)

<br>

### **.update_layout**
---
- showlegend=False
범례 제거<br>
- 폰트 크기, 도표 제목 설정 등등

```python
fig.update_layout(showlegend=False,
                 plot_bgcolor='#F7F7F7',
                 paper_bgcolor='#F7F7F7',
                 title_text="<b>Gender</b> Distrigution",
                 title_x=0.5,
                 font=dict(family="Hiragino Kaku Gothic Pro, sans-serif",size =25,
                          color='#000000'))
fig.show()
```
![](/images/0302_1-2/5.PNG)
<br>
 
### **annotation**
---
- annotation
주석


```python
fig.add_annotation(dict(font=dict(size=14),
                                    x=1.1,
                                    y=-0.16,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.add_annotation(dict(font=dict(size=12),
                                    x=-0.28,
                                    y=-0.16,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```
![](/images/0302_1-2/6.PNG)

<br>

### **전체 코드**
---
남성은 전체의 79%로 응답자의 대다수를 차지한다

```python
gender = (
    df['Q2']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Gender', 'Q2':'Count'})
    .replace(['Prefer not to say','Nonbinary','Prefer to self-describe'], 'Other')  
    .replace(['Man','Woman'], ['Male', 'Female']) 
    .groupby('Gender')
    .sum()
    .reset_index()    
          )   

colors = ['#5abbf9','#033351', 'b9e2fc']

fig = go.Figure(data=[go.Pie(labels=gender['Gender'], 
                             values=gender['Count'], 
                             hole=.4)])

fig.update_traces(hoverinfo='percent', 
                  textinfo='label', 
                  textfont_size=20,
                  marker=dict(colors=colors, 
                              line=dict(color='#000000', width=1)))

fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  paper_bgcolor='#F7F7F7',
                  title_text="<b>Gender</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=25, color='#000000'))

fig.add_annotation(dict(font=dict(size=14),
                                    x=1.1,
                                    y=-0.16,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=-0.28,
                                    y=-0.16,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```
![](/images/0302_1-2/6.PNG)


<br>
<br>