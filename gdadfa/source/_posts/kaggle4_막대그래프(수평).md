---
title: 막대그래프(Bar, 수평)
date: 2021-11-08
tags: kaggle, plotly
toc: true
thumbnail: /images/0304_2-1/9.PNG
categories: 
- kaggle 필사
widgets: null
---

### **2. Education & Occupation**
---
응답자의 77% 이상이 학사 및/또는 석사 학위를 가지고 있습니다.
<br>

## **2-1. 막대그래프(수평)**
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
- import plotly.express as px
- import plotly.graph_objects as go

Plotly는 그래프를 만드는데에는 두가지 방법이 있다 
- **px** 
express의 줄임으로
빠르게 그래프를 제작
- **go**
그래프를 하나하나 설정하여 제작


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


```python
df = pd.read_csv('../input/kaggle-survey-2021/kaggle_survey_2021_responses.csv')
df = df.iloc[1:, :]     
print(df)
```
![](/images/0304_2-1/1.PNG)
<br>

### **dataframe 생성**
---
- education
education이라는 데이터 프레임을 만들어줌
컬럼 값으로 Q4의 데이터 값을 가졌음
- .value_counts()
Q4의 컬럼의 중복된 데이터 값들의 갯수 표시
- .to_frame()
데이터 프레임으로 변환
- .reset_index()
컬럼명 인덱스가 아닌 행 번호 인덱스(숫자)를 사용하고 싶을때 사용
밑의 예시 참고.
- replace(old, new, [count])
문자열 변경 할 수 있는 함수
old : 현재 문자열에서 변경하고 싶은 문자
new: 새로 바꿀 문자
count: 변경할 횟수


```python
print(df['Q4'])
```
![](/images/0304_2-1/2.PNG)

```python
print(df['Q4'].value_counts())
```
![](/images/0304_2-1/3.PNG)

```python
education = (
    df['Q4']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Education', 'Q4':'Count'})
    .replace(['Some college/university study without earning a bachelor’s degree'], 'University studies - No degree')
          )  
education['percent'] = ((education['Count'] / education['Count'].sum())*100).round(2).astype(str) + '%'

print(education)
```
![](/images/0304_2-1/4.PNG)
<br>

### **colors**
---
색상의 범위를 정해줌 


```python
colors = ['#033351',] * 7   #남색
colors[0] = '#5abbf9' #맨위의 색 하늘색
colors[1] = '#5abbf9'
colors[2] = '#0779c3' #진하늘색
colors[3] = '#0779c3'
```
<br>

### **go.figure**
---

- x=education['Count']
education 컬럼 Count의 data 값을 x축에 대입
- y=education['Education']
education 컬럼 Education의 data 값을y 축에 대입
- text=education['percent']
문자열을 추가해줌
이때 education 데이터프레임의 컬럼 percent의 데이터 값을 추가함
- orientation='h'
h는 horizontal bar을 의미한다.
이 문장을 입력하지 않을때 수평 바가 만들어지지 않는다
- marker_color=colors
위에 정의해준 colors 값으로 그래프를 다른 색으로 표현함


```python
fig = go.Figure(go.Bar(
            x = education['Count'],
            y = education['Education'],
            text = education['percent'],
            orientation = 'h',
            marker_color = colors
                        ))
fig.show()
```
![](/images/0304_2-1/5.PNG)
<br>

- orientation='h'가 없을때
---


```python
fig = go.Figure(go.Bar(
            x=education['Count'],
            y=education['Education'],
            text=education['percent'],
            marker_color=colors
                        ))
fig.show()
```
![](/images/0304_2-1/6.PNG)
<br>

### **update_traces()**
---
여러가지 그래프를 한번에 업데이트 할 수 있다
예를들면 Scatter(), bar()를 동시에 포함할 수 있다


```python
fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  cliponaxis = False,
                  hovertemplate='<b>Education</b>: %{y}<br><extra></extra>'+
                                '<b>Count</b>: %{x}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
```
![](/images/0304_2-1/7.PNG)
<br>

### **.update_layout()**
---

```python
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  xaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  yaxis={'categoryorder':'total ascending'},
                  title_text="<b>Education</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=15, color='#000000'),
                  title_font_size=35)
```
![](/images/0304_2-1/8.PNG)
<br>


### **annotation**
---


```python
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.21,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.21,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
<br>

### **전체코드**


```python
education = (
    df['Q4']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Education', 'Q4':'Count'})
    .replace(['Some college/university study without earning a bachelor’s degree'], 'University studies - No degree')
          )  

education['percent'] = ((education['Count'] / education['Count'].sum())*100).round(2).astype(str) + '%'

colors = ['#033351',]*7
colors[0] = '#5abbf9'
colors[1] = '#5abbf9'
colors[2] = '#0779c3'
colors[3] = '#0779c3'


fig = go.Figure(go.Bar(
            x=education['Count'],
            y=education['Education'],
            text=education['percent'],
            orientation='h',
            marker_color=colors
                        ))

fig.update_traces(texttemplate='%{text}', 
                  textposition='outside',
                  cliponaxis = False,
                  hovertemplate='<b>Education</b>: %{y}<br><extra></extra>'+
                                '<b>Count</b>: %{x}',
                  textfont_size=12)
                  
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
 
fig.update_layout(showlegend=False, 
                  plot_bgcolor='#F7F7F7', 
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  xaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  yaxis={'categoryorder':'total ascending'},
                  title_text="<b>Education</b> Distribution",
                  title_x=0.5,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=15, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.98,
                                    y=-0.21,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0,
                                    y=-0.21,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
![](/images/0304_2-1/9.PNG)
<br>
<br>