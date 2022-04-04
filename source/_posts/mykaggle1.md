---
title: Kaggle Competition(1)
date: 2021-11-14
tags: kaggle, plotly
toc: true
thumbnail: images/mykaggle1/11.PNG
categories: 
- kaggle 필사
widgets: null
---
### **데이터 불러오기**
---
실행환경: kaggle notebook
사용언어: Python Plotly
[준비하는 kaggle competition 링크](https://www.kaggle.com/c/kaggle-survey-2021)
<br>

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

### **17년 - 21년도 데이터 불러오기**
---
```python
df17= pd.read_csv("/kaggle/input/kaggle-survey-2017/multipleChoiceResponses.csv", encoding="ISO-8859-1")
df18= pd.read_csv("/kaggle/input/kaggle-survey-2018/multipleChoiceResponses.csv", )
df19= pd.read_csv("/kaggle/input/kaggle-survey-2019/multiple_choice_responses.csv", )
df20= pd.read_csv("/kaggle/input/kaggle-survey-2020/kaggle_survey_2020_responses.csv", )
df21= pd.read_csv("/kaggle/input/kaggle-survey-2021/kaggle_survey_2021_responses.csv", )
```
<br>

### **데이터 전처리**
---

```python
#21년도에 설문조사에 참여한 국가들
pd.set_option('display.max_rows', None)
df21['Q3'].value_counts().sort_index(ascending=True)
```
![](/images/mykaggle1/1.PNG)
<br>

```python
#South Korea에 해당하는 참여자만 출력
df21_Ko = df21[df21['Q3'] == 'South Korea']
df21_Ko.head()
```
![](/images/mykaggle1/2.PNG)
<br>

```python
#한국을 제외한 참여자들의 투표결과
df21_Wo = df21[~(df21['Q3'] == 'South Korea')]
df21_Wo.head()
```
![](/images/mykaggle1/3.PNG)
<br>


```python
## 설문조사에 참여한 사람 비율

#한국
df21_Ko = df21[df21['Q3'] == 'South Korea']
#전세계
df21_Wo = df21[~(df21['Q3'] == 'South Korea')]

#동아시아를 제외한 국가는 전부 거주지역을 World로 바꿈
df21['region']=["Korea" if x == 'South Korea' 
                        else "World" for x in df21['Q3']]
df21['region'].value_counts()
```

<br>


```python
## if 변수 x 가 South Korea 일때
## else  -> South Korea가 아닐때
##          x가 0부터 df21['Q3']의 행값을 차례로 World값을 넣는다 
####        이 결과를 df['region'] 에 넣는다
##          따라서 동아시아가 아닌 국가들의 행 값은 전부 World로 바뀜
df21['region']=["Korea" if x == 'South Korea' 
                        else "World" for x in df21['Q3']]
df21['region'].head()
```
![](/images/mykaggle1/4.PNG)
### **데이터 Grouping**
---

- **연도별 EastAsia 국가 정리**
EastAsia17
EastAsia18
EastAsia19
EastAsia20
EastAsia21 
- **연도별 정리**
df21_Ea : 동아시아 국가만 데이터
df21_Wo : 동아시아 제외한 전세계 국가 데이터
df21['region'] : 동아시아를 제외한 국가는 World로 저장됨 (World랑 동아시아국가 이름밖에 없음)
- **isin()**
df21의 Q3열에 EastAsia의 리스트값과 동일한게 있을때 True , 없으면 False


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

```python
## 마지막 열에 region이 추가된 것을 확인 할 수 있음
df21.head()
```
![](/images/mykaggle1/5.PNG)
<br>

```python
df21['region'].value_counts()
##df20['region'].value_counts()
##df19['region'].value_counts()
##df18['region'].value_counts()
##df17['region'].value_counts()
```
![](/images/mykaggle1/6.PNG)
<br>

### **Bar 그래프 생성**
#### 연도별 kaggle 사용자 (전세계 vs 동아시아)
---

```python
# 설문 참여자 총 인원
Ea21 = len(df21_Ea)
Wo21 = len(df21) - len(df21_Ea)

Ea20 = len(df20_Ea)
Wo20 = len(df20) - len(df20_Ea)

Ea19 = len(df19_Ea)
Wo19 = len(df19) - len(df19_Ea)

Ea18 = len(df18_Ea)
Wo18 = len(df18) -  len(df18_Ea)

Ea17 = len(df17_Ea)
Wo17 = len(df17) - len(df17_Ea)

# 퍼센트 함수 만들어줌
# percent, percentR
def percent (a, b):
    result =a/(a+b)*100
    return result

def percentR (b, a):
    result =a/(a+b)*100
    return result


country = ['East Asia', 'Rest of the World']
years = ['2017', '2018', '2019', '2020', '2021']
```

```python
fig = go.Figure(data=[
    go.Bar(name='Rest of the World', x=years, y=[percentR(Ea17, Wo17), percentR(Ea18, Wo18), percentR(Ea19, Wo19), 
                                                 percentR(Ea20, Wo20), percentR(Ea21, Wo21)]),
    go.Bar(name='East Asia', x=years, y=[percent(Ea17, Wo17), percent(Ea18, Wo18), percent(Ea19, Wo19), 
                                                 percent(Ea20, Wo20), percent(Ea21, Wo21)])
])

fig.update_layout(barmode='stack')
fig.show()
```
![](/images/mykaggle1/7.PNG)

- **barmode ='stack' 이거 제거하면 그래프가 나란히 나온다**
![](/images/mykaggle1/13.PNG)
### **Pie 그래프 생성**
#### 연도별 kaggle 사용자 (전세계 vs 동아시아)
---

```python
total = (
    df21['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index() 
)
total
```
![](/images/mykaggle1/8.PNG)
<br>

```python
colors = ['#f2eda5','#bbbcbd', '#bbbcbd']

fig = go.Figure(data=[go.Pie(labels=total['type'], 
                             values=total['respodents'], 
                             hole=.3)])

fig.update_traces(hoverinfo='percent', 
                  textinfo='label', 
                  textfont_size=20,
                  marker=dict(colors=colors)
                 )

fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  paper_bgcolor='#F7F7F7',
                  title_text="<b>World vs EastAsia</b>",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=25, color='#000000')
                 )

fig.show()
# marker=dict(colors=colors,line=dict(color='#000000', width=1)) #테두리
```
![](/images/mykaggle1/9.PNG)
<br>

### **원형그래프 메서드 만들기**
---

```python
def pie(df):
    total = (
    df['region']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'type', 'region':'respodents'})
    .groupby('type')
    .sum()
    .reset_index() 
    )


    colors = ['#f2eda5','#bbbcbd', '#bbbcbd']

    fig = go.Figure(data=[go.Pie(labels=total['type'], 
                             values=total['respodents'], 
                             hole=.3)])

    fig.update_traces(hoverinfo='percent', 
                  textinfo='label', 
                  textfont_size=20,
                  marker=dict(colors=colors)
                 )

    fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  paper_bgcolor='#F7F7F7',
                  title_text="<b>World vs EastAsia</b>",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=25, color='#000000')
                 )

    fig.show()
# marker=dict(colors=colors,line=dict(color='#000000', width=1)) #테두리
```

- 메서드 호출


```python
pie(df21)
```
<br>


### **choropleth 지도 그래프 그리기**
---

```python
df21_Ea['Q3'].value_counts()
```
![](/images/mykaggle1/10.PNG)
<br>

```python
def world_map(locations,counts,title):
    data = [ dict(
            type = 'choropleth',
            locations = locations,
            z = counts,
            colorscale = 'Blues',
            locationmode = 'country names',
            autocolorscale = False,
            reversescale = True,
            marker = dict(
                line = dict(color = '#F7F7F7', width = 1.5)),
                colorbar = dict(autotick = True, legth = 3, len=0.75, title = 'respodents',
                               max = 1000, min = 0)
                )
           ]
    layout = dict(
        title = title,
        titlefont={'size': 28, 'family': 'san serif'},
        width=750, 
        height=475,
        paper_bgcolor='#F7F7F7',
        geo = dict(
            showframe = True,
            showcoastlines = True,
            fitbounds="locations",
            )
    )
    
    fig = dict(data=data, layout=layout)
    iplot(fig, validate=False, filename='world-map')
    
z = df21_Ea['Q3'].value_counts()
 
## 메서드 호출
world_map(locations=z.index, counts=z.values, title= '<b> EastAsia Countries (2021 survey) <b>')
```
![](/images/mykaggle1/11.PNG)
<br>

### **Bar 그래프**
---

21년도만

```python
## vertical bar graphs##########################
## def plotly_vBar(df, q, title, l=50,r=50,b=50,t=100):   
fig = px.histogram(df21.iloc[1:],
                       x = df21['region'],
                       orientation='v',
                       width=700,
                       height=450,
                       histnorm='percent',
                     
                       color_discrete_map={
                           "EastAsia": "gold", "World": "salmon"
                       },
                       opacity=0.6
                       )
fig.update_layout(title="21년도 전세계 vs 동아시아",
                      font_family="San Serif",
                      bargap=0.2,
                      barmode='group',
                      titlefont={'size': 28},
                      paper_bgcolor='#F5F5F5',
                      plot_bgcolor='#F5F5F5',
                      legend=dict(
                      orientation="v", 
                          y=1, 
                          yanchor="top", 
                          x=1.250, 
                          xanchor="right",)                 
                      ).update_xaxes(categoryorder='total descending')
fig.show()

```
![](/images/mykaggle1/12.PNG)
<br>
<br>
<br>

### **Ref**
---
[참고사이트](https://www.kaggle.com/desalegngeb/how-popular-is-kaggle-in-africa)
