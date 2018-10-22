import React from 'react';
import { Admin, Resource } from 'react-admin';
import simpleRestProvider from 'ra-data-simple-rest';
import { CustomersList, CustomerCreate, CustomerEdit } from './resource/customers'
import { JobsList } from './resource/jobs'
const dataProvider = simpleRestProvider('http://localhost:8080');
const App = () => (
  <Admin dataProvider={dataProvider}>
    <Resource name="customers" list={CustomersList} edit={CustomerEdit} create={CustomerCreate}  />
    <Resource name="jobs" list={JobsList}  />
  </Admin>
)

export default App;