import React from 'react';
import { List, Edit, DisabledInput, EditButton, Datagrid, DateInput, NumberInput, SelectInput, TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, TabbedShowLayout, Tab } from 'react-admin';
import Grid from '@material-ui/core/Grid';

export const JobsList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="name" />
            <TextField source="location" />
            <EditButton />
        </Datagrid>
    </List>
)