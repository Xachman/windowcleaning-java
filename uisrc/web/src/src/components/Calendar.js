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
            callback(this.arrangeEvents(data.data))
        })
    }

    arrangeEvents(data) {
        let result = [];
        for(var i = 0; i < data.length; i++ ){
            let item = data[i];
            result.push({
                title: item.servicedBy,
                start: new Date(item.serviceDate)
            })
        }
        return result
    }



}