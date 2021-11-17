---
title: Kaggle Competition(4)
date: 2021-11-17
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

### **EastAsia 데이터 Grouping**
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
# region 열 생성
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



### **학력**
---
- Q4


```python
df21_degree= df21['Q4'].value_counts().to_frame().reset_index()
df21_degree
```
![](/images/mykaggle4/1.PNG)
<br>

```python
#마지막 행 삭제
df21_degree.drop(df21_degree.index[7])
```
![](/images/mykaggle4/2.PNG)
<br>

```python
df21_degree['Q4'].to_numpy()
```
![](/images/mykaggle4/3.PNG)
<br>

```python
df21_degree['index'].tolist()
```
![](/images/mykaggle4/4.PNG)
<br>


```python
degree = df21_degree['index'].tolist()

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 학력', x=degree, y=df21_degree['Q4'].to_numpy() ,orientation='v')])

fig.update_layout(title_text="<b>21년 World kaggler들의 학력</b>",title_font_size=35)

fig.show()
```
![](/images/mykaggle4/5.PNG)
<br>



```python
#전체 코드
df21_degree= df21['Q4'].value_counts().to_frame().reset_index()
df21_degree

#마지막 행 삭제
df21_degree.drop(df21_degree.index[7])

degree = df21_degree['index'].tolist()

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 학력', x=degree, y=df21_degree['Q4'].to_numpy() ,orientation='v')])

fig.update_layout(title_text="<b>21년 World kaggler들의 학력</b>",title_font_size=35)

fig.show()
```
![](/images/mykaggle4/5.png)
<br>


```python
df21_Ea['Q4'].value_counts().index
```
![](/images/mykaggle4/6.PNG)
<br>


```python
df21_ea_degree = df21_Ea['Q4'].value_counts().to_frame().reset_index()
df21_ea_degree['Q4'].to_numpy()
```
![](/images/mykaggle4/7.PNG)
<br>


```python
degree = df21_Ea['Q4'].value_counts().index

fig = go.Figure(data=[
    go.Bar(name='China', x = degree, 
                           y=df21_Ea['Q4'][df21_Ea['Q3'] =='China'].value_counts()),
    go.Bar(name='Japan', x = degree, 
                             y=df21_Ea['Q4'][df21_Ea['Q3'] =='Japan'].value_counts()),
    go.Bar(name='South Korea', x= degree, 
                            y=df21_Ea['Q4'][df21_Ea['Q3'] =='South Korea'].value_counts()),
    go.Bar(name='Taiwan', x= degree, 
                            y=df21_Ea['Q4'][df21_Ea['Q3'] =='Taiwan'].value_counts())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 학력</b>",title_font_size=35)
fig.show()
```
![](/images/mykaggle4/8.png)
<br>


```python
#전체 코드

df21_ea_degree = df21_Ea['Q4'].value_counts().to_frame().reset_index()

degree = df21_Ea['Q4'].value_counts().index

fig = go.Figure(data=[
    go.Bar(name='China', x = degree, 
                           y=df21_Ea['Q4'][df21_Ea['Q3'] =='China'].value_counts()),
    go.Bar(name='Japan', x = degree, 
                             y=df21_Ea['Q4'][df21_Ea['Q3'] =='Japan'].value_counts()),
    go.Bar(name='South Korea', x= degree, 
                            y=df21_Ea['Q4'][df21_Ea['Q3'] =='South Korea'].value_counts()),
    go.Bar(name='Taiwan', x= degree, 
                            y=df21_Ea['Q4'][df21_Ea['Q3'] =='Taiwan'].value_counts())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 학력</b>",title_font_size=35)
fig.show()
```

### **직업**
---
- Q5


