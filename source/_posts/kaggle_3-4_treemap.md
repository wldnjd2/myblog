---
title: Kaggle(3-4) treemap
date: 2021-11-09
tags: kaggle, plotly
toc: true
thumbnail: /images/0307_3-4/2.PNG
categories: 
- kaggle
widgets: null
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

### **3-4 Treemap**
---
recommend_leng 객체 생성


```python
recommend_leng = (
    df['Q8']
    .value_counts()
    .to_frame()
    .reset_index()
    .rename(columns={'index':'Lenguage', 'Q8':'Count'})
    .sort_values(by=['Count'], ascending=False)   
          )   
print(recommend_leng)
```
![](/images/0307_3-4/1.PNG)

<br>

### **color**
---

```python
colors = ['#033351',] * 13
colors[0] = '#5abbf9'
colors[1] = '#066eb0'
colors[2] = '#044a77'
colors[3] = '#043e64'
colors[4] = '#043e64'
```
<br>

### **Treemap**
[참고사이트](https://plotly.com/python/treemaps/)
---
트리맵 차트는 내포된 직사각형을 사용하여 계층적 데이터를 시각화합니다.
계층 구조는 레이블(px.tremap의 이름) 및 상위 속성에 의해 정의됩니다.

- labels = recommend_leng['Lenguage']
labels값
- values = recommend_leng['Count']
values값
- parents = ['']*recommend_leng.shape[0]
treemap의 계층을 따로 만들어 주지 않았기 때문에 
recommend_leng.shape[0] 으로 정해줍니다.
- 
```python
fig = go.Figure(go.Treemap(
    labels = recommend_leng['Lenguage'],
    values = recommend_leng['Count'],
    parents = ['']*recommend_leng.shape[0],
    textinfo = "percent root+label+value+text",
))
fig.show()
```
![](/images/0307_3-4/2.PNG)
<br>

### **나머지 코드**
---
- treemapcolorway = colors
treemap 컬러 지정
- 
```python
fig.update_traces(hovertemplate='<b>Lenguage</b>: %{label}<br><extra></extra>'+
                                '<b>Count</b>: %{value}')
 
fig.update_layout(showlegend=False, 
                  treemapcolorway = colors,
                  margin=dict(pad=20),
                  paper_bgcolor='#F7F7F7',
                  plot_bgcolor='#F7F7F7',
                  height=600,
                  yaxis={'showticklabels': False},
                  yaxis_title=None,
                  xaxis_title=None,
                  title_text="Most Recommended <b>Programming Language</b>",
                  title_x=0.5,
                  title_y=0.95,
                  font=dict(family="Hiragino Kaku Gothic Pro, sans-serif", size=17, color='#000000'),
                  title_font_size=35)

fig.add_annotation(dict(font=dict(size=14),
                                    x=0.96,
                                    y=-0.14,
                                    showarrow=False,
                                    text="@miguelfzzz",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.add_annotation(dict(font=dict(size=12),
                                    x=0.01,
                                    y=-0.14,
                                    showarrow=False,
                                    text="Source: 2021 Kaggle Machine Learning & Data Science Survey",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))

fig.show()
```
![](/images/0307_3-4/3.PNG)
<br>
<br>
