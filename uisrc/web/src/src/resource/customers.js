import React from 'react';
import { Filter, Button, List, Edit, Show, SimpleShowLayout, DisabledInput, EditButton, Datagrid, DateInput, NumberInput, SelectInput, TextField, Create, TextInput, LongTextInput, TabbedForm, FormTab, BooleanInput, TabbedShowLayout, Tab } from 'react-admin';
import Grid from '@material-ui/core/Grid';
import Link from '../components/actions/Link'

const CustomersFilter = (props) => (
    <Filter {...props}>
        <TextInput source="name" />
    </Filter>
);

export const CustomersList = (props) => {
    return (
        <List {...props} filters={<CustomersFilter />}>
            <Datagrid>
                <TextField source="id" />
                <TextField source="name" />
                <TextField source="location" />
                <EditButton />
                <Link label="Create Job" path="/jobs/customer/create" recordField="id"/> 
            </Datagrid>
        </List>
    )
}

export const CustomerCreate = (props) => (
    <Create {...props}>
        <CreateEditForm />
    </Create>
);
const CustomerTitle = ({ record }) => {
    return <span>Post {record ? `"${record.name}"` : ''}</span>;
};
export const CustomerEdit = (props) => (
    <Edit title={<CustomerTitle/>} {...props}>
        <CreateEditForm />
    </Edit>
);

export const CustomerShow = (props) => (
    <Show {...props}>
        <SimpleShowLayout>
            <TextField source="name" />
        </SimpleShowLayout>
    </Show>
)

const CreateEditForm = (props) =>  {
    return (
    <TabbedForm {...props}>
        <FormTab label="General">
            <DisabledInput source="id" />
            <TextInput source="name" />
            <TextInput source="location" />
            <TextInput source="area" />
            <SelectInput source="type" choices={[
                { id: 'C', name: 'C' },
                { id: 'N', name: 'N' },
                { id: 'R', name: 'R' },
            ]} />

            <SelectInput source="company" choices={[
                { id: 'S', name: 'S' },
                { id: 'I', name: 'I' },
            ]} />
            <LongTextInput source="notes" />
        </FormTab>
        <FormTab label="Contacts">
            <Grid container spacing={24}>
                <Grid item md={6}>
                    <Grid container direction="column">
                        <h2>Location Information</h2>
                        <TextInput source="contact" />
                        <TextInput source="addressLine1" />
                        <TextInput source="addressLine2" />
                        <TextInput source="city" />
                        <TextInput source="state" />
                        <TextInput source="zip" />
                        <TextInput source="phone" />
                        <TextInput source="fax" />
                    </Grid>
                </Grid>
                <Grid item md={6}>
                    <Grid container direction="column">
                        <h2>Billing Information</h2>
                        <TextInput source="contactB" />
                        <TextInput source="addressLine1B" />
                        <TextInput source="addressLine2B" />
                        <TextInput source="cityB" />
                        <TextInput source="stateB" />
                        <TextInput source="zipB" />
                        <TextInput source="phoneB" />
                        <TextInput source="faxB" />
                    </Grid>
                </Grid>
            </Grid>
        </FormTab>
        <FormTab label="Accounting">
            <TextInput source="obtainedBy"/>
            <TextInput source="royaltyTo"/>
            <NumberInput source="monthlyBill" placeholder="0.0" />
            <NumberInput source="billCharge" placeholder="0.0"/>
            <NumberInput source="deposit" placeholder="0.0"/>
            <NumberInput source="salesTax" placeholder="0.0"/>
        </FormTab>
        <FormTab label="Settings">
            <DateInput source="obtainedDate" />
            <BooleanInput source="active" />
            <DateInput source="inactiveDate" />
            <BooleanInput source="fourWeekInvoice" />
            <BooleanInput source="calendarMonthInvoice" />
            <BooleanInput source="quarterlyInvoice" />
            <BooleanInput source="AWWPUR" />
            <BooleanInput source="AWWLine" />
        </FormTab>
    </TabbedForm>
)
}