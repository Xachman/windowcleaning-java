import React from 'react';
import { Config } from '../Config';
import { GET_LIST } from 'react-admin';

export class Calendar extends React.Component {
    dataProvider = Config.getDataProvider();
    constructor(props) {
        super(props)
        this.dataProvider(GET_LIST, 'jobs', {}).then((data) => {
            console.log(data)
        })
    }
    render() {
      return <h1>Hello, {this.props.name}</h1>;
    }
}