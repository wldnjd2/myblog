---
title: Plotly를 이용해 다양한 bar 그래프 그리기
categories:
  - Python Plotly
date: 2021-11-28
tags: plotly, bar graph, bar
thumbnail: /images/plotlybar/newplot.png
---
  
### **Bar Graph 1**
---

![](/images/plotlybar/newplot.png)


위의 그래프 경우는 bar 그래프만 있는게 아니고 scatter 그래프도 같이 있다.
따라서 그래프 2개를 하나의 페이지에 그린다고 볼 수 있다.

두개의 그래프를 동시에 그릴때 add_trace를 이용해서 하나씩 그린다고 보면 된다.
fig.add_trace(go.Bar)      --> 그래프-1
fig.add_trace(go.Scatter)  --> 그래프-2

<br>

x축은 공통사항이므로 같은 값을 넣어주었다.
y는 값이 다르므로 각각의 값을 넣어주었다

아래는 왼쪽 오른쪽 각각의 축을 나타냈고, 각각의 그래프에 지정해주었다.
yaxis = "y1"   
yaxis = "y2"

나머지 fig.update_trace, fig.update_layout, fig.add_annotation는 그래프를 꾸며주는 역할을 한다.
<br>



```python
fig = go.Figure()

fig.add_trace(go.Bar(x=years, y=[len(df17_Ea),len(df18_Ea), len(df19_Ea),len(df20_Ea),len(df21_Ea)],
                marker_color='#F2D64B',
                yaxis = "y1",
                name='East Asia',
                text= percent,
                texttemplate='%{text}  %', 
                textposition='outside',
                hovertemplate='<b>KaggleUser</b>: %{x}<br>'+ '<b>Count</b>: %{y}'))

fig.add_trace(go.Scatter(name = "World",
           x=years, 
           y=[len(df17), len(df18), len(df19), len(df20), len(df21)],
           marker_color='#979DA6',
           mode = 'lines+markers', # please check option here
           yaxis = "y2"))

fig.update_traces(hovertemplate='<b>Count</b>: %{y}<br><extra></extra>'+
                                '<b>Year</b>: %{x}<br>')

fig.update_layout(yaxis  = dict(title = "Kaggle User in East Asia",showgrid = False, range=[0, len(df21_Ea)*1.2]),
                  yaxis2 = dict(title = "Kaggle User in World", overlaying = "y1", side = "right", 
                  showgrid = False, 
                  zeroline = False, range=[0, len(df21)*1.2]))

fig.update_layout(title='<b>Kaggle Users</b>',title_font_size=20,
                  margin = dict(t=200, l=100, r=50, b=200),
                  height=700, width=700)

fig.update_layout(legend=dict(
    orientation="h",
    yanchor="bottom",
    y=1.1,
    xanchor="right",
    x=1))
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.9,
                                    y=-0.25,
                                    showarrow=False,
                                    text="@green_yhjw",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()

```
<br>


### **Bar Graph2**
---

![](/images/plotlybar/newplot1.png)

하나의 그래프에 여러개의 데이터를 표현할때
data=[]는 왜 쓸까? 안쓰면 에러남 의문

똑같이 go.bar로 x축 y축에 각각의 값을 넣어준다.
이때 내가 그리고자 하는 그래프가 총 5개이니까 하나씩 설정해서 그린다고 생각하면 된다.

다른 방법으로는 add_trace 이용해서 그려도 된다
```python
fig = go.Figure(data=[
    go.Bar(name='2017', x=df5years['Country'], y=df5years['17'], marker_color='#F2798F',text=df5years['17'].tolist(), textposition='outside'),
    go.Bar(name='2018', x=df5years['Country'], y=df5years['18'], marker_color='#88BFBA',text=df5years['18'].fillna(0).astype(int).tolist(), textposition='outside',),
    go.Bar(name='2019', x=df5years['Country'], y=df5years['19'], marker_color='#CDD9A3',text=df5years['19'].tolist(), textposition='outside'),
    go.Bar(name='2020', x=df5years['Country'], y=df5years['20'], marker_color='#F28705',text=df5years['20'].tolist(), textposition='outside',),
    go.Bar(name='2021', x=df5years['Country'], y=df5years['21'], marker_color='#D9946C',text=df5years['21'].tolist(), textposition='outside')])

fig.update_layout(title='<b>Kaggle User in East Asia</b>',title_font_size=23,
                  margin = dict(t=200, l=100, r=10, b=200),
                  height=600, width=700)
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.update_traces(hovertemplate='<b>Count</b>: %{y}')
fig.update_layout(legend=dict(
    orientation="v",
    yanchor="bottom",
    y=1.15,
    xanchor="right",
    x=1))
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
<br>

### **Bar Graph3**
---

![](/images/plotlybar/newplot2.png)

위의 그래프는 수평으로 그린 그래프이다.
orientation='h' -> 수평으로 그래프를 그린다
orientation='v' -> 수직으로 그래프를 그린다

base = 0는 값의 기준점이 0 이라는 의미

y값은 같지만, x값은 0을 기준으로 -와 +로 나누어지기 때문에
한쪽값에 -을 붙여야 한다
나는 x=World, x=-East_Asia 각각 이렇게 설정해주었다

```python
fig = go.Figure(data=[
    go.Bar(y=y, x=World, orientation='h', name="World", base=0, hovertemplate='<b>World</b>: %{x}%<br>', marker_color='#979DA6', text=World, textposition='outside'),
    go.Bar(y=y, x=-East_Asia, orientation='h', name="East Asia", base=0, hovertemplate='<b>East Asia</b>: %{x}%<br>', marker_color='#F2D64B', text=East_Asia, textposition='outside')])