```python
Data_Analyst =['Data Analyst','Data Miner,Information technology','Data Miner',
               'Predictive Modeler','Information technology, networking, or system administration' ]
Data_Engineer =['A business discipline (accounting, economics, finance, etc.)', 'Business Analyst',
                'Statistician', 'Mathematics or statistics', 'Data Scientist', 'Environmental science or geology', 
                'Humanities', 'Machine Learning Engineer', 'Medical or life sciences (biology, chemistry, medicine, etc.)', 
                'Physics or astronomy', 'Research Scientist', 'Researcher', 'Scientist/Researcher', 'Data Engineer',
                'Social sciences (anthropology, psychology, sociology, etc.)','Software Developer/Software Engineer','Humanities (history, literature, philosophy, etc.)']
Developer=['Developer Relations/Advocacy','Engineer','Engineering (non-computer focused)',
           'Programmer','Software Engineer', 'Computer Scientist','Computer science (software engineering, etc.)', 
           'Fine arts or performing arts','Product Manager', 
           'Product/Project Manager','Program/Project Manager','DBA/Database Engineer']
Not_Employeed =['Currently not employed', 'Not employed', 'Student']
Others = ['I never declared a major', 'Other']
```


```python
df21['Q5'].value_counts()
```
![](/images/mykaggle4/9.PNG)
<br>


```python
df21_Ea_DA=df21_Ea['Q3'][df21_Ea['Q5'].isin(Data_Analyst)].value_counts().to_frame().rename(columns = {'Q3':'Data_Analyst'})
df21_Ea_DE=df21_Ea['Q3'][df21_Ea['Q5'].isin(Data_Engineer)].value_counts().to_frame().rename(columns = {'Q3':'Data_Engineer'})
df21_Ea_D=df21_Ea['Q3'][df21_Ea['Q5'].isin(Developer)].value_counts().to_frame().rename(columns = {'Q3':'Developer'})
df21_Ea_NE=df21_Ea['Q3'][df21_Ea['Q5'].isin(Not_Employeed)].value_counts().to_frame().rename(columns = {'Q3':'Not_Employeed'})
df21_Ea_O=df21_Ea['Q3'][df21_Ea['Q5'].isin(Others)].value_counts().to_frame().rename(columns = {'Q3':'Others'})
```


```python
job=(df21_Ea_DA.join(df21_Ea_DE).join(df21_Ea_D).join(df21_Ea_NE).join(df21_Ea_O))
job
```
![](/images/mykaggle4/10.PNG)
<br>


```python
job.iloc[1,0:5].to_numpy()
```
    array([ 46, 292, 254, 211, 118])
<br>

```python
job_ =job.columns
fig = go.Figure(data=[
    go.Bar(name='China', x = job_, 
                           y=job.iloc[0,0:5].to_numpy()),
    go.Bar(name='Japan', x = job_, 
                             y=job.iloc[1,0:5].to_numpy()),
    go.Bar(name='South Korea', x= job_, 
                            y=job.iloc[2,0:5].to_numpy()),
    go.Bar(name='Taiwan', x= job_, 
                            y=job.iloc[3,0:5].to_numpy())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 직업</b>",title_font_size=35)
fig.show()
```
![](/images/mykaggle4/11.png)
<br>


```python
#전체 코드

Data_Analyst =['Data Analyst','Data Miner,Information technology','Data Miner',
               'Predictive Modeler','Information technology, networking, or system administration' ]
Data_Engineer =['A business discipline (accounting, economics, finance, etc.)', 'Business Analyst',
                'Statistician', 'Mathematics or statistics', 'Data Scientist', 'Environmental science or geology', 
                'Humanities', 'Machine Learning Engineer', 'Medical or life sciences (biology, chemistry, medicine, etc.)', 
                'Physics or astronomy', 'Research Scientist', 'Researcher', 'Scientist/Researcher', 'Data Engineer',
                'Social sciences (anthropology, psychology, sociology, etc.)','Software Developer/Software Engineer','Humanities (history, literature, philosophy, etc.)']
Developer=['Developer Relations/Advocacy','Engineer','Engineering (non-computer focused)',
           'Programmer','Software Engineer', 'Computer Scientist','Computer science (software engineering, etc.)', 
           'Fine arts or performing arts','Product Manager', 
           'Product/Project Manager','Program/Project Manager','DBA/Database Engineer']
Not_Employeed =['Currently not employed', 'Not employed', 'Student']
Others = ['I never declared a major', 'Other']

df21_Ea_DA=df21_Ea['Q3'][df21_Ea['Q5'].isin(Data_Analyst)].value_counts().to_frame().rename(columns = {'Q3':'Data_Analyst'})
df21_Ea_DE=df21_Ea['Q3'][df21_Ea['Q5'].isin(Data_Engineer)].value_counts().to_frame().rename(columns = {'Q3':'Data_Engineer'})
df21_Ea_D=df21_Ea['Q3'][df21_Ea['Q5'].isin(Developer)].value_counts().to_frame().rename(columns = {'Q3':'Developer'})
df21_Ea_NE=df21_Ea['Q3'][df21_Ea['Q5'].isin(Not_Employeed)].value_counts().to_frame().rename(columns = {'Q3':'Not_Employeed'})
df21_Ea_O=df21_Ea['Q3'][df21_Ea['Q5'].isin(Others)].value_counts().to_frame().rename(columns = {'Q3':'Others'})

job=(df21_Ea_DA.join(df21_Ea_DE).join(df21_Ea_D).join(df21_Ea_NE).join(df21_Ea_O))

job_ =job.columns
fig = go.Figure(data=[
    go.Bar(name='China', x = job_, 
                           y=job.iloc[0,0:5].to_numpy()),
    go.Bar(name='Japan', x = job_, 
                             y=job.iloc[1,0:5].to_numpy()),
    go.Bar(name='South Korea', x= job_, 
                            y=job.iloc[2,0:5].to_numpy()),
    go.Bar(name='Taiwan', x= job_, 
                            y=job.iloc[3,0:5].to_numpy())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 직업</b>",title_font_size=35)
fig.show()
```

