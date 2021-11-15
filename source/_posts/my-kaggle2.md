---
title: Kaggle Competition 준비하기(2)
date: 2021-11-15
tags: kaggle, plotly
toc: true
thumbnail: 
categories: 
- my kaggle
widgets: null
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

## 동아시아 vs 전세계
---

데이터 grouping


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

- isin()
- df21의 Q3열에 EastAsia의 리스트값과 동일한게 있을때 True , 없으면 False


```python
df21_Wo.head()
```


```python
## 마지막 열에 region이 추가된 것을 확인 할 수 있음
df21.head()
```


```python
df21['region'].value_counts()
##df20['region'].value_counts()
##df19['region'].value_counts()
##df18['region'].value_counts()
##df17['region'].value_counts()
```


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

fig.update_layout()
fig.show()
#barmode ='stack' 이거 제거하면 나란히 그래프 나온당
```


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


```python
df20_Ea.head()
```


```python
df21_Ea=df21[df21['Q3'].isin(EastAsia21)]
df21_Ea['Q3'].value_counts().to_frame()
```


```python
df20_Ea=df20[df20['Q3'].isin(EastAsia20)]
df20_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()
```


```python
df19_Ea=df19[df19['Q3'].isin(EastAsia19)]
df19_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()
```


```python
df18_Ea=df18[df18['Q3'].isin(EastAsia18)]
df18_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()
```


```python
df17_Ea = df17[df17['Country'].isin(EastAsia)]
df17_Ea['Country'].replace("People 's Republic of China","China").value_counts().to_frame()
```


```python
df17_Ea = df17[df17['Country'].isin(EastAsia)]
df17_Ea['Country'].replace("People 's Republic of China","China").value_counts().to_frame()

df18_Ea=df18[df18['Q3'].isin(EastAsia18)]
df18_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()

df19_Ea=df19[df19['Q3'].isin(EastAsia19)]
df19_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()

df20_Ea=df20[df20['Q3'].isin(EastAsia20)]
df20_Ea['Q3'].replace('Republic of Korea','South Korea').value_counts().to_frame()

df21_Ea=df21[df21['Q3'].isin(EastAsia21)]
df21_Ea['Q3'].value_counts().to_frame()
```


```python
df21_Ea = df21[df21['Q3'].isin(EastAsia)]
df_2121=(df21_Ea['Q3'].value_counts().to_frame())
dfdf= df_2121.loc[['Japan'], :]
dfdf
```


```python
df21_Ea = df21[df21['Q3'].isin(EastAsia)]
df_2121=(df21_Ea['Q3'].value_counts())
df_2121
```


```python
dfdfdfdf=df21['region'].value_counts()
dfdfdfdf['EastAsia']
```


```python
df21_Ea = df21[df21['Q3'].isin(EastAsia)]
df_2121=(df21_Ea['Q3'].value_counts())
df_2121['Japan']

```


```python
len(df21_Ea)
```


```python
def percent (a, b):
    result =a/(a+b)*100
    return result

def percentR (b, a):
    result =a/(a+b)*100
    return result
```


```python
fig = go.Figure(data=[
    go.Bar(name='2017', x=years, y=[percentR(Ea17, Wo17), percentR(Ea18, Wo18), percentR(Ea19, Wo19), 
                                                 percentR(Ea20, Wo20), percentR(Ea21, Wo21)]),
    
    go.Bar(name='2018', x=years, y=[percent(Ea17, Wo17), percent(Ea18, Wo18), percent(Ea19, Wo19), 
                                                 percent(Ea20, Wo20), percent(Ea21, Wo21)]),
    
    go.Bar(name='2019', x=years, y=[percent(Ea17, Wo17), percent(Ea18, Wo18), percent(Ea19, Wo19), 
                                                 percent(Ea20, Wo20), percent(Ea21, Wo21)]),
    
    go.Bar(name='2020', x=years, y=[percent(Ea17, Wo17), percent(Ea18, Wo18), percent(Ea19, Wo19), 
                                                 percent(Ea20, Wo20), percent(Ea21, Wo21)]),
    
    go.Bar(name='2021', x=years, y=[percent(Ea17, Wo17), percent(Ea18, Wo18), percent(Ea19, Wo19), 
                                                 percent(Ea20, Wo20), percent(Ea21, Wo21)])
    
])

fig.update_layout(barmode ='stack')
fig.show()
```


```python
import plotly.graph_objects as go
years=['2017', '2018', '2019', '2020', '2021']

fig = go.Figure(data=[
    go.Bar(x=years,name='SF Zoo', y=[20, 14, 23]),
    go.Bar(x=years,name='LA Zoo', y=[12, 18, 29]),
    go.Bar(x=years,name='LA Zoo', y=[12, 18, 29]),
    go.Bar(x=years,name='LA Zoo', y=[12, 18, 29]),
    go.Bar(x=years,name='LA Zoo', y=[12, 18, 29])
])
# Change the bar mode
fig.update_layout(barmode='stack')
fig.show()
```


```python
import plotly.express as px

wide_df = px.data.medals_wide()

fig = px.bar(wide_df, x="nation", y=["gold", "silver", "bronze"], title="Wide-Form Input")
fig.show()
```


```python
import plotly.express as px
data = px.data.gapminder()

data_canada = data[data.country == 'Canada']
fig = px.bar(data_canada, x='year', y='pop',
             hover_data=['lifeExp', 'gdpPercap'], color='lifeExp',
             labels={'pop':'population of Canada'}, height=400)
fig.show()
```

## 원형 그래프


```python
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

```


```python
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
total
```


```python
from plotly.subplots import make_subplots
import plotly.graph_objects as go
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
import plotly.graph_objects as go
from plotly.subplots import make_subplots

# Create subplots: use 'domain' type for Pie subplot
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
# Use `hole` to create a donut-like pie chart
fig.update_traces(hole=.2, hoverinfo="label+percent+name")
fig.update_layout(
    title_text="<b>World vs EastAsia</b>",
    # Add annotations in the center of the donut pies.
   )
fig.show()
```


```python
import plotly.graph_objects as go
from plotly.subplots import make_subplots

# Create subplots: use 'domain' type for Pie subplot
fig = make_subplots(rows=2, cols=3, specs=[[{'type':'domain'}, {'type':'domain'}, {'type':'domain'}], 
                                           [{'type':'domain'}, {'type':'domain'}, {'type':'domain'}]])


fig.add_trace(go.Pie(labels=total21['type'], values=total21['respodents'], name="2021", scalegroup='one'),
              1, 1)

fig.add_trace(go.Pie(labels=total20['type'], values=total20['respodents'], name="2020", scalegroup='one'),
              1, 2)

fig.add_trace(go.Pie(labels=total19['type'], values=total19['respodents'], name="2019", scalegroup='one'),
              1, 3)

fig.add_trace(go.Pie(labels=total18['type'], values=total18['respodents'], name="2018", scalegroup='one'),
              2, 1)

fig.add_trace(go.Pie(labels=total17['type'], values=total17['respodents'], name="2017", scalegroup='one'),
              2, 2)


# Use `hole` to create a donut-like pie chart
fig.update_traces(hole=.2, hoverinfo="label+percent+name")

fig.update_layout(
    title_text="<b>World vs EastAsia</b>",
    # Add annotations in the center of the donut pies.
   )
fig.show()
```


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

원형그래프 메서드 만들기


```python
df21['region'].value_counts()
```


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


```python
pie(df21)
```


```python
pie(df20)
```

## choropleth
---


```python
df21_Ea['Q3'].value_counts()
```


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

## 전세계 vs 동아시아 인원 비교 그래프
---

21년도


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
