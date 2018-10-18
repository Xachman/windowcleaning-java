import React from 'react';
import { Grid, List, Datagrid, TextField, Create, TextInput, LongTextInput, SimpleForm } from 'react-admin';

export const CustomersList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="name" />
            <TextField source="location" />
        </Datagrid>
    </List>
);

export const CustomerCreate = (props) => (
    <Create {...props}>
        <SimpleForm>
            <TextInput source="name" />
            <TextInput source="location" />
            <LongTextInput source="body" />
        </SimpleForm>
    </Create>
);