### **경력**
---
- Q6


```python
df21['Q6'].value_counts()
```
![](/images/mykaggle4/12.PNG)
<br>



```python
_3year = ['I have never written code', '< 1 years', '1-3 years']
_5year = ['3-5 years ','5-10 years']
_10year = ['10-20 years','20+ years']

df21_3year = df21['Q6'][df21['Q6'].isin(_3year)]
df21_5year = df21['Q6'][df21['Q6'].isin(_5year)]
df21_10year = df21['Q6'][df21['Q6'].isin(_10year)]

df21_3year.count()
df21_5year.count()
df21_10year.count()
```


```python
df21_3year
```
![](/images/mykaggle4/13.PNG)
<br>


```python
years =['_3year','_5year', '_10year']
values =[df21_3year.count(),
         df21_5year.count(),
        df21_10year.count()]

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 경력', x=years, y=values ,orientation='v'),])

fig.update_layout(title_text="<b>21년 World kaggler들의 경력</b>",title_font_size=35)

fig.show()
```
![](/images/mykaggle4/14.png)
<br>


```python
#전체 코드
_3year = ['I have never written code', '< 1 years', '1-3 years']
_5year = ['3-5 years ','5-10 years']
_10year = ['10-20 years','20+ years']

df21_3year = df21['Q6'][df21['Q6'].isin(_3year)]
df21_5year = df21['Q6'][df21['Q6'].isin(_5year)]
df21_10year = df21['Q6'][df21['Q6'].isin(_10year)]

df21_3year.count()
df21_5year.count()
df21_10year.count()

years =['_3year','_5year', '_10year']
values =[df21_3year.count(),
         df21_5year.count(),
        df21_10year.count()]

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 경력', x=years, y=values ,orientation='v'),])

fig.update_layout(title_text="<b>21년 World kaggler들의 경력</b>",title_font_size=35)

fig.show()
```


```python
df21_Ea['Q3']
```
![](/images/mykaggle4/15.PNG)
<br>


```python
_3year = ['I have never written code', '< 1 years', '1-3 years']
_5year = ['3-5 years ','5-10 years']
_10year = ['10-20 years','20+ years']

df21_Ea_3year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_3year)].value_counts().to_frame().rename(columns = {'Q3':'3year'})
df21_Ea_5year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_5year)].value_counts().to_frame().rename(columns = {'Q3':'5year'})
df21_Ea_10year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_10year)].value_counts().to_frame().rename(columns = {'Q3':'10year'})


```


```python
df21_Ea_3year
```
![](/images/mykaggle4/16.PNG)
<br>


```python
df21_Ea_5year
```
![](/images/mykaggle4/17.PNG)
<br>



```python
df21_Ea_10year
```
![](/images/mykaggle4/18.PNG)
<br>



```python
career=(df21_Ea_3year.join(df21_Ea_5year).join(df21_Ea_10year))
career
```
![](/images/mykaggle4/19.PNG)
<br>


