import React from 'react';
import { Admin, Resource } from 'react-admin';
import { CustomersList, CustomerCreate, CustomerEdit, CustomerShow } from './resource/customers'
import { JobsList } from './resource/jobs'
import Routes from './Routes';
import { Config } from './Config';
import AppLayout from './AppLayout';
const dataProvider = new Config.getDataProvider()

const App = () => (
  <Admin appLayout={AppLayout} customRoutes={Routes} dataProvider={dataProvider}>
    <Resource name="customers" list={CustomersList} edit={CustomerEdit} create={CustomerCreate} show={CustomerShow} />
    <Resource name="jobs" list={JobsList}  />
  </Admin>
)

export default App;