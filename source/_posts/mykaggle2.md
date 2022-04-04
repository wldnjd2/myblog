---
title: Kaggle Competition(2)
date: 2021-11-15
tags: kaggle, plotly, pie, bar
toc: true
thumbnail: images/mykaggle2/6.png
categories: 
- kaggle 필사
widgets: null
---

### **라이브러리 불러오기 & 캐글 데이터 불러오기**
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


```python
df17= pd.read_csv("/kaggle/input/kaggle-survey-2017/multipleChoiceResponses.csv", encoding="ISO-8859-1")
df18= pd.read_csv("/kaggle/input/kaggle-survey-2018/multipleChoiceResponses.csv", )
df19= pd.read_csv("/kaggle/input/kaggle-survey-2019/multiple_choice_responses.csv", )
df20= pd.read_csv("/kaggle/input/kaggle-survey-2020/kaggle_survey_2020_responses.csv", )
df21= pd.read_csv("/kaggle/input/kaggle-survey-2021/kaggle_survey_2021_responses.csv", )
```

<br>

### **데이터 Grouping**
---


```python
## East Asia에는 대한민국, 일본, 중국, 타이완, 몽골, 북조선 총 6개의 국가가 속해 있다. 

EastAsia17 = ['China',"People 's Republic of China", 'Taiwan', 'South Korea', 'Japan']
EastAsia18 = ['China', 'South Korea', 'Japan', 'Republic of Korea'] 
EastAsia19 = ['China','Taiwan', 'South Korea', 'Japan', 'Republic of Korea']
EastAsia20 = ['China','Taiwan', 'South Korea', 'Japan', 'Republic of Korea']
EastAsia21 = ['China','Taiwan', 'South Korea', 'Japan']
EastAsia = ['Republic of Korea','China','Taiwan', 'South Korea', 'Japan', "People 's Republic of China" ]

#21년
df21_Ea = df21[df21['Q3'].isin(EastAsia)]
df21_Wo = df21[~df21['Q3'].isin(EastAsia )]

##  동아시아 국가를 제외한 국가들을 region 열의 데이터 값을 World 로 바꿔줌
df21['region']=["EastAsia" if x in EastAsia 
                            else "World" for x in df21['Q3']]

#20년
df20_Ea = df20[df20['Q3'].isin(EastAsia)]
df20_Wo = df20[~df20['Q3'].isin(EastAsia )]
df20['region']=["EastAsia" if x in EastAsia 
                            else "World" for x in df20['Q3']]

#19년
df19_Ea = df19[df19['Q3'].isin(EastAsia)]
df19_Wo = df19[~df19['Q3'].isin(EastAsia )]
df19['region']=["EastAsia" if x in EastAsia
                            else "World" for x in df19['Q3']]

#18년
df18_Ea = df18[df18['Q3'].isin(EastAsia)]
df18_Wo = df18[~df18['Q3'].isin(EastAsia )]
df18['region']=["EastAsia" if x in EastAsia 
                            else "World" for x in df18['Q3']]

#17년
df17_Ea = df17[df17['Country'].isin(EastAsia)]
df17_Wo = df17[~df17['Country'].isin(EastAsia )]
df17['region']=["EastAsia" if x in EastAsia 
                            else "World" for x in df17['Country']]
```
<br>

### **Stack Bar 그래프 데이터 전처리1**
---
- 연도별로 데이터 정리 했음


```python
df21_Ea=df21[df21['Q3'].isin(EastAsia21)]
df21_Ea['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'21_n'})
```
![](/images/mykaggle2/1.PNG)
<br>


```python
df20_Ea=df20[df20['Q3'].isin(EastAsia20)]
df20_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'20_n'})
```
![](/images/mykaggle2/2.PNG)
<br>

```python
df19_Ea=df19[df19['Q3'].isin(EastAsia19)]
df19_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'19_n'})
```
![](/images/mykaggle2/3.PNG)
<br>

- append() 메서드를 사용해서 Taiwan = 0 값 추가해줌
- ignore_index=True  원래 있던 df의 index를 무시
```python
df18_Ea=df18[df18['Q3'].isin(EastAsia18)]
df18_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index().append({'index': 'Taiwan','Q3':'0'}, ignore_index=True).rename(columns={'index':'Country', 'Q3':'18_n'})
```
![](/images/mykaggle2/4.PNG)
<br>

```python
df17_Ea = df17[df17['Country'].isin(EastAsia)]
df17_Ea['Country'].replace("People 's Republic of China","China").value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Country':'17_n'})
```
![](/images/mykaggle2/5.PNG)
<br>