```python
career.iloc[0,0:3] #China
career.iloc[1,0:3] #Japan
career.iloc[2,0:3] #South Korea
career.iloc[3,0:3] #Taiwan
```
![](/images/mykaggle4/20.PNG)
<br>


```python
fig = go.Figure(data=[
    go.Bar(name='China', x = years, 
                           y=career.iloc[0,0:3]),
    go.Bar(name='Japan', x = years, 
                             y=career.iloc[1,0:3]),
    go.Bar(name='South Korea', x= years, 
                            y=career.iloc[2,0:3]),
    go.Bar(name='Taiwan', x= years, 
                            y=career.iloc[3,0:3])
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 경력</b>",title_font_size=35)
fig.show()
```
![](/images/mykaggle4/21.png)
<br>


```python
#최종 합친 코드
_3year = ['I have never written code', '< 1 years', '1-3 years']
_5year = ['3-5 years ','5-10 years']
_10year = ['10-20 years','20+ years']

df21_Ea_3year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_3year)].value_counts().to_frame().rename(columns = {'Q3':'3year'})
df21_Ea_5year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_5year)].value_counts().to_frame().rename(columns = {'Q3':'5year'})
df21_Ea_10year = df21_Ea['Q3'][df21_Ea['Q6'].isin(_10year)].value_counts().to_frame().rename(columns = {'Q3':'10year'})

career=(df21_Ea_3year.join(df21_Ea_5year).join(df21_Ea_10year))
career

career.iloc[0,0:3] #China
career.iloc[1,0:3] #Japan
career.iloc[2,0:3] #South Korea
career.iloc[3,0:3] #Taiwan

fig = go.Figure(data=[
    go.Bar(name='China', x = years, 
                           y=career.iloc[0,0:3]),
    go.Bar(name='Japan', x = years, 
                             y=career.iloc[1,0:3]),
    go.Bar(name='South Korea', x= years, 
                            y=career.iloc[2,0:3]),
    go.Bar(name='Taiwan', x= years, 
                            y=career.iloc[3,0:3])
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 경력</b>",title_font_size=35)
fig.show()
```

### **연봉**
---
- Q25


```python
#마지막 행 삭제해줌
df21_=(df21['Q25'].value_counts().to_frame())
df21_=df21_.drop(df21_.index[26])
df21_
```
![](/images/mykaggle4/22.PNG)
<br>


```python
df21_['Q25'].index
```
![](/images/mykaggle4/23.PNG)
<br>



```python
df21_['Q25'].to_numpy()
```
![](/images/mykaggle4/24.PNG)
<br>



```python
compensation = df21_['Q25'].index
fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 연봉', x=compensation, y=df21_['Q25'].to_numpy() ,orientation='v')])

fig.update_layout(title_text="<b>21년 World kaggler들의 연봉</b>",title_font_size=35)

fig.show()
```
![](/images/mykaggle4/25.png)
<br>


```python
#전체 코드

#마지막 행 삭제해줌
df21_=(df21['Q25'].value_counts().to_frame())
df21_=df21_.drop(df21_.index[26])
df21_

compensation = df21_['Q25'].index
fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들의 연봉', x=compensation, y=df21_['Q25'].to_numpy() ,orientation='v')])

fig.update_layout(title_text="<b>21년 World kaggler들의 연봉</b>",title_font_size=35)

fig.show()
```


```python
#일본 연봉
df21_Ea['Q25'][df21_Ea['Q3'] =='Japan'].value_counts()
```
![](/images/mykaggle4/26.PNG)
<br>


```python
df21_Ea['Q3'].value_counts()
```
![](/images/mykaggle4/27.PNG)
<br>


```python
df21_Ea['Q25'][df21_Ea['Q3'] =='Taiwan'].value_counts()
```
![](/images/mykaggle4/28.PNG)
<br>


