import React from 'react';
import { List, Datagrid, TextField } from 'react-admin';

export const CustomersList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="name" />
            <TextField source="location" />
        </Datagrid>
    </List>
);