import React from 'react';
import { List, Edit, DateField, ReferenceField, EditButton, Datagrid, DateInput, NumberInput, SelectInput, TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, TabbedShowLayout, Tab } from 'react-admin';
import Grid from '@material-ui/core/Grid';

export const JobsList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <ReferenceField label="Customer" source="customer.id" reference="customers">
                <TextField source="name" />
            </ReferenceField>
            <DateField source="serviceDate" />
            <EditButton />
        </Datagrid>
    </List>
)