```python
compensation = df21_['Q25'].index

fig = go.Figure(data=[
    go.Bar(name='China', x = compensation, 
                           y = df21_Ea['Q25'][df21_Ea['Q3'] =='Japan'].value_counts()),
    
    go.Bar(name='Japan', x = compensation, 
                             y=df21_Ea['Q25'][df21_Ea['Q3'] =='Taiwan'].value_counts()),
    
    go.Bar(name='South Korea', x = compensation, 
                            y=df21_Ea['Q25'][df21_Ea['Q3'] =='South Korea'].value_counts()),
    
    go.Bar(name='Taiwan', x = compensation, 
                            y=df21_Ea['Q25'][df21_Ea['Q3'] =='China'].value_counts())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 연봉</b>",title_font_size=35)
fig.show()
```
![](/images/mykaggle4/29.PNG)
<br>



```python
#전체 코드

compensation = df21_['Q25'].index

fig = go.Figure(data=[
    go.Bar(name='China', x = compensation, 
                           y = df21_Ea['Q25'][df21_Ea['Q3'] =='Japan'].value_counts()),
    
    go.Bar(name='Japan', x = compensation, 
                             y=df21_Ea['Q25'][df21_Ea['Q3'] =='Taiwan'].value_counts()),
    
    go.Bar(name='South Korea', x = compensation, 
                            y=df21_Ea['Q25'][df21_Ea['Q3'] =='South Korea'].value_counts()),
    
    go.Bar(name='Taiwan', x = compensation, 
                            y=df21_Ea['Q25'][df21_Ea['Q3'] =='China'].value_counts())
    ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들의 연봉</b>",title_font_size=35)
fig.show()
```

### **언어**
---
- Q7


```python
df21_p = df21['Q7_Part_1'].value_counts().to_frame() #python
df21_r = df21['Q7_Part_2'].value_counts().to_frame() #r
df21_s = df21['Q7_Part_3'].value_counts().to_frame() #sql
df21_c = df21['Q7_Part_4'].value_counts().to_frame() #c
df21_cc = df21['Q7_Part_5'].value_counts().to_frame() #c++
df21_j = df21['Q7_Part_6'].value_counts().to_frame() #java
df21_js = df21['Q7_Part_7'].value_counts().to_frame() #javascript
df21_ju = df21['Q7_Part_8'].value_counts().to_frame() #julia
df21_sw = df21['Q7_Part_9'].value_counts().to_frame() #swift
df21_b = df21['Q7_Part_10'].value_counts().to_frame() #bash
df21_ma = df21['Q7_Part_11'].value_counts().to_frame() #matlab
df21_n = df21['Q7_Part_12'].value_counts().to_frame() #none
```


```python
df21_p.iloc[0,0]
```


```python
languages = ['Python','R','SQL','C','C++','Java','Javascript','Julia','Swift','Bash','MATLAB','None']

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들이 사용하는 언어', x = languages, 
                                                     y = [df21_p.iloc[0,0],
                                                          df21_r.iloc[0,0],
                                                          df21_s.iloc[0,0],
                                                          df21_c.iloc[0,0],
                                                          df21_cc.iloc[0,0],
                                                          df21_j.iloc[0,0],
                                                          df21_js.iloc[0,0],
                                                          df21_ju.iloc[0,0],
                                                          df21_sw.iloc[0,0],
                                                          df21_b.iloc[0,0],
                                                          df21_ma.iloc[0,0],
                                                          df21_n.iloc[0,0]],orientation='v')
                    
                    ])

fig.update_layout(title_text="<b>21년 World kaggler들이 사용하는 언어</b>",title_font_size=35)

fig.show()
```
![](/images/mykaggle4/30.PNG)
<br>


