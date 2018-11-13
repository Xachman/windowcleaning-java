import React from 'react';
import { Admin, Resource } from 'react-admin';
import { CustomersList, CustomerCreate, CustomerEdit, CustomerShow } from './resource/customers'
import { JobsList, EditJob, CreateJob } from './resource/jobs'
import Routes from './Routes';
import { Config } from './Config';
import AppLayout from './AppLayout';
import { OrdersList } from './resource/orders';
import { InvoicesList } from './resource/invoices';
const dataProvider = new Config.getDataProvider()

const App = () => (
  <Admin appLayout={AppLayout} customRoutes={Routes} dataProvider={dataProvider}>
    <Resource name="customers" list={CustomersList} edit={CustomerEdit} create={CustomerCreate} show={CustomerShow} />
    <Resource name="jobs" list={JobsList} create={CreateJob} edit={EditJob}  />
    <Resource name="orders" list={OrdersList}  />
    <Resource name="invoices" list={InvoicesList}  />

    <Resource name="customers/filter/name" />
  </Admin>
)

export default App;