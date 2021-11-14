---
title: Kaggle2 bar v와 h
date: 2021-11-10
tags: kaggle, plotly
toc: true
thumbnail: /images/0401/6.PNG
categories: 
- kaggle
widgets: null
---

### **import문**
---


```python
import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pylab as plt

import plotly.io as pio
import plotly.express as px
import plotly.graph_objects as go
import plotly.figure_factory as ff
from plotly.subplots import make_subplots
from plotly.offline import init_notebook_mode, iplot
init_notebook_mode(connected=True)
pio.templates.default = "none"
# import plotly.offline as py
# py.offline.init_notebook_mode()

import os
for dirname, _, filenames in os.walk('/kaggle/input'):
    for filename in filenames:
        print(os.path.join(dirname, filename))

import warnings
warnings.filterwarnings("ignore")
```
<br>

### **캐글 데이터 불러오기**
---


```python
df17= pd.read_csv("/kaggle/input/kaggle-survey-2017/multipleChoiceResponses.csv", encoding="ISO-8859-1")
df18= pd.read_csv("/kaggle/input/kaggle-survey-2018/multipleChoiceResponses.csv", )
df19= pd.read_csv("/kaggle/input/kaggle-survey-2019/multiple_choice_responses.csv", )
df20= pd.read_csv("/kaggle/input/kaggle-survey-2020/kaggle_survey_2020_responses.csv", )
df21= pd.read_csv("/kaggle/input/kaggle-survey-2021/kaggle_survey_2021_responses.csv", )
```
<br>

## **1-1. 수평그래프(Bar_h)**
### **데이터 전처리**
---
- 리스트 생성
africa17, africa18, africa19, africa20, africa21
- isin
Pandas에서는 어떤 list에 존재하는 요소가 대상 DataFrame이나 Series에 존재 하는지 True(존재), False(존재안함)로 반환준다
- [df21['Q3'].isin(africa)]
거주지역이 africa 리스트에 있는 지역에 해당할경우 True로 반환
- df21[df21['Q3'].isin(africa)]
(21년도 기준)africa에 사는 사람들의 df21의 데이터 값만 불러옴


```python
# grouping african countries
# 리스트 만들어줌
africa17 = ['Nigeria','Kenya', 'South Africa', 'Egypt']
africa18 = ['Nigeria','Kenya', 'South Africa', 'Egypt', 'Tunisia', 'Morocco'] 
africa19 = ['Nigeria','Kenya', 'South Africa', 'Egypt', 'Tunisia', 'Morocco', 'Algeria']
africa20 = ['Nigeria','Kenya', 'South Africa', 'Egypt', 'Tunisia', 'Morocco', 'Ghana']
africa21 = ['Nigeria','Kenya', 'South Africa', 'Egypt', 'Tunisia', 'Morocco', 'Algeria', 'Ghana', 'Uganda', 'Ethiopia']

#df21['Q3'] -> In which country do you currently reside? 거주지역은?
africa = ['Nigeria', 'Egypt', 'South Africa', 'Algeria', 'Tunisia', 'Morocco', 'Kenya', 'Uganda', 'Ghana', 'Ethiopia']

#21년도 기준


df21_africa = df21[df21['Q3'].isin(africa)]
df21_world = df21[~df21['Q3'].isin(africa )]
df21['region']=["Africa" if x in africa else "World" for x in df21['Q3']]

df20_africa = df20[df20['Q3'].isin(africa)]
df20_world = df20[~df20['Q3'].isin(africa )]
df20['region']=["Africa" if x in africa else "World" for x in df20['Q3']]

df19_africa = df19[df19['Q3'].isin(africa)]
df19_world = df19[~df19['Q3'].isin(africa)]
df19['region']=["Africa" if x in africa else "World" for x in df19['Q3']]

df18_africa = df18[df18['Q3'].isin(africa)]
df18_world = df18[~df18['Q3'].isin(africa)]
df18['region']=["Africa" if x in africa else "World" for x in df18['Q3']]

df17_africa = df17[df17['Country'].isin(africa)]
df17_world = df17[~df17['Country'].isin(africa )]
df17['region']=["Africa" if x in africa else "World" for x in df17['Country']]       
```


<br>

```python
print(africa)
```
> ['Nigeria', 'Egypt', 'South Africa', 'Algeria', 'Tunisia', 'Morocco', 'Kenya', 'Uganda', 'Ghana', 'Ethiopia']


<br>

```python
print(df21['Q3'])
```
>   0        In which country do you currently reside?
    1                                            India
    2                                        Indonesia
    3                                         Pakistan
    4                                           Mexico
                           ...                    
    25969                                        Egypt
    25970                                        China
    25971                                       Sweden
    25972                     United States of America
    25973                                        India
    Name: Q3, Length: 25974, dtype: object


<br>

```python
print(df21['Q3'].isin(africa))
```
>   0        False
    1        False
    2        False
    3        False
    4        False
         ...  
    25969     True
    25970    False
    25971    False
    25972    False
    25973    False
    Name: Q3, Length: 25974, dtype: bool


<br>

```python
print(df21)
```
![](/images/0401/1.PNG)
<br>
<br>

```python
print(df21[df21['Q3'].isin(africa)])
```
![](/images/0401/2.PNG)
<br>
<br>

### **데이터 전처리**
---
- afro21 = len(df21_africa)
df21_africa의 행 갯수 -> 아프리카에 거주하는 캐글러 수
- len(df21) 
행의 갯수 -> 설문조사에 응답한 전세계 캐글러 수
- row21 = len(df21) - afro21 
전세계 캐글러 수 - 아프리카 거주하는 캐글러 수 = 나머지