```python
#코드 전체

df21_p = df21['Q7_Part_1'].value_counts().to_frame() #python
df21_r = df21['Q7_Part_2'].value_counts().to_frame() #r
df21_s = df21['Q7_Part_3'].value_counts().to_frame() #sql
df21_c = df21['Q7_Part_4'].value_counts().to_frame() #c
df21_cc = df21['Q7_Part_5'].value_counts().to_frame() #c++
df21_j = df21['Q7_Part_6'].value_counts().to_frame() #java
df21_js = df21['Q7_Part_7'].value_counts().to_frame() #javascript
df21_ju = df21['Q7_Part_8'].value_counts().to_frame() #julia
df21_sw = df21['Q7_Part_9'].value_counts().to_frame() #swift
df21_b = df21['Q7_Part_10'].value_counts().to_frame() #bash
df21_ma = df21['Q7_Part_11'].value_counts().to_frame() #matlab
df21_n = df21['Q7_Part_12'].value_counts().to_frame() #none

languages = ['Python','R','SQL','C','C++','Java','Javascript','Julia','Swift','Bash','MATLAB','None']

fig = go.Figure(data=[
    go.Bar(name='21년 World kaggler들이 사용하는 언어', x = languages, 
                                                     y = [df21_p.iloc[0,0],
                                                          df21_r.iloc[0,0],
                                                          df21_s.iloc[0,0],
                                                          df21_c.iloc[0,0],
                                                          df21_cc.iloc[0,0],
                                                          df21_j.iloc[0,0],
                                                          df21_js.iloc[0,0],
                                                          df21_ju.iloc[0,0],
                                                          df21_sw.iloc[0,0],
                                                          df21_b.iloc[0,0],
                                                          df21_ma.iloc[0,0],
                                                          df21_n.iloc[0,0]],orientation='v')
                    
                    ])

fig.update_layout(title_text="<b>21년 World kaggler들이 사용하는 언어</b>",title_font_size=35)

fig.show()
```


