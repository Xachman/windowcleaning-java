import React from 'react';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import { Title } from 'react-admin';
import { Calendar } from '../components/Calendar';

const CalendarPage = () => (
    <Card>
        <Title title="Calendar" />
        <CardContent>
            <Calendar />
        </CardContent>
    </Card>
);

export default CalendarPage;