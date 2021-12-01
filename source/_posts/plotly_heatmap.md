---
title: Plotly를 이용해 Heatmap 그래프 그리기
categories:
- Python Plotly
date: 2021-11-28
tags: plotly, heatmap graph, heatmap, subplot
thumbnail: /images/plotlyheatmap/newplot.png
---

### **heatmap Graph**
---

![](/images/plotlyheatmap/newplot.png)


![](/images/plotlyheatmap/merge.PNG)
데이터 전처리를 통해서 정리된 데이터 셋이다

<br>

x축 y축의 index 값을 각각 설정해주고
z에는 데이터 값을 넣어준다

```python
x1=['South Korea','Taiwan','China','Japan']
y1=merge.sort_values(by=['index'], ascending=True)['index'].tolist()
z1=merge.iloc[:,[2,4,6,8]].to_numpy()

fig = go.Figure(data=go.Heatmap(
                   z=z1,
                   x=x1,
                   y=y1,
                   hoverongaps = True,
                   opacity=1.0, xgap=2.5, ygap=2.5))
fig = ff.create_annotated_heatmap(z1, x = x1, y = y1, colorscale='sunset')
fig.update_layout(height=500, width=600,
                 title_text="<b>East Asia Age (2021)</b>", title_font_size=20,
                 title_x=0.5)
fig.update_traces(hovertemplate='<b>Age</b>: %{y}<br>'+
                                '<b>Country</b>: %{x}<br>'+
                                '<b>Percent</b>: %{z}%')
fig.add_annotation(dict(font=dict(size=14),
                                    x=0.8,
                                    y=-0.2,
                                    showarrow=False,
                                    text="@green_yhjw",
                                    xanchor='left',
                                    xref="paper",
                                    yref="paper"))
fig.show()
```

<br>

### **Heatmap Graph2**
---
Heatmap을 subplot을 이용해서 그렸다
여태까지 해왔던 pie그래프, bar그래프와는 달리 더 복잡했다


    
    x=['2017-year','2018-year','2019-year','2020-year','2021-year']
일단 x값을 '2017-year' 이런식으로 이름을 지었는데,
버그를 발견했다!
'2017'로만 지으면 숫자로 인식해서 계속 에러가 났던것...


![](/images/plotlyheatmap/newplot1.png)
```python
z1=((merge_Wo.iloc[:,[1,2,3,4,5]].to_numpy()/merge_Wo.iloc[:,[1,2,3,4,5]].to_numpy().sum())*100).round(1)
z2=((merge.iloc[:,[1,2,3,4,5]].to_numpy()/merge.iloc[:,[1,2,3,4,5]].to_numpy().sum())*100).round(1)

x=['2017-year','2018-year','2019-year','2020-year','2021-year']
y1=merge_Wo['JOB'].tolist()
y2=merge['JOB'].tolist()


fig1 = ff.create_annotated_heatmap(z1, x = x, y = y1, colorscale='sunset')
fig2 = ff.create_annotated_heatmap(z2, x = x, y = y2, colorscale='sunset')

for annot in fig2['layout']['annotations']:
    annot['xref'] = 'x2'
    
fig = make_subplots(rows=1, cols=2)
fig.add_trace(fig1.data[0], row=1, col=1)
fig.add_trace(fig2.data[0], row=1, col=2)
fig.update_layout(fig1.layout, title='<b>           World vs EastAsia</b>',title_font_size=22,
                  margin = dict(t=200, l=100, r=10, b=200),
                  height=700, width=1150, coloraxis=dict(showscale=True, colorscale='sunset'))
fig.update_traces(hovertemplate='<b>Job</b>: %{y}<br>'+
                                '<b>Year</b>: %{x}<br>'+
                                '<b>Percent</b>: %{z}%')
fig.layout.annotations += fig2.layout.annotations
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

### **Ref**
---
https://plotly.com/python/heatmaps/