fig.update_layout(barmode='stack')
fig.update_layout(title='<b>World vs EastAsia</b>',title_font_size=22,
                  margin = dict(t=200, l=100, r=50, b=200),
                  height=700, width=750,
                  xaxis_title=None,
                  yaxis_title=None)
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.update_layout(legend=dict(
    orientation="h",
    yanchor="bottom",
    y=1.1,
    xanchor="right",
    x=1))
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
<br>


### **Bar Graph4**
---

![](/images/plotlybar/newplot3.png)

subplot을 이용해 그려준것이다
subplot이란 하나의 페이지에 여러개의 그래프를 합쳐놓은 것이다
 
    fig = make_subplots(rows = 1, cols = 4, 
                    shared_yaxes=True, 
                    vertical_spacing = 0.05)
 
위에처럼 행 열을 지정해줘야하는데
내가 그린 그래프는 1행 4열 그래프이다

add_trace를 이용해 그래프를 그려주면 된다

subplot을 사용하지 않고 그냥 add_trace만 이용해도 되는데,
그럴경우에는 아래 사진처럼 그래프 x축이 분리되지 않고 이어진다.

```python
fig = make_subplots(rows = 1, cols = 4, 
                    shared_yaxes=True, 
                    vertical_spacing = 0.05)

fig.add_trace(go.Bar(x = dfCh_Edu21['Dgree'], 
                     y = dfCh_Edu21['%'], 
                     text = dfCh_Edu21['%'].astype(str) + "%", 
                     textposition='outside',
                     name='China',
                     marker_color='#88BFBA'), 
                     row = 1, col = 1)

fig.add_trace(go.Bar(x = dfJp_Edu21['Dgree'], 
                     y = dfJp_Edu21['%'], 
                     text = dfJp_Edu21['%'].astype(str) + "%", 
                     textposition='outside',
                     name='Japan',
                     marker_color='#CDD9A3'), 
                      row = 1, col = 2)

fig.add_trace(go.Bar(x = dfKo_Edu21['Dgree'], 
                     y = dfKo_Edu21['%'], 
                     text = dfKo_Edu21['%'].astype(str) + "%", 
                     textposition='outside',
                     name='South Korea',
                     marker_color='#F28705'), 
                      row = 1, col = 3)

fig.add_trace(go.Bar(x = dfTw_Edu21['Dgree'], 
                     y = dfTw_Edu21['%'], 
                     text = dfTw_Edu21['%'].astype(str) + "%", 
                     textposition='outside',
                     name='Taiwan',
                     marker_color='#D9946C'), 
                     row = 1, col = 4)

fig.update_layout(showlegend=True,title='<b>Degree in East Asia</b>',title_font_size=22,
                  margin = dict(t=200, l=100, r=50, b=200),
                  height=700, width=700)
fig.update_traces(hovertemplate='<b>Percent</b>: %{y}%<br>'+
                                '<b>Degree</b>: %{x}<br>')
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.update_layout(legend=dict(
    orientation="h",
    yanchor="bottom",
    y=1.1,
    xanchor="right",
    x=1))
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
<br>

### **Bar Graph5**
---

![](/images/plotlybar/newplot4.png)


    fig.update_layout(barmode='stack')
위의 한 줄을 추가해주면 그래프가 Stack으로 쌓여서 그려진다.

![](/images/plotlybar/newplot5.png)
위의 한 줄을 뺏을때는 이렇게 따로따로 분리되어서 그려진다.

```python
fig = go.Figure()
fig.add_trace(go.Bar(x = df21_Ea_DS_salary.index, 
                     y = df21_Ea_DS_salary['Data Scientist'], 
                     name = "Data Scientist", 
                     text = df21_Ea_DS_salary['Data Scientist'].astype(str) + "%", 
                     textposition='auto', marker_color='#F2798F'))

fig.add_trace(go.Bar(x = df21_Ea_DS_salary.index, 
                     y = df21_Ea_DS_salary['Machine Learning Engineer'], 
                     name = "Machine Learning Engineer", 
                     text = df21_Ea_DS_salary['Machine Learning Engineer'].astype(str) + "%", 
                     textposition='auto', marker_color='#CDD9A3'))

fig.add_trace(go.Bar(x = df21_Ea_DS_salary.index, 
                     y = df21_Ea_DS_salary['Research Scientist'], 
                     name = "Research Scientist", 
                     text = df21_Ea_DS_salary['Research Scientist'].astype(str) + "%", 
                     textposition='auto', marker_color='#88BFBA'))

fig.update_layout(barmode='stack',
                 showlegend=True,
                 height=600, width=700,
                 title_text="<b>Data Scientist's Salary in East Asia</b>",
                 title_x=0.5,
                 title_font_size=20, 
                  margin=dict(l=100, r=100, t=100, b=100))
fig.update_traces(hovertemplate='<b>Percent</b>: %{y}%<br>'+
                                '<b>Salary</b>: %{x}$<br>')
fig.update_xaxes(showgrid=False)
fig.update_yaxes(showgrid=False)
fig.update_layout(legend=dict(
    orientation="v",
    yanchor="bottom",
    y=0.8,
    xanchor="right",
    x=1.2))

fig.show()
```
<br>

### **느낀점**
---
처음 그래프를 그리면서 bar그래프에도 다양한 종류가 있는데 
내가 원하는대로 그리기가 쉽지 않았다
하지만 사실 복잡한게 아니기에 조금만 공부하면 쉽게 그릴수 있다
모를때는 사실 plotly 원문 사이트를 들어가서 그려보는게 도움이 됬다
위의 그래프를 참고해서 그리면 된다
누군가에게 도움이 되었기를...

<br>
<br>
<br>
<br>