---
title: Plotly를 이용해 Pie 그래프 그리기
categories:
- Python Plotly
date: 2021-11-28
tags: plotly, pie graph, pie, subplot
thumbnail: /images/plotlypie/newplot.png
---
  
### **Ref**
---
https://plotly.com/python/subplots/  
https://plotly.com/python/pie-charts/

<br>

### **Pie Graph1**
---

Pie 그래프는 원 그래프로, 데이터 값이 %가 아니여도 자동으로 percent 수치로 그려지게 된다.

![](/images/plotlypie/newplot.png)

5개의 원을 하나의 페이지에 그리기 위해서 subplot을 이용했다

    fig = make_subplots(rows=1, cols=5, specs=[[{'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}]],)
specs는 그래프의 종류를 정의해주는것인데, (Bar와 Scatter 그래프는 따로 정의 안해줘도 된다)
위의 코드는 pie 그래프가 1행 5열로 배치 되어있는것을 의미한다
Pie그래프가 아닌경우에는 'domain' 부분을 바꿔줘야함
'pie' 라고 정의해도 됨
<br>

ex)

    specs=[[{"type": "domain"}, {"type": "domain"}],
           [{"type": "domain"}, {"type": "domain"}]]
위의 코드는 pie그래프 4개가 2행 2열로 그려질때

<br>

- 이전에 bar 그래프를 그렸던거와 마찬가지로, add_trace() 를 이용해서 그렸다
- scalegroup='one'는 그래프 크기를 하나로 지정했다
 각 그래프 별로 위의 코드를 추가해주면 pie의 크기가 달라진다
 값이 클수록 그래프가 크고, 작을수록 작다.

```python
colors = ['#D9946C','#88BFBA', '#CDD9A3']

fig = make_subplots(rows=1, cols=5, specs=[[{'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}, {'type':'domain'}]],)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=Gender_21['type'], values=Gender_21['Gender'], name="2021", scalegroup='one', text=np.array(Gender_21['Gender'].sum()), title="2021", titleposition='bottom center'),
              1, 5)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=Gender_20['type'], values=Gender_20['Gender'], name="2020", scalegroup='one', text=np.array(Gender_20['Gender'].sum()), title="2020", titleposition='bottom center'),
              1, 4)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=Gender_19['type'], values=Gender_19['Gender'], name="2019", scalegroup='one', text=np.array(Gender_19['Gender'].sum()), title="2019", titleposition='bottom center'),
              1, 3)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=Gender_18['type'], values=Gender_18['Gender'], name="2018", scalegroup='one', text=np.array(Gender_18['Gender'].sum()), title="2018", titleposition='bottom center'),
              1, 2)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=Gender_17['type'], values=Gender_17['Gender'], name="2017", scalegroup='one', text=np.array(Gender_17['Gender'].sum()), title="2017", titleposition='bottom center'),
              1, 1)

fig.update_traces(hole=.0, hoverinfo="label+percent+name", 
                  textinfo='label+percent+value')

fig.update_layout(title='<b>World Gender</b>',title_font_size=23,
                  margin = dict(t=300, l=100, r=0, b=200),
                  height=700, width=1000)

fig.update_layout(legend=dict(
    orientation="v",
    yanchor="bottom",
    y=1.3,
    xanchor="right",
    x=1))
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.85,
                                    y=-0.5,
                                    showarrow=False,
                                    text="@green_yhjw",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()

```

<br>

### **Pie Graph2**
---

![](/images/plotlypie/newplot1.png)

dict의 의미는 dictionary이다.

```python
#graph
colors = ['#F2798F','#88BFBA', '#CDD9A3', '#F28705', '#D9946C']
fig = make_subplots(rows=1, cols=2, specs=[[{'type':'pie'}, {'type':'pie'}]], subplot_titles=("World", "East Asia"))
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=degree_wo.index, values=degree_wo['Q4'].to_numpy(), name="World"),
              1, 1)
fig.add_trace(go.Pie(marker=dict(colors=colors), labels=degree_ea.index, values=degree_ea['Q4'].to_numpy(), name="East Asia"),
              1, 2)

fig.update_traces(hole=.0, hoverinfo="label+percent+name")

fig.update_layout(title='<b>World vs East Asia</b>',title_font_size=22,
                  margin = dict(t=200, l=30, r=0, b=200),
                  height=700, width=700)
fig.update_layout(legend=dict(
    orientation="h",
    yanchor="bottom",
    y=1.1,
    xanchor="right",
    x=1.0))
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.8,
                                    y=-0.5,
                                    showarrow=False,
                                    text="@green_yhjw",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```