### **Stack Bar 그래프 데이터 전처리2**
---

- 18년도 Taiwan 데이터 값이 없음
- iloc: 데이터프레임의 행이나 컬럼에 인덱스 값으로 접근
- loc: 데이터프레임의 행이나 컬럼에 label이나 boolean array로 접근 (location의 약자)
<br>
<br>
- 위에 전처리 내용을 아래 그래프에 맞게 더 다듬어 정리하였다


```python
df17_Ea = df17[df17['Country'].isin(EastAsia)]
df17_StackB = df17_Ea['Country'].replace("People 's Republic of China","China").value_counts().to_frame().reset_index()

df18_Ea = df18[df18['Q3'].isin(EastAsia18)]
df18_StackB = df18_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index().append({'index': 'Taiwan','Q3':'0'}, ignore_index=True)

df19_Ea = df19[df19['Q3'].isin(EastAsia19)]
df19_StackB = df19_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index()

df20_Ea = df20[df20['Q3'].isin(EastAsia20)]
df20_StackB = df20_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame().reset_index()

df21_Ea = df21[df21['Q3'].isin(EastAsia21)]
df21_StackB = df21_Ea['Q3'].value_counts().to_frame().reset_index()
```

- barmode ='stack'
Bar 그래프를 stack 형식으로 쌓아서 표현하였다

```python
fig = go.Figure(data=[
    go.Bar(name='China', x=years, y=[df17_StackB.iloc[0,1], df18_StackB.iloc[0,1], df19_StackB.iloc[1,1], df20_StackB.iloc[1,1], df21_StackB.iloc[1,1]]),
    
    go.Bar(name='Japan', x=years, y=[df17_StackB.iloc[1,1], df18_StackB.iloc[1,1], df19_StackB.iloc[0,1], df20_StackB.iloc[0,1], df21_StackB.iloc[0,1]]),
    
    go.Bar(name='Taiwan', x=years, y=[df17_StackB.iloc[2,1], df18_StackB.iloc[3,1], df19_StackB.iloc[2,1], df20_StackB.iloc[2,1], df21_StackB.iloc[3,1]]),
    
    go.Bar(name='South Korea', x=years, y=[df17_StackB.iloc[3,1], df18_StackB.iloc[2,1], df19_StackB.iloc[3,1], df20_StackB.iloc[3,1], df21_StackB.iloc[2,1]])
    
    ])

fig.update_layout(barmode ='stack')
fig.show()
```
![](/images/mykaggle2/6.png)
<br>

### **Pie 그래프**
### **데이터 전처리**
---


```python
fig = make_subplots(rows=1, cols=5)
total17 = (
    df17['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index()
)
total18 = (
    df18['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index()
)
total19 = (
    df19['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index()
)
total20 = (
    df20['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index()
)
total21 = (
    df21['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index()
)
```
<br>

### **Pie 그래프 그리기**
---
- scalegroup='one'
원 그래프의 자체 사이즈를 변경 할 수 있다.
```python
fig = make_subplots(rows=1, cols=5, specs=[[{'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}]])

fig.add_trace(go.Pie(labels=total21['type'], values=total21['respodents'], name="2021", scalegroup='one'),
              1, 1)
fig.add_trace(go.Pie(labels=total20['type'], values=total20['respodents'], name="2020", scalegroup='one'),
              1, 2)
fig.add_trace(go.Pie(labels=total19['type'], values=total19['respodents'], name="2019", scalegroup='one'),
              1, 3)
fig.add_trace(go.Pie(labels=total18['type'], values=total18['respodents'], name="2018", scalegroup='one'),
              1, 4)
fig.add_trace(go.Pie(labels=total17['type'], values=total17['respodents'], name="2017", scalegroup='one'),
              1, 5)

fig.update_traces(hole=.2, hoverinfo="label+percent+name")
fig.update_layout(
    title_text="<b>World vs EastAsia</b>",
    
   )
fig.show()
```
![](/images/mykaggle2/7.png)
<br>

### **느낀점**
---
그래프 그리는것보다
데이터 전처리가 더 힘든것 같다..
그래도 이번에 직접 해보면서
헷갈렸던 문법들을 다시 정리할수 있었고
의미를 완벽하게 익혔고 어느정도 감이 잡혔음을 느꼈다.

내일도 더 열심히...


### **Ref**
---
https://plotly.com/python/pie-charts/
<br>
<br>
<br>
