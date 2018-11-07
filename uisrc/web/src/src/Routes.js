import React from 'react';
import { Route } from 'react-router-dom';
import CalendarPage from './pages/CalendarPage';
import { CreateJob } from './resource/jobs';

export default [
    <Route exact path="/calendar" component={CalendarPage} />,
    <Route exact path="/jobs/customer/create/:customer_id" render={(routeProps) => {
        return<CreateJob resource="jobs" {...routeProps} />
    }} />
]