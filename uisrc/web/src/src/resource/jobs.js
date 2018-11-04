import React from 'react';
import { List, Edit, DateField, ReferenceField, EditButton, Datagrid, DateInput, NumberInput, SelectInput, 
    TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, 
    TabbedShowLayout, Tab, SimpleForm, ReferenceInput, AutocompleteInput } from 'react-admin';
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

export const CreateJob = (props) => (
    <Create title="Create Job" {...props}>
        <SimpleForm>
            <ReferenceInput label="Customer" source="customer.id" reference="customers/filter/name">
                <AutocompleteInput optionText="name" />
            </ReferenceInput>
            <TextInput source="servicedBy" />
            <DateInput source="serviceDate" />
            <NumberInput source="amount" />
            <NumberInput source="daysBetween" />
            <NumberInput source="percent" />
            <BooleanInput source="printServiceStatement" />
            <BooleanInput source="printInvoice" />
            <BooleanInput source="paymentExpected" />
            <BooleanInput source="autoPrintServiceStatement" />
            <NumberInput source="difficulty" />
            <LongTextInput source="description" />
            <LongTextInput source="notes" />
        </SimpleForm>
    </Create>
)