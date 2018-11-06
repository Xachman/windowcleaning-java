import React from 'react';
import { Route } from 'react-router-dom';
import CalendarPage from './pages/CalendarPage';
import { CreateEditJob } from './resource/jobs';

export default [
    <Route exact path="/calendar" component={CalendarPage} />,
    <Route exact path="/jobs/customer/create/:id" render={(routeProps) => {
        return<CreateEditJob resource="jobs" {...routeProps} />
    }} />
]