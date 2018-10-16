import React from 'react';
import { Admin, Resource } from 'react-admin';
import simpleRestProvider from 'ra-data-simple-rest';
import { CustomersList } from './resource/customers'
const dataProvider = simpleRestProvider('http://localhost:4567');
const App = () => (
  <Admin dataProvider={dataProvider}>
    <Resource name="customers" list={CustomersList} />
  </Admin>
)

export default App;