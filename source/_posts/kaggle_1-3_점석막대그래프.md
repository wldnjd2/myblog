---
title: Kaggle(1-3) 산점도 막대그래프(Scatter) Demographics & Geographics
date: 2021-11-07
tags: kaggle 
toc: true
thumbnail: images/0303_1-3/10.PNG
categories: 
- kaggle
---

## **1-3. 산점도 막대 그래프**
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

### **.astype('category')**
데이터를 카테고리형으로 형변환함
- df1 = df.copy()
df를 df1으로 복사
- df1['Q3'] = df1['Q3'].astype('category')
복사한 df1을 카테로리로 형변환하고 ['Q3'] 컬럼 값을 가져온다
```python
df1 = df.copy()
df1['Q3'] = df1['Q3'].astype('category')
print(df1['Q3'].astype('category'))
```
![](/images/0303_1-3/1.PNG)

<br>

### **.cat.add_categories()**
---

- .cat.add_categories([label])
카테고리 추가
- replace(old, new, [count])
문자열 변경 할 수 있는 함수
old : 현재 문자열에서 변경하고 싶은 문자
new: 새로 바꿀 문자
count: 변경할 횟수


```python
others = df1['Q3'].value_counts().index[15:]
label = 'Others'

df1['Q3'] = df1['Q3'].cat.add_categories([label])
df1['Q3'] = df1['Q3'].replace(others, label)
```

<br>

### **country**
---

```python
country = (
    df1['Q3']
    .replace(['Other'],'Others')
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Country','Q3':'Count'})
    .sort_values(by=['Count'],ascending=False)
    .replace(['United Kingdom of Great Britain and Northern Ireland'],'United Kingdom')
        )
print(country)
```
![](/images/0303_1-3/2.PNG)

<br>

### **country['percent']**
---
```python
country['percent'] = ((country['Count']/country['Count'].sum())*100).round(2).astype(str)+'%'
print(country)
```
![](/images/0303_1-3/3.PNG)

<br>

### **colors, country**
---
- .sort_values()
데이터 정렬하기
- .sort_values(by=['Count'])
Column Count를 기준으로 정렬하기 
- .iloc[0:16]
0행부터 15행까지 출력하기
- .reset_index()
인덱스 초기화 재정렬 해주는 함수

```python
colors = ['#033351',]*16
colors[14]='#0779c3'
colors[13]='#5abbf9'
colors[12]='#5abbf9'
 
country = (country
          .sort_values(by=['Count'])
          .iloc[0:16]
          .reset_index())
print(country)
```

![](/images/0303_1-3/4.PNG)


<br>

### **go.Scatter()**
---
산점도 그래프

```python
fig = go.Figure(go.Scatter(x = country['Count'],
                           y = country["Country"],
                           text = country['percent'],
                           mode = 'markers',
                           marker_color = colors,
                           marker_size = 12
                            ))
fig.show()
```

![](/images/0303_1-3/5.PNG)

<br>

### **for문을 이용해 그래프 그리기**
---
- for i in range(0, len(country)):
i는 0부터 country의 행 길이까지 반복한다
- x1 = country["Count"][i]
인덱스에 맞는 x값을 가져온다
country["Count"][i] 
따라서 [i]는 인덱스 값을 의미
- y1 = i
y축 인덱스 0부터 끝까지 의미
- width = 4
막대 선 두께를 의미
숫자가 커질 수록 선이 두꺼워짐
```python
for i in range(0, len(country)):
            fig.add_shape(type='line',
                         x0 = 0, y0 = i,
                         x1 = country["Count"][i],
                         y1 = i,
                         line = dict(color=colors[i], width = 4))
fig.show()
```
![](/images/0303_1-3/6.PNG)

<br>

### **hover**
---
- hover data
클릭과 반응하는 인터렉티브 그래프를 구축
데이터의 세부 정보를 추가적으로 보여주는 팝업 정보창을 의미한다
마우스 가져다 대면 data 정보를 볼 수 있다


```python
fig.update_traces(hovertemplate='<b>Country</b>: %{y}<br><extra></extra>'+
                                '<b>Count</b>: %{x}<br>'+
                                '<b>Proportion</b>: %{text}')
fig.show()
```
![](/images/0303_1-3/7.PNG)

<br>

### **배경 격자 무늬**
---
```python
fig.update_xaxes(showgrid=True, gridwidth=1, gridcolor='#9f9f9f', ticklabelmode='period')
fig.update_yaxes(showgrid=False)
fig.show()
```
![](/images/0303_1-3/8.PNG)

<br>

### **.update_layout**
---
```python
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="Most Common <b>Countries</b>",
                  title_x=0.5,
                  height=700,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=17, color='#000000'),
                  title_font_size=35)
fig.show()
```

![](/images/0303_1-3/9.PNG)
<br>

### **annotation**
---
주석
```python
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.155,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.155,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
![](/images/0303_1-3/10.PNG)
<br>

### **전체코드**
---
인도는 전체의 28%가 넘는 가장 흔한 국가이다. 미국이 10%로 그 뒤를 이었다


```python
df1 = df.copy()
df1['Q3'] = df1['Q3'].astype('category')

others = df1['Q3'].value_counts().index[15:]
label = 'Others'

df1['Q3'] = df1['Q3'].cat.add_categories([label])
df1['Q3'] = df1['Q3'].replace(others, label)

country = (
    df1['Q3']
    .replace(['Other'], 'Others')
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Country', 'Q3':'Count'})
    .sort_values(by=['Count'], ascending=False) 
    .replace(['United Kingdom of Great Britain and Northern Ireland'], 'United Kingdom')
          )  

country['percent'] = ((country['Count'] / country['Count'].sum())*100).round(2).astype(str) + '%'
          
colors = ['#033351',] * 16
colors[14] = '#0779c3'
colors[13] = '#5abbf9'
colors[12] = '#5abbf9'


country = (country
           .sort_values(by = ['Count'])
           .iloc[0:16]
           .reset_index())

fig = go.Figure(go.Scatter(x = country['Count'], 
                           y = country["Country"],
                           text = country['percent'],
                           mode = 'markers',
                           marker_color =colors,
                           marker_size  = 12))

for i in range(0, len(country)):
               fig.add_shape(type='line',
                              x0 = 0, y0 = i,
                              x1 = country["Count"][i],
                              y1 = i,
                              line=dict(color=colors[i], width = 4))

fig.update_traces(hovertemplate='<b>Country</b>: %{y}<br><extra></extra>'+
                                '<b>Count</b>: %{x}<br>'+
                                '<b>Proportion</b>: %{text}')

fig.update_xaxes(showgrid=True, gridwidth=1, gridcolor='#9f9f9f', ticklabelmode='period')
fig.update_yaxes(showgrid=False)
 
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="Most Common <b>Countries</b>",
                  title_x=0.5,
                  height=700,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=17, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.155,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.155,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
![](/images/0303_1-3/10.PNG)

<br>
<br>