```python
df21_lan_ch_p=df21_Ea['Q7_Part_1'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_1':'cnt'})
df21_lan_ch_r=df21_Ea['Q7_Part_2'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_2':'cnt'})
df21_lan_ch_s=df21_Ea['Q7_Part_3'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_3':'cnt'})
df21_lan_ch_c=df21_Ea['Q7_Part_4'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_4':'cnt'})
df21_lan_ch_cc=df21_Ea['Q7_Part_5'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_5':'cnt'})
df21_lan_ch_j=df21_Ea['Q7_Part_6'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_6':'cnt'})
df21_lan_ch_js=df21_Ea['Q7_Part_7'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_7':'cnt'})
df21_lan_ch_ju=df21_Ea['Q7_Part_8'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_8':'cnt'})
df21_lan_ch_sw=df21_Ea['Q7_Part_9'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_9':'cnt'})
df21_lan_ch_b=df21_Ea['Q7_Part_10'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_10':'cnt'})
df21_lan_ch_ma=df21_Ea['Q7_Part_11'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_11':'cnt'})
df21_lan_ch_n=df21_Ea['Q7_Part_12'][df21_Ea['Q3']=='China'].value_counts().to_frame().rename(columns = {'Q7_Part_12':'cnt'})
ch_lan = pd.concat([df21_lan_ch_p,df21_lan_ch_r,df21_lan_ch_s,df21_lan_ch_c,df21_lan_ch_cc,df21_lan_ch_j,df21_lan_ch_js,df21_lan_ch_ju,df21_lan_ch_sw,df21_lan_ch_b,df21_lan_ch_ma,df21_lan_ch_n])


df21_lan_jp_p=df21_Ea['Q7_Part_1'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_1':'cnt'})
df21_lan_jp_r=df21_Ea['Q7_Part_2'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_2':'cnt'})
df21_lan_jp_s=df21_Ea['Q7_Part_3'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_3':'cnt'})
df21_lan_jp_c=df21_Ea['Q7_Part_4'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_4':'cnt'})
df21_lan_jp_cc=df21_Ea['Q7_Part_5'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_5':'cnt'})
df21_lan_jp_j=df21_Ea['Q7_Part_6'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_6':'cnt'})
df21_lan_jp_js=df21_Ea['Q7_Part_7'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_7':'cnt'})
df21_lan_jp_ju=df21_Ea['Q7_Part_8'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_8':'cnt'})
df21_lan_jp_sw=df21_Ea['Q7_Part_9'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_9':'cnt'})
df21_lan_jp_b=df21_Ea['Q7_Part_10'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_10':'cnt'})
df21_lan_jp_ma=df21_Ea['Q7_Part_11'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_11':'cnt'})
df21_lan_jp_n=df21_Ea['Q7_Part_12'][df21_Ea['Q3']=='Japan'].value_counts().to_frame().rename(columns = {'Q7_Part_12':'cnt'})
jp_lan = pd.concat([df21_lan_jp_p,df21_lan_jp_r,df21_lan_jp_s,df21_lan_jp_c,df21_lan_jp_cc,df21_lan_jp_j,df21_lan_jp_js,df21_lan_jp_ju,df21_lan_jp_sw,df21_lan_jp_b,df21_lan_jp_ma,df21_lan_jp_n])


df21_lan_tw_p=df21_Ea['Q7_Part_1'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_1':'cnt'})
df21_lan_tw_r=df21_Ea['Q7_Part_2'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_2':'cnt'})
df21_lan_tw_s=df21_Ea['Q7_Part_3'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_3':'cnt'})
df21_lan_tw_c=df21_Ea['Q7_Part_4'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_4':'cnt'})
df21_lan_tw_cc=df21_Ea['Q7_Part_5'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_5':'cnt'})
df21_lan_tw_j=df21_Ea['Q7_Part_6'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_6':'cnt'})
df21_lan_tw_js=df21_Ea['Q7_Part_7'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_7':'cnt'})
df21_lan_tw_ju=df21_Ea['Q7_Part_8'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_8':'cnt'})
df21_lan_tw_sw=df21_Ea['Q7_Part_9'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_9':'cnt'})
df21_lan_tw_b=df21_Ea['Q7_Part_10'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_10':'cnt'})
df21_lan_tw_ma=df21_Ea['Q7_Part_11'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_11':'cnt'})
df21_lan_tw_n=df21_Ea['Q7_Part_12'][df21_Ea['Q3']=='Taiwan'].value_counts().to_frame().rename(columns = {'Q7_Part_12':'cnt'})
tw_lan = pd.concat([df21_lan_tw_p,df21_lan_tw_r,df21_lan_tw_s,df21_lan_tw_c,df21_lan_tw_cc,df21_lan_tw_j,df21_lan_tw_js,df21_lan_tw_ju,df21_lan_tw_sw,df21_lan_tw_b,df21_lan_tw_ma,df21_lan_tw_n])


df21_lan_ko_p=df21_Ea['Q7_Part_1'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_1':'cnt'})
df21_lan_ko_r=df21_Ea['Q7_Part_2'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_2':'cnt'})
df21_lan_ko_s=df21_Ea['Q7_Part_3'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_3':'cnt'})
df21_lan_ko_c=df21_Ea['Q7_Part_4'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_4':'cnt'})
df21_lan_ko_cc=df21_Ea['Q7_Part_5'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_5':'cnt'})
df21_lan_ko_j=df21_Ea['Q7_Part_6'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_6':'cnt'})
df21_lan_ko_js=df21_Ea['Q7_Part_7'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_7':'cnt'})
df21_lan_ko_ju=df21_Ea['Q7_Part_8'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_8':'cnt'})
df21_lan_ko_sw=df21_Ea['Q7_Part_9'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_9':'cnt'})
df21_lan_ko_b=df21_Ea['Q7_Part_10'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_10':'cnt'})
df21_lan_ko_ma=df21_Ea['Q7_Part_11'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_11':'cnt'})
df21_lan_ko_n=df21_Ea['Q7_Part_12'][df21_Ea['Q3']=='South Korea'].value_counts().to_frame().rename(columns = {'Q7_Part_12':'cnt'})
ko_lan = pd.concat([df21_lan_ko_p,df21_lan_ko_r,df21_lan_ko_s,df21_lan_ko_c,df21_lan_ko_cc,df21_lan_ko_j,df21_lan_ko_js,df21_lan_ko_ju,df21_lan_ko_sw,df21_lan_ko_b,df21_lan_ko_ma,df21_lan_ko_n])
```


```python
ch_lan['cnt'].to_list()
```


```python
languages = ['Python','R','SQL','C','C++','Java','Javascript','Julia','Swift','Bash','MATLAB','None']

fig = go.Figure(data=[
    go.Bar(name='China', x = languages, 
                         y = ch_lan['cnt'].tolist()),
    
    go.Bar(name='Japan', x = languages, 
                             y=jp_lan['cnt'].tolist()),
    
    go.Bar(name='South Korea', x = languages, 
                            y=ko_lan['cnt'].tolist()),
    
    go.Bar(name='Taiwan', x = languages, 
                            y=tw_lan['cnt'].tolist())
          ])

fig.update_layout(title_text="<b>21년 EastAisa kaggler들이 사용하는 언어</b>",title_font_size=35)
fig.show()
```
![](/images/mykaggle4/31.png)
<br>
<br>
<br>
<br>
