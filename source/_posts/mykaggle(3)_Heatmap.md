
---
title: Kaggle Competition Heatmap(3)
date: 2021-11-16
tags: kaggle, plotly
toc: true
categories: 
- my kaggle
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

### **Heatmap**
---


```python
# 21년 Business Analyst가 직업인 국가별 인원수
df21_BA = df21[df21['Q5'] == 'Business Analyst']
df21_BA = df21_BA['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'Business Analyst'})
df21_BA

# 21년 Data Analyst가 직업인 국가별 인원수
df21_DA = df21[df21['Q5'] == 'Data Analyst']
df21_DA = df21_DA['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'Data Analyst'})
df21_DA

# 21년 Data Engineer가 직업인 국가별 인원수
df21_DE = df21[df21['Q5'] == 'Data Engineer']
df21_DE = df21_DE['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'Data Engineer'})
df21_DE

# 21년 Data Scientist가 직업인 국가별 인원수
df21_DS = df21[df21['Q5'] == 'Data Scientist']
df21_DS = df21_DS['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'Data Scientist'})
df21_DS
```
![](/images/mykaggle3/1.PNG)
<br>


```python
merge = pd.merge(df21_BA, df21_DA)
job=merge.loc[:,["Business Analyst","Data Analyst"]]
job.columns.tolist()
```
    ['B]usiness Analyst', 'Data Analyst]
<br>

```python
merge
```
![](/images/mykaggle3/2.PNG)
<br>


```python
merge.to_numpy().reshape(-1)
```
![](/images/mykaggle3/3.PNG)
<br>


```python
merge.columns.tolist()
#merge.Country.tolist()
```
    ['Country', Business Analyst', 'Data Analyst]
<br>

```python
merge.to_numpy()
```
![](/images/mykaggle3/4.PNG)
<br>


```python
# 21년 Data Engineer가 직업인 국가별 인원수
df21_DE = df21[df21['Q5'] == 'Data Engineer']
df21_DE = df21_DE['Q3'].value_counts().to_frame().reset_index().rename(columns={'index':'Country', 'Q3':'Data Engineer'})
df21_DE.head()
```
![](/images/mykaggle3/5.PNG)
<br>


```python
merge.iloc[:,[1,2]].to_numpy()
```
![](/images/mykaggle3/6.PNG)
<br>


```python
# x축직업
# y축국가
fig = go.Figure(data=go.Heatmap(
                   z=merge.iloc[:,[1,2]].to_numpy(),
                   x=job.columns.tolist(),
                   y = merge.Country.tolist(),
                   hoverongaps = True,
     coloraxis = "coloraxis"
    
                ))

fig.update_layout(title_text='<i><b>Heatmap</b></i>',
                  xaxis = dict(title='x'),
                  yaxis = dict(title='x')
                 )
# add custom xaxis title
fig.add_annotation(dict(font=dict(color="black",size=14),
                        x=0.5,
                        y=-0.15,
                        showarrow=False,
                        text="",
                        xref="paper",
                        yref="paper"))

# add custom yaxis title
fig.add_annotation(dict(font=dict(color="black",size=14),
                        x=-0.35,
                        y=0.5,
                        showarrow=False,
                        text="",
                        textangle=-90,
                        xref="paper",
                        yref="paper"))

# adjust margins to make room for yaxis title
fig.update_layout(margin=dict(t=50, l=200))

# add colorbar
fig['data'][0]['showscale'] = True

fig.show()

```
![](/images/mykaggle3/7.PNG)
<br>



```python
merge.iloc[0,[1,2]].values.tolist()
```


```python
merge['Business Analyst'].values.tolist()
```


```python
#21년 직업 종류
df21_job = df21['Q5'].value_counts().to_frame().reset_index().rename(columns={'index':'Job', 'Q5':'CNT'})
df21_job = df21_job['Job'].to_frame()
```

### **데이터 전처리**
---


```python
#data 확인

Data_Analyst =['Data Analyst','Data Engineer','Data Miner,Information technology', 'networking, or system ...','Predictive Modeler' ]
Data_Engineer =['A business discipline (accounting, economics, ...', 'Business Analyst',
                'Statistician', 'Mathematics or statistics', 'Data Scientist', 'Environmental science or geology', 
                'Humanities', 'Machine Learning Engineer', 'Medical or life sciences (biology, chemistry, ...', 
                'Physics or astronomy', 'Research Scientist', 'Researcher', 'Scientist/Researcher', 
                'Social sciences (anthropology, psychology, soc...','Software Developer/Software Engineer']
Developer=['Developer Relations/Advocacy','Engineer','Engineering (non-computer focused)',
           'Programmer','Software Engineer', 'Computer Scientist','Computer science (software engineering, etc.)', 
           'Fine arts or performing arts','Product Manager', 
           'Product/Project Manager','Program/Project Manager','DBA/Database Engineer']
Not_Employeed =['Currently not employed', 'Not employed', 'Student']
Others = ['I never declared a major', 'Other']
```
