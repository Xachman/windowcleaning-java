import React from 'react'
import { Layout } from 'react-admin';
import Menu from './Menu';

const AppLayout = props => <Layout
    {...props}
    menu={Menu}
/>;

export default AppLayout;