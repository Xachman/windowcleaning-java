import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { Button } from 'react-admin';
import { showNotification } from 'react-admin';
import { push } from 'react-router-redux';

class Link extends Component {
    handleClick = () => {
        console.log(this.props)
        var path = this.props.path;
        var field = this.props.recordField;
        var value = this.props.record[field]
        console.log(value)
        if(value != undefined) {
            window.location = "#"+path+"/"+value
            return;
        }
        window.location = "#"+path

    }

    render() {
        return <Button label={this.props.label} onClick={this.handleClick} />;
    }
}

Link.propTypes = {
    label: PropTypes.string,
    path: PropTypes.string,
    recordField: PropTypes.string
};

export default Link;
