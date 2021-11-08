---
title: Kaggle(3-1) 막대그래프(Bar, 수직)
date: 2021-11-09
tags: kaggle, plotly
toc: true
thumbnail: /images/0305_3-1/6.PNG
categories: 
- kaggle
widgets: null
---

<br>

## **3-1. 막대그래프(수직)**
### **라이브러리 임포트 해주기 & 캐글 데이터 불러오기**
---

```python
import pandas as pd
import numpy as np
import seaborn as sns
import plotly.express as px
import plotly.graph_objects as go

import warnings
warnings.filterwarnings('ignore') 

df = pd.read_csv('../input/kaggle-survey-2021/kaggle_survey_2021_responses.csv')
df = df.iloc[1:, :]
```

<br>

### **experience 객체 생성**
---
- **.replace([a],[b])**
a 이름을 b로 바꾼다 



```python
experience = (
    df['Q6']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Experience', 'Q6':'Count'})
    .replace(['I have never written code','< 1 years',
              '1-3 years', '3-5 years', '5-10 years',
              '10-20 years', '20+ years'], ['No experience', '<1 years',
                                        '1-3 years', '3-5 years', '5-10 years',
                                        '10-20 years', '20+ years'])
          ) 
```


```python
print(df['Q6'])
```

![](/images/0305_3-1/1.PNG)

```python
print(experience)
```
![](/images/0305_3-1/2.PNG)

<br>

### **pandas categorical**

pandas 에서 자료형으로 사용되는 object와 category
- object 
문자열을 object라는 자료형으로 나타낸다.
- category
category 형식은 가능한 값들의 범위가 고정되어있고, 한정적일 때 매우 사용한다.

???????카테고리로 바꿔준거??????????????
```python
experience['Experience'] = pd.Categorical(
                                        experience['Experience'], 
                                        ['No experience', '<1 years',
                                        '1-3 years', '3-5 years', '5-10 years',
                                        '10-20 years', '20+ years']
                                         )
print(experience['Experience'])
```
![](/images/0305_3-1/3.PNG)

<br>

```python
experience['percent'] = ((experience['Count'] / experience['Count'].sum())*100).round(2).astype(str) + '%'
print(experience['percent'])
```
![](/images/0305_3-1/4.PNG)

<br>

```python
experience = experience.sort_values('Experience')
print(experience)
```
![](/images/0305_3-1/5.PNG)
<br>

```python
colors = ['#033351',] * 7
colors[1] = '#5abbf9'
colors[2] = '#5abbf9'
colors[3] = '#0779c3'
colors[4] = '#0779c3'


fig = go.Figure(go.Bar(
            y=experience['Count'],
            x=experience['Experience'],
            cliponaxis = False,
            text=experience['percent'],
            marker_color=colors
                        ))

fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  hovertemplate='<b>Experience</b>: %{x}<br><extra></extra>'+
                                '<b>Count</b>: %{y}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
 
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  height=500,
                  yaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="<b>Experience</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=14, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.24,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=-0.03,
                                    y=-0.24,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```
![](/images/0305_3-1/6.PNG)

<br>

### **전체코드**
---


```python
experience = (
    df['Q6']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Experience', 'Q6':'Count'})
    .replace(['I have never written code','< 1 years',
              '1-3 years', '3-5 years', '5-10 years',
              '10-20 years', '20+ years'], ['No experience', '<1 years',
                                        '1-3 years', '3-5 years', '5-10 years',
                                        '10-20 years', '20+ years'])
          ) 

experience['Experience'] = pd.Categorical(
                                        experience['Experience'], 
                                        ['No experience', '<1 years',
                                        '1-3 years', '3-5 years', '5-10 years',
                                        '10-20 years', '20+ years']
                                         )
                                         

experience['percent'] = ((experience['Count'] / experience['Count'].sum())*100).round(2).astype(str) + '%'

experience = experience.sort_values('Experience')

colors = ['#033351',] * 7
colors[1] = '#5abbf9'
colors[2] = '#5abbf9'
colors[3] = '#0779c3'
colors[4] = '#0779c3'


fig = go.Figure(go.Bar(
            y=experience['Count'],
            x=experience['Experience'],
            cliponaxis = False,
            text=experience['percent'],
            marker_color=colors
                        ))

fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  hovertemplate='<b>Experience</b>: %{x}<br><extra></extra>'+
                                '<b>Count</b>: %{y}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
 
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  height=500,
                  yaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="<b>Experience</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=14, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.24,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=-0.03,
                                    y=-0.24,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()

```
![](/images/0305_3-1/6.PNG)
<br>
<br>
