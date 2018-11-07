import React from 'react';
import { List, Edit, DateField, ReferenceField, EditButton, Datagrid, DateInput, NumberInput, SelectInput, 
    TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, 
    TabbedShowLayout, DateTimeInput, Tab, DisabledInput, SimpleForm, ReferenceInput, AutocompleteInput } from 'react-admin';
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



export const CreateJob = (props) => {
    console.log(props.match.params.customer_id)
    return (
        <Create title="Create Job" {...props}>
         {editCreateForm(customerInput(props.match.params.customer_id))}
        </Create>
    )

}

export const EditJob = (props) => (
    <Edit title="Edit Job" {...props}>
        {editCreateForm(customerInput(props.match.params.customer_id))}
    </Edit>
)

const customerInput = (id) => {
    var customerInput = (
                <ReferenceInput label="Customer" source="customer.id" reference="customers/filter/name">
                    <AutocompleteInput optionText="name" defaultValue="Tim"/>
                </ReferenceInput>
    )
    if(id != undefined) {
        customerInput = <DisabledInput defaultValue={id} source="customer.id" />
    }
    return customerInput
}
const editCreateForm = (customerInput) => (
    <SimpleForm>
        <DisabledInput source="id" />
        {customerInput}
        <TextInput source="servicedBy" />
        <DateTimeInput source="serviceDate" />
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
)