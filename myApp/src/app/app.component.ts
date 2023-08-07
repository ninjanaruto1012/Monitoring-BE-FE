import { Component, OnInit, AfterViewInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { Injectable } from '@angular/core';
import * as Highcharts from 'highcharts';
import HighchartsMore from 'highcharts/highcharts-more';
import { DataServiceService } from './data-service.service';

@Injectable({
  providedIn: 'root'
})

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'Monitoring Actual and Predicted CPU';
  msgList: any[] = [];
  msgList2: any[] = [];
  private serverUrl = 'http://localhost:8080/websocket';
  private stompClient: any;
  chart: any;
  chart2: any;
  chart3: any;
  chart4: any;


  constructor(private dataService: DataServiceService){
    this.initializeWebSocketConnection();
  }

  private getRandomNumber(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min + 1) + min)
  }

  removeAction() {

    this.dataService.removeData();
  }

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    const epochNow = (new Date).getTime();
    const data: any[] = [];
    const data2: any[] = [];
    const data3: any[] = [];
    const data4: any[] = [];
    const data5: any[] = [];
    const data6: any[] = [];
    const data7: any[] = [];
    const data8: any[] = [];

    for (let i = 60; i >= 0; i--) {
      data.push([epochNow-i*5000,0]);
      data2.push([epochNow-i*5000,0]);
      data3.push([epochNow-i*5000,0]);
      data4.push([epochNow-i*5000,0]);
      data5.push([epochNow-i*5000,0]);
      data6.push([epochNow-i*5000,0]);
      data7.push([epochNow-i*5000,0]);
      data8.push([epochNow-i*5000,0]);
    } 
    
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function() {
      that.stompClient.subscribe("/monitor/abc", (message: any) => {
          let newValue = JSON.parse(message.body);
          let valueInt: any = Number(newValue.CpuRealVm1);
          let cpuPredictedVm1Int: any = Number(newValue.CpuPredictedVm1);
          let cpuActualVm2Int: any = Number(newValue.CpuRealVm2);
          let cpuPredictedVm2Int: any = Number(newValue.CpuPredictedVm2);
          let cpuActualVm3Int: any = Number(newValue.CpuRealVm3);
          let cpuPredictedVm3Int: any = Number(newValue.CpuPredictedVm3);
          let cpuActualVm4Int: any = Number(newValue.CpuRealVm4);
          let cpuPredictedVm4Int: any = Number(newValue.CpuPredictedVm4);
          let timeStr: any = Number(newValue.Epoch);

          that.chart = Highcharts.chart('chart-line', {
            chart: {
              type: 'spline',
              zoomType: 'xy'
            },
            scrollbar: {
              enabled: true
            },
            title: {
              text: 'CPU: Actual and Predicted',
            },
            credits: {
              enabled: false,
            },
            legend: {
              enabled: true,
            },
            yAxis: [{
              title: {
                text: 'Percentage of Usage (%)'
              },
              min:0,
              max:100,
              endOnTick: false,
              startOnTick: false,
              opposite: false
            }
          ],
            xAxis: {
              title: {
                text: 'Date and Time UTC (YYYY-MM-DD HH:mm:SS)'
              },
              type: 'datetime',
              ordinal: false,
              zoomEnabled: true,
              labels: {
                format: '{value:%Y-%b-%e %H:%M:%S}'
              }

            },
            tooltip: {
              headerFormat: `<div>Date: {point.key}</div>`,
              pointFormat: `<div>{series.name}: {point.y}</div>`,
              shared: true,
              useHTML: true,
            },
            navigator: {
              adaptToUpdatedData: true,
              series: [{
                  data: data
              },
              {
                data:data2
              }]
            },
            
            series: [{
              name: "Actual",
              data: data,
              animation: false
            },
            {
              name: "Predicted",
              data:data2,
              animation: false
            }
          ],
          } as any);

          that.chart2 = Highcharts.chart('chart-line-2', {
            chart: {
              type: 'spline',
              zoomType: 'xy'
            },
            scrollbar: {
              enabled: true
            },
            title: {
              text: 'CPU 2: Actual and Predicted',
            },
            credits: {
              enabled: false,
            },
            legend: {
              enabled: true,
            },
            yAxis: [{
              title: {
                text: 'Percentage of Usage (%)'
              },
              min:0,
              max:100,
              endOnTick: false,
              startOnTick: false,
              opposite: false
            }
          ],
            xAxis: {
              title: {
                text: 'Date and Time UTC (YYYY-MM-DD HH:mm:SS)'
              },
              type: 'datetime',
              ordinal: false,
              zoomEnabled: true,
              labels: {
                format: '{value:%Y-%b-%e %H:%M:%S}'
              }

            },
            tooltip: {
              headerFormat: `<div>Date: {point.key}</div>`,
              pointFormat: `<div>{series.name}: {point.y}</div>`,
              shared: true,
              useHTML: true,
            },
            navigator: {
              adaptToUpdatedData: true,
              series: [{
                  data: data3
              },
              {
                data:data4
              }]
            },
            
            series: [{
              name: "Actual",
              data: data3,
              animation: false
            },
            {
              name: "Predicted",
              data:data4,
              animation: false
            }
          ],
          } as any);

          that.chart3 = Highcharts.chart('chart-line-3', {
            chart: {
              type: 'spline',
              zoomType: 'xy'
            },
            scrollbar: {
              enabled: true
            },
            title: {
              text: 'CPU 3: Actual and Predicted',
            },
            credits: {
              enabled: false,
            },
            legend: {
              enabled: true,
            },
            yAxis: [{
              title: {
                text: 'Percentage of Usage (%)'
              },
              min:0,
              max:100,
              endOnTick: false,
              startOnTick: false,
              opposite: false
            }
          ],
            xAxis: {
              title: {
                text: 'Date and Time UTC (YYYY-MM-DD HH:mm:SS)'
              },
              type: 'datetime',
              ordinal: false,
              zoomEnabled: true,
              labels: {
                format: '{value:%Y-%b-%e %H:%M:%S}'
              }

            },
            tooltip: {
              headerFormat: `<div>Date: {point.key}</div>`,
              pointFormat: `<div>{series.name}: {point.y}</div>`,
              shared: true,
              useHTML: true,
            },
            navigator: {
              adaptToUpdatedData: true,
              series: [{
                  data: data5
              },
              {
                data:data6
              }]
            },
            
            series: [{
              name: "Actual",
              data: data5,
              animation: false
            },
            {
              name: "Predicted",
              data:data6,
              animation: false
            }
          ],
          } as any);

          that.chart4 = Highcharts.chart('chart-line-4', {
            chart: {
              type: 'spline',
              zoomType: 'xy'
            },
            scrollbar: {
              enabled: true
            },
            title: {
              text: 'CPU 4: Actual and Predicted',
            },
            credits: {
              enabled: false,
            },
            legend: {
              enabled: true,
            },
            yAxis: [{
              title: {
                text: 'Percentage of Usage (%)'
              },
              min:0,
              max:100,
              endOnTick: false,
              startOnTick: false,
              opposite: false
            }
          ],
            xAxis: {
              title: {
                text: 'Date and Time UTC (YYYY-MM-DD HH:mm:SS)'
              },
              type: 'datetime',
              ordinal: false,
              zoomEnabled: true,
              labels: {
                format: '{value:%Y-%b-%e %H:%M:%S}'
              }

            },
            tooltip: {
              headerFormat: `<div>Date: {point.key}</div>`,
              pointFormat: `<div>{series.name}: {point.y}</div>`,
              shared: true,
              useHTML: true,
            },
            navigator: {
              adaptToUpdatedData: true,
              series: [{
                  data: data7
              },
              {
                data:data7
              }]
            },
            
            series: [{
              name: "Actual",
              data: data7,
              animation: false
            },
            {
              name: "Predicted",
              data:data8,
              animation: false
            }
          ],
          } as any);
          

          data.push([timeStr,valueInt]);
          data2.push([timeStr,cpuPredictedVm1Int]);
          data3.push([timeStr,cpuActualVm2Int]);
          data4.push([timeStr,cpuPredictedVm2Int]);
          data5.push([timeStr,cpuActualVm3Int]);
          data6.push([timeStr,cpuPredictedVm3Int]);
          data7.push([timeStr,cpuActualVm4Int]);
          data8.push([timeStr,cpuPredictedVm4Int]);
          that.chart.series[0].setData(data);
          that.chart.series[1].setData(data2);
          that.chart2.series[0].setData(data3);
          that.chart2.series[1].setData(data4);
          that.chart3.series[0].setData(data5);
          that.chart3.series[1].setData(data6);
          that.chart4.series[0].setData(data7);
          that.chart4.series[1].setData(data8);

          that.chart.series[0].data[0].remove();
          that.chart.series[1].data[0].remove();
          that.chart2.series[0].data[0].remove();
          that.chart2.series[1].data[0].remove();
          that.chart3.series[0].data[0].remove();
          that.chart3.series[1].data[0].remove();
          that.chart4.series[0].data[0].remove();
          that.chart4.series[1].data[0].remove();
      });
      
    });

    
  }


}
