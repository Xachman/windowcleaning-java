import React from 'react';
import { Config } from '../Config';
import { GET_LIST } from 'react-admin';
import $ from 'jquery'; 
import 'moment/min/moment.min.js';

import 'fullcalendar/dist/fullcalendar.css';
import 'fullcalendar/dist/fullcalendar.js';

export class Calendar extends React.Component {
    dataProvider = Config.getDataProvider();
    cal;
    items = [];
    componentDidMount() {
        this.cal = $(this.refs.calendar).fullCalendar({
            events: this.getEvents.bind(this),
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay,listWeek'
              }
        })
    }
    render() {
      return <div ref="calendar"></div>;
    }

    getEvents(startDate, endDate, timezone, callback) {

        this.dataProvider(GET_LIST, 'orders', {
            between: {
                serviceDate: [startDate.unix()*1000,endDate.unix()*1000]
            }
        }).then((data) => {
            console.log(data)
            this.items = []
            data.data.map((item) => {
                this.dataProvider(GET_LIST, 'jobs', {
                    filter: {"id": [item.job.id]}
                }).then((job) => {
                    this.dataProvider(GET_LIST, 'customers', {
                        filter: {"id": [job.data[0].customer.id]}
                    }).then((customer) => {
                        item["job"] = job.data[0]
                        item["customer"] = customer.data[0]
                        this.items.push(item)
                        callback(this.arrangeEvents(this.items))
                    })
                })

            })
        })
    }

    arrangeEvents(data) {
        let result = [];
        for(var i = 0; i < data.length; i++ ){
            let item = data[i];
            console.log(item)
            result.push({
                start: new Date(item.serviceDate),
                title: item.customer.name
            })
        }
        return result
    }



}