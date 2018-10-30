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
    constructor(props) {
        super(props)
    }
    componentDidMount() {
        this.cal = $(this.refs.calendar).fullCalendar()
        let date = new Date();
        this.getEvents(new Date(date.getFullYear(), date.getMonth(), 1),
            new Date(date.getFullYear(), date.getMonth()+1, 0) 
        )
    }
    render() {
      return <div ref="calendar"></div>;
    }

    getEvents(startDate, endDate) {

        this.dataProvider(GET_LIST, 'jobs', {
            between: {
                serviceDate: [startDate.getTime(),endDate.getTime()]
            }
        }).then((data) => {
            console.log(data)
            console.log(this.cal)
        })
    }

    arrangeEvents(data) {
        result = [];
        for(var i = 0; i < data.length; i++ ){
            item = data[i];
            result.push({
                
            })
        }
    }



}