import React from 'react';
import { List, Edit, DateField, ReferenceField, EditButton, Datagrid, DateInput, NumberInput, SelectInput, 
    TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, 
    TabbedShowLayout, DateTimeInput, Tab, DisabledInput, SimpleForm, ReferenceInput, AutocompleteInput } from 'react-admin';
    
export const OrdersList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="id" />
            <TextField source="status" />
            <ReferenceField label="Customer" source="job.id" reference="jobs">
                <ReferenceField source="customer.id" reference="customers">
                    <TextField source="name" />
                </ReferenceField>
            </ReferenceField>
            <EditButton />
        </Datagrid>
    </List>
)