```python
afro21 = len(df21_africa)
row21 = len(df21) - afro21

afro20 = len(df20_africa)
row20 = len(df20) - afro20

afro19 = len(df19_africa)
row19 = len(df19) - afro19

afro18 = len(df18_africa)
row18 = len(df18) - afro18

afro17 = len(df17_africa)
row17 = len(df17) - afro17
```

<br>

```python
print(afro21) 
print(len(df21)) 
print(row21) 
```
>   2060
    25974
    23914

<br>

```python
#리스트 생성
region = ['Africa', 'Rest of the World']
value = [afro21, row21]
percent =[afro21/(afro21 +row21)*100, row21/(afro21+row21)*100]
```

<br>

```python
print(region)
print(value)
print(percent) #아프리카에 사는 캐글러, 전세계의 캐글러 percent 값
```
>   ['Africa', 'Rest of the World']
    [2060, 23914]
    [7.931007931007931, 92.06899206899207]

<br>

### **1-1. africa에 사는 kaggler 수 VS 전 세계 kaggler 수**
---


### **데이터 시각화하기**
---
- go.Bar
막대그래프 생성
- np.round(percent,1)
반올림하기
- textposition=['outside', 'inside']
괄호 안은 각각 설정값[afica, rest of the world]
- textfont=dict()
막대그래프 데이터 값 폰트 설정
- orientation='h'
수평으로 그래프 그리기
- marker_color=['gold', 'salmon']
막대그래프 색상 설정 (africa, rest of the world)
- opacity=0.6
그래프 투명도 설정 (0.0 ~ 1)


```python
fig = go.Figure(data=[go.Bar(
            x=value, y=region,
            text=(np.round(percent,1)),
            textposition=['outside', 'inside'],
            texttemplate = ["<b style='color: #f'>%{text}%</b>"]*2,
            textfont=dict(  family="sans serif",
                            size=16,
                            color="black"),
            orientation='h',
            marker_color=['gold', 'salmon'],
            opacity=0.6,
                    )])
fig.show()
```
![](/images/0401/3.PNG)
<br>

### **update_traces**
---
- marker_line_color='black'
: 막대그래프 테두리 색상
- marker_line_width=2.5
: 막대그래프 테두리 두께

<br>

### **update_layout**
---
- yaxis_linewidth=2.5
y축 테두리 두께
- bargap=0.2
막대그래프 두께 (0 ~ 1.0 숫자가 작을수록 두꺼움)
- barmode='group'


```python
fig.update_traces(marker_line_color='black',
                  marker_line_width=2.5)
fig.update_layout(title='<b>Number of respondents: Africa vs Rest of the world (2021)<b>', 
                  font_family="San Serif",
                  yaxis_linewidth=2.5,
                  bargap=0.2,
                  barmode='group',
                  titlefont={'size': 24},
                  paper_bgcolor='#F5F5F5',
                  plot_bgcolor='#F5F5F5',                  
              
                  )

```
![](/images/0401/4.PNG)
<br>

### **update_layout**
---
- fig.update_layout(xaxis = dict(
x축 레이아웃 설정
- autosize=False
사이즈 고정
true로 설정시 대시보드 자체가 화면에 맞는 크기로 엄청 커짐 (좌우로)
- showgrid=False
배경 격자무늬 생성 안함
- margin
배경화면에서의 그래프 크기 비율 조정


```python
fig.update_layout(
        xaxis = dict(
        zeroline = False,
        showline = False,
        showticklabels = False,
        gridwidth = 1
    ),
    autosize=False,
    margin=dict(
        l=150,
        r=50,
        b=50,
        t=100,
    ),
    )

fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.show()
```
![](/images/0401/5.PNG)
<br>
<br>

## **1-2. 수직 그래프(Bar_v)**
### **연도별 african kagglers의 수 비교**
### **데이터 전처리**
---


```python
## historical data, all gender
year = ['2017', '2018', '2019', '2020', '2021']
value = [afro17, afro18, afro19, afro20, afro21]

percent =[
    afro17/(afro17 +row17)*100,
    afro18/(afro18 +row18)*100,
    afro19/(afro19 +row19)*100,
    afro20/(afro20 +row20)*100,
    afro21/(afro21 +row21)*100]  
   

color = 5* ['salmon'] 
color[4] = 'gold'

fig = go.Figure(data=[go.Bar(
            y=value, x=year,
            text=np.round(percent, 1), 
            textposition='outside',
            texttemplate = ["<b style='color: #f'>%{text}%</b>"]*5,
            textfont=dict(  family="sans serif",
                            size=16,
                            color="black"),
            orientation='v',
            marker_color= color, 
            opacity=0.6
                    )])

fig.update_traces(marker_line_color='black',
                  marker_line_width=2.5)

fig.update_layout(title='<b>The rise of African kagglers<b>', 
                  font_family="San Serif",
                  xaxis_linewidth=2.5,
                  bargap=0.2,
                  barmode='group',
                  titlefont={'size': 28},
                  template='simple_white',
                  paper_bgcolor='#F5F5F5',
                  plot_bgcolor='#F5F5F5',                  
                  )
fig.update_layout(yaxis_title='Number of Respondents',xaxis_title='Year',
    autosize=False,
    margin=dict(
        l=100,
        r=50,
        b=50,
        t=70,
        pad=0,
    ),
    )

fig.show()
```
![](/images/0401/6.PNG)
<br>
<br>
<br>
